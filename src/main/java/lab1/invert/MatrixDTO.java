package lab1.invert;

import common.FileManager;

import java.io.File;
import java.io.IOException;

public class MatrixDTO {
    private static String fileContent;

    public MatrixDTO(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile>");
        }

        File inputFile = FileManager.create(args[0]);
        String fileContent = FileManager.read(inputFile);

        setFileContent(fileContent);
    }

    private static void setFileContent(String content) {
        fileContent = content;
    }

    public String getFileContent() {
        return fileContent;
    }
}
