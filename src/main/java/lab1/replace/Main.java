package lab1.replace;

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

            String fileContent = FileManager.read(inputFile);
            String replaced = StringReplacer.replaceString(fileContent, search, replace);
            FileManager.write(outputFile, replaced);

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

}
