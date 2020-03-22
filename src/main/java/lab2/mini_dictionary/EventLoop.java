package lab2.mini_dictionary;

import java.io.IOException;
import java.util.*;

public class EventLoop {
    private static final String SAVE_DICT_ACCEPT = "y";
    private static final String TERMINAL_STRING = "...";

    public static void run(String dictionaryPath) throws IOException {
        Map<String, List<String>> dictionary = Dictionary.load(dictionaryPath);
        while (true) {
            final Scanner scanner = new Scanner(System.in);
            final String word = scanner.nextLine();
            if (word.isEmpty()) {
                onEmptyInput();
            } else if (Objects.equals(word, TERMINAL_STRING)) {
                boolean saved = onFinishWord(dictionary, dictionaryPath);
                if (saved) {
                    onExit();
                    return;
                }
            } else {
                onInputWord(dictionary, word.toLowerCase().trim());
            }
        }
    }

    private static void onEmptyInput() {
        System.out.println("Пусто. Введите заново.");
    }

    private static void onExit() {
        System.out.println("Изменения сохранены. До свидания.");
    }

    private static void onInputWord(Map<String, List<String>> dictionary, String word) {
        if (dictionary.containsKey(word)) {
            System.out.println(String.join(", ", dictionary.get(word)));
            return;
        }
        System.out.println("Неизвестное слово \"" + word + "\". Введите перевод или пустую строку для отказа.");
        final Scanner scanner = new Scanner(System.in);
        final String translation = scanner.nextLine();
        if (translation.isEmpty()) {
            System.out.println("Слово \"" + word + "\" проигнорировано.");
        } else if (translation.contains(",")) {
            fillDictionaryWithTranslations(dictionary, word, translation.split(","));
            System.out.println("Слова \"" + translation + "\" добавлены в словарь как " + word);
        } else {
            Dictionary.addWord(dictionary, translation, word);
            System.out.println("Слово \"" + word + "\" добавлено в словарь как " + translation);
        }
    }

    private static void fillDictionaryWithTranslations(Map<String, List<String>> dictionary, String word, String[] translations) {
        for (final String translatedWord : translations) {
            Dictionary.addWord(dictionary, translatedWord.trim(), word);
        }
    }

    private static boolean onFinishWord(Map<String, List<String>> dictionary, String dictionaryPath) throws IOException {
        System.out.println("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.");
        final Scanner input = new Scanner(System.in);
        final String word = input.nextLine();
        if (Objects.equals(word.toLowerCase().trim(), SAVE_DICT_ACCEPT) ) {
            Dictionary.save(dictionary, dictionaryPath);
            return true;
        } else {
            System.out.println("Изменения не сохранены.");
            return false;
        }
    }
}
