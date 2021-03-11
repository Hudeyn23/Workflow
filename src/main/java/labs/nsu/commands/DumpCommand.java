package labs.nsu.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class DumpCommand extends AbstractCommand {

    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.MIDDLE;
    }

    @Override
    public String toString() {
        return "DumpCommand{}";
    }

    @Override
    public void execute(List<String> context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + args.size());
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args.get(0)))) {
                for (String line : context) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new CommandException("Error with write to dump file", e);
            }
        }
    }
}
