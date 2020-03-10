package lab1.fill;

import common.FileManager;

import java.io.File;
import java.io.IOException;

public class FillDTO {
    private static File inputFile;
    private static File outputFile;
    private static String fileContent;

    public FillDTO(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile> <outputFile>");
        }

        File inputFile = FileManager.create(args[0]);
        File outputFile = FileManager.create(args[1]);
        String fileContent = FileManager.read(inputFile);

        setInputFile(inputFile);
        setOutputFile(outputFile);
        setFileContent(fileContent);
    }

    private static void setInputFile(File input) {
        inputFile = input;
    }

    private static void setOutputFile(File output) {
        outputFile = output;
    }

    private static void setFileContent(String content) {
        fileContent = content;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public String getFileContent() {
        return fileContent;
    }
}
