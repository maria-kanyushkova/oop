package common;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BaseInputOutput {
    public static final String DELIMITER = " ";

    public static String read(Scanner in) throws IOException {
        final String input = in.nextLine();
        if (input.length() == 0) {
            throw new IOException("Empty input!");
        }
        return input;
    }

    public static void print(List<Float> numbers) {
        for (Float number : numbers) {
            System.out.printf("%.3f%s", number, DELIMITER);
        }
        System.out.println();
    }

    public static void print(String line) {
        System.out.println(line);
    }
}
