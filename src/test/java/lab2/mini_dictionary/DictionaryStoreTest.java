package lab2.mini_dictionary;

import common.FileManager;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DictionaryStoreTest {
    private String path = "tasks/lab2/mini_dictionary/dictionary-test.txt";
    private Dictionary dictionary = new Dictionary();
    private DictionaryStore store = new DictionaryStore(path);

    public DictionaryStoreTest() {
        dictionary.add("hello", "world");
        dictionary.add("hello", "user");
        dictionary.add("hi", "world");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        final File file = new File("tasks/lab2/mini_dictionary/dictionary-test.txt");
        file.delete();
    }

    @Test
    public void testSaveWorks() throws IOException {
        store = new DictionaryStore(path);
        store.save(dictionary);
        final File file = new File(path);
        assertTrue(file.exists());
        final String expected = "hi:world\nhello:world\nhello:user\n";
        final String actual = FileManager.read(file);
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadWorks() throws IOException {
        store = new DictionaryStore(path);
        dictionary = store.load();
        final Dictionary expectedDict = new Dictionary();
        expectedDict.add("hello", "world");
        expectedDict.add("hello", "user");
        expectedDict.add("hi", "world");
        Map<String, List<String>> expectedMap = new HashMap<>();
        expectedDict.traverse(expectedMap::put);
        Map<String, List<String>> actualMap = new HashMap<>();
        dictionary.traverse(actualMap::put);
        assertThat(expectedMap, is(actualMap));
    }
}
