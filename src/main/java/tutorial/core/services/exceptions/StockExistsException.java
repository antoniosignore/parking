package tutorial.core.services.exceptions;

public class StockExistsException extends RuntimeException {
    public StockExistsException() {
    }

    public StockExistsException(String message) {
        super(message);
    }

    public StockExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockExistsException(Throwable cause) {
        super(cause);
    }
}
