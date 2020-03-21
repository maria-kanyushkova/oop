package lab1.invert;

import java.io.*;

public class Utils {
    static final int MATRIX_SIZE = 3;
    static final String DELIMITER_LINE_REGEX = "[\\s\t]+";

    public static double[][] parse(File inputFile) throws IOException {
        double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE];
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String stringLine;
            int i = 0;
            while ((stringLine = bufferedReader.readLine()) != null) {
                if (i + 1 > MATRIX_SIZE) {
                    throw new IOException("Incorrect matrix size!");
                }
                String[] line = verifyArray(stringLine.split(DELIMITER_LINE_REGEX));
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    matrix[i][j] = verifySymbolOfString(line[j]);
                }
                i++;
            }
        }
        return matrix;
    }

    public static void print(final double[][] matrix) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.printf("%.3f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static double verifySymbolOfString(String digit) throws ArithmeticException {
        for (int i = 0; i < digit.length(); i++) {
            if (!verifySymbol(digit.charAt(i))) {
                throw new ArithmeticException("Incorrect symbol!");
            }
        }
        return Double.parseDouble(digit);
    }

    private static boolean verifySymbol(char symbol) {
        return ('0' <= symbol && symbol <= '9') || symbol == '-' || symbol == ',' || symbol == '.';
    }

    private static String[] verifyArray(String[] array) throws IOException {
        if (array.length != MATRIX_SIZE) {
            throw new IOException("Incorrect matrix size!");
        }
        return array;
    }
}
