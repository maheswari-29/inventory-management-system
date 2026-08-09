import java.util.ArrayList;
import java.io.*;

// This class manages ALL the items. It does NOT know if an item is
// Electronics or Grocery - it just works with the parent type "Item".
// This is called POLYMORPHISM: one list, many different real object types inside.
public class Inventory {
    private ArrayList<Item> items;
    private static final String FILE_NAME = "inventory_data.txt";

    public Inventory() {
        items = new ArrayList<>();
    }

    // Add a new item. Throws exception if quantity is invalid or ID already exists.
    public void addItem(Item item) throws InvalidQuantityException {
        if (item.getQuantity() < 0) {
            throw new InvalidQuantityException("Quantity cannot be negative for item: " + item.getName());
        }
        items.add(item);
    }

    // Find item by ID, or throw exception if not found.
    public Item findItem(String id) throws ItemNotFoundException {
        for (Item item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        throw new ItemNotFoundException("No item found with ID: " + id);
    }

    // Reduce stock when items are sold. Throws OutOfStockException if not enough stock.
    public void sellItem(String id, int qty) throws ItemNotFoundException, OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Sell quantity must be greater than zero.");
        }
        Item item = findItem(id); // may throw ItemNotFoundException
        if (item.getQuantity() < qty) {
            throw new OutOfStockException("Not enough stock for " + item.getName()
                    + ". Available: " + item.getQuantity() + ", Requested: " + qty);
        }
        item.setQuantity(item.getQuantity() - qty);
    }

    // Add more stock (restocking).
    public void restockItem(String id, int qty) throws ItemNotFoundException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Restock quantity must be greater than zero.");
        }
        Item item = findItem(id);
        item.setQuantity(item.getQuantity() + qty);
    }

    public void removeItem(String id) throws ItemNotFoundException {
        Item item = findItem(id);
        items.remove(item);
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Save all items to a text file, so data is not lost when program closes.
    // Format per line: TYPE,id,name,quantity,price,extraField
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                if (item instanceof Electronics) {
                    Electronics e = (Electronics) item;
                    writer.println("ELECTRONICS," + e.getId() + "," + e.getName() + ","
                            + e.getQuantity() + "," + e.getPrice() + "," + e.getWarrantyMonths());
                } else if (item instanceof Grocery) {
                    Grocery g = (Grocery) item;
                    writer.println("GROCERY," + g.getId() + "," + g.getName() + ","
                            + g.getQuantity() + "," + g.getPrice() + "," + g.getExpiryDate());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    // Load items back from the file when program starts.
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // first time running, no file yet - that's fine
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6) continue; // skip broken lines

                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                int qty = Integer.parseInt(parts[3]);
                double price = Double.parseDouble(parts[4]);
                String extra = parts[5];

                if (type.equals("ELECTRONICS")) {
                    items.add(new Electronics(id, name, qty, price, Integer.parseInt(extra)));
                } else if (type.equals("GROCERY")) {
                    items.add(new Grocery(id, name, qty, price, extra));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
    }
}
