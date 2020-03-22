package lab2.mini_dictionary;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    private static final String SAVE_DICT_ACCEPT = "y";
    private final Dictionary dictionary;
    private final DictionaryStore store;

    Controller(Dictionary dictionary, DictionaryStore store) {
        this.dictionary = dictionary;
        this.store = store;
    }

    public void onEmptyInput() {
        System.out.println("Пусто. Введите заново.");
    }

    public void onExit() {
        System.out.println("Изменения сохранены. До свидания.");
    }

    public void onInputWord(String word) {
        if (dictionary.contains(word)) {
            System.out.println(String.join(", ", dictionary.get(word)));
            return;
        }
        System.out.println("Неизвестное слово \"" + word + "\". Введите перевод или пустую строку для отказа. ");
        final String translation = readFromConsole();
        if (translation.isEmpty()) {
            System.out.println("Слово \"" + word + "\" проигнорировано.");
        } else {
            dictionary.add(translation, word);
            System.out.println("Слово \"" + translation + "\" добавлено в словарь как \"" + word + "\".");
        }
    }

    public boolean onFinishWord() throws IOException {
        System.out.println("В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.");
        final String answer = readFromConsole();
        final boolean isAcceptSaveDictionary = Objects.equals(answer.toLowerCase().trim(), SAVE_DICT_ACCEPT);
        if (isAcceptSaveDictionary) {
            store.save(dictionary);
            return true;
        } else {
            System.out.println("Изменения не сохранены.");
            return false;
        }
    }

    public static String readFromConsole() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
