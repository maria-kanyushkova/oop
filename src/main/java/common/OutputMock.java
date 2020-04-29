package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class OutputMock {
    private final PrintStream original = System.out;
    private final ByteArrayOutputStream mock = new ByteArrayOutputStream();

    public OutputMock() {
        System.setOut(new PrintStream(mock));
    }

    public static void setSystemInput(final String input) {
        final ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    public static String[] readSystemInput() throws IOException {
        final Scanner in = new Scanner(System.in);
        return BaseInputOutput.read(in).split(BaseInputOutput.DELIMITER);
    }

    public String read() {
        return mock.toString();
    }

    public void destruct() {
        System.setOut(original);
    }
}