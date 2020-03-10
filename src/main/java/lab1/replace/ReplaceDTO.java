package lab1.replace;

import common.FileManager;

import java.io.File;
import java.io.IOException;

public class ReplaceDTO {
    private static File inputFile;
    private static File outputFile;
    private static String searchString;
    private static String replaceString;

    public ReplaceDTO(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile> <outputFile> <searchString> <replaceString>");
        }

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

        setInputFile(inputFile);
        setOutputFile(outputFile);
        setSearchString(args[3]);
        setReplaceString(args[2]);
    }

    private static void setInputFile(File input) {
        inputFile = input;
    }

    private static void setOutputFile(File output) {
        outputFile = output;
    }

    private static void setSearchString(String valueOfNumber) {
        searchString = valueOfNumber;
    }

    private static void setReplaceString(String valueOfNumber) {
        replaceString = valueOfNumber;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public String getSearchString() {
        return searchString;
    }

    public String getReplaceString() {
        return replaceString;
    }
}
