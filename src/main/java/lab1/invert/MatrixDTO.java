package lab1.invert;

public class MatrixDTO {
    private String inputPath;

    public MatrixDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String content) {
        inputPath = content;
    }

    public String getInputPath() {
        return inputPath;
    }
}
