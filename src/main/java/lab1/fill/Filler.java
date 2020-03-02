package lab1.fill;

public class Filler {
    private static char[][] dataAria;
    private static final char FILL_CHARACTER = '.';
    private static final char EMPTY_CHARACTER = ' ';
    private static final char START_CHARACTER = 'O';

    public static char[][] fill(char[][] aria) {
        dataAria = aria;
        boolean wasFilled = true;
        while (wasFilled) {
            wasFilled = false;
            for (int i = 1; i < aria.length - 1; i++) {
                for (int j = 1; j < aria[i].length - 1; j++) {
                    if (dataAria[i][j] == START_CHARACTER || dataAria[i][j] == FILL_CHARACTER) {
                        if (wave(i, j)) {
                            wasFilled = true;
                        }
                    }
                }
            }
        }
        return aria;
    }

    private static boolean wave(int i, int j) {
        return tide(i + 1, j) || tide(i - 1, j) || tide(i, j + 1) || tide(i, j - 1);
    }

    private static boolean tide(int i, int j) {
        if (dataAria[i][j] == EMPTY_CHARACTER) {
            dataAria[i][j] = FILL_CHARACTER;
            return true;
        }
        return false;
    }
}
