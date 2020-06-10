package lab7.my_list

import java.util.function.Consumer

class MyList<T> : Iterable<T> {
    private var counter: Int = 0
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    fun pushBack(value: T?) {
        if (counter == 0) {
            first = Node(value)
            last = first
        } else {
            val prev = last
            last = Node(value)
            last!!.setPrev(prev!!)
            prev.setNext(last!!)
        }
        counter++
    }

    fun pushFront(value: T?) {
        if (counter == 0) {
            first = Node(value)
            last = first
        } else {
            val node = Node(value)
            first!!.setPrev(node)
            node.setNext(first)
            first = node
        }
        counter++
    }

    fun insert(iterator: ListIterator<T>, value: T?) {
        if (iterator.getList() !== this) {
            throw Exception("Incorrect iterator!")
        }
        if (iterator.getCurrentNode() === first) {
            pushFront(value)
        } else if (!iterator.hasNext()) {
            pushBack(value)
        } else {
            val node = Node(value)
            val current = iterator.getCurrentNode()
            val prev = current!!.getPrev()
            prev!!.setNext(node)
            node.setPrev(prev)
            node.setNext(current)
            current.setPrev(node)
            counter++
        }
    }

    fun erase(iterator: ListIterator<T>) {
        if (iterator.getList() !== this) {
            throw Exception("Incorrect iterator!")
        }
        val current = iterator.getCurrentNode() ?: throw Exception("Current element is null!")
        val prev = current.getPrev()
        val next = current.getNext()
        prev?.setNext(next)
        if (prev != null) {
            next?.setPrev(prev)
        }
        if (current === first) {
            first = next
        }
        if (current === last) {
            last = prev
        }
        counter--
    }

    fun size(): Int {
        return counter
    }

    fun begin(): ListIterator<T> {
        return ListIterator(first, this)
    }

    fun end(): ListIterator<T> {
        return ListIterator(last, this)
    }

    fun rbegin(): ListIterator<T> {
        return ListReverseIterator(last, this)
    }

    fun rend(): ListIterator<T> {
        return ListReverseIterator(first, this)
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

