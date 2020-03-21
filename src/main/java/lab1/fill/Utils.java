package lab1.fill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    static final String DELIMITER_LINES = "\n";

    public static List<List<Character>> parse(File inputFile) throws IOException {
        List<List<Character>> aria = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                List<Character> buffer = new ArrayList<>();
                for (int j = 0; j < stringLine.length(); j++) {
                    buffer.add(verifySymbol(stringLine.charAt(j)));
                }
                aria.add(buffer);
            }
        }
        return aria;
    }

    public static String print(final List<List<Character>> aria) {
        int rows = aria.size();
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < rows; i++) {
            for (char symbol : aria.get(i)) {
                out.append(String.valueOf(symbol));
            }
            out.append(DELIMITER_LINES);
        }
        return out.toString();
    }

    private static char verifySymbol(char symbol) throws IOException {
        if (!(symbol == '#' || symbol == ' ' || symbol == 'O')) {
            throw new IOException("Incorrect symbol!");
        }
        return symbol;
    }
}
