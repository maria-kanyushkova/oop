package lab7.my_array

import org.junit.jupiter.api.Assertions.*

fun initArrayWithStringValue(array: MyArray<String>, values: List<String>): MyArray<String> {
    for (value in values) {
        array.push(value)
    }
    return array
}

fun initArrayWithDoubleValue(array: MyArray<Double>, values: List<Double>): MyArray<Double> {
    for (value in values) {
        array.push(value)
    }
    return array
}

fun assertNullStringIterator(it1: ArrayIterator<String>) {
    assertNull(it1.getCurrent())
}

fun assertNullDoubleIterator(it1: ArrayIterator<Double>) {
    assertNull(it1.getCurrent())
}

fun assertNotEqualsStringIterators(it1: ArrayIterator<String>, it2: ArrayIterator<String>) {
    assertNotEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertNotEqualsDoubleIterators(it1: ArrayIterator<Double>, it2: ArrayIterator<Double>) {
    assertNotEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsStringIterators(it1: ArrayIterator<String>, it2: ArrayIterator<String>) {
    assertEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsDoubleIterators(it1: ArrayIterator<Double>, it2: ArrayIterator<Double>) {
    assertEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsIteratorValue(expected: String, it1: ArrayIterator<String>) {
    assertEquals(expected, it1.getCurrent())
}

fun assertEqualsIteratorValue(expected: Double, it1: ArrayIterator<Double>) {
    assertEquals(expected, it1.getCurrent())
}

fun assertStringValues(array: List<String>, it: ArrayIterator<String>) {
    for (value in array) {
        assertEqualsIteratorValue(value, it)
        it.next()
    }
}

fun assertDoubleValues(array: List<Double>, it: ArrayIterator<Double>) {
    for (value in array) {
        assertEqualsIteratorValue(value, it)
        it.next()
    }
}