package lab1.fill;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 2);
            File inputFile = FileManager.create(args[0]);
            File outputFile = FileManager.create(args[1]);
            String fileContent = FileManager.read(inputFile);
            char[][] aria = Helper.parse(fileContent);
            aria = Filler.fill(aria);
            FileManager.write(outputFile, Helper.print(aria));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

}
