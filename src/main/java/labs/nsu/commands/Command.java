package labs.nsu.commands;

public interface Command {
    void execute(CommandContext context) throws CommandException;
}
