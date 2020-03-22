package lab2.mini_dictionary;

import common.FileManager;

import java.io.*;
import java.util.*;

public class Dictionary {
    private static final String DELIMITER = ":";
    private static final String EOLN = "\n";

    private String dictionaryPath;

    Dictionary (String dictionaryPath) throws IOException {
        this.dictionaryPath = dictionaryPath;
        File file = FileManager.isFileExist(dictionaryPath) ?
                FileManager.getFileByPath(dictionaryPath) : FileManager.create(dictionaryPath);
    }

    public static void addWord(Map<String, List<String>> dictionary, String word, String translation) {
        if (dictionary.containsKey(word)) {
            final List<String> translations = dictionary.get(word);
            translations.add(translation);
        } else {
            final List<String> translations = new ArrayList<>();
            translations.add(translation);
            dictionary.put(word, translations);
        }
    }

    public static Map<String, List<String>> load(String dictionaryPath) throws IOException {
        Map<String, List<String>> dictionary = new HashMap<>();
        try (
                FileReader fileReader = new FileReader(dictionaryPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null) {
                final String[] value = stringLine.split(DELIMITER);
                final String word = value[0];
                final String translation = value[1];
                addWord(dictionary, word, translation);
            }
        } catch (NoSuchElementException e) {
            throw new IOException("Словарь повреждён!");
        }
        return dictionary;
    }

    public static void save(Map<String, List<String>> dictionary, String dictionaryPath) throws IOException {
        final File file = FileManager.getFileByPath(dictionaryPath);
        try (final FileWriter writer = new FileWriter(file, false)) {
            for (Map.Entry<String, List<String>> value : dictionary.entrySet()) {
                String word = value.getKey();
                List<String> translations = value.getValue();
                for (String translation : translations) {
                    writer.write(word);
                    writer.write(DELIMITER);
                    writer.write(translation);
                    writer.write(EOLN);
                }
            }
        }
    }
}
