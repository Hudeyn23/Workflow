package labs.nsu.factory;

import labs.nsu.commands.Command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandFactory {
    private static volatile CommandFactory instance;
    private final Properties config = new Properties();

    private CommandFactory() throws IOException {
        var configStream = getClass().getResourceAsStream("/configs/CommandFactory.config");
        if (configStream == null) {
            throw new IOException("Unable to read config");
        }
        config.load(configStream);
    }

    public static CommandFactory getInstance() throws IOException {
        if (instance == null) {
            synchronized (CommandFactory.class) {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        }
        return instance;
    }

    public Command getCommand(String commandName) throws CommandFactoryException {
        if (!config.containsKey(commandName)) {
            throw new CommandFactoryException("Command " + commandName + " not found");
        }
        Command command;
        try {
            var commandClass = Class.forName(config.getProperty(commandName));
            var commandObject = commandClass.getDeclaredConstructor().newInstance();
            command = ((Command) commandObject);
        } catch (ClassNotFoundException e) {
            throw new CommandFactoryException("Unable to find class with " + commandName + " command from the config", e);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new CommandFactoryException("Trouble with create command " + commandName + " instance", e);
        } catch (ClassCastException e) {
            throw new CommandFactoryException("Your command " + commandName + " class must implement command interface", e);
        }
        return command;
    }
}
