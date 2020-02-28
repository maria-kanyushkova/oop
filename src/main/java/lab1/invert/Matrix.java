package lab1.invert;

public class Matrix {
    static double[][] inverse(double[][] matrix) throws ArithmeticException {
        double determinant = getDeterminant(matrix);
        if (determinant == 0) {
            throw new ArithmeticException("No inverse matrix");
        }
        return multiply(transpose(calculateAdditionalMatrix(matrix)), 1.0 / determinant);
    }

    static double[][] transpose(final double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    static double[][] multiply(final double[][] matrix, double constant) {
        double[][] nextMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                nextMatrix[i][j] =  matrix[i][j] * constant;
            }
        }
        return nextMatrix;
    }

    static double getDeterminant(final double[][] matrix) {
        if (matrix.length == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }
        if (matrix.length == 3) {
            return (
                matrix[0][0] * matrix[1][1] * matrix[2][2] +
                matrix[0][1] * matrix[1][2] * matrix[2][0] +
                matrix[0][2] * matrix[1][0] * matrix[2][1] -
                matrix[0][2] * matrix[1][1] * matrix[2][0] -
                matrix[0][1] * matrix[1][0] * matrix[2][2] -
                matrix[0][0] * matrix[1][2] * matrix[2][1]
            );
        }
        return 0;
    }

    static double[][] createSubMatrix(final double[][] matrix, int rows, int columns) {
        double[][] subMatrix = new double[matrix.length - 1][matrix.length - 1];
        int r = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (i == rows) {
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < matrix.length; j++) {
                if (j == columns) {
                    continue;
                }
                subMatrix[r][++c] = matrix[i][j];
            }
        }
        return subMatrix;
    }

    static double[][] calculateAdditionalMatrix(final double[][] matrix) {
        double[][] additionalMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int sign = (i + j) % 2 == 0 ? 1 : -1;
                additionalMatrix[i][j] = sign * getDeterminant(createSubMatrix(matrix, i, j));
            }
        }
        return additionalMatrix;
    }
}
