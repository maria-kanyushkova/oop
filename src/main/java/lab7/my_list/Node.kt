package lab7.my_list

class Node<T>(private var value: T? = null) {
    private var prev: Node<T>? = null
    private var next: Node<T>? = null

    fun getValue(): T? {
        return value
    }

    fun getNext(): Node<T>? {
        return next
    }

    fun setNext(next: Node<T>?) {
        this.next = next
    }

    fun getPrev(): Node<T>? {
        return prev
    }

    fun setPrev(prev: Node<T>) {
        this.prev = prev
    }
}
