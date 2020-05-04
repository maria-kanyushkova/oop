package lab5.string

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MyString constructor() {
    private var length: Int = 0
    private var string: Array<Char> = emptyArray()

    constructor(string: Array<Char>) : this() {
        this.length = string.size
        this.string = string
    }

    constructor(string: Array<Char>, length: Int) : this() {
        if (length < 0 || length > string.size) {
            throw StringIndexOutOfBoundsException("Index is out of range")
        }
        this.length = length
        this.string = copyCharArray(string, length)
    }

    constructor(other: MyString) : this() {
        this.length = other.length
        this.string = copyCharArray(other.string, other.length)
    }

    constructor(other: String) : this() {
        this.length = other.length
        this.string = stringToCharArray(other)
    }

    fun getLength(): Int {
        return length
    }

    fun getStringData(): Array<Char> {
        val nullChar = '\u0000'
        return string + nullChar
    }

    fun substring(start: Int, length: Int): MyString {
        if (start >= this.length || start < 0 || start + length > this.length) {
            throw StringIndexOutOfBoundsException("Index is out of range")
        }
        if (this.string.isEmpty()) {
            return MyString("")
        }
        val range = IntRange(start, start + length - 1)
        return MyString(this.string.sliceArray(range), length)
    }

    fun clear() {
        length = 0
        string = emptyArray()
    }

    operator fun plus(other: MyString): MyString {
        return MyString(string + other.string)
    }

    operator fun plus(other: String): MyString {
        return MyString(string + stringToCharArray(other))
    }

    operator fun plus(other: Array<Char>): MyString {
        return MyString(string + other)
    }

    operator fun plusAssign(other: MyString) {
        string += other.string
        length += other.length
    }

    operator fun plusAssign(other: String) {
        string += stringToCharArray(other)
        length += other.length
    }

    override operator fun equals(other: Any?): Boolean {
        return when (other) {
            is MyString -> string.contentEquals(other.string)
            is String -> string.contentEquals(stringToCharArray(other))
            else -> return false
        }
    }

    operator fun set(index: Int, value: Char) {
        string[index] = value
    }

    operator fun get(index: Int): Char {
        return string[index]
    }

    operator fun compareTo(other: MyString): Int {
        return string.joinToString("").compareTo(other.string.joinToString(""))
    }

    operator fun compareTo(other: String): Int {
        return string.joinToString("").compareTo(other)
    }

    private fun stringToCharArray(string: String): Array<Char> {
        var array = emptyArray<Char>()
        string.forEach { ch -> array += ch }
        return array
    }

    private fun copyCharArray(chars: Array<Char>, length: Int): Array<Char> {
        return Array(length) { i -> chars[i] }
    }
}

fun OutputStream.write(string: MyString) {
    val str = string.getStringData().joinToString("")
    write(str.toByteArray())
}

fun InputStream.read(string: MyString) {
    val input = readLine() ?: throw IOException("Cannot read from input!")
    string += input
}