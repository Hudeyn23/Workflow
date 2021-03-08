package labs.nsu.commands;

public class ReplaceCommand extends AbstractCommand {
    AllowablePosition position = AllowablePosition.MIDDLE;
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 2) {
            throw new CommandException("Incorrect number of arguments. Required 2, but in fact" + args.size());
        } else {
            context.getContext().replaceAll(S -> (S.replaceAll(args.get(0), args.get(1))));
        }

    }
}
