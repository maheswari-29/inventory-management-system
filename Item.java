// Item is the PARENT class. Electronics and Grocery will EXTEND this (inheritance).
// It is "abstract" because we never create a plain "Item" directly -
// every item in real life is either Electronics or Grocery (or some other specific type).
public abstract class Item {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    // Every child class MUST provide its own version of this method.
    // This is method overriding - a core OOP concept.
    public abstract String getCategory();

    // Common display format used by all items.
    // Child classes can add extra details on top of this (see Electronics/Grocery).
    public String basicInfo() {
        return String.format("%-6s %-15s %-12s Qty:%-5d Price: Rs.%-8.2f",
                id, name, getCategory(), quantity, price);
    }
}
