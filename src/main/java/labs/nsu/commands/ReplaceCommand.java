package labs.nsu.commands;

import java.util.logging.Logger;

public class ReplaceCommand extends AbstractCommand {


    private static final Logger log = Logger.getLogger(ReplaceCommand.class.getName());

    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.MIDDLE;
    }

    @Override
    public String toString() {
        return "ReplaceCommand{}";
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 2) {
            throw new CommandException("Incorrect number of arguments. Required 2, but in fact" + args.size());
        } else {
            context.getContext().replaceAll(S -> (S.replaceAll(args.get(0), args.get(1))));
        }

    }
}
