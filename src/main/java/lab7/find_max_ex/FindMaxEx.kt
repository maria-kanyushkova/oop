package lab7.find_max_ex

import kotlin.comparisons.compareValues

fun <T : Comparable<T>> defaultCompare(value: T, maxValue: T): Boolean {
    return compareValues(value, maxValue) >= 0
}

// todo: изменить на ::defaultCompare
fun <T : Comparable<T>> findMax(values: ArrayList<T>): T? {
    return findMaxEx(values, {value: T, maxValue: T -> compareValues(value, maxValue) >= 0})
}

fun <T, L : (value: T, maxValue: T) -> Boolean> findMaxEx(values: ArrayList<T>, less: L): T? {
    if (values.isEmpty()) {
        return null
    }
    var max = values[0]
    val end = values.size - 1
    for (i in 1..end) {
        if (less(values[i], max)) max = values[i]
    }
    return max
}

