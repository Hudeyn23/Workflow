package labs.nsu.executor;

public class WorkflowExecutorException extends Exception {
    public WorkflowExecutorException() {
        super();
    }

    public WorkflowExecutorException(String message) {
        super(message);
    }

    public WorkflowExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowExecutorException(Throwable cause) {
        super(cause);
    }
}
