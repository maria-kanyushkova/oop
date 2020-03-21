package lab1.fill;

public class FillDTO {
    private static String inputPath;
    private static String outputPath;

    public FillDTO(String inputPath, String outputPath) {
        setInputPath(inputPath);
        setOutputPath(outputPath);
    }

    private static void setInputPath(String value) {
        inputPath = value;
    }

    private static void setOutputPath(String value) {
        outputPath = value;
    }


    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

}
