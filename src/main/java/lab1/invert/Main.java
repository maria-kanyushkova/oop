package lab1.invert;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            MatrixDTO matrixDTO = parseArgs(args);
            File inputFile = FileManager.getFileByPath(matrixDTO.getInputPath());
            double[][] array = Utils.parse(inputFile);
            Utils.print(Matrix.inverse(array));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static MatrixDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile>");
        }
        return new MatrixDTO(args[0]);
    }
}
