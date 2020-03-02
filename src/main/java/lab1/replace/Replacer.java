package lab1.replace;

import java.io.*;

public class Replacer {
    public static void replaceFile(File inputFile, File outputFile, String searchStr, String replaceStr) throws IOException {

        FileReader fileReader = new FileReader(inputFile);
        FileWriter writer = new FileWriter(outputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String stringLine;
        while ((stringLine = bufferedReader.readLine()) != null) {
            String newString = stringLine;
            if (searchStr.length() > 0) {
                newString = stringLine.replace(searchStr, replaceStr);
            }
            writer.write(newString + "\n");
        }
        bufferedReader.close();
        fileReader.close();
        writer.close();
    }
}
