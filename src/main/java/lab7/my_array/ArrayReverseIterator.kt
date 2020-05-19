package lab7.my_array

class ArrayReverseIterator<T>(current: Node<T>? = null, array: MyArray<T>) : ArrayIterator<T>(current, array) {
    override operator fun hasNext(): Boolean {
        return current != null
    }

    override operator fun next(): T {
        val prev = current ?: throw NoSuchElementException("Iterator doesn't point to next element!")
        current = current!!.getPrev()
        return prev.getValue()!!
    }
}