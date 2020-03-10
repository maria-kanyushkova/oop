package lab1.fill;

import java.io.IOException;

public class Utils {
    static final String DELIMITER_LINES = "\n";

    public static char[][] parse(String fileContent) throws IOException {
        String[] lines = fileContent.split(DELIMITER_LINES);
        int rows = lines.length;
        int cols = getMaxCols(lines);
        char value = ' ';
        char[][] aria = fillingBorders(new char[rows + 2][cols + 2]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j >= lines[i].length()) {
                    aria[i + 1][j + 1] = ' ';
                } else {
                    value = lines[i].charAt(j);
                    aria[i + 1][j + 1] = verifySymbol(value);
                }
            }
        }
        return aria;
    }

    public static String print(final char[][] aria) {
        int rows = aria.length - 1;
        int cols = aria[1].length - 1;
        StringBuilder out = new StringBuilder();
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                out.append(String.valueOf(aria[i][j]));
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

    private static int getMaxCols(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }
        return maxLength;
    }

    private static char[][] fillingBorders(char[][] aria) {
        int rows = aria.length - 2;
        int cols = aria[0].length - 2;
        for (int i = 0; i < rows + 2; i++) {
            aria[i][0] = '#';
            aria[i][cols + 1] = '#';
        }
        for (int j = 0; j < cols + 2; j++) {
            aria[0][j] = '#';
            aria[rows + 1][j] = '#';
        }
        return aria;
    }
}
