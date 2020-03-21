package lab1.invert;

public class MatrixDTO {
    private static String inputPath;

    public MatrixDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private static void setInputPath(String content) {
        inputPath = content;
    }

    public String getInputPath() {
        return inputPath;
    }
}
