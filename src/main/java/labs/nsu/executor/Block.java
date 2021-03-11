package labs.nsu.executor;

import java.util.List;

public class Block {
    private final int id;
    private final String name;
    private final List<String> args;

    public Block(int id, String name, List<String> args) {
        this.id = id;
        this.name = name;
        this.args = List.copyOf(args);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }
}
