package lab5.string

import common.OutputMock
import common.OutputMock.setSystemInput
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.lang.System.`in`
import java.lang.System.out

class MyStringTest {
    private val NULL_CHAR = '\u0000'
    private var mockString: String = ""
    private var mockMyString: MyString = MyString()
    private var mockCharArray: Array<Char> = arrayOf()

    private fun toCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }

    @BeforeEach
    fun init() {
        mockString = "Suum cuique"
        mockMyString = MyString(mockString)
        mockCharArray = arrayOf('S', 'u', 'u', 'm', ' ', 'c', 'u', 'i', 'q', 'u', 'e')
    }

    @Nested
    @DisplayName("Constructor")
    inner class Constructor {
        @Nested
        @DisplayName("CharArray")
        inner class CharArray {
            @Test
            @DisplayName("should be define MyString has correct data")
            fun shouldBeDefineCharArrayHasCorrectData() {
                val actual = MyString(mockCharArray)
                val expected = mockCharArray + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length")
            fun shouldBeDefineCharArrayHasCorrectLength() {
                val actual = MyString(mockCharArray)
                val expected = mockCharArray.size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("should be define MyString with null char has correct data")
            fun shouldBeDefineMyStringWithNullCharHasCorrectData() {
                val chars = arrayOf('S', 'u', 'u', 'm', NULL_CHAR, 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(chars)
                val expected = chars + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString with null char has correct length")
            fun shouldBeDefineMyStringWithNullCharHasCorrectLength() {
                val chars = arrayOf('S', 'u', 'u', 'm', NULL_CHAR, 'c', 'u', 'i', 'q', 'u', 'e')
                val actual = MyString(chars)
                val expected = chars.size
                assertEquals(actual.getLength(), expected)
            }
        }

        @Nested
        @DisplayName("CharArray with Length")
        inner class CharArrayWithLength {
            @Test
            @DisplayName("should be define MyString has correct data")
            fun `constructor(chars, length) has correct data`() {
                val actual = MyString(mockCharArray, mockCharArray.size)
                val expected = mockCharArray + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length")
            fun `constructor(chars, length) has correct length`() {
                val actual = MyString(mockCharArray, mockCharArray.size)
                val expected = mockCharArray.size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("should be define MyString has correct data if length is less then char array length")
            fun `constructor(chars, length) has correct data if length is less then chars`() {
                val actual = MyString(mockCharArray, mockCharArray.size - 7)
                val expected = arrayOf('S', 'u', 'u', 'm', NULL_CHAR)
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length if length is less then char array length")
            fun `constructor(chars, length) has correct length if length is less then chars`() {
                val actual = MyString(mockCharArray, mockCharArray.size - 7)
                val expected = arrayOf('S', 'u', 'u', 'm').size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("should be define MyString has correct data if length is 0")
            fun `constructor(chars, length) has correct data if length is 0`() {
                val actual = MyString(mockCharArray, 0)
                val expected = arrayOf(NULL_CHAR)
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length if length is 0")
            fun `constructor(chars, length) has correct length if length is 0`() {
                val actual = MyString(mockCharArray, 0)
                val expected = emptyArray<Char>().size
                assertEquals(actual.getLength(), expected)
            }

            @Test
            @DisplayName("should not define MyString throws if length is negative")
            fun `constructor(chars, length) throws if length is negative`() {
                assertThrows<StringIndexOutOfBoundsException> { MyString(mockCharArray, -mockCharArray.size) }
            }

            @Test
            @DisplayName("should not define MyString throws if length is more than string has chars")
            fun `constructor(chars, length) throws if length is more then string has chars`() {
                assertThrows<StringIndexOutOfBoundsException> { MyString(mockCharArray, 2 * mockCharArray.size) }
            }
        }

        @Nested
        @DisplayName("String")
        inner class String {
            @Test
            @DisplayName("should be define MyString has correct data")
            fun `constructor(string) has correct data`() {
                val expected = mockString + NULL_CHAR
                assertThat(toCharArray(expected), `is`(mockMyString.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length")
            fun `constructor(string) has correct length`() {
                val expected = mockString.length
                assertEquals(mockMyString.getLength(), expected)
            }
        }

        @Nested
        @DisplayName("MyString")
        inner class MyString {
            @Test
            @DisplayName("should be define MyString has correct data")
            fun `constructor(MyString) has correct data`() {
                val string = MyString(mockCharArray)
                val actual = MyString(string)
                val expected = mockCharArray + NULL_CHAR
                assertThat(expected, `is`(actual.getStringData()))
            }

            @Test
            @DisplayName("should be define MyString has correct length")
            fun `constructor(MyString) has correct length`() {
                val string = MyString(mockCharArray)
                val actual = MyString(string)
                val expected = mockCharArray.size
                assertEquals(actual.getLength(), expected)
            }
        }
    }

    @Nested
    @DisplayName("Method: substring(start, length)")
    inner class MethodSubstring {
        @Test
        @DisplayName("should be substring MyString has correct data")
        fun `substring has correct data`() {
            val actual = mockMyString.substring(2, 5)
            val chars = mockString.substring(2, 3 + 4)
            val expected = MyString(chars)
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be substring MyString has correct length")
        fun `substring has correct length`() {
            val actual = mockMyString.substring(2, 5)
            val chars = mockString.substring(2, 3 + 4)
            val expected = MyString(chars)
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }

        @Test
        @DisplayName("should be substring MyString has correct data if length is 0")
        fun `substring has correct data with zero length`() {
            val actual = mockMyString.substring(2, 0)
            val chars = emptyArray<Char>()
            val expected = MyString(chars)
            assertThat(expected.getStringData(), `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be substring MyString has length data if length is 0")
        fun `substring has correct length with zero length`() {
            val actual = mockMyString.substring(2, 0)
            val chars = emptyArray<Char>()
            val expected = MyString(chars)
            assertThat(expected.getLength(), `is`(actual.getLength()))
        }

        @Test
        @DisplayName("should not substring MyString throws error if out of range")
        fun `substring throws error if out of range`() {
            assertThrows<StringIndexOutOfBoundsException> { mockMyString.substring(7, 5) }
        }

        @Test
        @DisplayName("should not substring MyString throws error if length is negative")
        fun `substring throws error if length is negative`() {
            assertThrows<StringIndexOutOfBoundsException> { mockMyString.substring(2, -5) }
        }
    }

    @Nested
    @DisplayName("Method: clear()")
    inner class MethodClear {
        @Test
        @DisplayName("should be cleared data in MyString")
        fun `clear data`() {
            mockMyString.clear()
            val expected = MyString()
            assertThat(expected.getStringData(), `is`(mockMyString.getStringData()))
        }

        @Test
        @DisplayName("should be cleared data with length in MyString")
        fun `clear length`() {
            mockMyString.clear()
            val expected = MyString()
            assertThat(expected.getLength(), `is`(mockMyString.getLength()))
        }
    }

    @Nested
    @DisplayName("Operator: =")
    inner class OperatorAssignment {
        @Test
        @DisplayName("should be assignment MyString")
        fun shouldBeAssignmentMyString() {
            var actual = mockMyString
            actual = actual
            assertThat(mockMyString.getStringData(), `is`(actual.getStringData()))
        }
    }

    @Nested
    @DisplayName("Operator: +")
    inner class OperatorPlus {
        @Test
        @DisplayName("should be correct data after addition MyString")
        fun `plus has correct data with MyString`() {
            val other = mockMyString
            val actual = mockMyString + other
            val expected = toCharArray(mockString + mockString + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be correct length after addition MyString")
        fun `plus has correct length with MyString`() {
            val other = mockMyString
            val actual = mockMyString + other
            val expected = toCharArray(mockString + mockString).size
            assertEquals(actual.getLength(), expected)
        }

        @Test
        @DisplayName("should be correct data after addition MyString and String")
        fun `plus has correct data with String`() {
            val other = mockString
            val actual = mockMyString + other
            val expected = toCharArray(mockString + mockString + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be correct length after addition MyString and String")
        fun `plus has correct length with String`() {
            val other = mockString
            val actual = mockMyString + other
            val expected = toCharArray(mockString + mockString).size
            assertEquals(actual.getLength(), expected)
        }

        @Test
        @DisplayName("should be correct data after addition MyString and Char Array")
        fun `plus has correct data with Chars`() {
            val actual = mockMyString + mockCharArray
            val expected = toCharArray(mockString + mockString + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be correct length after addition MyString and Char Array")
        fun `plus has correct length with Chars`() {
            val actual = mockMyString + mockCharArray
            val expected = toCharArray(mockString + mockString).size
            assertEquals(actual.getLength(), expected)
        }
    }

    @Nested
    @DisplayName("Operator: +=")
    inner class OperatorPlusAssign {
        @Test
        @DisplayName("should be correct data after increment of MyString to MyString")
        fun `plus assign has correct data`() {
            val actual = mockMyString
            val other = mockMyString
            actual += other
            val expected = toCharArray(mockString + mockString + NULL_CHAR)
            assertThat(expected, `is`(actual.getStringData()))
        }

        @Test
        @DisplayName("should be correct length after increment of MyString to MyString")
        fun `plus assign has correct length`() {
            val actual = mockMyString
            val other = mockMyString
            actual += other
            val expected = toCharArray(mockString + mockString).size
            assertEquals(actual.getLength(), expected)
        }
    }

    @Nested
    @DisplayName("Operator: ==")
    inner class OperatorEqual {
        @Test
        @DisplayName("should be equal MyString")
        fun shouldBeEqualMyString() {
            assertTrue(mockMyString == mockMyString)
            assertFalse(mockMyString != mockMyString)
        }

        @Test
        @DisplayName("should be equal MyString and String")
        fun shouldBeEqualMyStringAndString() {
            val other = mockString
            assertTrue(mockMyString.equals(other))
            assertFalse(!mockMyString.equals(other))
        }
    }

    @Nested
    @DisplayName("Operator: !=")
    inner class OperatorNotEqual {
        @Test
        @DisplayName("should not equal MyString")
        fun shouldNotBeEqualMyString() {
            val other = MyString(mockString + mockString)
            assertTrue(mockMyString != other)
            assertFalse(mockMyString == other)
        }

        @Test
        @DisplayName("should not equal MyString and String")
        fun shouldNotBeEqualMyStringAndString() {
            val other = mockString + mockString
            assertTrue(!mockMyString.equals(other))
            assertFalse(mockMyString.equals(other))
        }

        @Test
        @DisplayName("should not equal MyString and any other type")
        fun shouldNotBeEqualMyStringAndAnyOtherType() {
            assertFalse(mockMyString.equals(10))
            assertFalse(mockMyString.equals(null))
            assertFalse(mockMyString.equals(true))
        }
    }

    @Nested
    @DisplayName("Operator: < && >")
    inner class OperatorLessMore {
        val string = MyString("abc")
        val other = MyString("bbc")

        @Test
        @DisplayName("should be less MyString")
        fun shouldBeLessMyString() {
            assertTrue(string < other)
            assertFalse(other < string)
        }

        @Test
        @DisplayName("should be more MyString")
        fun shouldBeMoreMyString() {
            assertTrue(other > string)
            assertFalse(string > other)
        }
    }

    @Nested
    @DisplayName("Operator: <= && =>")
    inner class OperatorLessEqualMoreEqual {
        val string = MyString("abc")
        val other = MyString("abc")

        @Test
        @DisplayName("should be less equals with MyString")
        fun shouldBeLessEqualsWithMyString() {
            assertTrue(string <= other)
            assertFalse(string < other)
            assertFalse(other < string)
        }

        @Test
        @DisplayName("should be more equals with MyString")
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
        @DisplayName("should be set char in MyString")
        fun shouldBeSetCharInMyString() {
            val expected = toCharArray("Suum!cuique") + NULL_CHAR
            mockMyString[4] = '!'
            assertThat(mockMyString.getStringData(), `is`(expected))
        }

        @Test
        @DisplayName("should not set char out of range of MyString")
        fun shouldNotSetCharOutOfRangeOfInMyString() {
            assertThrows<ArrayIndexOutOfBoundsException> { mockMyString[12] = '!' }
        }
    }

    @Nested
    @DisplayName("Operator: get")
    inner class OperatorGet {
        @Test
        @DisplayName("should be get char in MyString")
        fun shouldBeGetCharInMyString() {
            val expected = 'S'
            val actual = mockMyString[0]
            assertEquals(expected, actual)
        }

        @Test
        @DisplayName("should not get char out of range of MyString")
        fun shouldNotGetCharOutOfRangeOfMyString() {
            assertThrows<ArrayIndexOutOfBoundsException> { mockMyString[12] }
        }
    }

    @Nested
    @DisplayName("Operator: <<")
    inner class OperatorPrint {
        @Test
        @DisplayName("should be print MyString in output")
        fun shouldBePrintMyStringInOutput() {
            val mock = OutputMock()
            out.write(mockMyString)
            val expected = mockString + NULL_CHAR
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
            setSystemInput(mockString)
            val actual = MyString()
            `in`.read(actual)
            val expected = toCharArray(mockString) + NULL_CHAR
            assertThat(actual.getStringData(), `is`(expected))
        }
    }
}