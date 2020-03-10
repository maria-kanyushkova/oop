package lab1.radix;

public class Utils {
    public static int charToInt(final char symbol, final int radix) {
        if (Helper.isDigitSymbol(symbol) && (symbol - '0') < radix) {
            return symbol - '0';
        }
        if (Helper.isAlphaSymbol(symbol) && (symbol - 'A') < radix) {
            return symbol - 'A' + 10;
        }
        return -1;
    }

    public static char intToChar(final int digit) {
        if (Helper.isDigit(digit)) {
            return (char) (digit + '0');
        }
        return (char) (digit + 'A' - 10);
    }
}
