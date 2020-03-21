package lab1.fill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final String DELIMITER_LINES = "\n";
    private static final int MAX_SIZE = 99;

    public static List<List<Character>> parse(File inputFile) throws IOException {
        List<List<Character>> aria = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                if (aria.size() > MAX_SIZE) {
                    continue;
                }
                List<Character> buffer = new ArrayList<>();
                for (int j = 0; j < stringLine.length(); j++) {
                    if (j > MAX_SIZE) {
                        continue;
                    }
                    buffer.add(verifySymbol(stringLine.charAt(j)));
                }
                aria.add(buffer);
            }
        }
        return aria;
    }

    public static String print(final List<List<Character>> aria) {
        StringBuilder out = new StringBuilder();
        for (List<Character> characters : aria) {
            for (char symbol : characters) {
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
