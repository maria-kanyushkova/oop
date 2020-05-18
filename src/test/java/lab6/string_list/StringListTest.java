package lab6.string_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {
    StringList list;

    public void assertEqualsIteratorValue(String expected, ListIterator it1) {
        assertEquals(expected, it1.getCurrent());
    }

    public void assertEqualsIterators(ListIterator it1, ListIterator it2) {
        assertEquals(it1.getCurrent(), it2.getCurrent());
    }

    public void assertNotEqualsIterators(ListIterator it1, ListIterator it2) {
        assertNotEquals(it1.getCurrent(), it2.getCurrent());
    }

    public void assertNullIterator(ListIterator it1) {
        assertNull(it1.getCurrent());
    }

    @BeforeEach
    public void initList() {
        list = new StringList();
    }

    @Nested
    @DisplayName("number of elements and whether node exist")
    class NumberOfNode {
        @Test
        public void listIsEmpty() {
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        public void listIsNotEmpty() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            assertEquals(2, list.size());
            assertFalse(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("push elements to list")
    class AddNode {
        @Test
        @DisplayName("1. Вставка в конец одного элемента в пустой список")
        public void test1() {
            list.pushBack("Lorem");
            assertEquals(1, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("2. Вставка элемента в конец существующего списка")
        public void test2() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            assertEquals(2, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("ipsum", list.end());
        }

        @Test
        @DisplayName("3. Вставка элемента в начало пустого списка")
        public void test3() {
            list.pushFront("Lorem");
            assertEquals(1, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("4. Вставка элемента в начало заполненого списка")
        public void test4() {
            list.pushBack("Lorem");
            list.pushFront("ipsum");
            assertEquals(2, list.size());
            assertEqualsIteratorValue("ipsum", list.begin());
            assertEqualsIteratorValue("Lorem", list.end());
        }
    }

    @Nested
    @DisplayName("iterators")
    class Iterators {
        @Test
        @DisplayName("5. проверка итераторов при пустом списке")
        public void test5() {
            assertNullIterator(list.begin());
            assertNullIterator(list.end());
            assertNullIterator(list.rbegin());
            assertNullIterator(list.rend());
        }

        @Test
        @DisplayName("6. проверка итераторов со списком из 1ого элемента")
        public void test6() {
            list.pushBack("Lorem");
            assertEqualsIterators(list.begin(), list.end());
            assertEqualsIterators(list.begin(), list.rbegin());
            assertEqualsIterators(list.end(), list.rend());
        }

        @Test
        @DisplayName("7. проверка итераторов на заполненном списке")
        public void test7() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            assertNotEqualsIterators(list.begin(), list.rbegin());
            assertNotEqualsIterators(list.end(), list.rend());
            assertEqualsIterators(list.begin(), list.rend());
            assertEqualsIterators(list.end(), list.rbegin());
        }

        @Test
        @DisplayName("8. проверка итератора на заполненном списке")
        public void listIsIterable() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            int counter = 0;
            ListIterator it = list.begin();
            for (final String string : list) {
                assertThat(string, is(it.getCurrent()));
                it.next();
                counter++;
            }
            assertEquals(list.size(), counter);
        }

        @Test
        @DisplayName("9. проверка реверсивного итератора на заполненном списке")
        public void test9() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            ListIterator it = list.rbegin();
            assertEquals("dolor", it.getCurrent());
            it.next();
            assertEquals("ipsum", it.getCurrent());
        }
    }

    @Nested
    @DisplayName("delete all elements")
    class DeleteAllNodes {
        @Test
        @DisplayName("")
        public void clearEmptyList() {
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("")
        public void clearList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("insert element into position from iterator")
    class InsertNode {
        @Test
        @DisplayName("11. Вставка элемента в начало пустого списка")
        public void test11() throws Exception {
            list.insert(list.begin(), "Lorem");
            assertEqualsIteratorValue("Lorem", list.begin());
        }

        @Test
        @DisplayName("12. Вставка элемента в конец пустого списка")
        public void test12() throws Exception {
            list.insert(list.end(), "Lorem");
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("13. Вставка элемента в любую позицию списка")
        public void insertToMiddleOfList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            ListIterator it = list.begin();
            it.next();
            list.insert(it, "sit");
            it = list.begin();
            assertEqualsIteratorValue("Lorem", it);
            it.next();
            assertEqualsIteratorValue("sit", it);
            it.next();
            assertEqualsIteratorValue("ipsum", it);
        }

        @Test
        @DisplayName("14. Вставка элемента в начало заполненного списка")
        public void insertToFrontOfList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            list.insert(list.begin(), "sit");
            ListIterator it = list.begin();
            assertEqualsIteratorValue("sit", it);
            it.next();
            assertEqualsIteratorValue("Lorem", it);
            it.next();
            assertEqualsIteratorValue("ipsum", it);
            it.next();
            assertEqualsIteratorValue("dolor", it);
        }

        @Test
        @DisplayName("15. Вставка элемента в конец заполненного списка")
        public void insertToBackOfList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            ListIterator it = list.begin();
            it.next();
            it.next();
            list.insert(it, "sit");
            it = list.begin();
            assertEqualsIteratorValue("Lorem", it);
            it.next();
            assertEqualsIteratorValue("ipsum", it);
            it.next();
            assertEqualsIteratorValue("sit", it);
            it.next();
            assertEqualsIteratorValue("dolor", it);
        }

        @Test
        @DisplayName("16. Вставка элемента в конец заполненного списка")
        public void insertNotBreakingList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            ListIterator it = list.begin();
            it.next();
            it.next();
            it.next();
            list.insert(it, "sit");
            assertEqualsIteratorValue("sit", list.end());
            assertEquals(4, list.size());
        }

        @Test
        @DisplayName("17. Попытка использовать итераторы другого списка при вставке")
        public void insertIteratorFromOtherList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            StringList other = new StringList();
            other.pushBack("sit");
            assertThrows(Exception.class, () -> list.insert(other.begin(), "sit"));
        }
    }

    @Nested
    @DisplayName("erase element in position from iterator")
    class EraseNode {
        @Test
        @DisplayName("11. Удаление элемента из начала пустого списка")
        public void test11(){
            assertThrows(Exception.class, () -> list.erase(list.begin()));
        }

        @Test
        @DisplayName("12. Удаление элемента из конца пустого списка")
        public void test12() {
            assertThrows(Exception.class, () -> list.erase(list.end()));
        }

        @Test
        @DisplayName("13. Удаление элемента из начала списка")
        public void eraseFromFront() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.erase(list.begin());
            assertEqualsIteratorValue("ipsum", list.begin());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("14. Удаление элемента из конца списка")
        public void eraseFromBack() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.erase(list.end());
            assertEqualsIteratorValue("Lorem", list.end());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("15. Удаление элемента из середины списка")
        public void eraseFromMiddle() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            ListIterator it = list.begin();
            it.next();
            list.erase(it);
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("dolor", list.end());
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("15. Удаление пустых строк из списка")
        public void testRemoveAllEmptyStrings() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("");
            list.pushBack("ipsum");
            list.pushBack("");
            list.pushBack("dolor");
            ListIterator it = list.begin();
            for (String string : list) {
                if (string.isEmpty()) {
                    list.erase(it);
                }
                it.next();
            }
            it = list.begin();
            assertEqualsIteratorValue("Lorem", it);
            it.next();
            assertEqualsIteratorValue("ipsum", it);
            it.next();
            assertEqualsIteratorValue("dolor", it);
            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("17. Попытка использовать итераторы другого списка при удалении")
        public void eraseIteratorFromOtherList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            StringList other = new StringList();
            other.pushBack("sit");
            assertThrows(Exception.class, () -> list.erase(other.begin()));
        }
    }
}
