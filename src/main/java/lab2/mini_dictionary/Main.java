/*
Вариант 2 – мини-словарь – 90 баллов
Разработайте программу-словарь, осуществляющую перевод слов и словосочетаний, поступающих со стандартного потока ввода,
с английского языка на русский с использованием заданного файла словаря и выводящую результат перевода в стандартный
поток вывода.
Если вводимое слово или словосочетание, отсутствует в словаре, программа должна попросить пользователя ввести перевод и
 запомнить его, в случае, если пользователь ввел непустую строку.
Для выхода из диалога с программой пользователь должен ввести строку, состоящую из трех точек. Перед выходом программа
 спрашивает о необходимости сохранить изменения в файле словаря, в том случае, если в словарь были добавлены фразы во
 время текущей сессии работы с программой.
Имя файла словаря передается программе с помощью параметра командной строки. Если файл словаря отсутствует, то
программа должна считать его пустым и предложить сохранить словарь по окончании работы, если туда были добавлены фразы.
*/

package lab2.mini_dictionary;

public class Main {
    public static void main(String[] args) {
        try {
            MiniDictionaryDTO miniDictionaryDTO = parseArgs(args);
            DictionaryStore store = new DictionaryStore(miniDictionaryDTO.getInputPath());
            Dictionary dictionary = store.load();
            Controller controller = new Controller(dictionary, store);
            EventLoop eventLoop = new EventLoop(controller);
            eventLoop.run();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static MiniDictionaryDTO parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile>");
        }
        return new MiniDictionaryDTO(args[0]);
    }
}
