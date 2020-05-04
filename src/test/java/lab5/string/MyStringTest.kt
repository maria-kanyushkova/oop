package lab5.string

import common.OutputMock
import common.OutputMock.setSystemInput
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.System.`in`
import java.lang.System.out

class MyStringTest {
    private val NULL_CHAR = '\u0000'

    private fun getMockArray(): Array<Char> {
        return arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
    }

    private fun getMockString(): String {
        return "Suum cuique"
    }

    private fun toCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }

    @Nested
    @DisplayName("")
    inner class Constructor {
        @Nested
        @DisplayName("")
        inner class CharArray {
            @Test
            @DisplayName("")
            fun `constructor(chars) has correct data`() {
                val chars = getMockArray()
                val actual = MyString(chars)
                val expected = chars + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(chars) has correct length`() {
                val chars = getMockArray()
                val actual = MyString(chars)
                val expected = chars.size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("")
            fun `constructor(chars) with null char in middle has correct length`() {
                val chars = arrayOf('S', 'u', 'u', 'm', NULL_CHAR, 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(chars)
                val expected = chars.size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("")
            fun `constructor(chars) with null char in middle has correct data`() {
                val chars = arrayOf('S', 'u', 'u', 'm', NULL_CHAR, 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(chars)
                val expected = chars + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }
        }

        @Nested
        @DisplayName("")
        inner class CharArrayWithLength {
            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct data`() {
                val chars = getMockArray()
                val actual = MyString(chars, chars.size)
                val expected = chars + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct length`() {
                val chars = getMockArray()
                val actual = MyString(chars, chars.size)
                val expected = chars.size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct data if length is less then chars`() {
                val chars = getMockArray()
                val actual = MyString(chars, chars.size - 7)
                val expected = arrayOf('S', 'u', 'u', 'm', NULL_CHAR)
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct length if length is less then chars`() {
                val chars = getMockArray()
                val actual = MyString(chars, chars.size - 7)
                val expected = arrayOf('S', 'u', 'u', 'm').size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct data if length is 0`() {
                val chars = getMockArray()
                val actual = MyString(chars, 0)
                val expected = arrayOf(NULL_CHAR)
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) has correct length if length is 0`() {
                val chars = getMockArray()
                val actual = MyString(chars, 0)
                val expected = emptyArray<Char>().size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) throws if length is negative`() {
                val chars = getMockArray()
                assertThrows<StringIndexOutOfBoundsException> { MyString(chars, -chars.size) }
            }

            @Test
            @DisplayName("")
            fun `constructor(chars, length) throws if length is more then string has chars`() {
                val chars = getMockArray()
                assertThrows<StringIndexOutOfBoundsException> { MyString(chars, 2 * chars.size) }
            }
        }

        @Nested
        @DisplayName("")
        inner class String {
            @Test
            @DisplayName("")
            fun `constructor(string) has correct data`() {
                val actual = MyString(getMockString())
                val expected = getMockString() + NULL_CHAR
                assertThat(toCharArray(expected), `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(string) has correct length`() {
                val actual = MyString(getMockString())
                val expected = getMockString().length
                assertEquals(actual.getLength(), expected)
            }
        }

        @Nested
        @DisplayName("")
        inner class MyString {
            @Test
            @DisplayName("")
            fun `constructor(MyString) has correct data`() {
                val chars = getMockArray()
                val string = MyString(chars)
                val actual = MyString(string)
                val expected = chars + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("")
            fun `constructor(MyString) has correct length`() {
                val chars = getMockArray()
                val string = MyString(chars)
                val actual = MyString(string)
                val expected = chars.size
                assertEquals(actual.getLength(), expected)
            }
        }
    }

    @Nested
    @DisplayName("")
    inner class MethodSubstring {
        @Test
        @DisplayName("")
        fun `substring has correct data`() {
            val string = MyString(getMockString())
            val actual = string.substring(2, 5)
            val chars = getMockString().substring(2, 3 + 4)
            val expected = MyString(chars)
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `substring has correct length`() {
            val string = MyString(getMockString())
            val actual = string.substring(2, 5)
            val chars = getMockString().substring(2, 3 + 4)
            val expected = MyString(chars)
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }

        @Test
        @DisplayName("")
        fun `substring has correct data with zero length`() {
            val string = MyString(getMockString())
            val actual = string.substring(2, 0)
            val chars = emptyArray<Char>()
            val expected = MyString(chars)
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `substring has correct length with zero length`() {
            val string = MyString(getMockString())
            val actual = string.substring(2, 0)
            val chars = emptyArray<Char>()
            val expected = MyString(chars)
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }

        @Test
        @DisplayName("")
        fun `substring throws error if out of range`() {
            val actual = MyString(getMockString())
            assertThrows<StringIndexOutOfBoundsException> { actual.substring(7, 5) }
        }

        @Test
        @DisplayName("")
        fun `substring throws error if length is negative`() {
            val actual = MyString(getMockString())
            assertThrows<StringIndexOutOfBoundsException> { actual.substring(2, -5) }
        }
    }

    @Nested
    @DisplayName("")
    inner class MethodClear {
        @Test
        @DisplayName("")
        fun `clear data`() {
            val actual = MyString(getMockString())
            actual.clear()
            val expected = MyString()
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `clear length`() {
            val actual = MyString(getMockString())
            actual.clear()
            val expected = MyString()
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorPlus {
        @Test
        @DisplayName("")
        fun `plus has correct data with MyString`() {
            val string = MyString(getMockString())
            val other = MyString(getMockString())
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `plus has correct length with MyString`() {
            val string = MyString(getMockString())
            val other = MyString(getMockString())
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString()).size
            assertEquals(actual.getLength(), expected)
        }

        @Test
        @DisplayName("")
        fun `plus has correct data with String`() {
            val string = MyString(getMockString())
            val other = getMockString()
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `plus has correct length with String`() {
            val string = MyString(getMockString())
            val other = getMockString()
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString()).size
            assertEquals(actual.getLength(), expected)
        }

        @Test
        @DisplayName("")
        fun `plus has correct data with Chars`() {
            val string = MyString(getMockString())
            val other = getMockArray()
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `plus has correct length with Chars`() {
            val string = MyString(getMockString())
            val other = getMockArray()
            val actual = string + other
            val expected = toCharArray(getMockString() + getMockString()).size
            assertEquals(actual.getLength(), expected)
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorPlusAssign {
        @Test
        @DisplayName("")
        fun `plus assign has correct data`() {
            val actual = MyString(getMockString())
            val other = MyString(getMockString())
            actual += other
            val expected = toCharArray(getMockString() + getMockString() + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("")
        fun `plus assign has correct length`() {
            val actual = MyString(getMockString())
            val other = MyString(getMockString())
            actual += other
            val expected = toCharArray(getMockString() + getMockString()).size
            assertEquals(actual.getLength(), expected)
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorEqual {
        @Test
        @DisplayName("")
        fun `equal with MyString`() {
            val string = MyString(getMockString())
            val other = MyString(getMockString())
            assertTrue(string == other)
            assertFalse(string != other)
        }

        @Test
        @DisplayName("")
        fun `equal with String`() {
            val string = MyString(getMockString())
            val other = getMockString()
            assertTrue(string.equals(other))
            assertFalse(!string.equals(other))
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorNotEqual {
        @Test
        @DisplayName("")
        fun `not equal with MyString`() {
            val string = MyString(getMockString())
            val other = MyString(getMockString() + getMockString())
            assertTrue(string != other)
            assertFalse(string == other)
        }

        @Test
        @DisplayName("")
        fun `not equal with String`() {
            val string = MyString(getMockString())
            val other = getMockString() + getMockString()
            assertTrue(!string.equals(other))
            assertFalse(string.equals(other))
        }

        @Test
        @DisplayName("")
        fun `not equal with any other type`() {
            val string = MyString(getMockString())
            assertFalse(string.equals(10))
            assertFalse(string.equals(null))
            assertFalse(string.equals(true))
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorLessMore {
        @Test
        @DisplayName("")
        fun `less more with MyString`() {
            val string = MyString("abc")
            val other = MyString("bbc")
            assertTrue(string < other)
            assertTrue(other > string)
            assertFalse(string > other)
            assertFalse(other < string)
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorLessEqualMoreEqual {
        @Test
        @DisplayName("")
        fun `less equal more equal with MyString`() {
            val string = MyString("abc")
            val other = MyString("abc")
            assertTrue(string <= other)
            assertTrue(other >= string)
            assertFalse(string < other)
            assertFalse(string > other)
            assertFalse(other > string)
            assertFalse(other < string)
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorSet {
        @Test
        @DisplayName("")
        fun set() {
            val actual = MyString(getMockString())
            val expected = toCharArray("Suum!cuique") + NULL_CHAR
            actual[4] = '!'
            assertThat(actual.getStringData(), `is`(expected))
        }

        @Test
        @DisplayName("")
        fun `set throws if out of bounds`() {
            val string = MyString(getMockString())
            assertThrows<ArrayIndexOutOfBoundsException> { string[12] = '!' }
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorGet {
        @Test
        @DisplayName("")
        fun get() {
            val string = MyString(getMockString())
            val expected = 'S'
            val actual = string[0]
            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("")
        fun `get throws out of bounds`() {
            val string = MyString(getMockString())
            assertThrows<ArrayIndexOutOfBoundsException> { string[12] }
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorPrint {
        @Test
        @DisplayName("")
        fun print() {
            val mock = OutputMock()
            val string = MyString(getMockString())
            out.write(string)
            val expected = getMockString() + NULL_CHAR
            assertEquals(expected, mock.read())
            mock.destruct()
        }
    }

    @Nested
    @DisplayName("")
    inner class OperatorRead {
        @Test
        @DisplayName("")
        fun read() {
            setSystemInput(getMockString())
            val actual = MyString()
            `in`.read(actual)
            val expected = toCharArray(getMockString()) + NULL_CHAR
            assertThat(actual.getStringData(), `is`(expected))
        }
    }

    @Test
    @DisplayName("")
    fun `mutation of one MyString doesnt mutate other MyString`() {
        val string = MyString(getMockString())
        val other = MyString(string)
        other += string
        assertThat(string.getStringData(), `is`(getMockArray() + NULL_CHAR))
        assertThat(other.getStringData(), `is`(getMockArray() + getMockArray() + NULL_CHAR))
    }
}