package lab2.mini_dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EventLoopTest {
    private String path = "tasks/lab2/mini_dictionary/dictionary-test.txt";
    private final PrintStream original = System.out;
    private Dictionary dictionary = new Dictionary();
    private final DictionaryStore store = new DictionaryStore(path);
    private final Controller controller = spy(new Controller(dictionary, store));
    private ByteArrayOutputStream mock = new ByteArrayOutputStream();

    @AfterClass
    public static void tearDownAfterClass() {
        final File file = new File("tasks/lab2/mini_dictionary/dictionary-test.txt");
        file.delete();
    }

    @Before
    public void setUp() {
        mock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mock));
    }

    @After
    public void tearDown() {
        System.setOut(original);
        dictionary = new Dictionary();
    }

    @Test
    public void testStateEmptyInput() {
        controller.onEmptyInput();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onEmptyInput();
        final String expectedMessage = "Пусто. Введите заново.\r\n";
        assertEquals(expectedMessage, mock.toString());
    }

    @Test
    public void testStateFinish() throws IOException {
        final InputStream original = System.in;
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        controller.onFinishWord();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onFinishWord();
        controller.onExit();
        verify(controller, times(wantedNumberOfInvocations)).onExit();
        final String expectedMessage = "В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n" +
                "Изменения сохранены. До свидания.";
        assertEquals(
                expectedMessage.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateUnknownWordSave() {
        final InputStream original = System.in;
        final String word = "cat";
        final String translation = "кот";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "Неизвестное слово \"" + word + "\". " +
                "Введите перевод или пустую строку для отказа. \n" +
                "Слово \"" + translation + "\" добавлено в словарь как \"" + word + "\".";
        assertEquals(
                expectedMessage.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateEmptyWord() {
        final InputStream original = System.in;
        final String word = "\n";
        System.setIn(new ByteArrayInputStream(word.getBytes()));
        controller.onEmptyInput();
        final String expectedMessage = "Пусто. Введите заново.";
        assertEquals(
                expectedMessage.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateUnknownWordIgnore() {
        final InputStream original = System.in;
        final String word = "cat";
        final String translation = "\n";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "Неизвестное слово \"" + word + "\". " +
                "Введите перевод или пустую строку для отказа. \n" +
                "Слово \"" + word + "\" проигнорировано.";
        assertEquals(
                expectedMessage.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateExit() throws IOException {
        final InputStream original = System.in;
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        controller.onFinishWord();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onFinishWord();
        controller.onExit();
        verify(controller, times(wantedNumberOfInvocations)).onExit();
        final String expectedMessage = "В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n" +
                "Изменения сохранены. До свидания.";
        assertEquals(
                expectedMessage.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testDispatchPrintWord() {
        final String word = "cat";
        dictionary.add(word, "кот");
        dictionary.add(word, "кошка");
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "кот, кошка\r\n";
        assertEquals(expectedMessage, mock.toString());
    }
}
