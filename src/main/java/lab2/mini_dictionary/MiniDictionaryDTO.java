package lab2.mini_dictionary;

public class MiniDictionaryDTO {
    private String inputPath;

    public MiniDictionaryDTO(String inputPath) {
        setInputPath(inputPath);
    }

    private void setInputPath(String content) {
        inputPath = content;
    }

    public String getInputPath() {
        return inputPath;
    }
}