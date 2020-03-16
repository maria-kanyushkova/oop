package lab1.radix;

public class Utils {
    private static final int MIN_RADIX = 2;
    private static final int MAX_RADIX = 36;

    public static boolean isRadixValid(final int radix) {
        return MIN_RADIX <= radix && radix <= MAX_RADIX;
    }

    public static boolean isValueValid(final String value, final int radix) {
        for (int i = 0; i < value.length(); ++i) {
            int symbol = Utils.charToInt(value.charAt(i), radix);
            if (symbol >= radix - 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigitSymbol(final char symbol) {
        return '0' <= symbol && symbol <= '9';
    }

    public static boolean isAlphaSymbol(final char symbol) {
        return 'A' <= symbol && symbol <= 'Z';
    }

    public static boolean isDigit(final int digit) {
        return 0 <= digit && digit <= 9;
    }

    public static void validateCorrectnessOfNumberSystems(final int fromRadix, final int toRadix) throws ArithmeticException {
        if (!(isRadixValid(fromRadix) && isRadixValid(toRadix))) {
            throw new ArithmeticException("Incorrect number systems!");
        }
    }

    public static String validateValueOnNumberSystem(final String value, final int fromRadix) throws ArithmeticException {
        if (!isValueValid(value, fromRadix)) {
            throw new ArithmeticException("Incorrect value!");
        }
        return value;
    }

    public static int charToInt(final char symbol, final int radix) {
        if (isDigitSymbol(symbol) && (symbol - '0') < radix) {
            return symbol - '0';
        }
        if (isAlphaSymbol(symbol) && (symbol - 'A') < radix) {
            return symbol - 'A' + 10;
        }
        return -1;
    }

    public static char intToChar(final int digit) {
        if (isDigit(digit)) {
            return (char) (digit + '0');
        }
        return (char) (digit + 'A' - 10);
    }
}
