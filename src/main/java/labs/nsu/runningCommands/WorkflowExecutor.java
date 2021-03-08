package labs.nsu.runningCommands;

import labs.nsu.commands.Command;
import labs.nsu.commands.CommandContext;
import labs.nsu.commands.CommandException;
import labs.nsu.factory.CommandFactory;
import labs.nsu.factory.CommandFactoryException;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class WorkflowExecutor {
    CommandContext context = new CommandContext();
    Map<Integer, Command> commandMap = new HashMap<>();
    private static Logger log = Logger.getLogger("WorkflowExecutor.class.getName()");
    private static Logger log2 = Logger.getLogger(Command.class.getName());
    public void execute(String fileName) throws WorkflowExecutorException {
        try {
            LogManager.getLogManager().readConfiguration(
                    getClass().getResourceAsStream("/configs/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        try {
            log.info("Some message");
            log2.info("Some message");
            CommandFactory factory = CommandFactory.getInstance();
            WorkflowParser parser = new WorkflowParser();
            int commandId;
            String commandName;
            List<String> commandArgs;
            parser.parse(fileName);
            Block block;
            while ((block = parser.nextBlock()) != null) {
                System.out.println(block.getName());
                Command command = factory.getCommand(block.getName());
                for (String args : block.getArgs()) {
                    command.addArg(args);
                }
                commandMap.put(block.getId(), command);
            }
            for (Integer i : parser.getCommands()) {
                commandMap.get(i).execute(context);
            }
        } catch (WorkflowParserException | CommandFactoryException | IOException | CommandException e) {
            throw new WorkflowExecutorException("There is a trouble with workflow", e);

        }

    }
}
