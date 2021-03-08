package labs.nsu;

import labs.nsu.runningCommands.WorkflowExecutor;
import labs.nsu.runningCommands.WorkflowExecutorException;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        WorkflowExecutor executor = new WorkflowExecutor();
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/configs/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        try {
            executor.execute("workflow.txt");
        } catch (WorkflowExecutorException e) {
            e.printStackTrace();
        }
    }
}
