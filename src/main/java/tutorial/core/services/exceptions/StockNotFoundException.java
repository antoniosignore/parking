package tutorial.core.services.exceptions;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException() {
    }
}
