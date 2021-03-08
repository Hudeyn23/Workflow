package labs.nsu.runningCommands;

public class WorkflowParserException extends Exception {
    public WorkflowParserException(String message) {
        super(message);
    }

    public WorkflowParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowParserException(Throwable cause) {
        super(cause);
    }

    protected WorkflowParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
