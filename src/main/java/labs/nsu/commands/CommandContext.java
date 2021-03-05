package labs.nsu.commands;

import java.util.List;

public abstract class CommandContext {
    private List<String> context;
    private  List<String> arguments;

    public List<String> getContext() {
        return context;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
