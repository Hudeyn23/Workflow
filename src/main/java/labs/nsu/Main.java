package labs.nsu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            CommandFactory factory = CommandFactory.getInstance();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
