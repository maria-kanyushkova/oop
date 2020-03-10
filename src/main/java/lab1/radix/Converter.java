package lab1.radix;

public class Converter {
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRASUVWXYZ";

    public static String convert(String value, final int fromRadix, final int toRadix) throws ArithmeticException {
        boolean isNegative = false;
        int number = 0;
        for (int i = 0; i < value.length(); ++i) {
            char symbol = value.charAt(i);
            if (symbol == '-') {
                isNegative = true;
                continue;
            }
            if (SYMBOLS.indexOf(symbol) != -1) {
                int digit = Utils.charToInt(symbol, fromRadix);
                if (number > Integer.MAX_VALUE / fromRadix || number > Integer.MAX_VALUE - digit) {
                    throw new ArithmeticException("Value is larger than the allowed size");
                }
                number *= fromRadix;
                number += digit;
            }
        }
        StringBuilder temp = new StringBuilder();
        do {
            temp.insert(0, Utils.intToChar(number % toRadix));
            number /= toRadix;
        } while (number > 0);
        if (isNegative) {
            temp.insert(0, '-');
        }
        return temp.toString();
    }
}