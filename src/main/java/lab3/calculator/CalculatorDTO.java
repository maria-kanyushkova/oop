package lab3.calculator;

public class CalculatorDTO {
    private String inputPath;

    public CalculatorDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    public String getInputPath() {
        return inputPath;
    }
}
