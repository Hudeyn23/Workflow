package labs.nsu.commands;

import java.util.List;

public class ReplaceCommand extends AbstractCommand {


    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.MIDDLE;
    }

    @Override
    public String toString() {
        return "ReplaceCommand{}";
    }

    @Override
    public void execute(List<String> context) throws CommandException {
        if (args.size() != 2) {
            throw new CommandException("Incorrect number of arguments. Required 2, but in fact" + args.size());
        } else {
            context.replaceAll(S -> (S.replaceAll(args.get(0), args.get(1))));
        }

    }
}
