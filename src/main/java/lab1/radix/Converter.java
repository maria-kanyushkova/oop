package lab1.radix;

public class Converter {
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRASUVWXYZ";

    public static String convert(String value, final int fromRadix, final int toRadix) throws ArithmeticException {
        int number = transformStringToNumber(value, fromRadix);
        StringBuilder temp = new StringBuilder();
        temp.append(transformNumberToString(number, toRadix));
        if (isNegative(value)) {
            temp.insert(0, '-');
        }
        return temp.toString();
    }

    private static boolean isNegative(String value) {
        for (int i = 0; i < value.length(); ++i) {
            if (value.charAt(i) == '-') {
                return true;
            }
        }
        return false;
    }

    private static int transformStringToNumber(String value, int fromRadix) throws ArithmeticException {
        int number = 0;
        for (int i = 0; i < value.length(); ++i) {
            char symbol = value.charAt(i);
            if (SYMBOLS.indexOf(symbol) != -1) {
                int digit = Utils.charToInt(symbol, fromRadix);
                if (number > (Integer.MAX_VALUE - digit) / fromRadix) {
                    throw new ArithmeticException("Value is larger than the allowed size");
                }
                number *= fromRadix;
                number += digit;
            }
        }
        return number;
    }

    private static String transformNumberToString(int number, int toRadix) {
        StringBuilder temp = new StringBuilder();
        do {
            temp.insert(0, Utils.intToChar(number % toRadix));
            number /= toRadix;
        } while (number > 0);
        return temp.toString();
    }
}