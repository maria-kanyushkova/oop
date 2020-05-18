package lab6.string_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator implements Iterator<String> {
    protected Node current;
    protected StringList list;

    ListIterator(Node current, StringList list) {
        this.current = current;
        this.list = list;
    }

    Node getCurrentNode() {
        return current;
    }

    String getCurrent() {
        return current == null ? null : current.getValue();
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
}