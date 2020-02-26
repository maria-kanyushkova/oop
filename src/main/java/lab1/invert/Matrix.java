package lab1.invert;

public class Matrix {
    private static int size = Helper.MATRIX_SIZE;
    private static double[][] data;

    public static void setValueAt(int row, int col, double value) {
        data[row][col] = value;
    }

    public static double getValueAt(int row, int col) {
        return data[row][col];
    }

    public static double[][] multiply(double[][] matrix, double constant) {
        data = matrix;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double value = data[i][j] * constant;
                setValueAt(i, j, value);
            }
        }
        return data;
    }

    public static double[][] transpose(double[][] matrix) {
        data = matrix;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                setValueAt(i, j, data[j][i]);
            }
        }
        return data;
    }

    public static double determinant(double[][] matrix) {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            sum += inverseValue(i) * getValueAt(0, i) * determinant(createSubMatrix(matrix, new ImmutablePair<>(0, i)));
        }
        return sum;
    }

    public static int inverseValue(final int i) {
        if (i % 2 == 0) {
            return 1;
        }
        return -1;
    }
}
