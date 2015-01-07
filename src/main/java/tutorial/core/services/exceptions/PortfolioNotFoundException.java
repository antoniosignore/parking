package tutorial.core.services.exceptions;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortfolioNotFoundException(String message) {
        super(message);
    }

    public PortfolioNotFoundException() {
    }
}
