package labs.nsu.commands;

import java.util.ArrayList;
import java.util.List;


abstract public class AbstractCommand implements Command {
    protected List<String> args = new ArrayList<>();

    public abstract AllowablePosition getPosition();

    public void addArg(String arg) {
        args.add(arg);
    }
}
