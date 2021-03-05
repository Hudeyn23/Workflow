package labs.nsu.commands;

public class SortCommand implements Command {
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (context.getArguments().size() != 0) {
            throw new CommandException("Incorrect number of arguments. Required 0, but in fact" + context.getArguments().size());
        } else {
            context.getContext().sort(String::compareTo);
        }
    }

}
