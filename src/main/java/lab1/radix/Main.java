package lab1.radix;

import common.ArgsValidator;

public class Main {
    public static void main(String[] args) {
        try {
            String expectedFormat = "<from> <to> <value>";
            ArgsValidator.validate(args, 3, expectedFormat);
            final int from = Integer.parseInt(args[0]);
            final int to = Integer.parseInt(args[1]);
            String value = args[2];

            Helper.checkNumberSystemsValid(from, to);
            Helper.checkValueValid(value, from);

            System.out.println(Converter.convert(value, from, to));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
