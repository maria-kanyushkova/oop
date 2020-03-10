package lab1.invert;

public class Main {
    public static void main(String[] args) {
        try {
            MatrixDTO matrixDTO = new MatrixDTO(args);
            double[][] array = Utils.parse(
                    matrixDTO.getFileContent()
            );
            Utils.print(Matrix.inverse(array));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
