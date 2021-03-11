package labs.nsu.commands;

import java.util.List;

public class SortCommand extends AbstractCommand {


    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.MIDDLE;
    }

    @Override
    public String toString() {
        return "SortCommand{}";
    }

    @Override
    public void execute(List<String> context) throws CommandException {
        if (args.size() != 0) {
            throw new CommandException("Incorrect number of arguments. Required 0, but in fact" + args.size());
        } else {
            context.sort(String::compareTo);
        }
    }

}
