package lab2.mini_dictionary;

import java.util.*;
import java.util.function.BiConsumer;

public class Dictionary {
    private Map<String, List<String>> dictionary = new HashMap<>();

    public void add(String word, String translation) {
        if (dictionary.containsKey(word)) {
            final List<String> translations = dictionary.get(word);
            translations.add(translation);
        } else {
            final List<String> translations = new ArrayList<>();
            translations.add(translation);
            dictionary.put(word, translations);
        }
    }

    public List<String> get(String word) {
        return dictionary.get(word);
    }

    public boolean contains (String word) {
        return dictionary.containsKey(word);
    }

    public Map<String, List<String>> getDictionary() {
        return dictionary;
    }

    void traverse(BiConsumer<String, List<String>> consumer) {
        dictionary.forEach(consumer);
    }
}
