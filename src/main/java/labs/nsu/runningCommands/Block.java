package labs.nsu.runningCommands;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int id;
    private String name = null;
    private List<String> args = new ArrayList<>();

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
