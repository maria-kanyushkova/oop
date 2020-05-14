package lab6.string_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator implements Iterator<String> {
    private Node current;
    private StringList list;

    ListIterator(Node current, StringList list) {
        this.current = current;
        this.list = list;
    }

    Node getCurrentNode() {
        return current;
    }

    String getCurrent() {
        if (current == null) {
            throw new NoSuchElementException("Iterator doesn't point to next element!");
        }
        return current.getValue();
    }

    StringList getList() {
        return list;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public String next() {
        Node next = current;
        if (next == null) {
            throw new NoSuchElementException("Iterator doesn't point to next element!");
        }
        current = current.getNext();
        return next.getValue();
    }

    public boolean hasPrev() {
        return current != null;
    }

    public String prev() {
        Node prev = current;
        if (prev == null) {
            throw new NoSuchElementException("Iterator doesn't point to prev element!");
        }
        current = current.getPrev();
        return prev.getValue();
    }
}