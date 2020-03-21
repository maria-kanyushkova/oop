package lab1.replace;

public class ReplaceDTO {
    private static String inputPath;
    private static String outputPath;
    private static String searchString;
    private static String replaceString;

    public ReplaceDTO(String inputPath, String outputPath, String search, String replace) {
        setInputPath(inputPath);
        setOutputPath(outputPath);
        setSearchString(search);
        setReplaceString(replace);
    }

    private static void setInputPath(String value) {
        inputPath = value;
    }

    private static void setOutputPath(String value) {
        outputPath = value;
    }

    private static void setSearchString(String value) {
        searchString = value;
    }

    private static void setReplaceString(String value) {
        replaceString = value;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getSearchString() {
        return searchString;
    }

    public String getReplaceString() {
        return replaceString;
    }
}
