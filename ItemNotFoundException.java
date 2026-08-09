// Custom exception: thrown when the item ID does not exist in the inventory
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
