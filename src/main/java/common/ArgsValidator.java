package common;

public class ArgsValidator {
    public static void validate(String[] args, int expected, String expectedFormat) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty arguments\n" + expectedFormat);
        }
        if (args.length < expected) {
            throw new IllegalArgumentException("Few arguments\n" + expectedFormat);
        }
    }
}
