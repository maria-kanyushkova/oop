package lab7.my_array

open class ArrayIterator<T>(var current: Node<T>? = null, var array: MyArray<T>) : Iterable<T>, Iterator<T> {
    fun getCurrentNode(): Node<T>? {
        return current
    }

    fun getCurrent(): T? {
        return if (current == null) null else current!!.getValue()
    }

    fun getList(): MyArray<T>? {
        return array
    }

    override operator fun hasNext(): Boolean {
        return current != null
    }

    override operator fun next(): T {
        val next = current ?: throw NoSuchElementException("Iterator doesn't point to next element!")
        current = current!!.getNext()
        return next.getValue()!!
    }

    override fun iterator(): Iterator<T> {
        return this
    }
}