package labs.nsu.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCommand implements Command {
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (context.getArguments().size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + context.getArguments().size());
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(context.getArguments().get(0)))) {
                for (String line : context.getContext()) {
                    writer.write(line);
                }
            } catch (IOException e) {
                throw new CommandException("Error with write to output file", e);
            }
        }
    }
}
