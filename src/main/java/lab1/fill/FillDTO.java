package lab1.fill;

public class FillDTO {
    private String inputPath;
    private String outputPath;

    public FillDTO(String inputPath, String outputPath) {
        setInputPath(inputPath);
        setOutputPath(outputPath);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    private void setOutputPath(String value) {
        outputPath = value;
    }


    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

}
