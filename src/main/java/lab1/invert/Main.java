package lab1.invert;

import common.ArgsValidator;
import common.FileManager;
import lab1.radix.Converter;
import lab1.radix.Helper;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 1);
            File inputFile = FileManager.create(args[0]);
            String fileContent = "1 2 3\n" +
                    "4 5 6\n" +
                    "7 8 9\n"; //FileManager.read(inputFile);


        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
