package lab1.radix;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRASUVWXYZ";
    private final int fromRadix;
    private final int toRadix;
    private final String value;
    private boolean negative = false;

    Converter(final int from, final int to, String value) {
        this.fromRadix = from;
        this.toRadix = to;
        this.value = value;
    }

    public String convert() {
        List<Integer> number = getInitNumber();
        return getRequiredValue(number);
    }

    public List<Integer> getInitNumber() {
        List<Integer> number = new ArrayList<Integer>();
        for (int i = 0; i < value.length(); ++i) {
            char symbol = value.charAt(i);
            if (symbol == '-') {
                this.negative = true;
            }
            if (SYMBOLS.indexOf(symbol) != -1) {
                number.add(Converter.charToInt(symbol, this.fromRadix));
            }
        }
        return number;
    }

    public String getRequiredValue(final List<Integer> number) {
        List<Integer> buffer = new ArrayList<Integer>();
        do { // сделать по другому всё что дальше
            buffer.add(this.getNumber(number));
        } while (!this.hasOnlyZeroes(number));
        StringBuilder temp = new StringBuilder();
        for (int i = buffer.size() - 1; i >= 0; i--) {
            temp.append(intToChar(buffer.get(i)));
        }
        if (negative) {
            temp.insert(0, '-');
        }
        return temp.toString();
    }

    public int getNumber(List<Integer> number) {
        int temp = 0;
        for (int i = 0; i < number.size(); i++) {
            temp = temp * this.fromRadix + number.get(i);
            number.set(i, temp / this.toRadix);
            temp = temp % this.toRadix;
        }
        return temp;
    }

    private boolean hasOnlyZeroes(List<Integer> number) {
        for (int integer : number) {
            if (integer != 0) {
                return false;
            }
        }
        return true;
    }

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