package common;

import java.io.*;

// get methods from
// https://github.com/android10/Android-CleanArchitecture/blob/master/data/src/main/java/com/fernandocejas/android10/sample/data/cache/FileManager.java

public class FileManager {
    public static File create(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            final boolean isCreated = file.createNewFile();
            if (!isCreated) {
                throw new IOException("Cannot create input file!");
            }
        }
        return file;
    }

    public static File getFileByPath(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("File: " + file.getName() + " is not exist");
        }
        return file;
    }

    public static String read(File file) throws IOException {
        final StringBuilder fileContentBuilder = new StringBuilder();
        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                fileContentBuilder.append(stringLine).append("\n");
            }
        }
        return fileContentBuilder.toString();
    }

    public static void write(File file, String content) throws IOException {
        try (
                FileWriter writer = new FileWriter(file);
        ) {
            writer.write(content);
        }
    }

    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }
}
