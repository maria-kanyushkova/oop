package lab1.radix;

public class RadixDTO {
    private static int fromRadix;
    private static int toRadix;
    private static String value;

    public RadixDTO(int fromRadix, int toRadix, String value) {
        setFromRadix(fromRadix);
        setToRadix(toRadix);
        setValue(value);
    }

    private static void setFromRadix(int from) {
        fromRadix = from;
    }

    private static void setToRadix(int to) {
        toRadix = to;
    }

    private static void setValue(String valueOfNumber) {
        value = valueOfNumber;
    }

    public int getFromRadix() {
        return fromRadix;
    }

    public int getToRadix() {
        return toRadix;
    }

    public String getValue() {
        return value;
    }
}
