import labs.nsu.commands.Command;
import labs.nsu.commands.CommandException;
import labs.nsu.factory.CommandFactory;
import labs.nsu.factory.CommandFactoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestWorkflow {
    private static List<String> context;

    private static List<String> testText;

    @BeforeAll
    static void CreateWorkflowTemplate() throws IOException {
        testText = Files.readAllLines(Path.of("in_test.txt"));
        testText = Collections.unmodifiableList(testText);

    }

    @BeforeEach
    void createContext() {
        context = new ArrayList<>();
    }

    @Test
    void wrongCommandExceptionTest() {
        String wrongCommand = "newCommand";
        Throwable thrown = assertThrows(CommandFactoryException.class, () -> CommandFactory.getInstance().getCommand(wrongCommand));
        assertNotNull(thrown.getMessage());
    }

    @Test
    void readTest() throws CommandException, IOException, CommandFactoryException {
        Command readCommand = CommandFactory.getInstance().getCommand("readfile");
        readCommand.addArg("in_test.txt");
        readCommand.execute(context);
        Assertions.assertEquals(testText, context);
    }

    @Test
    void sortTest() throws CommandException, IOException, CommandFactoryException {
        Command sortCommand = CommandFactory.getInstance().getCommand("sort");
        context.addAll(testText);
        sortCommand.execute(context);
        List<String> sortText = new ArrayList<>(testText);
        sortText.sort(String::compareTo);
        Assertions.assertEquals(sortText, context);
    }

    @Test
    void dumpFileCreationTest() throws IOException, CommandException, CommandFactoryException {
        Command dumpCommand = CommandFactory.getInstance().getCommand("dump");
        context.addAll(testText);
        String dumpFile = "test_dump.txt";
        dumpCommand.addArg(dumpFile);
        dumpCommand.execute(context);
        Assertions.assertTrue(Files.exists(Path.of(dumpFile)));
    }

    @Test
    void dumpFileInfoTest() throws IOException, CommandException, CommandFactoryException {
        Command dumpCommand = CommandFactory.getInstance().getCommand("dump");
        context.addAll(testText);
        String dumpFile = "test_dump.txt";
        dumpCommand.addArg(dumpFile);
        dumpCommand.execute(context);
        List<String> dumpText = Files.readAllLines(Path.of(dumpFile));
        Assertions.assertEquals(context, dumpText);
    }

    @Test
    void replaceTest() throws CommandException, IOException, CommandFactoryException {
        Command replaceCommand = CommandFactory.getInstance().getCommand("replace");
        context.addAll(testText);
        String oldWord = "word1";
        String replacedWord = "word2";
        replaceCommand.addArg(oldWord);
        replaceCommand.addArg(replacedWord);
        replaceCommand.execute(context);
        List<String> replaceText = new ArrayList<>(testText);
        replaceText = replaceText.stream().map((s) -> s.replaceAll(oldWord, replacedWord)).collect(Collectors.toList());
        Assertions.assertEquals(replaceText, context);
    }

    @Test
    void grepTest() throws CommandException, IOException, CommandFactoryException {
        Command grepCommand = CommandFactory.getInstance().getCommand("grep");
        context.addAll(testText);
        String grepWord = "grep";
        grepCommand.addArg(grepWord);
        grepCommand.execute(context);
        List<String> grepText = new ArrayList<>(testText);
        grepText = grepText.stream().filter((s) -> s.contains(grepWord)).collect(Collectors.toList());
        Assertions.assertEquals(grepText, context);
    }

}
