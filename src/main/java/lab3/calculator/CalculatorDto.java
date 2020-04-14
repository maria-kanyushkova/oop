package lab3.calculator;

public class CalculatorDto {
    private String inputPath;

    public CalculatorDto(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    public String getInputPath() {
        return inputPath;
    }
}
