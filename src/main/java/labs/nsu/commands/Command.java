package labs.nsu.commands;

import java.util.List;

public interface Command {
    void execute(List<String> context) throws CommandException;

    void addArg(String arg);

    AllowablePosition getPosition();
}
