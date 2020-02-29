package lab1.fill;

public class Helper {
    static final String DELIMITER_LINES = "\n";
    static int rows = 0;
    static int cols = 0;

    public static char[][] parse(String fileContent) {
        String[] lines = fileContent.split(DELIMITER_LINES);
        rows = lines.length;
        cols = getMaxCols(lines);
        char value = ' ';
        char[][] aria = fillingBorders(new char[rows + 2][cols + 2]);
        for (int i = 1; i < lines.length; i++) {
            for (int j = 0; j < cols; j++) {
                if (j >= lines[i].length()) {
                    aria[i][j + 1] = ' ';
                } else {
                    value = lines[i].charAt(j);
                    if (value == '#' || value == ' ' || value == 'O') {
                        aria[i][j + 1] = value;
                    } else {
                        aria[i][j + 1] = ' ';
                    }
                }
            }
        }
        return aria;
    }

    public static void print(final char[][] aria) {
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                System.out.print(String.valueOf(aria[i][j]));
            }
            System.out.println();
        }
    }

    public static int getMaxCols(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }
        return maxLength;
    }

    public static char[][] fillingBorders(char[][] aria) {
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
