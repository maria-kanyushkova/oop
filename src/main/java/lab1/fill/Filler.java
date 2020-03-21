package lab1.fill;

import java.util.List;

public class Filler {
    private static final char FILL_CHARACTER = '.';
    private static final char EMPTY_CHARACTER = ' ';
    private static final char START_CHARACTER = 'O';

    public static List<List<Character>> fill(List<List<Character>> aria) {
        boolean wasFilled = true;
        while (wasFilled) {
            wasFilled = false;
            for (int i = 0; i < aria.size(); i++) {
                for (int j = 0; j < aria.get(i).size(); j++) {
                    if (aria.get(i).get(j) == START_CHARACTER || aria.get(i).get(j) == FILL_CHARACTER) {
                        if (wave(aria, i, j)) {
                            wasFilled = true;
                        }
                    }
                }
            }
        }
        return aria;
    }

    private static boolean wave(List<List<Character>> dataAria, int i, int j) {
        boolean wasFilling = false;
        if (dataAria.get(i + 1).get(j) == EMPTY_CHARACTER) {
            dataAria.get(i + 1).set(j, FILL_CHARACTER);
            wasFilling = true;
        }
        if (dataAria.get(i - 1).get(j) == EMPTY_CHARACTER) {
            dataAria.get(i - 1).set(j, FILL_CHARACTER);
            wasFilling = true;
        }
        if (dataAria.get(i).get(j + 1) == EMPTY_CHARACTER) {
            dataAria.get(i).set(j + 1, FILL_CHARACTER);
            wasFilling = true;
        }
        if (dataAria.get(i).get(j - 1) == EMPTY_CHARACTER) {
            dataAria.get(i).set(j - 1, FILL_CHARACTER);
            wasFilling = true;
        }
        return wasFilling;
    }
}
