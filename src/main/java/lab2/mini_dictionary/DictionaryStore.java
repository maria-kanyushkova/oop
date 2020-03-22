package lab2.mini_dictionary;

import common.FileManager;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DictionaryStore {
    private static final String DELIMITER = ":";
    private static final String EOLN = "\n";

    private String dictionaryPath;

    DictionaryStore(String path) {
        dictionaryPath = path;
    }

    public Dictionary load() throws IOException {
        Dictionary dictionary = new Dictionary();
        if (!FileManager.isFileExist(dictionaryPath)) {
            return dictionary;
        }
        try (
                FileReader fileReader = new FileReader(dictionaryPath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] value = line.split(DELIMITER);
                final String word = value[0];
                final String translation = value[1];
                dictionary.add(word, translation);
            }
        } catch (NoSuchElementException e) {
            throw new IOException("Словарь повреждён!");
        }
        return dictionary;
    }

    public void save(Dictionary dictionary) throws IOException {
        File dictionaryFile = FileManager.isFileExist(dictionaryPath) ?
                FileManager.getFileByPath(dictionaryPath) : FileManager.create(dictionaryPath);
        final Map<String, List<String>> dictionaryValues = dictionary.getDictionary();
        try (
                final FileWriter writer = new FileWriter(dictionaryFile, false)
        ) {
            for (Map.Entry<String, List<String>> value : dictionaryValues.entrySet()) {
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
