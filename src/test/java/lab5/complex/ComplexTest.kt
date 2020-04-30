package lab5.complex

import common.OutputMock
import common.OutputMock.setSystemInput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.lang.System.`in`
import java.lang.System.out

class ComplexTest {
    val EPS = 10e-15

    private fun equalsRealPart(expected: Double, actual: Complex) {
        assertEquals(expected, actual.re(), EPS)
        assertEquals(0.0, actual.im())
    }

    private fun equalsImaginaryPart(expected: Double, actual: Complex) {
        assertEquals(expected, actual.im(), EPS)
        assertEquals(0.0, actual.re())
    }

    @Test
    @DisplayName("should be existing real part")
    fun shouldBeExistingRealPart() {
        val complex = Complex(2.0)
        val expected = 2.0
        equalsRealPart(expected, complex)
    }

    @Test
    @DisplayName("should be existing imaginary part")
    fun shouldBeExistingImaginaryPart() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.0
        equalsImaginaryPart(expected, complex)
    }

    @Nested
    @DisplayName("should calculated magnitude")
    inner class Magnitude {
        private fun equalsMagnitude(expected: Double, actual: Complex) {
            assertEquals(expected, actual.getMagnitude(), EPS)
        }

        @Test
        @DisplayName("of complex number")
        fun getMagnitudeOfPositiveComplexNumber() {
            val complex = Complex(2.0, 1.0)
            val expected = 2.23606797749979
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of complex number with negative real part")
        fun getMagnitudeOfComplexNumberWithNegativeRealPart() {
            val complex = Complex(-2.0, 1.0)
            val expected = 2.23606797749979
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of complex number with negative imaginary part")
        fun getMagnitudeOfComplexNumberWithNegativeImaginaryPart() {
            val complex = Complex(2.0, -1.0)
            val expected = 2.23606797749979
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of negative complex number")
        fun getMagnitudeOfNegativeComplexNumber() {
            val complex = Complex(-2.0, -1.0)
            val expected = 2.23606797749979
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of complex number with real part is zero")
        fun getMagnitudeOfComplexNumberWithRealPartIsZero() {
            val complex = Complex(0.0, 1.0)
            val expected = 1.0
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of complex number with imaginary part is zero")
        fun getMagnitudeOfComplexNumberWithImaginaryPartIsZero() {
            val complex = Complex(2.0, 0.0)
            val expected = 2.0
            equalsMagnitude(expected, complex)
        }

        @Test
        @DisplayName("of zero complex number")
        fun getMagnitudeOfComplexNumberIsZero() {
            val complex = Complex(0.0, 0.0)
            val expected = 0.0
            equalsMagnitude(expected, complex)
        }
    }

    @Nested
    @DisplayName("calculated argument")
    inner class Argument {
        private fun equalsArgument(expected: Double, actual: Complex) {
            assertEquals(expected, actual.getArgument(), EPS)
        }

        @Test
        @DisplayName("of complex number")
        fun getArgumentOfPositiveComplexNumber() {
            val complex = Complex(2.0, 1.0)
            val expected = 0.4636476090008061
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of complex number with negative real part")
        fun getArgumentOfComplexNumberWithNegativeRealPart() {
            val complex = Complex(-2.0, 1.0)
            val expected = 2.677945044588987
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of complex number with negative imaginary part")
        fun getArgumentOfComplexNumberWithNegativeImaginaryPart() {
            val complex = Complex(2.0, -1.0)
            val expected = -0.4636476090008061
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of negative complex number")
        fun getArgumentOfNegativeComplexNumber() {
            val complex = Complex(-2.0, -1.0)
            val expected = -2.677945044588987
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of complex number with real part is zero")
        fun getArgumentOfComplexNumberWithRealPartIsZero() {
            val complex = Complex(0.0, 1.0)
            val expected = 1.5707963267948966
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of complex number with imaginary part is zero")
        fun getArgumentOfComplexNumberWithImaginaryPartIsZero() {
            val complex = Complex(2.0, 0.0)
            val expected = 0.0
            equalsArgument(expected, complex)
        }

        @Test
        @DisplayName("of zero complex number")
        fun getArgumentOfComplexNumberIsZero() {
            val complex = Complex(0.0, 0.0)
            val expected = 0.0
            equalsArgument(expected, complex)
        }
    }

    @Nested
    @DisplayName("result of actions with complex numbers")
    inner class ActionsWithComplexNumbers {
        private fun equalsComplexNumbers(expected: Complex, actual: Complex) {
            assertEquals(expected.re(), actual.re(), EPS)
            assertEquals(expected.im(), actual.im(), EPS)
        }

        @Nested
        @DisplayName("binary plus")
        inner class BinaryPlus {
            @Test
            @DisplayName("should be addition of complex numbers")
            fun shouldAdditionOfComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val addend = Complex(1.0, 2.0)
                val expected = Complex(3.0, 3.0)
                val actual = complex + addend
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be adding a complex number and double number")
            fun shouldAddingComplexNumberAndDoubleNumber() {
                val complex = Complex(2.0, 1.0)
                val addend = 1.0
                val expected = Complex(3.0, 1.0)
                val actual = complex + addend
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be adding a double number and complex number")
            fun shouldAddingDoubleNumberAndComplexNumber() {
                val double = 2.0
                val complex = Complex(2.0, 1.0)
                val expected = Complex(4.0, 1.0)
                val actual = double + complex
                equalsComplexNumbers(expected, actual)
            }
        }

        @Nested
        @DisplayName("binary minus")
        inner class BinaryMinus {
            @Test
            @DisplayName("should be subtraction of complex numbers")
            fun shouldSubtractionOfComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val subtrahend = Complex(3.0, 2.0)
                val expected = Complex(-1.0, -1.0)
                val actual = complex - subtrahend
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be subtraction from a complex number is double")
            fun shouldSubtractionFromComplexNumberIsDouble() {
                val complex = Complex(2.0, 1.0)
                val subtrahend = 1.0
                val expected = Complex(1.0, 1.0)
                val actual = complex - subtrahend
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be subtraction from a double is complex number")
            fun shouldSubtractionFromDoubleIsComplexNumber() {
                val double = 2.0
                val complex = Complex(2.0, 1.0)
                val expected = Complex(0.0, 1.0)
                val actual = double - complex
                equalsComplexNumbers(expected, actual)
            }
        }

        @Nested
        @DisplayName("multiplication")
        inner class Multiplication {
            @Test
            @DisplayName("should be multiplication of complex numbers")
            fun shouldMultiplicationOfComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val multiplier = Complex(4.0, 5.0)
                val expected = Complex(3.0, 14.0)
                val actual = complex * multiplier
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be multiplication complex number and double")
            fun shouldMultiplicationComplexNumberAndDouble() {
                val complex = Complex(2.0, 1.0)
                val factor = 2.0
                val expected = Complex(4.0, 2.0)
                val actual = complex * factor
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be multiplication double and complex number")
            fun shouldMultiplicationDoubleAndComplexNumber() {
                val double = 2.0
                val complex = Complex(2.0, 1.0)
                val expected = Complex(4.0, 1.0)
                val actual = double * complex
                equalsComplexNumbers(expected, actual)
            }
        }

        @Nested
        @DisplayName("division")
        inner class Division {
            @Test
            @DisplayName("should be divide complex numbers")
            fun shouldDivideComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val divider = Complex(4.0, 5.0)
                val expected = Complex(0.3170731707317073, -0.14634146341463414)
                val actual = complex / divider
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be divide from a complex number is double")
            fun shouldDivideFromComplexNumberIsDouble() {
                val complex = Complex(2.0, 1.0)
                val divider = 2.0
                val expected = Complex(1.0, 0.5)
                val actual = complex / divider
                equalsComplexNumbers(expected, actual)
            }

            @Test
            @DisplayName("should be divide from a double is complex number")
            fun shouldDivideFromDoubleIsComplexNumber() {
                val double = 2.0
                val complex = Complex(2.0, 1.0)
                val expected = Complex(1.0, 1.0)
                val actual = double / complex
                equalsComplexNumbers(expected, actual)
            }
        }

        @Nested
        @DisplayName("unary operations")
        inner class UnaryOperations {
            @Test
            @DisplayName("should be return copy complex number after unary plus")
            fun shouldBeReturnCopyComplexNumberAfterUnaryPlus() {
                val complex = Complex(2.0, 1.0)
                val copy = +complex
                assertNotEquals(copy.toString(), complex.toString())
            }

            @Test
            @DisplayName("should be return inverted complex number after unary minus")
            fun shouldBeReturnInvertedComplexNumberAfterUnaryMinus() {
                val complex = Complex(2.0, 1.0)
                val inverted = -complex
                val expected = Complex(-2.0, -1.0)
                equalsComplexNumbers(expected, inverted)
            }
        }

        @Nested
        @DisplayName("cumulative assignments for plus")
        inner class CumulativeAssignmentsForPlus {
            @Test
            @DisplayName("should be increment of a complex number to a complex number")
            fun shouldBeIncrementOfComplexNumberToComplexNumber() {
                val complex = Complex(2.0, 1.0)
                val addend = Complex(1.0, 1.0)
                val expected = Complex(3.0, 2.0)
                complex += addend
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be increment of a complex number to a double")
            fun shouldBeIncrementOfComplexNumberToDouble() {
                val complex = Complex(2.0, 1.0)
                val addend = 2.0
                val expected = Complex(4.0, 1.0)
                complex += addend
                equalsComplexNumbers(expected, complex)
            }
        }

        @Nested
        @DisplayName("cumulative assignments for minus")
        inner class CumulativeAssignmentsForMinus {
            @Test
            @DisplayName("should be decrement of a complex number to a complex number")
            fun shouldBeDecrementOfComplexNumberToComplexNumber() {
                val complex = Complex(2.0, 1.0)
                val subtrahend = Complex(1.0, 1.0)
                val expected = Complex(1.0, 0.0)
                complex -= subtrahend
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be decrement of a complex number to a double")
            fun shouldBeDecrementOfComplexNumberToDouble() {
                val complex = Complex(2.0, 1.0)
                val subtrahend = 2.0
                val expected = Complex(0.0, 1.0)
                complex -= subtrahend
                equalsComplexNumbers(expected, complex)
            }
        }

        @Nested
        @DisplayName("cumulative assignments for times")
        inner class CumulativeAssignmentsForTimes {
            @Test
            @DisplayName("should be multiply of a complex number to a complex number")
            fun shouldBeMultiplyOfComplexNumberToComplexNumber() {
                val complex = Complex(2.0, 1.0)
                val multiplier = Complex(1.0, 1.0)
                val expected = Complex(1.0, 3.0)
                complex *= multiplier
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be multiply of a complex number to a double")
            fun shouldBeMultiplyOfComplexNumberToDouble() {
                val complex = Complex(2.0, 1.0)
                val factor = 2.0
                val expected = Complex(4.0, 2.0)
                complex *= factor
                equalsComplexNumbers(expected, complex)
            }
        }

        @Nested
        @DisplayName("cumulative assignments for divide")
        inner class CumulativeAssignmentsForDivide {
            @Test
            @DisplayName("should be divide of a complex number to a complex number")
            fun shouldBeDivideOfComplexNumberToComplexNumber() {
                val complex = Complex(2.0, 1.0)
                val divider = Complex(1.0, 1.0)
                val expected = Complex(1.5, -0.5)
                complex /= divider
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be divide of a complex number to a double")
            fun shouldBeDivideOfComplexNumberToDouble() {
                val complex = Complex(2.0, 1.0)
                val divider = 2.0
                val expected = Complex(1.0, 0.5)
                complex /= divider
                equalsComplexNumbers(expected, complex)
            }
        }

        @Nested
        @DisplayName("equals operator")
        inner class EqualsOperator {
            @Test
            @DisplayName("should be equal complex numbers")
            fun shouldBeEqualComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val other = Complex(2.0, 1.0)
                assertTrue(complex == other)
            }

            @Test
            @DisplayName("should be equal complex number and double")
            fun shouldBeEqualComplexNumberAndDouble() {
                val complex = Complex(2.0, 0.0)
                val other = 2.0
                assertTrue(complex.equals(other))
            }
        }

        @Nested
        @DisplayName("not equals operator")
        inner class NotEqualsOperator {
            @Test
            @DisplayName("should be not equal complex numbers")
            fun shouldBeNotEqualComplexNumbers() {
                val complex = Complex(2.0, 1.0)
                val other = Complex(1.0, 1.0)
                assertTrue(complex != other)
            }

            @Test
            @DisplayName("should be not equal complex number and double")
            fun shouldBeNotEqualComplexNumberAndDouble() {
                val complex = Complex(2.0, 0.0)
                val other = 1.0
                assertTrue(!complex.equals(other))
            }
        }

        @Nested
        @DisplayName("output to output stream operator")
        inner class OutputStreamOperator {
            @Test
            @DisplayName("should be print positive complex number")
            fun shouldBePrintPositiveComplexNumber() {
                val mock = OutputMock()
                val complex = Complex(2.0, 1.0)
                out.write(complex)
                val expected = "2.0+1.0i"
                assertEquals(expected, mock.read())
                mock.destruct()
            }

            @Test
            @DisplayName("should be print negative complex number")
            fun shouldBePrintNegativeComplexNumber() {
                val mock = OutputMock()
                val complex = Complex(-2.0, -1.0)
                out.write(complex)
                val expected = "-2.0-1.0i"
                assertEquals(expected, mock.read())
                mock.destruct()
            }
        }

        @Nested
        @DisplayName("input stream operator")
        inner class InputStreamOperator {
            @Test
            @DisplayName("should be read positive complex number")
            fun shouldBeReadPositiveComplexNumber() {
                setSystemInput("4+2i")
                val complex = Complex()
                `in`.read(complex)
                val expected = Complex(4.0, 2.0)
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be read positive complex number with double parts")
            fun shouldBeReadPositiveComplexNumberWithDoubleParts() {
                setSystemInput("4.2+2.8i")
                val complex = Complex()
                `in`.read(complex)
                val expected = Complex(4.2, 2.8)
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be read negative complex number")
            fun shouldBeReadNegativeComplexNumber() {
                setSystemInput("-4-2i")
                val complex = Complex()
                `in`.read(complex)
                val expected = Complex(-4.0, -2.0)
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be read negative complex number with double parts")
            fun shouldBeReadNegativeComplexNumberWithDoubleParts() {
                setSystemInput("-4.2-2.8i")
                val complex = Complex()
                `in`.read(complex)
                val expected = Complex(-4.2, -2.8)
                equalsComplexNumbers(expected, complex)
            }

            @Test
            @DisplayName("should be read complex number with corrupted real part")
            fun shouldBeReadComplexNumberWithCorruptedRealPart() {
                setSystemInput("a+2i")
                val complex = Complex()
                assertThrows<IOException> { `in`.read(complex) }
            }

            @Test
            @DisplayName("should be read complex number with corrupted imaginary part")
            fun shouldBeReadComplexNumberWithCorruptedImaginaryPart() {
                setSystemInput("4+ai")
                val complex = Complex()
                assertThrows<IOException> { `in`.read(complex) }
            }
        }
    }
}