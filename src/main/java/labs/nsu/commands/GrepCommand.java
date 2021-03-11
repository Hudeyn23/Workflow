package labs.nsu.commands;

import java.util.List;

public class GrepCommand extends AbstractCommand {
    @Override
    public String toString() {
        return "GrepCommand{}";
    }

    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.MIDDLE;
    }

    @Override
    public void execute(List<String> context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + args.size());
        } else {
            context.removeIf(S -> (!S.contains(args.get(0))));
        }
    }
}
