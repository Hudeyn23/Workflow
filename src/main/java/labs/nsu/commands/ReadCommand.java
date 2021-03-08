package labs.nsu.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCommand extends AbstractCommand {

    @Override
    public AllowablePosition getPosition() {
        return AllowablePosition.FIRST;
    }

    @Override
    public String toString() {
        return "ReadCommand{}";
    }

    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + args.size());
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(args.get(0)))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    context.getContext().add(line);
                }
            } catch (IOException e) {
                throw new CommandException("Error with read from input file", e);
            }
        }
    }


}
