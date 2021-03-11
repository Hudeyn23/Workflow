package labs.nsu;

import labs.nsu.executor.WorkflowExecutor;
import labs.nsu.executor.WorkflowExecutorException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            executor.execute(new FileInputStream("workflow.txt"));
        } catch (WorkflowExecutorException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
