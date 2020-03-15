package lab2.vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputOutput {
    public static final String DELIMITER = " ";

    public static String read(Scanner in) throws IOException {
        final String input = in.nextLine();
        if (input.length() == 0) {
            throw new IOException("Empty input!");
        }
        return input;
    }

    public static List<Float> parse(final String[] args) throws IOException {
        List<Float> parsed = new ArrayList<>();
        for (String arg : args) {
            if (!isNumeric(arg)) {
                throw new IOException("Cannot parse \"" + arg + "\"!");
            }
            parsed.add(Float.parseFloat(arg));
        }
        Collections.sort(parsed);
        return parsed;
    }

    public static void print(List<Float> numbers) {
        for (Float number : numbers) {
            System.out.printf("%.3f%s", number, DELIMITER);
        }
        System.out.println();
    }

    public static boolean isNumeric(String strNum) {
        try {
            double doubleValue = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
