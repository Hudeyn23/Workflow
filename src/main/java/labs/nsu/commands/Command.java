package labs.nsu.commands;

public interface Command {
    void execute(CommandContext context) throws CommandException;

    void addArg(String arg);

    AllowablePosition getPosition();
}
