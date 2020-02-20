package lab1.radix;

import common.ArgsValidator;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 3);
            final int from = Integer.parseInt(args[0]);
            final int to = Integer.parseInt(args[1]);
            String value = args[2];

            Helper.checkNumberSystemsValid(from, to);
            Helper.checkValueValid(value, from);

            Converter converter = new Converter(from, to, value);
            System.out.println(converter.convert());
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
