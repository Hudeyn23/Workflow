package labs.nsu.commands;

public class GrepCommand implements Command {
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (context.getArguments().size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + context.getArguments().size());
        } else {
            context.getContext().removeIf(S -> (!S.contains(context.getArguments().get(0))));
        }
    }
}
