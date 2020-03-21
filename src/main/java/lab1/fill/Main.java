package lab1.fill;

import common.FileManager;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            FillDTO fillDTO = parseArgs(args);
            File inputFile = FileManager.getFileByPath(fillDTO.getInputPath());
            File outputFile = FileManager.create(fillDTO.getOutputPath());
            List<List<Character>> aria = Utils.parse(inputFile);
            aria = Filler.fill(aria);
            FileManager.write(outputFile, Utils.print(aria));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static FillDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile> <outputFile>");
        }
        return new FillDTO(args[0], args[1]);
    }
}
