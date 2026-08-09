// Custom exception: thrown when someone tries to sell/remove more items than available
public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}
