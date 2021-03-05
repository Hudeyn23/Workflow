package labs.nsu.commands;

import java.io.*;

public class ReadCommand implements Command {
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (context.getArguments().size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + context.getArguments().size());
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(context.getArguments().get(0)))) {
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
