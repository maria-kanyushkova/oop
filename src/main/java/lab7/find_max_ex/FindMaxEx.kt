package lab7.find_max_ex

import kotlin.comparisons.compareValues

fun <T : Comparable<T>> defaultCompare(value: T, maxValue: T): Boolean {
    return compareValues(value, maxValue) >= 0
}

fun <T : Comparable<T>> findMax(values: ArrayList<T>): T? {
    return findMaxEx(values) { maxValue: T, value: T -> compareValues(maxValue, value) <= 0}
}

fun <T, L : (value: T, maxValue: T) -> Boolean> findMaxEx(values: ArrayList<T>, less: L): T? {
    if (values.isEmpty()) {
        return null
    }
    var max = values[0]
    val end = values.size - 1
    for (i in 1..end) {
        if (less(max, values[i])) max = values[i]
    }
    return max
}

