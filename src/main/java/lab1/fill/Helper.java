package lab1.fill;

import java.io.IOException;

public class Helper {
    static final int MATRIX_SIZE = 3;
    static final String DELIMITER_LINES = "\n";
    static int rows = 0;
    static int cols = 0;

    public static char[][] parse(String fileContent) throws IOException {
        String[] lines = fileContent.split(DELIMITER_LINES);
        rows = lines.length;
    }

    public static void print(final char[][] aria) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf(String.valueOf(aria[i][j]));
            }
            System.out.println();
        }
    }
}
