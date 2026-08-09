// Electronics IS-A Item. It inherits id, name, quantity, price from Item
// and adds one extra field of its own: warrantyMonths.
public class Electronics extends Item {
    private int warrantyMonths;

    public Electronics(String id, String name, int quantity, double price, int warrantyMonths) {
        super(id, name, quantity, price); // calls Item's constructor
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public String getCategory() {
        return "Electronics";
    }

    @Override
    public String basicInfo() {
        // reuse parent's formatting, then add warranty info
        return super.basicInfo() + String.format(" Warranty: %d months", warrantyMonths);
    }
}
