package tutorial.core.services.exceptions;

public class PortfolioExistsException extends RuntimeException {
    public PortfolioExistsException() {
    }

    public PortfolioExistsException(String message) {
        super(message);
    }

    public PortfolioExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortfolioExistsException(Throwable cause) {
        super(cause);
    }
}
