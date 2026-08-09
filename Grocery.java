// Grocery IS-A Item too, but adds a different extra field: expiryDate.
public class Grocery extends Item {
    private String expiryDate; // keeping it simple as text, e.g. "2026-12-31"

    public Grocery(String id, String name, int quantity, double price, String expiryDate) {
        super(id, name, quantity, price);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getCategory() {
        return "Grocery";
    }

    @Override
    public String basicInfo() {
        return super.basicInfo() + String.format(" Expiry: %s", expiryDate);
    }
}
