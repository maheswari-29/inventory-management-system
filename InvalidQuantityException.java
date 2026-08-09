// Custom exception: thrown when someone tries to enter a negative or zero quantity
public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
