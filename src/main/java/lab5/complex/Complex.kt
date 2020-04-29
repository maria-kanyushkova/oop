package lab5.complex

import org.apache.commons.lang3.math.NumberUtils
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.atan2

class Complex(private var real: Double = 0.0, private var image: Double = 0.0) {
    companion object {
        const val EPS = 10e-15
    }

    fun re(): Double {
        return real
    }

    fun im(): Double {
        return image
    }

    fun getMagnitude(): Double {
        return sqrt(real.pow(2) + image.pow(2))
    }

    fun getArgument(): Double {
        return atan2(image, real)
    }

    operator fun plus(addend: Complex): Complex {
        return Complex(real + addend.re(), image + addend.im())
    }

    operator fun plus(addend: Double): Complex {
        return Complex(real + addend, image)
    }

    operator fun minus(subtrahend: Complex): Complex {
        return Complex(real - subtrahend.re(), image - subtrahend.im())
    }

    operator fun minus(subtrahend: Double): Complex {
        return Complex(real - subtrahend, image)
    }

    operator fun times(multiplier: Complex): Complex {
        val left = (real * multiplier.re()) - (image * multiplier.im())
        val right = (real * multiplier.im()) + (image * multiplier.re())
        return Complex(left, right)
    }

    operator fun times(factor: Double): Complex {
        return this * Complex(factor)
    }

    operator fun div(divider: Complex): Complex {
        val common = (divider.re().pow(2) + divider.im().pow(2))
        val left = ((real * divider.re()) + (image * divider.im())) / common
        val right = ((image * divider.re()) - (real * divider.im())) / common
        return Complex(left, right)
    }

    operator fun div(divider: Double): Complex {
        return this / Complex(divider)
    }

    operator fun unaryPlus(): Complex {
        return Complex(real, image)
    }

    operator fun unaryMinus(): Complex {
        return Complex(-real, -image)
    }

    operator fun plusAssign(addend: Complex) {
        val complex = this + addend
        real = complex.re()
        image = complex.im()
    }

    operator fun plusAssign(addend: Double) {
        this += Complex(addend)
    }

    operator fun minusAssign(subtrahend: Complex) {
        val complex = this - subtrahend
        real = complex.re()
        image = complex.im()
    }

    operator fun minusAssign(subtrahend: Double) {
        this -= Complex(subtrahend)
    }

    operator fun timesAssign(multiplier: Complex) {
        val complex = this * multiplier
        real = complex.re()
        image = complex.im()
    }

    operator fun timesAssign(factor: Double) {
        val complex = this * factor
        real = complex.re()
        image = complex.im()
    }

    operator fun divAssign(divider: Complex) {
        val complex = this / divider
        real = complex.re()
        image = complex.im()
    }

    operator fun divAssign(divider: Double) {
        val complex = this / divider
        real = complex.re()
        image = complex.im()
    }

    override operator fun equals(other: Any?): Boolean {
        return when (other) {
            is Complex -> eq(real, other.re()) && eq(image, other.im())
            is Double -> {
                val complex = Complex(other)
                return eq(real, complex.re()) && eq(image, complex.im())
            }
            else -> return false
        }
    }

    private fun eq(left: Double, right: Double): Boolean {
        return abs(left - right) < EPS
    }
}

operator fun Double.plus(other: Complex): Complex {
    return Complex(this + other.re(), other.im())
}

operator fun Double.minus(other: Complex): Complex {
    return Complex(this - other.re(), other.im())
}

operator fun Double.times(other: Complex): Complex {
    return Complex(this * other.re(), other.im())
}

operator fun Double.div(other: Complex): Complex {
    return Complex(this / other.re(), other.im())
}

fun OutputStream.write(complex: Complex) {
    val sign = if (complex.im() < 0) '-' else '+'
    val str = "${complex.re()}$sign${abs(complex.im())}i"
    write(str.toByteArray())
}

fun InputStream.read(complex: Complex) {
    val plus = '+'.toInt()
    val minus = '-'.toInt()
    val suffix = 'i'.toInt()

    var input = read()
    val unary =
            if (input == minus) {
                input = read()
                '-'
            } else '+'

    var re = ""
    while (input != plus && input != minus) {
        re += input.toChar()
        input = read()
    }

    val sign = input.toChar()
    input = read()

    var im = ""
    while (input != suffix && input != -1) {
        im += input.toChar()
        input = read()
    }

    if (!NumberUtils.isCreatable(re)) {
        throw IOException("Something is wrong with real part!")
    }
    if (!NumberUtils.isCreatable(im)) {
        throw IOException("Something is wrong with imaginary part!")
    }

    val real = NumberUtils.toDouble(re)
    val image = NumberUtils.toDouble(im)
    complex += Complex(if (unary == '-') -real else real, if (sign == '-') -image else image)
}