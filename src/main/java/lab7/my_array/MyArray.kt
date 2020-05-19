package lab7.my_array

import java.util.function.Consumer

class MyArray<T>: Iterable<T> {
    private var counter: Int = 0
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    fun push(value: T?) {
        if (counter == 0) {
            first = Node(value)
            last = first
        } else {
            val prev = last
            last = Node(value)
            last!!.setPrev(prev!!)
            prev.setNext(last!!)
        }
    }

    fun size(): Int {
        return counter
    }

    operator fun get(index: Int): T? {
        if (index > counter || index < 0) throw IndexOutOfBoundsException("Index out of range")
        if (first == null) throw NullPointerException("List is empty!")
        var next = first
        for (i in 1..index) next = next!!.getNext()
        return next!!.getValue()
    }

    fun resize(length: Int) {
        if (length <= counter) {
            var node = first
            for (i in 0..length) {
                node = node!!.getNext()
            }
            for (i in counter..length) {
                if (last != null) {
                    last!!.setNext(null)
                    last = last!!.getPrev()
                }
            }
        } else {
            for (i in counter..length) {
                push(null)
            }
        }
    }

    fun clear() {
        if (first == null) {
            return
        }
        for (i in counter downTo 1) {
            if (last != null) {
                last!!.setNext(null)
                last = last!!.getPrev()
            }
        }
        first = null
        counter = 0
    }

    fun begin(): ArrayIterator<T> {
        return ArrayIterator(first, this)
    }

    fun end(): ArrayIterator<T> {
        return ArrayIterator(last, this)
    }

    fun rbegin(): ArrayIterator<T> {
        return ArrayReverseIterator(last, this)
    }

    fun rend(): ArrayIterator<T> {
        return ArrayReverseIterator(first, this)
    }

    override fun forEach(action: Consumer<in T>?) {
        if (first == null) return
        var it = first
        for (i in 0..counter) {
            action!!.accept(it!!.getValue()!!)
            it = first!!.getNext()
        }
    }

    override fun iterator(): Iterator<T> {
        return begin()
    }
}

