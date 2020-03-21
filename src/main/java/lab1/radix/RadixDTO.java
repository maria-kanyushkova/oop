package lab1.radix;

public class RadixDTO {
    private int fromRadix;
    private int toRadix;
    private String value;

    public RadixDTO(int fromRadix, int toRadix, String value) {
        setFromRadix(fromRadix);
        setToRadix(toRadix);
        setValue(value);
    }

    private void setFromRadix(int from) {
        fromRadix = from;
    }

    private void setToRadix(int to) {
        toRadix = to;
    }

    private void setValue(String valueOfNumber) {
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
