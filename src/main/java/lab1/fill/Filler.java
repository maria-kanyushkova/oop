package lab1.fill;

public class Filler {
    private static final char FILL_CHARACTER = '.';
    private static final char EMPTY_CHARACTER = ' ';
    private static final char START_CHARACTER = 'O';

    public static char[][] fill(char[][] aria) {
        boolean wasFilled = true;
        while (wasFilled) {
            wasFilled = false;
            for (int i = 1; i < aria.length - 1; i++) {
                for (int j = 1; j < aria[i].length - 1; j++) {
                    if (aria[i][j] == START_CHARACTER || aria[i][j] == FILL_CHARACTER) {
                        if (wave(aria, i, j)) {
                            wasFilled = true;
                        }
                    }
                }
            }
        }
        return aria;
    }

    private static boolean wave(char[][] dataAria, int i, int j) {
        boolean wasFilling = false;
        if (dataAria[i + 1][j] == EMPTY_CHARACTER) {
            dataAria[i + 1][j] = FILL_CHARACTER;
            wasFilling = true;
        }
        if (dataAria[i - 1][j] == EMPTY_CHARACTER) {
            dataAria[i - 1][j] = FILL_CHARACTER;
            wasFilling = true;
        }
        if (dataAria[i][j + 1] == EMPTY_CHARACTER) {
            dataAria[i][j + 1] = FILL_CHARACTER;
            wasFilling = true;
        }
        if (dataAria[i][j - 1] == EMPTY_CHARACTER) {
            dataAria[i][j - 1] = FILL_CHARACTER;
            wasFilling = true;
        }
        return wasFilling;
    }
}
