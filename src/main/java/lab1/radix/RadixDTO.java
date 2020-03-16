package lab1.radix;

public class RadixDTO {
    private static int fromRadix;
    private static int toRadix;
    private static String value;

    public RadixDTO(String[] args) throws IllegalArgumentException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <from> <to> <value>");
        }
        int fromRadix = Integer.parseInt(args[0]);
        int toRadix = Integer.parseInt(args[1]);
        String value = args[2];

        Utils.validateCorrectnessOfNumberSystems(fromRadix, toRadix);
        value = Utils.validateValueOnNumberSystem(value, fromRadix);

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
