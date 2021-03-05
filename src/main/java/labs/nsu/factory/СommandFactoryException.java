package labs.nsu.factory;

class CommandFactoryException extends Exception {
    public CommandFactoryException(String message) {
        super(message);
    }

    public CommandFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandFactoryException() {
        super();
    }

    public CommandFactoryException(Throwable t) {
        super(t);
    }
}
