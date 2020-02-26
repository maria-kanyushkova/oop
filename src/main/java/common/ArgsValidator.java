package common;

public class ArgsValidator {
    public static void validate(String[] args, int expected) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty arguments");
        }
        if (args.length < expected) {
            throw new IllegalArgumentException("Few arguments");
        }
    }
}
