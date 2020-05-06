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

    private fun toCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }

    @Nested
    @DisplayName("Constructor")
    inner class Constructor {
        @Nested
        @DisplayName("CharArray")
        inner class CharArray {
            @Test
            @DisplayName("should be define MyString")
            fun shouldBeDefineMyString() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(charArray)
                assertThat(charArray + NULL_CHAR, `is`(actual.getStringData()))
                assertEquals(actual.getLength(), charArray.size)
            }

            @Test
            @DisplayName("should to define MyString from chars with null symbol")
            fun shouldToDefineMyStringFromCharsWithNullSymbol() {
                val chars = arrayOf('S', 'u', 'u', 'm', NULL_CHAR, 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(chars)
                assertThat(chars + NULL_CHAR, `is`(actual.getStringData()))
                assertEquals(actual.getLength(), chars.size)
            }
        }

        @Nested
        @DisplayName("CharArray with Length")
        inner class CharArrayWithLength {
            @Test
            @DisplayName("should be define MyString")
            fun shouldBeDefineMyString() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(charArray, charArray.size)
                assertThat(charArray + NULL_CHAR, `is`(actual.getStringData()))
                assertEquals(actual.getLength(), charArray.size)
            }

            @Test
            @DisplayName("should be define MyString if length is less array length")
            fun shouldBeDefineMyStringIfLengthIsLessArrayLength() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(charArray, charArray.size - 7)
                assertThat(arrayOf('S', 'u', 'u', 'm', NULL_CHAR), `is`(actual.getStringData()))
                assertEquals(actual.getLength(), arrayOf('S', 'u', 'u', 'm').size)
            }

            @Test
            @DisplayName("should be define MyString if length is 0")
            fun shouldBeDefineMyStringIfLengthIsZero() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(charArray, 0)
                assertThat(arrayOf(NULL_CHAR), `is`(actual.getStringData()))
                assertEquals(actual.getLength(), emptyArray<Char>().size)
            }

            @Test
            @DisplayName("try define MyString if length is negative")
            fun tryDefineMyStringIfLengthIsNegative() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                assertThrows<StringIndexOutOfBoundsException> { MyString(charArray, -charArray.size) }
            }

            @Test
            @DisplayName("try define MyString if length is more array length")
            fun tryDefineMyStringIfLengthIsMoreArrayLength() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                assertThrows<StringIndexOutOfBoundsException> { MyString(charArray, 2 * charArray.size) }
            }
        }

        @Nested
        @DisplayName("String")
        inner class String {
            @Test
            @DisplayName("should be define MyString")
            fun shouldBeDefineMyString() {
                val string = "Suum cuique"
                val myString = MyString(string)
                assertThat(toCharArray(string + NULL_CHAR), `is`(myString.getStringData()))
                assertEquals(myString.getLength(), string.length)
            }
        }

        @Nested
        @DisplayName("MyString")
        inner class MyString {
            @Test
            @DisplayName("should be define MyString")
            fun shouldBeDefineMyString() {
                val charArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
                val myString = MyString(charArray)
                val actual = MyString(myString)
                assertThat(charArray + NULL_CHAR, `is`(actual.getStringData()))
                assertEquals(actual.getLength(), charArray.size)
            }
        }
    }

    @Nested
    @DisplayName("Method: substring(start, length)")
    inner class MethodSubstring {
        @Test
        @DisplayName("should get substring by set index")
        fun shouldGetSubstringBySetIndex() {
            val myString = MyString("Suum cuique").substring(2, 5)
            val expected = MyString("um cu")
            assertThat(myString.getStringData(), `is`(expected.getStringData()))
            assertThat(myString.getLength(), `is`(expected.getLength()))
        }

        @Test
        @DisplayName("should get substring in empty string")
        fun shouldGetSubstringInEmptyString() {
            val myString = MyString("Suum cuique")
            val actual = myString.substring(2, 0)
            val expected = MyString(emptyArray())
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }

        @Test
        @DisplayName("try get substring by index from outside of range")
        fun tryGetSubstringByIndexFromOutsideOfRange() {
            val myString = MyString("Suum cuique")
            assertThrows<StringIndexOutOfBoundsException> { myString.substring(7, 5) }
        }

        @Test
        @DisplayName("try get substring by negative length")
        fun tryGetSubstringByNegativeLength() {
            val myString = MyString("Suum cuique")
            assertThrows<StringIndexOutOfBoundsException> { myString.substring(2, -5) }
        }
    }

    @Nested
    @DisplayName("Method: clear()")
    inner class MethodClear {
        @Test
        @DisplayName("should be cleared data")
        fun shouldBeClearedData() {
            val myString = MyString("Suum cuique")
            myString.clear()
            val expected = MyString()
            assertThat(expected.getStringData(), `is`(myString.getStringData()))
            assertThat(expected.getLength(), `is`(myString.getLength()))
        }
    }

    @Nested
    @DisplayName("Operator: =")
    inner class OperatorAssignment {
        @Test
        @DisplayName("should to assign himself")
        fun shouldToAssignHimself() {
            val myString = MyString("Suum cuique")
            var actual = myString
            actual = actual
            assertThat(myString.getStringData(), `is`(actual.getStringData()))
        }
    }

    @Nested
    @DisplayName("Operator: +")
    inner class OperatorPlus {
        @Test
        @DisplayName("should to concat with other MyString")
        fun shouldToConcatWithOtherMyString() {
            val string = "Suum cuique"
            val actual = MyString(string) + MyString(string)
            assertThat(toCharArray(string + string + NULL_CHAR), `is`(actual.getStringData()))
            assertEquals(actual.getLength(), toCharArray(string + string).size)
        }

        @Test
        @DisplayName("should to concat MyString and String")
        fun shouldToConcatMyStringAndString() {
            val string = "Suum cuique"
            val actual = MyString(string) + string
            assertThat(toCharArray(string + string + NULL_CHAR), `is`(actual.getStringData()))
            assertEquals(actual.getLength(), toCharArray(string + string).size)
        }

        @Test
        @DisplayName("should to concat MyString and Char Array")
        fun shouldToConcatMyStringAndCharArray() {
            val string = "Suum cuique"
            val actual = MyString(string) + arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
            assertThat(toCharArray(string + string + NULL_CHAR), `is`(actual.getStringData()))
            assertEquals(actual.getLength(), toCharArray(string + string).size)
        }
    }

    @Nested
    @DisplayName("Operator: +=")
    inner class OperatorPlusAssign {
        @Test
        @DisplayName("should to concat with other MyString")
        fun shouldToConcatWithOtherMyString() {
            val string = "Suum cuique"
            val myString = MyString(string)
            myString += MyString(string)
            assertThat(toCharArray(string + string + NULL_CHAR), `is`(myString.getStringData()))
            assertEquals(myString.getLength(), toCharArray(string + string).size)
        }
    }

    @Nested
    @DisplayName("Operator: ==")
    inner class OperatorEqual {
        @Test
        @DisplayName("should be equal MyString")
        fun shouldBeEqualMyString() {
            assertTrue(MyString("Suum cuique") == MyString("Suum cuique"))
        }

        @Test
        @DisplayName("should be equal MyString and String")
        fun shouldBeEqualMyStringAndString() {
            assertTrue(MyString("Suum cuique").equals("Suum cuique"))
        }
    }

    @Nested
    @DisplayName("Operator: !=")
    inner class OperatorNotEqual {
        @Test
        @DisplayName("should not equal MyString")
        fun shouldNotBeEqualMyString() {
            assertTrue(MyString("Suum cuique") != MyString("Suum cuique" + "Suum cuique"))
        }

        @Test
        @DisplayName("should not equal MyString and String")
        fun shouldNotBeEqualMyStringAndString() {
            assertTrue(!MyString("Suum cuique" + "Suum cuique").equals(MyString("Suum cuique")))
        }

        @Test
        @DisplayName("should not equal MyString and any other type")
        fun shouldNotBeEqualMyStringAndAnyOtherType() {
            assertFalse(MyString("Suum cuique").equals(10))
            assertFalse(MyString("Suum cuique").equals(null))
            assertFalse(MyString("Suum cuique").equals(true))
        }
    }

    @Nested
    @DisplayName("Operator: < && >")
    inner class OperatorLessMore {
        private val string = MyString("abc")
        private val other = MyString("bbc")

        @Test
        @DisplayName("should be less")
        fun shouldBeLessMyString() {
            assertTrue(string < other)
            assertFalse(other < string)
        }

        @Test
        @DisplayName("should be more")
        fun shouldBeMoreMyString() {
            assertTrue(other > string)
            assertFalse(string > other)
        }
    }

    @Nested
    @DisplayName("Operator: <= && =>")
    inner class OperatorLessEqualMoreEqual {
        private val string = MyString("abc")
        private val other = MyString("abc")

        @Test
        @DisplayName("should be less equals")
        fun shouldBeLessEqualsWithMyString() {
            assertTrue(string <= other)
            assertFalse(string < other)
            assertFalse(other < string)
        }

        @Test
        @DisplayName("should be more equals")
        fun shouldBeMoreEqualsWithMyString() {
            assertTrue(other >= string)
            assertFalse(string > other)
            assertFalse(other > string)
        }
    }

    @Nested
    @DisplayName("Operator: set")
    inner class OperatorSet {
        @Test
        @DisplayName("should set char")
        fun shouldSetChar() {
            val myString = MyString("Suum cuique")
            val expected = toCharArray("Suum!cuique") + NULL_CHAR
            myString[4] = '!'
            assertThat(myString.getStringData(), `is`(expected))
        }

        @Test
        @DisplayName("try set char is out of range")
        fun trySetCharIsOutOfRange() {
            assertThrows<ArrayIndexOutOfBoundsException> { MyString("Suum cuique")[12] = '!' }
        }
    }

    @Nested
    @DisplayName("Operator: get")
    inner class OperatorGet {
        @Test
        @DisplayName("should get char")
        fun shouldGetChar() {
            assertEquals('S', MyString("Suum cuique")[0])
        }

        @Test
        @DisplayName("try get char is out of range")
        fun tryGetCharIsOutOfRange() {
            assertThrows<ArrayIndexOutOfBoundsException> { MyString("Suum cuique")[12] }
        }
    }

    @Nested
    @DisplayName("Operator: <<")
    inner class OperatorPrint {
        @Test
        @DisplayName("should be print MyString in output")
        fun shouldBePrintMyStringInOutput() {
            val mock = OutputMock()
            out.write(MyString("Suum cuique"))
            val expected = "Suum cuique" + NULL_CHAR
            assertEquals(expected, mock.read())
            mock.destruct()
        }
    }

    @Nested
    @DisplayName("Operator: >>")
    inner class OperatorRead {
        @Test
        @DisplayName("should be read MyString from input")
        fun shouldBeReadMyStringFromInput() {
            setSystemInput("Suum cuique")
            val actual = MyString()
            `in`.read(actual)
            val expected = toCharArray("Suum cuique") + NULL_CHAR
            assertThat(actual.getStringData(), `is`(expected))
        }
    }
}