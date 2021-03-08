package labs.nsu.commands;

public class GrepCommand extends AbstractCommand {
    AllowablePosition position = AllowablePosition.MIDDLE;
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + args.size());
        } else {
            context.getContext().removeIf(S -> (!S.contains(args.get(0))));
        }
    }
}
