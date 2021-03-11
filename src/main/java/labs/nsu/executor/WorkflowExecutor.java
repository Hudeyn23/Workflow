package labs.nsu.executor;

import labs.nsu.commands.AllowablePosition;
import labs.nsu.commands.Command;
import labs.nsu.commands.CommandException;
import labs.nsu.factory.CommandFactory;
import labs.nsu.factory.CommandFactoryException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkflowExecutor {
    private static final Logger log = Logger.getLogger(WorkflowExecutor.class.getName());
    List<String> context = new ArrayList<>();
    Map<Integer, Command> commandMap = new HashMap<>();
    private int currentStage = 0;

    public void execute(InputStream input) throws WorkflowExecutorException {

        try {
            CommandFactory factory = CommandFactory.getInstance();
            WorkflowParser parser = new WorkflowParser();
            log.info("start parsing workflow file");
            parser.parse(input);
            Block block;
            while ((block = parser.nextBlock()) != null) {
                System.out.println(block.getName());
                log.info("Trying to load " + block.getName() + " block from factory");
                Command command = factory.getCommand(block.getName());
                log.info("Command " + block.getName() + " has been successfully load");
                for (String args : block.getArgs()) {
                    command.addArg(args);
                }
                commandMap.put(block.getId(), command);
            }
            log.info("workflow file has been successfully parse");
            log.info("Start executing commands");
            for (Integer i : parser.getCommands()) {
                Command command = commandMap.get(i);
                if (command == null) {
                    log.severe("workflow file does`t have block with id = " + i);
                    throw new WorkflowExecutorException("There is no block with id = " + i);
                }
                if (!checkCommandPosition(command, parser.getCommands().size())) {
                    log.severe("Command  " + command.toString() + "isn`t in the right position. " + "Required + " + command.getPosition());
                    throw new WorkflowExecutorException("Incorrect commands order");
                }
                log.info("Trying to execute " + command.toString() + " command");
                command.execute(context);
                log.info("Command " + command.toString() + " is successfully executed ");
                currentStage++;
            }
            log.info("Workflow execution completed successfully");
        } catch (WorkflowParserException e) {
            log.log(Level.SEVERE, "Workflow parse error", e);
            log.info("Workflow execution completed due to error");
            throw new WorkflowExecutorException("Error while parsing ", e);
        } catch (CommandException e) {
            log.log(Level.SEVERE, "Command error", e);
            log.info("Workflow execution completed due to error");
            throw new WorkflowExecutorException("Command error", e);
        } catch (CommandFactoryException | IOException e) {
            log.log(Level.SEVERE, "Workflow command load error", e);
            log.info("Workflow execution completed due to error");
            throw new WorkflowExecutorException("", e);
        }

    }

    boolean checkCommandPosition(Command command, int commandsCount) {
        if (command.getPosition() == AllowablePosition.FIRST && currentStage == 0) return true;
        if (command.getPosition() == AllowablePosition.LAST && currentStage == commandsCount - 1) return true;
        return command.getPosition() == AllowablePosition.MIDDLE && (currentStage != 0 && currentStage != commandsCount - 1);
    }

}
