package labs.nsu.commands;

import java.io.*;

public class ReadCommand extends AbstractCommand {
    AllowablePosition position = AllowablePosition.FIRST;

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
