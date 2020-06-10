package lab7.my_list

import org.junit.jupiter.api.Assertions.*

fun initArrayWithStringValue(array: MyList<String>, values: List<String>): MyList<String> {
    for (value in values) {
        array.pushBack(value)
    }
    return array
}

fun initArrayWithIntValue(array: MyList<Int>, values: List<Int>): MyList<Int> {
    for (value in values) {
        array.pushBack(value)
    }
    return array
}

fun assertNullStringIterator(it1: ListIterator<String>) {
    assertNull(it1.getCurrent())
}

fun assertNullIntIterator(it1: ListIterator<Int>) {
    assertNull(it1.getCurrent())
}

fun assertNotEqualsStringIterators(it1: ListIterator<String>, it2: ListIterator<String>) {
    assertNotEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertNotEqualsIntIterators(it1: ListIterator<Int>, it2: ListIterator<Int>) {
    assertNotEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsStringIterators(it1: ListIterator<String>, it2: ListIterator<String>) {
    assertEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsIntIterators(it1: ListIterator<Int>, it2: ListIterator<Int>) {
    assertEquals(it1.getCurrent(), it2.getCurrent())
}

fun assertEqualsIteratorValue(expected: String, it1: ListIterator<String>) {
    assertEquals(expected, it1.getCurrent())
}

fun assertEqualsIteratorValue(expected: Int, it1: ListIterator<Int>) {
    assertEquals(expected, it1.getCurrent())
}

fun assertStringValues(array: List<String>, it: ListIterator<String>) {
    for (value in array) {
        assertEqualsIteratorValue(value, it)
        it.next()
    }
}

fun assertIntValues(array: List<Int>, it: ListIterator<Int>) {
    for (value in array) {
        assertEqualsIteratorValue(value, it)
        it.next()
    }
}