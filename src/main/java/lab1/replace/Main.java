package lab1.replace;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ReplaceDTO replaceDTO = parseArgs(args);
            File inputFile = FileManager.getFileByPath(replaceDTO.getInputPath());
            File outputFile = FileManager.create(replaceDTO.getOutputPath());

            Replacer.replaceFile(
                    inputFile,
                    outputFile,
                    replaceDTO.getSearchString(),
                    replaceDTO.getReplaceString()
            );
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static ReplaceDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile> <outputFile> <searchString> <replaceString>");
        }
        return new ReplaceDTO(args[0], args[1], args[2], args[3]);
    }
}
