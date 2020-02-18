package lab1.replace;

import common.ArgsValidator;
import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsValidator.validate(args, 4);
            File inputFile = FileManager.create(args[0]);
            File outputFile = FileManager.create(args[1]);
            String search = args[2];
            String replace = args[3];

            String fileContent = FileManager.read(inputFile);
            String replaced = Main.replaceString(fileContent, search, replace);
            FileManager.write(outputFile, replaced);

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static String replaceString(String fileContent, String searchStr, String replaceStr) {
        StringBuilder outString = new StringBuilder();
        boolean canReplace = searchStr.length() > 0;
        if (!canReplace) {
            return fileContent;
        }

        for (int i = 0; i < fileContent.length(); ) {
            if (Main.findSubstring(fileContent, i, searchStr)) {
                i += searchStr.length();
                outString.append(replaceStr);
            } else {
                outString.append(fileContent.charAt(i));
                ++i;
            }
        }
        return outString.toString();
    }

    private static boolean findSubstring(String fileContent, Integer index, String substr) {
        if ((fileContent.length() - index) >= substr.length() && substr.length() > 0) {
            if (Main.checkFirstAndLastSymbols(fileContent.charAt(index), substr.charAt(0), fileContent.charAt(index + substr.length() - 1), substr.charAt(substr.length() - 1))) {
                return fileContent.substring(index, index + substr.length()).equals(substr);
            }
        }
        return false;
    }

    private static boolean checkFirstAndLastSymbols(char first1, char first2, char last1, char last2) {
        return first1 == first2 && last1 == last2;
    }
}
