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
        @DisplayName("should be state is correct if list is empty")
        public void listIsEmpty() {
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("should be state is correct if list is not empty")
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
        @DisplayName("should to push back element in empty list")
        public void shouldToPushBackElementInEmptyList() {
            list.pushBack("Lorem");
            assertEquals(1, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("should to push back element in list")
        public void shouldToPushBackElementInList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            assertEquals(2, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("ipsum", list.end());
        }

        @Test
        @DisplayName("should to push front element in empty list")
        public void shouldToPushFrontElementInEmptyList() {
            list.pushFront("Lorem");
            assertEquals(1, list.size());
            assertEqualsIteratorValue("Lorem", list.begin());
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("should to push front element in list")
        public void shouldToPushFrontElementInList() {
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
        @DisplayName("should be correct iterators if is empty list")
        public void shouldBeCorrectValueIfIsEmptyList() {
            assertNullIterator(list.begin());
            assertNullIterator(list.end());
            assertNullIterator(list.rbegin());
            assertNullIterator(list.rend());
        }

        @Test
        @DisplayName("should be correct iterators if list have one element")
        public void shouldBeCorrectIteratorsIfListHaveOneElement() {
            list.pushBack("Lorem");
            assertEqualsIterators(list.begin(), list.end());
            assertEqualsIterators(list.begin(), list.rbegin());
            assertEqualsIterators(list.end(), list.rend());
        }

        @Test
        @DisplayName("should be correct iterators in list")
        public void shouldBeCorrectIteratorsInList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            assertNotEqualsIterators(list.begin(), list.rbegin());
            assertNotEqualsIterators(list.end(), list.rend());
            assertEqualsIterators(list.begin(), list.rend());
            assertEqualsIterators(list.end(), list.rbegin());
        }

        @Test
        @DisplayName("should be iterable iterator in list")
        public void shouldBeIterableIteratorInList() {
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
        @DisplayName("should be iterable reverse iterator in list")
        public void shouldBeIterableReverseIteratorInList() {
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
        @DisplayName("should be cleared empty list")
        public void shouldBeClearedEmptyList() {
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("should be cleared list")
        public void shouldBeClearedList() {
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
        @DisplayName("should to insert element to start position in empty list")
        public void shouldToInsertElementToStartPositionInEmptyList() throws Exception {
            list.insert(list.begin(), "Lorem");
            assertEqualsIteratorValue("Lorem", list.begin());
        }

        @Test
        @DisplayName("should to insert element to end position in empty list")
        public void shouldToInsertElementToEndPositionInEmptyList() throws Exception {
            list.insert(list.end(), "Lorem");
            assertEqualsIteratorValue("Lorem", list.end());
        }

        @Test
        @DisplayName("should to insert element to list")
        public void shouldToInsertElementToList() throws Exception {
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
        @DisplayName("should to insert element to start position in list")
        public void shouldToInsertElementToStartPositionInList() throws Exception {
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
        @DisplayName("should to insert element to end position in list")
        public void shouldToInsertElementToEndPositionInList() throws Exception {
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
        @DisplayName("should to insert element to end position to next element in list")
        public void shouldToInsertElementToEndPositionToNextElementInList() throws Exception {
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
        @DisplayName("try insert value with other iterators list")
        public void tryInsertValueWithOtherIteratorsList() {
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
        @DisplayName("should to erase element from start position in empty list")
        public void shouldToEraseElementFromStartPositionInEmptyList(){
            assertThrows(Exception.class, () -> list.erase(list.begin()));
        }

        @Test
        @DisplayName("should to erase element from end position in empty list")
        public void shouldToEraseElementFromEndPositionInEmptyList() {
            assertThrows(Exception.class, () -> list.erase(list.end()));
        }

        @Test
        @DisplayName("should to erase element from start position in list")
        public void shouldToEraseElementFromStartPositionInList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.erase(list.begin());
            assertEqualsIteratorValue("ipsum", list.begin());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("should to erase element from end position in list")
        public void shouldToEraseElementFromEndPositionInList() throws Exception {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.erase(list.end());
            assertEqualsIteratorValue("Lorem", list.end());
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("should to erase element in list")
        public void shouldToEraseElementInList() throws Exception {
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
        @DisplayName("should to erase empty string in list")
        public void shouldToEraseEmptyStringInList() throws Exception {
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
        @DisplayName("try erase value with other iterators list")
        public void tryEraseValueWithOtherIteratorsList() {
            list.pushBack("Lorem");
            list.pushBack("ipsum");
            list.pushBack("dolor");
            StringList other = new StringList();
            other.pushBack("sit");
            assertThrows(Exception.class, () -> list.erase(other.begin()));
        }
    }
}
