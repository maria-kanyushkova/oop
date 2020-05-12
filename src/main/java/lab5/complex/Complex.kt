package lab5.complex

import org.apache.commons.lang3.math.NumberUtils
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

class Complex(private var real: Double = 0.0, private var imaginary: Double = 0.0) {
    val re: Double
        get() {
            return real
        }

    val im: Double
        get() {
            return imaginary
        }

    val getMagnitude: Double
        get() {
            return sqrt(real.pow(2) + imaginary.pow(2))
        }

    val getArgument: Double
        get() {
            return atan2(imaginary, real)
        }

    operator fun plus(addend: Complex): Complex {
        return Complex(real + addend.re, imaginary + addend.im)
    }

    operator fun plus(addend: Double): Complex {
        return Complex(real + addend, imaginary)
    }

    operator fun minus(subtrahend: Complex): Complex {
        return Complex(real - subtrahend.re, imaginary - subtrahend.im)
    }

    operator fun minus(subtrahend: Double): Complex {
        return Complex(real - subtrahend, imaginary)
    }

    operator fun times(multiplier: Complex): Complex {
        val realPart = (real * multiplier.re) - (imaginary * multiplier.im)
        val imaginaryPart = (real * multiplier.im) + (imaginary * multiplier.re)
        return Complex(realPart, imaginaryPart)
    }

    operator fun times(multiplier: Double): Complex {
        return this * Complex(multiplier)
    }

    operator fun div(divider: Complex): Complex {
        val common = (divider.re.pow(2) + divider.im.pow(2))
        val realPart = ((real * divider.re) + (imaginary * divider.im)) / common
        val imaginaryPart = ((imaginary * divider.re) - (real * divider.im)) / common
        return Complex(realPart, imaginaryPart)
    }

    operator fun div(divider: Double): Complex {
        return this / Complex(divider)
    }

    operator fun unaryPlus(): Complex {
        return Complex(real, imaginary)
    }

    operator fun unaryMinus(): Complex {
        return Complex(-real, -imaginary)
    }

    operator fun plusAssign(addend: Complex) {
        val complex = this + addend
        real = complex.re
        imaginary = complex.im
    }

    operator fun plusAssign(addend: Double) {
        this += Complex(addend)
    }

    operator fun minusAssign(subtrahend: Complex) {
        val complex = this - subtrahend
        real = complex.re
        imaginary = complex.im
    }

    operator fun minusAssign(subtrahend: Double) {
        this -= Complex(subtrahend)
    }

    operator fun timesAssign(multiplier: Complex) {
        val complex = this * multiplier
        real = complex.re
        imaginary = complex.im
    }

    operator fun timesAssign(factor: Double) {
        val complex = this * factor
        real = complex.re
        imaginary = complex.im
    }

    operator fun divAssign(divider: Complex) {
        val complex = this / divider
        real = complex.re
        imaginary = complex.im
    }

    operator fun divAssign(divider: Double) {
        val complex = this / divider
        real = complex.re
        imaginary = complex.im
    }

    override operator fun equals(other: Any?): Boolean {
        return when (other) {
            is Complex -> eq(real, other.re) && eq(imaginary, other.im)
            is Double -> {
                val complex = Complex(other)
                return eq(real, complex.re) && eq(imaginary, complex.im)
            }
            else -> return false
        }
    }

    private fun eq(left: Double, right: Double): Boolean {
        return abs(left - right) < 10e-15
    }
}

operator fun Double.plus(addend: Complex): Complex {
    return Complex(this + addend.re, addend.im)
}

operator fun Double.minus(subtrahend: Complex): Complex {
    return Complex(this - subtrahend.re, subtrahend.im)
}

operator fun Double.times(multiplier: Complex): Complex {
    return Complex(this * multiplier.re, multiplier.im)
}

operator fun Double.div(divider: Complex): Complex {
    return Complex(this / divider.re, divider.im)
}

fun OutputStream.write(number: Complex) {
    val sign = if (number.im < 0) '-' else '+'
    val str = "${number.re}$sign${abs(number.im)}i"
    write(str.toByteArray())
}

fun InputStream.read(complex: Complex) {
    var input = read()
    val unary =
            if (input == '-'.toInt()) {
                input = read()
                '-'
            } else '+'

    var realPart = ""
    while (input != '+'.toInt() && input != '-'.toInt()) {
        realPart += input.toChar()
        input = read()
    }
    if (!NumberUtils.isCreatable(realPart)) {
        throw IOException(realPart + "не вещественное число")
    }

    val sign = input.toChar()
    input = read()

    var imaginaryPart = ""
    while (input != 'i'.toInt() && input != -1) {
        imaginaryPart += input.toChar()
        input = read()
    }
    if (!NumberUtils.isCreatable(imaginaryPart)) {
        throw IOException(imaginaryPart + "не вещественное число")
    }

    val real = NumberUtils.toDouble(realPart)
    val image = NumberUtils.toDouble(imaginaryPart)
    complex += Complex(
            if (unary == '-') -real else real,
            if (sign == '-') -image else image
    )
}
