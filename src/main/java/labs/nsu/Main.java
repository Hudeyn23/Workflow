package labs.nsu;

import labs.nsu.factory.CommandFactory;
import labs.nsu.runningCommands.WorkflowExecutor;
import labs.nsu.runningCommands.WorkflowExecutorException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WorkflowExecutor executor = new WorkflowExecutor();
        try {
            executor.execute("workflow.txt");
        } catch (WorkflowExecutorException e) {
            e.printStackTrace();
        }
    }
}
