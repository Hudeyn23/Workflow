package labs.nsu.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DumpCommand extends AbstractCommand {
    AllowablePosition position = AllowablePosition.MIDDLE;
    @Override
    public void execute(CommandContext context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Incorrect number of arguments. Required 1, but in fact" + args.size());
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args.get(0)))) {
                for (String line : context.getContext()) {
                    writer.write(line);
                }
            } catch (IOException e) {
                throw new CommandException("Error with write to dump file", e);
            }
        }
    }
}
