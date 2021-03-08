package labs.nsu.runningCommands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkflowParser {
    List<String> commandsDescription = new ArrayList<>();
    List<Integer> commandsSequence = new ArrayList<>();

    public void parse(String fileName) throws WorkflowParserException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = reader.readLine();

            if ((line == null) ? true : !line.equals("desc")) {
                throw new WorkflowParserException("workflow have to starts with desc word");
            }
            while (!(line = reader.readLine()).equals("csed") && line != null) {
                commandsDescription.add(line);
            }
            if (line == null) {
                throw new WorkflowParserException("workflow command declaration block have to end with csed word");
            }
            String workflowStructure = reader.readLine();
            for (String s : workflowStructure.split(" -> ")) {
                commandsSequence.add(Integer.parseInt(s));
            }


        } catch (IOException e) {
            throw new WorkflowParserException("Error while parsing workflow file", e);
        }
    }

    Block nextBlock() throws WorkflowParserException {
        if (commandsDescription.size() == 0) {
            return null;
        }
        String[] words = commandsDescription.get(0).split(" ");
        commandsDescription.remove(0);
        if (words.length < 3) {
            throw new WorkflowParserException("invalid command format");
        }
        int currentId = Integer.parseInt(words[0]);
        String currentName = words[2];
        List<String> currentArgs = new ArrayList<>();
        for (int i = 3; i < words.length; i++) {
            currentArgs.add(words[i]);
        }
        return new Block(currentId, currentName, currentArgs);
    }

    List<Integer> getCommands() {
        return commandsSequence;
    }

}
