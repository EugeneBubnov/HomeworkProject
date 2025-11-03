package exceptions;

public class BrowserNotSupportedException extends RuntimeException {
    public BrowserNotSupportedException(String driverName) {
        super(String.format("Driver %s not supported", driverName));
    }
}
