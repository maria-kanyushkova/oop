package lab1.replace;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String expectedFormat = "<inputFile> <outputFile> <searchString> <replaceString>";
            ArgsValidator.validate(args, 4, expectedFormat);
            File inputFile = new File(args[0]);
            FileManager.validate(inputFile);
            File outputFile = new File(args[1]);
            FileManager.validate(outputFile);
            if (!inputFile.exists()) {
                throw new IOException("File: " + inputFile.getName() + " is not exist");
            }
            if (!outputFile.exists()) {
                throw new IOException("File: " + outputFile.getName() + " is not exist");
            }
            String search = args[2];
            String replace = args[3];

            Replacer.replaceFile(inputFile, outputFile, search, replace);
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
