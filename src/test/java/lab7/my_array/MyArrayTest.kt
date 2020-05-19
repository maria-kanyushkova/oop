package lab7.my_array

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class MyArrayTest {
    var stringArray: MyArray<String> = MyArray()
    var doubleArray: MyArray<Double> = MyArray()

    @BeforeEach
    fun initArrays() {
        stringArray = MyArray()
        doubleArray = MyArray()
    }

    @Nested
    @DisplayName("number of elements and whether node exist")
    inner class NumberOfNode {
        @Test
        @DisplayName("should be state is correct if string array is empty")
        fun stringArrayIsEmpty() {
            assertEquals(0, stringArray.size())
        }

        @Test
        @DisplayName("should be state is correct if array is empty")
        fun doubleArrayIsEmpty() {
            assertEquals(0, doubleArray.size())
        }

        @Test
        @DisplayName("should be state is correct if string array is not empty")
        fun stringArrayIsNotEmpty() {
            stringArray = initArrayWithStringValue(stringArray, listOf("Lorem", "ipsum"))
            assertEquals(2, stringArray.size())
        }

        @Test
        @DisplayName("should be state is correct if double array is not empty")
        fun doubleArrayIsNotEmpty() {
            doubleArray = initArrayWithDoubleValue(doubleArray, listOf(0.00001, 0.00002))
            assertEquals(2, doubleArray.size())
        }
    }

    @Nested
    @DisplayName("push elements to array")
    inner class PushNode {
        @Test
        @DisplayName("should to push string in empty list")
        fun shouldToPushStringInEmptyArray() {
            stringArray.push("Lorem")
            assertEquals(1, stringArray.size())
            assertEqualsIteratorValue("Lorem", stringArray.begin())
            assertEqualsIteratorValue("Lorem", stringArray.end())
        }

        @Test
        @DisplayName("should to push string in list")
        fun shouldToPushStringInArray() {
            stringArray.push("Lorem")
            stringArray.push("ipsum")
            assertEquals(2, stringArray.size())
            assertEqualsIteratorValue("Lorem", stringArray.begin())
            assertEqualsIteratorValue("ipsum", stringArray.end())
        }

        @Test
        @DisplayName("should to push double in empty list")
        fun shouldToPushDoubleInEmptyArray() {
            doubleArray.push(0.00001)
            assertEquals(1, doubleArray.size())
            assertEqualsIteratorValue(0.00001, doubleArray.begin())
            assertEqualsIteratorValue(0.00001, doubleArray.end())
        }

        @Test
        @DisplayName("should to push double in list")
        fun shouldToPushDoubleInArray() {
            doubleArray.push(0.00001)
            doubleArray.push(0.00002)
            assertEquals(2, doubleArray.size())
            assertEqualsIteratorValue(0.00001, doubleArray.begin())
            assertEqualsIteratorValue(0.00002, doubleArray.end())
        }
    }

    @Nested
    @DisplayName("iterators")
    inner class Iterators {
        @Test
        @DisplayName("should be correct iterators if is empty array")
        fun shouldBeCorrectValueIfIsEmptyArray() {
            assertNullStringIterator(stringArray.begin())
            assertNullStringIterator(stringArray.end())
            assertNullStringIterator(stringArray.rbegin())
            assertNullStringIterator(stringArray.rend())

            assertNullDoubleIterator(doubleArray.begin())
            assertNullDoubleIterator(doubleArray.end())
            assertNullDoubleIterator(doubleArray.rbegin())
            assertNullDoubleIterator(doubleArray.rend())
        }

        @Test
        @DisplayName("should be correct string iterators if array have one element")
        fun shouldBeCorrectStringIteratorsIfArrayHaveOneElement() {
            stringArray.push("Lorem")
            assertEqualsStringIterators(stringArray.begin(), stringArray.end())
            assertEqualsStringIterators(stringArray.begin(), stringArray.rbegin())
            assertEqualsStringIterators(stringArray.end(), stringArray.rend())
        }

        @Test
        @DisplayName("should be correct double iterators if array have one element")
        fun shouldBeCorrectDoubleIteratorsIfArrayHaveOneElement() {
            doubleArray.push(0.00001)
            assertEqualsDoubleIterators(doubleArray.begin(), doubleArray.end())
            assertEqualsDoubleIterators(doubleArray.begin(), doubleArray.rbegin())
            assertEqualsDoubleIterators(doubleArray.end(), doubleArray.rend())
        }

        @Test
        @DisplayName("should be correct string iterators in array")
        fun shouldBeCorrectStringIteratorsInArray() {
            stringArray = initArrayWithStringValue(stringArray, listOf("Lorem", "ipsum", "dolor"))
            assertNotEqualsStringIterators(stringArray.begin(), stringArray.rbegin())
            assertNotEqualsStringIterators(stringArray.end(), stringArray.rend())
            assertEqualsStringIterators(stringArray.begin(), stringArray.rend())
            assertEqualsStringIterators(stringArray.end(), stringArray.rbegin())
        }

        @Test
        @DisplayName("should be correct iterators in array")
        fun shouldBeCorrectDoubleIteratorsInArray() {
            doubleArray = initArrayWithDoubleValue(doubleArray, listOf(0.00001, 0.00002, 0.00003))
            assertNotEqualsDoubleIterators(doubleArray.begin(), doubleArray.rbegin())
            assertNotEqualsDoubleIterators(doubleArray.end(), doubleArray.rend())
            assertEqualsDoubleIterators(doubleArray.begin(), doubleArray.rend())
            assertEqualsDoubleIterators(doubleArray.end(), doubleArray.rbegin())
        }

        @Test
        @DisplayName("should be iterable string iterator in array")
        fun shouldBeIterableStringIteratorInArray() {
            stringArray = initArrayWithStringValue(stringArray, listOf("Lorem", "ipsum", "dolor"))
            var counter = 0
            val it: ArrayIterator<String> = stringArray.begin()
            for (string in stringArray) {
                MatcherAssert.assertThat(string, CoreMatchers.`is`(it.getCurrent()))
                it.next()
                counter++
            }
            assertEquals(stringArray.size(), counter)
        }

        @Test
        @DisplayName("should be iterable double iterator in array")
        fun shouldBeIterableDoubleIteratorInArray() {
            doubleArray = initArrayWithDoubleValue(doubleArray, listOf(0.00001, 0.00002, 0.00003))
            var counter = 0
            val it: ArrayIterator<Double> = doubleArray.begin()
            for (string in doubleArray) {
                MatcherAssert.assertThat(string, CoreMatchers.`is`(it.getCurrent()))
                it.next()
                counter++
            }
            assertEquals(doubleArray.size(), counter)
        }

        @Test
        @DisplayName("should be iterable reverse string iterator in array")
        fun shouldBeIterableReverseStringIteratorInArray() {
            stringArray = initArrayWithStringValue(stringArray, listOf("Lorem", "ipsum", "dolor"))
            val it: ArrayIterator<String> = stringArray.rbegin()
            assertStringValues(listOf("dolor", "ipsum", "Lorem"), it)
        }

        @Test
        @DisplayName("should be iterable reverse double iterator in array")
        fun shouldBeIterableReverseDoubleIteratorInArray() {
            doubleArray = initArrayWithDoubleValue(doubleArray, listOf(0.00001, 0.00002, 0.00003))
            val it: ArrayIterator<Double> = doubleArray.rbegin()
            assertDoubleValues(listOf(0.00003, 0.00002, 0.00001), it)
        }
    }

    @Nested
    @DisplayName("delete all elements")
    inner class DeleteAllNodes {
        @Test
        @DisplayName("should be cleared empty string array")
        fun shouldBeClearedEmptyStringArray() {
            stringArray.clear()
            assertEquals(0, stringArray.size())
        }

        @Test
        @DisplayName("should be cleared empty double list")
        fun shouldBeClearedEmptyDoubleArray() {
            doubleArray.clear()
            assertEquals(0, doubleArray.size())
        }

        @Test
        @DisplayName("should be cleared string array")
        fun shouldBeClearedStringArray() {
            stringArray = initArrayWithStringValue(stringArray, listOf("Lorem", "ipsum", "dolor"))
            stringArray.clear()
            assertEquals(0, stringArray.size())
        }

        @Test
        @DisplayName("should be cleared double array")
        fun shouldBeClearedDoubleArray() {
            doubleArray = initArrayWithDoubleValue(doubleArray, listOf(0.00001, 0.00002, 0.00003))
            doubleArray.clear()
            assertEquals(0, doubleArray.size())
        }
    }
}