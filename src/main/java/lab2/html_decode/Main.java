/*
Вариант 5 – HTML Decode – 60 баллов
Разработайте функцию
    std::string HtmlDecode(std::string const& html),
выполняющую декодирование HTML-сущностей строки html, перечисленных в варианте 3, обратно в их символьное представление.
Разработайте на ее основе программу, выполняющую декодирование html-сущностей текста, поступающего со стандартного потока ввода, и выводящую результат в стандартный поток вывода.
Внимание, реализация данной функции должна иметь сложность близкую к O(N)
*/

package lab2.html_decode;

public class Main {
    public static void main(String[] args) {
        try {
            HtmlDecoder.decode();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
