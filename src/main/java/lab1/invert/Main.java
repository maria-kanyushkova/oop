package lab1.invert;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            //ArgsValidator.validate(args, 1);
            //File inputFile = FileManager.create(args[0]);
            String fileContent = "1\t2\t3\n" +
                    "4\t5\t6\n" +
                    "7\t8\t9"; //FileManager.read(inputFile);
            double[][] array = Helper.parse(fileContent);
            Helper.print(array);
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
