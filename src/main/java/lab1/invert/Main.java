package lab1.invert;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 1);
            File inputFile = FileManager.create(args[0]);
            String fileContent = FileManager.read(inputFile);
            double[][] array = Helper.parse(fileContent);
            Helper.print(array);
            System.out.println();
            Helper.print(Matrix.inverse(array));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
