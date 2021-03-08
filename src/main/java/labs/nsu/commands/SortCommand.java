package labs.nsu.commands;

public class SortCommand extends AbstractCommand {
    AllowablePosition position = AllowablePosition.MIDDLE;
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 0) {
            throw new CommandException("Incorrect number of arguments. Required 0, but in fact" + args.size());
        } else {
            context.getContext().sort(String::compareTo);
        }
    }

}
