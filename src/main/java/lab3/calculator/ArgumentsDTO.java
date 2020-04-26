package lab3.calculator;

public class ArgumentsDTO {
    private String inputPath;

    public ArgumentsDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    public String getInputPath() {
        return inputPath;
    }
}
