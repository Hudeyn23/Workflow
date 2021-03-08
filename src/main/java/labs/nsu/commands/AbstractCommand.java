package labs.nsu.commands;

import java.util.ArrayList;
import java.util.List;

enum AllowablePosition {
    FIRST,
    LAST,
    MIDDLE,
    AFTER;
    List<String> prevCommands;
}

abstract public class AbstractCommand implements Command {
    AllowablePosition position;
    protected List<String> args = new ArrayList<>();

    public AllowablePosition getPosition() {
        return position;
    }

    public void addArg(String arg) {
        args.add(arg);
    }
}
