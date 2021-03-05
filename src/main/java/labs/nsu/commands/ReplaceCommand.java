package labs.nsu.commands;

public class ReplaceCommand implements Command {
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (context.getArguments().size() != 2) {
            throw new CommandException("Incorrect number of arguments. Required 2, but in fact" + context.getArguments().size());
        } else {
            context.getContext().replaceAll(S -> (S.replaceAll(context.getArguments().get(0), context.getArguments().get(1))));
        }

    }
}
