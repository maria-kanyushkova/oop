package lab1.replace;

import java.io.*;

public class Replacer {
    public static void replaceFile(File inputFile, File outputFile, String searchStr, String replaceStr) throws IOException {
        if (!inputFile.exists()) {
            throw new IOException("File: " + inputFile.getName() + " is not exist");
        }
        if (!outputFile.exists()) {
            throw new IOException("File: " + outputFile.getName() + " is not exist");
        }
        String stringLine;
        FileReader fileReader = new FileReader(inputFile);
        FileWriter writer = new FileWriter(outputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((stringLine = bufferedReader.readLine()) != null) {
            String newString = stringLine;
            if (searchStr.length() > 0) {
                newString = replaceString(stringLine, searchStr, replaceStr);
            }
            writer.write(newString + "\n");
        }
        bufferedReader.close();
        fileReader.close();
        writer.close();
    }
    public static String replaceString(String fileContent, String searchStr, String replaceStr) {
        StringBuilder outString = new StringBuilder();
        for (int i = 0; i < fileContent.length(); ) {
            if (findSubstring(fileContent, i, searchStr)) {
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
            if (checkFirstAndLastSymbols(fileContent.charAt(index), substr.charAt(0), fileContent.charAt(index + substr.length() - 1), substr.charAt(substr.length() - 1))) {
                return fileContent.substring(index, index + substr.length()).equals(substr);
            }
        }
        return false;
    }

    private static boolean checkFirstAndLastSymbols(char first1, char first2, char last1, char last2) {
        return first1 == first2 && last1 == last2;
    }
}
