package labs.nsu.runningCommands;

import labs.nsu.commands.CommandContext;
import labs.nsu.factory.CommandFactory;

import java.io.IOException;

public class WorkflowExecutor {
    CommandContext context;
    public void execute() {
        try {
            CommandFactory factory = CommandFactory.getInstance();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
