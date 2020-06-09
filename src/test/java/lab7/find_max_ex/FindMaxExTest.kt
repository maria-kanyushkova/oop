package lab7.find_max_ex

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class FindMaxKtTest {
    data class People(val name: String, val growth: Double, val weight: Double)

    fun getMockPeopleList(): ArrayList<People> {
        return arrayListOf(
                People("Cecil Shaeffer", 200.0, 198.0),
                People("Obdulia Belisle", 175.0, 65.0),
                People("Graig Herriman", 168.0, 50.0)
        )
    }

    @Nested
    @DisplayName("findMax")
    inner class FindMax {
        @Test
        @DisplayName("should be correct value in empty array")
        fun shouldBeCorrectValueInEmptyArray() {
            val array = arrayListOf<Int>()
            val max = findMax(array)
            assertNull(max)
        }

        @Test
        @DisplayName("should be correct value in integers array at start")
        fun shouldBeCorrectValueInIntegersArrayAtStart() {
            val array = arrayListOf(10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
            val max = findMax(array)
            assertEquals(10, max)
        }

        @Test
        @DisplayName("should be correct value in integers array at middle")
        fun shouldBeCorrectValueInIntegersArrayAtMiddle() {
            val array = arrayListOf(0, 1, 2, 3, 4, 10, 5, 6, 7, 8, 9)
            val max = findMax(array)
            assertEquals(10, max)
        }

        @Test
        @DisplayName("should be correct value in integers array at end")
        fun shouldBeCorrectValueInIntegersArrayAtEnd() {
            val array = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            val max = findMax(array)
            assertEquals(10, max)
        }

        @Test
        @DisplayName("should be correct value in double array at start")
        fun shouldBeCorrectValueInDoubleArrayAtStart() {
            val array = arrayListOf(0.000005, 0.000001, 0.000002, 0.000003, 0.000004)
            val max = findMax(array)
            assertEquals(0.000005, max)
        }

        @Test
        @DisplayName("should be correct value in double array at middle")
        fun shouldBeCorrectValueInDoubleArrayAtMiddle() {
            val array = arrayListOf(0.000001, 0.000002, 0.000005, 0.000003, 0.000004)
            val max = findMax(array)
            assertEquals(0.000005, max)
        }

        @Test
        @DisplayName("should be correct value in double array at end")
        fun shouldBeCorrectValueInDoubleArrayAtEnd() {
            val array = arrayListOf(0.000001, 0.000002, 0.000003, 0.000004, 0.000005)
            val max = findMax(array)
            assertEquals(0.000005, max)
        }

        @Test
        @DisplayName("should be correct value in string array at start")
        fun shouldBeCorrectValueInStringArrayAtStart() {
            val array = arrayListOf("abcdefg", "aaaaaaa", "abcdeff", "abcdeee", "abcdddd", "abccccc", "abbbbbb")
            val max = findMax(array)
            assertEquals("abcdefg", max)
        }

        @Test
        @DisplayName("should be correct value in string array at middle")
        fun shouldBeCorrectValueInStringArrayAtMiddle() {
            val array = arrayListOf("aaaaaaa", "abcdeff", "abcdeee", "abcdefg", "abcdddd", "abccccc", "abbbbbb")
            val max = findMax(array)
            assertEquals("abcdefg", max)
        }

        @Test
        @DisplayName("should be correct value in string array at end")
        fun shouldBeCorrectValueInStringArrayAtEnd() {
            val array = arrayListOf("abcdeff", "abcdeee", "abcdddd", "abccccc", "abbbbbb", "aaaaaaa", "abcdefg")
            val max = findMax(array)
            assertEquals("abcdefg", max)
        }

        @Test
        @DisplayName("should be correct value in char array at start")
        fun shouldBeCorrectValueInCharArrayAtStart() {
            val array = arrayListOf("j", "b", "c", "d", "e", "f", "a")
            val max = findMax(array)
            assertEquals("j", max)
        }

        @Test
        @DisplayName("should be correct value in char array at middle")
        fun shouldBeCorrectValueICharArrayAtMiddle() {
            val array = arrayListOf("a", "b", "j", "d", "e", "f", "c")
            val max = findMax(array)
            assertEquals("j", max)
        }

        @Test
        @DisplayName("should be correct value in char array at end")
        fun shouldBeCorrectValueInCharArrayAtEnd() {
            val array = arrayListOf("a", "b", "c", "d", "e", "f", "j")
            val max = findMax(array)
            assertEquals("j", max)
        }
    }

    @Nested
    @DisplayName("findMaxEx")
    inner class FindMaxEx {
        @Test
        @DisplayName("should be correct value in empty array")
        fun shouldBeCorrectValueInEmptyArray() {
            val array = arrayListOf<People>()
            val max = findMaxEx(array, { max: People, current: People -> max.growth <= current.growth })
            assertNull(max)
        }

        @Test
        @DisplayName("should be correct value in types array by name")
        fun shouldBeCorrectValueInTypesArrayByName() {
            val array = getMockPeopleList()
            val max = findMaxEx(array, { max: People, current: People -> max.name <= current.name })
            assertNotNull(max)
            assertEquals(max.name, "Obdulia Belisle")
        }

        @Test
        @DisplayName("should be correct value in types array by growth")
        fun shouldBeCorrectValueInTypesArrayByGrowth() {
            val array = getMockPeopleList()
            val max = findMaxEx(array, { max: People, current: People -> max.growth <= current.growth })
            assertNotNull(max)
            assertEquals(max.name, "Cecil Shaeffer")
        }

        @Test
        @DisplayName("should be correct value in types array by weight")
        fun shouldBeCorrectValueInTypesArrayByWeight() {
            val array = getMockPeopleList()
            val min = findMaxEx(array, { max: People, current: People -> max.weight >= current.weight })
            assertNotNull(min)
            assertEquals(min.name, "Graig Herriman")
        }
    }
}