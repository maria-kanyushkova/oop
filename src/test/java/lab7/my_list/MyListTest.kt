package lab7.my_list

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class MyListTest {
    var stringList: MyList<String> = MyList()
    var intList: MyList<Int> = MyList()

    @BeforeEach
    fun initArrays() {
        stringList = MyList()
        intList = MyList()
    }

    @Nested
    @DisplayName("number of elements and whether node exist")
    inner class NumberOfNode {
        @Test
        @DisplayName("should be state is correct if string array is empty")
        fun stringListIsEmpty() {
            assertEquals(0, stringList.size())
        }

        @Test
        @DisplayName("should be state is correct if array is empty")
        fun intListIsEmpty() {
            assertEquals(0, intList.size())
        }

        @Test
        @DisplayName("should be state is correct if string array is not empty")
        fun stringListIsNotEmpty() {
            stringList = initArrayWithStringValue(stringList, listOf("Lorem", "ipsum"))
            assertEquals(2, stringList.size())
        }

        @Test
        @DisplayName("should be state is correct if int array is not empty")
        fun intListIsNotEmpty() {
            intList = initArrayWithIntValue(intList, listOf(1, 2))
            assertEquals(2, intList.size())
        }
    }

    @Nested
    @DisplayName("push elements to list")
    inner class AddNode {
        @Nested
        @DisplayName("push back elements to array")
        inner class PushBack {
            @Test
            @DisplayName("should to push back string in empty list")
            fun shouldToPushBackStringInEmptyArray() {
                stringList.pushBack("Lorem")
                assertEquals(1, stringList.size())
                assertEqualsIteratorValue("Lorem", stringList.begin())
                assertEqualsIteratorValue("Lorem", stringList.end())
            }

            @Test
            @DisplayName("should to push back string in list")
            fun shouldToPushBackStringInArray() {
                stringList.pushBack("Lorem")
                stringList.pushBack("ipsum")
                assertEquals(2, stringList.size())
                assertEqualsIteratorValue("Lorem", stringList.begin())
                assertEqualsIteratorValue("ipsum", stringList.end())
            }

            @Test
            @DisplayName("should to push back int in empty list")
            fun shouldToPushBackIntInEmptyArray() {
                intList.pushBack(1)
                assertEquals(1, intList.size())
                assertEqualsIteratorValue(1, intList.begin())
                assertEqualsIteratorValue(1, intList.end())
            }

            @Test
            @DisplayName("should to push back int in list")
            fun shouldToPushBackIntInArray() {
                intList.pushBack(1)
                intList.pushBack(2)
                assertEquals(2, intList.size())
                assertEqualsIteratorValue(1, intList.begin())
                assertEqualsIteratorValue(2, intList.end())
            }
        }

        @Nested
        @DisplayName("push front elements to array")
        inner class PushFront {
            @Test
            @DisplayName("should to push front string in empty list")
            fun shouldToPushFrontStringInEmptyArray() {
                stringList.pushFront("Lorem")
                assertEquals(1, stringList.size())
                assertEqualsIteratorValue("Lorem", stringList.begin())
                assertEqualsIteratorValue("Lorem", stringList.end())
            }

            @Test
            @DisplayName("should to push front string in list")
            fun shouldToPushFrontStringInArray() {
                stringList.pushFront("Lorem")
                stringList.pushFront("ipsum")
                assertEquals(2, stringList.size())
                assertEqualsIteratorValue("ipsum", stringList.begin())
                assertEqualsIteratorValue("Lorem", stringList.end())
            }

            @Test
            @DisplayName("should to push front int in empty list")
            fun shouldToPushFrontIntInEmptyArray() {
                intList.pushFront(1)
                assertEquals(1, intList.size())
                assertEqualsIteratorValue(1, intList.begin())
                assertEqualsIteratorValue(1, intList.end())
            }

            @Test
            @DisplayName("should to push front int in list")
            fun shouldToPushFrontIntInArray() {
                intList.pushFront(1)
                intList.pushFront(2)
                assertEquals(2, intList.size())
                assertEqualsIteratorValue(2, intList.begin())
                assertEqualsIteratorValue(1, intList.end())
            }
        }
    }

    @Nested
    @DisplayName("iterators")
    inner class Iterators {
        @Test
        @DisplayName("should be correct iterators if is empty array")
        fun shouldBeCorrectValueIfIsEmptyArray() {
            assertNullStringIterator(stringList.begin())
            assertNullStringIterator(stringList.end())
            assertNullStringIterator(stringList.rbegin())
            assertNullStringIterator(stringList.rend())

            assertNullIntIterator(intList.begin())
            assertNullIntIterator(intList.end())
            assertNullIntIterator(intList.rbegin())
            assertNullIntIterator(intList.rend())
        }

        @Test
        @DisplayName("should be correct string iterators if array have one element")
        fun shouldBeCorrectStringIteratorsIfArrayHaveOneElement() {
            stringList.pushBack("Lorem")
            assertEqualsStringIterators(stringList.begin(), stringList.end())
            assertEqualsStringIterators(stringList.begin(), stringList.rbegin())
            assertEqualsStringIterators(stringList.end(), stringList.rend())
        }

        @Test
        @DisplayName("should be correct int iterators if array have one element")
        fun shouldBeCorrectIntIteratorsIfArrayHaveOneElement() {
            intList.pushBack(1)
            assertEqualsIntIterators(intList.begin(), intList.end())
            assertEqualsIntIterators(intList.begin(), intList.rbegin())
            assertEqualsIntIterators(intList.end(), intList.rend())
        }

        @Test
        @DisplayName("should be correct string iterators in array")
        fun shouldBeCorrectStringIteratorsInArray() {
            stringList = initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            assertNotEqualsStringIterators(stringList.begin(), stringList.rbegin())
            assertNotEqualsStringIterators(stringList.end(), stringList.rend())
            assertEqualsStringIterators(stringList.begin(), stringList.rend())
            assertEqualsStringIterators(stringList.end(), stringList.rbegin())
        }

        @Test
        @DisplayName("should be correct iterators in array")
        fun shouldBeCorrectIntIteratorsInArray() {
            intList = initArrayWithIntValue(intList, listOf(1, 2, 3))
            assertNotEqualsIntIterators(intList.begin(), intList.rbegin())
            assertNotEqualsIntIterators(intList.end(), intList.rend())
            assertEqualsIntIterators(intList.begin(), intList.rend())
            assertEqualsIntIterators(intList.end(), intList.rbegin())
        }

        @Test
        @DisplayName("should be iterable string iterator in array")
        fun shouldBeIterableStringIteratorInArray() {
            stringList = initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            var counter = 0
            val it: ListIterator<String> = stringList.begin()
            for (string in stringList) {
                MatcherAssert.assertThat(string, CoreMatchers.`is`(it.getCurrent()))
                it.next()
                counter++
            }
            assertEquals(stringList.size(), counter)
        }

        @Test
        @DisplayName("should be iterable int iterator in array")
        fun shouldBeIterableIntIteratorInArray() {
            intList = initArrayWithIntValue(intList, listOf(1, 2, 3))
            var counter = 0
            val it: ListIterator<Int> = intList.begin()
            for (string in intList) {
                MatcherAssert.assertThat(string, CoreMatchers.`is`(it.getCurrent()))
                it.next()
                counter++
            }
            assertEquals(intList.size(), counter)
        }

        @Test
        @DisplayName("should be iterable reverse string iterator in array")
        fun shouldBeIterableReverseStringIteratorInArray() {
            stringList = initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val it: ListIterator<String> = stringList.rbegin()
            assertStringValues(listOf("dolor", "ipsum", "Lorem"), it)
        }

        @Test
        @DisplayName("should be iterable reverse int iterator in array")
        fun shouldBeIterableReverseIntIteratorInArray() {
            intList = initArrayWithIntValue(intList, listOf(1, 2, 3))
            val it: ListIterator<Int> = intList.rbegin()
            assertIntValues(listOf(3, 2, 1), it)
        }
    }

    @Nested
    @DisplayName("insert element into position from iterator")
    inner class InsertNode {
        @Test
        @DisplayName("should to insert element to start position in empty string list")
        fun shouldToInsertElementToStartPositionInEmptyStringList() {
            stringList.insert(stringList.begin(), "Lorem")
            assertEqualsIteratorValue("Lorem", stringList.begin())
        }

        @Test
        @DisplayName("should to insert element to start position in empty int list")
        fun shouldToInsertElementToStartPositionInEmptyIntList() {
            intList.insert(intList.begin(), 1)
            assertEqualsIteratorValue(1, intList.begin())
        }

        @Test
        @DisplayName("should to insert element to end position in empty string list")
        fun shouldToInsertElementToEndPositionInEmptyStringList() {
            stringList.insert(stringList.end(), "Lorem")
            assertEqualsIteratorValue("Lorem", stringList.end())
        }

        @Test
        @DisplayName("should to insert element to end position in empty int list")
        fun shouldToInsertElementToEndPositionInEmptyIntList() {
            intList.insert(intList.end(), 1)
            assertEqualsIteratorValue(1, intList.end())
        }

        @Test
        @DisplayName("should to insert element to string list")
        fun shouldToInsertElementToStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "dolor"))
            val it: ListIterator<String> = stringList.begin()
            it.next()
            stringList.insert(it, "ipsum")
            assertStringValues(listOf("Lorem", "ipsum", "dolor"), stringList.begin())
        }

        @Test
        @DisplayName("should to insert element to int list")
        fun shouldToInsertElementToIntList() {
            initArrayWithIntValue(intList, listOf(1, 3))
            val it: ListIterator<Int> = intList.begin()
            it.next()
            intList.insert(it, 2)
            assertIntValues(listOf(1, 2, 3), intList.begin())
        }

        @Test
        @DisplayName("should to insert element to start position in string list")
        fun shouldToInsertElementToStartPositionInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            stringList.insert(stringList.begin(), "sit")
            assertStringValues(listOf("sit", "Lorem", "ipsum", "dolor"), stringList.begin())
        }

        @Test
        @DisplayName("should to insert element to start position in int list")
        fun shouldToInsertElementToStartPositionInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            intList.insert(intList.begin(), 4)
            assertIntValues(listOf(4, 1, 2, 3), intList.begin())
        }

        @Test
        @DisplayName("should to insert element to end position in string list")
        fun shouldToInsertElementToEndPositionInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val it: ListIterator<String> = stringList.begin()
            it.next()
            it.next()
            stringList.insert(it, "sit")
            assertStringValues(listOf("Lorem", "ipsum", "sit", "dolor"), stringList.begin())
        }

        @Test
        @DisplayName("should to insert element to end position in int list")
        fun shouldToInsertElementToEndPositionInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            val it: ListIterator<Int> = intList.begin()
            it.next()
            it.next()
            intList.insert(it, 4)
            assertIntValues(listOf(1, 2, 4, 3), intList.begin())
        }

        @Test
        @DisplayName("should to insert element to end position to next element in string list")
        fun shouldToInsertElementToEndPositionToNextElementInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val it: ListIterator<String> = stringList.begin()
            it.next()
            it.next()
            it.next()
            stringList.insert(it, "sit")
            assertEqualsIteratorValue("sit", stringList.end())
            assertEquals(4, stringList.size())
        }

        @Test
        @DisplayName("should to insert element to end position to next element in int list")
        fun shouldToInsertElementToEndPositionToNextElementInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            val it: ListIterator<Int> = intList.begin()
            it.next()
            it.next()
            it.next()
            intList.insert(it, 4)
            assertEqualsIteratorValue(4, intList.end())
            assertEquals(4, intList.size())
        }

        @Test
        @DisplayName("try insert value with other iterators string list")
        fun tryInsertValueWithOtherIteratorsStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val other = MyList<String>()
            other.pushBack("sit")
            Assertions.assertThrows(Exception::class.java) { stringList.insert(other.begin(), "sit") }
        }

        @Test
        @DisplayName("try insert value with other iterators int list")
        fun tryInsertValueWithOtherIteratorsIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            val other = MyList<Int>()
            other.pushBack(4)
            Assertions.assertThrows(Exception::class.java) { intList.insert(other.begin(), 4) }
        }
    }

    @Nested
    @DisplayName("erase element in position from iterator")
    inner class EraseNode {
        @Test
        @DisplayName("should to erase element from start position in empty string list")
        fun shouldToEraseElementFromStartPositionInEmptyStringList() {
            Assertions.assertThrows(Exception::class.java) { stringList.erase(stringList.begin()) }
        }

        @Test
        @DisplayName("should to erase element from start position in empty int list")
        fun shouldToEraseElementFromStartPositionInEmptyIntList() {
            Assertions.assertThrows(Exception::class.java) { intList.erase(intList.begin()) }
        }

        @Test
        @DisplayName("should to erase element from end position in empty string list")
        fun shouldToEraseElementFromEndPositionInEmptyStringList() {
            Assertions.assertThrows(Exception::class.java) { stringList.erase(stringList.end()) }
        }

        @Test
        @DisplayName("should to erase element from end position in empty int list")
        fun shouldToEraseElementFromEndPositionInEmptyIntList() {
            Assertions.assertThrows(Exception::class.java) { intList.erase(intList.end()) }
        }

        @Test
        @DisplayName("should to erase element from start position in string list")
        fun shouldToEraseElementFromStartPositionInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum"))
            stringList.erase(stringList.begin())
            assertEqualsIteratorValue("ipsum", stringList.begin())
            assertEquals(1, stringList.size())
        }

        @Test
        @DisplayName("should to erase element from start position in int list")
        fun shouldToEraseElementFromStartPositionInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2))
            intList.erase(intList.begin())
            assertEqualsIteratorValue(2, intList.begin())
            assertEquals(1, intList.size())
        }

        @Test
        @DisplayName("should to erase element from end position in string list")
        fun shouldToEraseElementFromEndPositionInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum"))
            stringList.erase(stringList.end())
            assertEqualsIteratorValue("Lorem", stringList.end())
            assertEquals(1, stringList.size())
        }

        @Test
        @DisplayName("should to erase element from end position in int list")
        fun shouldToEraseElementFromEndPositionInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2))
            intList.erase(intList.end())
            assertEqualsIteratorValue(1, intList.end())
            assertEquals(1, intList.size())
        }

        @Test
        @DisplayName("should to erase element in string list")
        fun shouldToEraseElementInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val it: ListIterator<String> = stringList.begin()
            it.next()
            stringList.erase(it)
            assertEqualsIteratorValue("Lorem", stringList.begin())
            assertEqualsIteratorValue("dolor", stringList.end())
            assertEquals(2, stringList.size())
        }

        @Test
        @DisplayName("should to erase element in int list")
        fun shouldToEraseElementInIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            val it: ListIterator<Int> = intList.begin()
            it.next()
            intList.erase(it)
            assertEqualsIteratorValue(1, intList.begin())
            assertEqualsIteratorValue(3, intList.end())
            assertEquals(2, intList.size())
        }

        @Test
        @DisplayName("should to erase empty in string list")
        fun shouldToEraseEmptyInStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "", "ipsum", "", "dolor"))
            val it: ListIterator<String> = stringList.begin()
            for (string in stringList) {
                if (string.isEmpty()) {
                    stringList.erase(it)
                }
                it.next()
            }
            assertStringValues(listOf("Lorem", "ipsum", "dolor"), stringList.begin())
            assertEquals(3, stringList.size())
        }

        @Test
        @DisplayName("should to erase empty in int list")
        fun shouldToEraseEmptyInIntList() {
            initArrayWithIntValue(intList, listOf(1, -1, 2, -1, 3))
            val it: ListIterator<Int> = intList.begin()
            for (int in intList) {
                if (int < 0) {
                    intList.erase(it)
                }
                it.next()
            }
            assertIntValues(listOf(1, 2, 3), intList.begin())
            assertEquals(3, intList.size())
        }

        @Test
        @DisplayName("try erase value with other iterators string list")
        fun tryEraseValueWithOtherIteratorsStringList() {
            initArrayWithStringValue(stringList, listOf("Lorem", "ipsum", "dolor"))
            val other = MyList<String>()
            other.pushBack("sit")
            Assertions.assertThrows(Exception::class.java) { stringList.erase(other.begin()) }
        }

        @Test
        @DisplayName("try erase value with other iterators int list")
        fun tryEraseValueWithOtherIteratorsIntList() {
            initArrayWithIntValue(intList, listOf(1, 2, 3))
            val other = MyList<Int>()
            other.pushBack(4)
            Assertions.assertThrows(Exception::class.java) { intList.erase(other.begin()) }
        }
    }
}