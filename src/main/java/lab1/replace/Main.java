package lab1.replace;

public class Main {
    public static void main(String[] args) {
        try {
            ReplaceDTO replaceDTO = new ReplaceDTO(args);
            Replacer.replaceFile(
                    replaceDTO.getInputFile(),
                    replaceDTO.getOutputFile(),
                    replaceDTO.getSearchString(),
                    replaceDTO.getReplaceString()
            );
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
