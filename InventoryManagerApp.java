import java.util.Scanner;

// This is the class with "main" - where the program starts running.
// It just shows a menu and calls Inventory's methods based on user's choice.
public class InventoryManagerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        inventory.loadFromFile(); // load any previously saved data

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(sc);

            switch (choice) {
                case 1 -> addElectronics(sc, inventory);
                case 2 -> addGrocery(sc, inventory);
                case 3 -> displayAllItems(inventory);
                case 4 -> sellItem(sc, inventory);
                case 5 -> restockItem(sc, inventory);
                case 6 -> removeItem(sc, inventory);
                case 7 -> {
                    inventory.saveToFile();
                    System.out.println("Data saved. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== INVENTORY MANAGER =====");
        System.out.println("1. Add Electronics Item");
        System.out.println("2. Add Grocery Item");
        System.out.println("3. Display All Items");
        System.out.println("4. Sell Item (reduce stock)");
        System.out.println("5. Restock Item (increase stock)");
        System.out.println("6. Remove Item");
        System.out.println("7. Save and Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addElectronics(Scanner sc, Inventory inventory) {
        try {
            System.out.print("Enter ID: ");
            String id = sc.next();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Quantity: ");
            int qty = readInt(sc);
            System.out.print("Enter Price: ");
            double price = readDouble(sc);
            System.out.print("Enter Warranty (months): ");
            int warranty = readInt(sc);

            Item item = new Electronics(id, name, qty, price, warranty);
            inventory.addItem(item); // may throw InvalidQuantityException
            System.out.println("Electronics item added successfully.");
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addGrocery(Scanner sc, Inventory inventory) {
        try {
            System.out.print("Enter ID: ");
            String id = sc.next();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Quantity: ");
            int qty = readInt(sc);
            System.out.print("Enter Price: ");
            double price = readDouble(sc);
            System.out.print("Enter Expiry Date (e.g. 2026-12-31): ");
            String expiry = sc.next();

            Item item = new Grocery(id, name, qty, price, expiry);
            inventory.addItem(item);
            System.out.println("Grocery item added successfully.");
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAllItems(Inventory inventory) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (Item item : inventory.getAllItems()) {
            System.out.println(item.basicInfo());
        }
    }

    private static void sellItem(Scanner sc, Inventory inventory) {
        try {
            System.out.print("Enter Item ID to sell: ");
            String id = sc.next();
            System.out.print("Enter quantity to sell: ");
            int qty = readInt(sc);
            inventory.sellItem(id, qty);
            System.out.println("Sold " + qty + " unit(s) of item " + id);
        } catch (ItemNotFoundException | OutOfStockException | InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void restockItem(Scanner sc, Inventory inventory) {
        try {
            System.out.print("Enter Item ID to restock: ");
            String id = sc.next();
            System.out.print("Enter quantity to add: ");
            int qty = readInt(sc);
            inventory.restockItem(id, qty);
            System.out.println("Restocked item " + id + " with " + qty + " unit(s).");
        } catch (ItemNotFoundException | InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeItem(Scanner sc, Inventory inventory) {
        try {
            System.out.print("Enter Item ID to remove: ");
            String id = sc.next();
            inventory.removeItem(id);
            System.out.println("Item removed successfully.");
        } catch (ItemNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper to safely read an integer (avoids crashing on bad input)
    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid whole number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    // Helper to safely read a decimal number
    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}
