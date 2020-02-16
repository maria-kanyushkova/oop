package lab1.task1;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 4);
            File inputFile = FileManager.create(args[0]);
            File outputFile = FileManager.create(args[1]);
            String search = args[2];
            String replace = args[3];

            String lines = FileManager.read(inputFile);
            FileManager.write(outputFile, lines.replaceAll(search, replace));

        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
