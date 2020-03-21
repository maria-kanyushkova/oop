package lab1.replace;

public class ReplaceDTO {
    private String inputPath;
    private String outputPath;
    private String searchString;
    private String replaceString;

    public ReplaceDTO(String inputPath, String outputPath, String search, String replace) {
        setInputPath(inputPath);
        setOutputPath(outputPath);
        setSearchString(search);
        setReplaceString(replace);
    }

    private void setInputPath(String value) {
        inputPath = value;
    }

    private void setOutputPath(String value) {
        outputPath = value;
    }

    private void setSearchString(String value) {
        searchString = value;
    }

    private void setReplaceString(String value) {
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
