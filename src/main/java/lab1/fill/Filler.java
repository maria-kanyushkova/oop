package lab1.fill;

public class Filler {
    private static char[][] dataAria;
    private static final char FILL_CHARACTER = '.';
    private static final char EMPTY_CHARACTER = ' ';
    private static final char BORDER_CHARACTER = '#';
    private static final char START_CHARACTER = 'O';

    public static char[][] fill(char[][] aria) {
        dataAria = aria;
        for (int i = 1; i < aria.length - 1; i++) {
            for (int j = 1; j < aria[i].length - 1; j++) {
                if (dataAria[i][j] == START_CHARACTER) {
                    wave(i, j);
                }
            }
        }
        return aria;
    }

    private static void wave(int i, int j) {
        if (dataAria[i][j] != BORDER_CHARACTER && dataAria[i][j] != FILL_CHARACTER) {
            if (dataAria[i][j] == EMPTY_CHARACTER) {
                dataAria[i][j] = FILL_CHARACTER;
            }
            wave(i + 1, j);
            wave(i - 1, j);
            wave(i, j + 1);
            wave(i, j - 1);
        }
    }
}