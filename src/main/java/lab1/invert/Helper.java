package lab1.invert;

import java.io.IOException;

public class Helper {
    static final int MATRIX_SIZE = 3;
    static final String DELIMITER_LINES = "\n";
    static final String DELIMITER_LINE = "\t";

    public static double[][] parse(String fileContent) throws IOException {
        double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE];
        String[] arrayLines = verifyArray(fileContent.split(DELIMITER_LINES));
        for (int i = 0; i < MATRIX_SIZE; i++) {
            String[] line = verifyArray(arrayLines[i].split(DELIMITER_LINE));
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = Double.parseDouble(line[j]);
            }
        }
        return matrix;
    }

    private static String[] verifyArray(String[] array) throws IOException {
        if (array.length != MATRIX_SIZE) {
            throw new IOException("Incorrect matrix size!");
        }
        return array;
    }

    public static void print(final double[][] matrix) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.printf("%.3f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}