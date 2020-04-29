package lab5.complex

import common.OutputMock
import common.OutputMock.setSystemInput
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import java.io.IOException
import java.lang.System.`in`
import java.lang.System.out

class ComplexTest {
    val EPS = 10e-15

    private fun assertComplexParts(expected: Complex, actual: Complex) {
        assertEquals(expected.re(), actual.re(), EPS)
        assertEquals(expected.im(), actual.im(), EPS)
    }

    private fun assertComplexMagnitude(expected: Double, actual: Complex) {
        assertEquals(expected, actual.getMagnitude(), EPS)
    }

    private fun assertComplexArgument(expected: Double, actual: Complex) {
        assertEquals(expected, actual.getArgument(), EPS)
    }

    private fun assertComplexReal(expected: Double, actual: Complex) {
        assertEquals(expected, actual.re(), EPS)
        assertEquals(0.0, actual.im())
    }

    private fun assertComplexImage(expected: Double, actual: Complex) {
        assertEquals(expected, actual.im(), EPS)
        assertEquals(0.0, actual.re())
    }

    @Test
    @DisplayName("")
    fun `get real part`() {
        val complex = Complex(2.0)
        val expected = 2.0
        assertComplexReal(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get imaginary part`() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.0
        assertComplexImage(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude`() {
        val complex = Complex(2.0, 1.0)
        val expected = 2.23606797749979
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if real negative`() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.23606797749979
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if imaginary negative`() {
        val complex = Complex(2.0, -1.0)
        val expected = 2.23606797749979
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if both parts negative`() {
        val complex = Complex(-2.0, -1.0)
        val expected = 2.23606797749979
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if real is zero`() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.0
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if imaginary is zero`() {
        val complex = Complex(2.0, 0.0)
        val expected = 2.0
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get magnitude if both zero`() {
        val complex = Complex(0.0, 0.0)
        val expected = 0.0
        assertComplexMagnitude(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument`() {
        val complex = Complex(2.0, 1.0)
        val expected = 0.4636476090008061
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if real is negative`() {
        val complex = Complex(-2.0, 1.0)
        val expected = 2.677945044588987
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if imaginary is negative`() {
        val complex = Complex(2.0, -1.0)
        val expected = -0.4636476090008061
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if both parts are negative`() {
        val complex = Complex(-2.0, -1.0)
        val expected = -2.677945044588987
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if real is zero`() {
        val complex = Complex(0.0, 1.0)
        val expected = 1.5707963267948966
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if imaginary is zero`() {
        val complex = Complex(2.0, 0.0)
        val expected = 0.0
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `get argument if both parts are zero`() {
        val complex = Complex(0.0, 0.0)
        val expected = 0.0
        assertComplexArgument(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `add complex to complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = Complex(1.0, 2.0)
        val expected = Complex(3.0, 3.0)
        val actual = complex + addend
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `add double to complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = 1.0
        val expected = Complex(3.0, 1.0)
        val actual = complex + addend
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `subtract complex from complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = Complex(3.0, 2.0)
        val expected = Complex(-1.0, -1.0)
        val actual = complex - subtrahend
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `subtract double from complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = 1.0
        val expected = Complex(1.0, 1.0)
        val actual = complex - subtrahend
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `multiply complex by complex`() {
        val complex = Complex(2.0, 1.0)
        val multiplier = Complex(4.0, 5.0)
        val expected = Complex(3.0, 14.0)
        val actual = complex * multiplier
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `multiply complex by double`() {
        val complex = Complex(2.0, 1.0)
        val factor = 2.0
        val expected = Complex(4.0, 2.0)
        val actual = complex * factor
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `divide complex by complex`() {
        val complex = Complex(2.0, 1.0)
        val divider = Complex(4.0, 5.0)
        val expected = Complex(0.3170731707317073, -0.14634146341463414)
        val actual = complex / divider
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `divide complex by double`() {
        val complex = Complex(2.0, 1.0)
        val divider = 2.0
        val expected = Complex(1.0, 0.5)
        val actual = complex / divider
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `unary plus returns copy`() {
        val complex = Complex(2.0, 1.0)
        val copy = +complex
        assertNotEquals(copy.toString(), complex.toString())
    }

    @Test
    @DisplayName("")
    fun `unary minus returns inverted copy`() {
        val complex = Complex(2.0, 1.0)
        val inverted = -complex
        val expected = Complex(-2.0, -1.0)
        assertEquals(expected.re(), inverted.re(), EPS)
        assertEquals(expected.im(), inverted.im(), EPS)
    }

    @Test
    @DisplayName("")
    fun `plus assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val addend = Complex(1.0, 1.0)
        val expected = Complex(3.0, 2.0)
        complex += addend
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `plus assign by double`() {
        val complex = Complex(2.0, 1.0)
        val addend = 2.0
        val expected = Complex(4.0, 1.0)
        complex += addend
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `minus assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = Complex(1.0, 1.0)
        val expected = Complex(1.0, 0.0)
        complex -= subtrahend
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `minus assign by double`() {
        val complex = Complex(2.0, 1.0)
        val subtrahend = 2.0
        val expected = Complex(0.0, 1.0)
        complex -= subtrahend
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `times assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val multiplier = Complex(1.0, 1.0)
        val expected = Complex(1.0, 3.0)
        complex *= multiplier
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `times assign by double`() {
        val complex = Complex(2.0, 1.0)
        val factor = 2.0
        val expected = Complex(4.0, 2.0)
        complex *= factor
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `div assign by complex`() {
        val complex = Complex(2.0, 1.0)
        val divider = Complex(1.0, 1.0)
        val expected = Complex(1.5, -0.5)
        complex /= divider
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `div assign by double`() {
        val complex = Complex(2.0, 1.0)
        val divider = 2.0
        val expected = Complex(1.0, 0.5)
        complex /= divider
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `equals to other complex`() {
        val complex = Complex(2.0, 1.0)
        val other = Complex(2.0, 1.0)
        assertTrue(complex == other)
        assertFalse(complex != other)
    }


    @Test
    @DisplayName("")
    fun `not equals to other complex`() {
        val complex = Complex(2.0, 1.0)
        val other = Complex(1.0, 1.0)
        assertFalse(complex == other)
        assertTrue(complex != other)
    }

    @Test
    @DisplayName("")
    fun `equals to double`() {
        val complex = Complex(2.0, 0.0)
        val other = 2.0
        assertTrue(complex.equals(other))
        assertFalse(!complex.equals(other))
    }

    @Test
    @DisplayName("")
    fun `not equals to double`() {
        val complex = Complex(2.0, 0.0)
        val other = 1.0
        assertFalse(complex.equals(other))
        assertTrue(!complex.equals(other))
    }

    @Test
    @DisplayName("")
    fun `print positive`() {
        val mock = OutputMock()
        val complex = Complex(2.0, 1.0)
        out.write(complex)
        val expected = "2.0+1.0i"
        assertEquals(expected, mock.read())
        mock.destruct()
    }

    @Test
    @DisplayName("")
    fun `print negative`() {
        val mock = OutputMock()
        val complex = Complex(-2.0, -1.0)
        out.write(complex)
        val expected = "-2.0-1.0i"
        assertEquals(expected, mock.read())
        mock.destruct()
    }

    @Test
    @DisplayName("")
    fun `read positive`() {
        setSystemInput("4+2i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(4.0, 2.0)
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `read positive double`() {
        setSystemInput("4.2+2.8i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(4.2, 2.8)
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `read negative`() {
        setSystemInput("-4-2i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(-4.0, -2.0)
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `read negative double`() {
        setSystemInput("-4.2-2.8i")
        val complex = Complex()
        `in`.read(complex)
        val expected = Complex(-4.2, -2.8)
        assertComplexParts(expected, complex)
    }

    @Test
    @DisplayName("")
    fun `corrupted real part`() {
        setSystemInput("a+2i")
        val complex = Complex()
        assertThrows<IOException> { `in`.read(complex) }
    }

    @Test
    @DisplayName("")
    fun `corrupted imaginary part`() {
        setSystemInput("4+ai")
        val complex = Complex()
        assertThrows<IOException> { `in`.read(complex) }
    }

    @Test
    @DisplayName("")
    fun `add complex to double`() {
        val double = 2.0
        val complex = Complex(2.0, 1.0)
        val expected = Complex(4.0, 1.0)
        val actual = double + complex
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `subtract complex from double`() {
        val double = 2.0
        val complex = Complex(2.0, 1.0)
        val expected = Complex(0.0, 1.0)
        val actual = double - complex
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `multiply double by complex`() {
        val double = 2.0
        val complex = Complex(2.0, 1.0)
        val expected = Complex(4.0, 1.0)
        val actual = double * complex
        assertComplexParts(expected, actual)
    }

    @Test
    @DisplayName("")
    fun `divide double by complex`() {
        val double = 2.0
        val complex = Complex(2.0, 1.0)
        val expected = Complex(1.0, 1.0)
        val actual = double / complex
        assertComplexParts(expected, actual)
    }
}