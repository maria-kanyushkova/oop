package lab6.string_list;

import java.util.Iterator;
import java.util.function.Consumer;

public class StringList implements Iterable<String> {
    private int counter = 0;
    private Node first = null;
    private Node last = null;

    void pushBack(String value) {
        if (counter == 0) {
            first = new Node(value);
            last = first;
        } else {
            Node prev = last;
            last = new Node(value);
            last.setPrev(prev);
            prev.setNext(last);
        }
        counter++;
    }

    void pushFront(String value) {
        if (counter == 0) {
            first = new Node(value);
            last = first;
        } else {
            Node node = new Node(value);
            first.setPrev(node);
            node.setNext(first);
            first = node;
        }
        counter++;
    }


    void insert(ListIterator iterator, String value) throws Exception {
        if (iterator.getList() != this) {
            throw new Exception("Incorrect iterator!");
        }
        if (iterator.getCurrentNode() == first) {
            pushFront(value);
        } else if (!iterator.hasNext()) {
            pushBack(value);
        } else {
            Node node = new Node(value);
            Node current = iterator.getCurrentNode();
            Node prev = current.getPrev();
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(current);
            current.setPrev(node);
            counter++;
        }
    }

    void clear() {
        if (first == null) {
            return;
        }
        for (int i = counter; i > 0; --i) {
            if (last != null) {
                last.setNext(null);
                last = last.getPrev();
            }
        }
        first = null;
        counter = 0;
    }

    void erase(ListIterator iterator) throws Exception {
        if (iterator.getList() != this) {
            throw new Exception("Incorrect iterator!");
        }
        Node current = iterator.getCurrentNode();
        if (current == null) {
            throw new Exception("Current element is null!");
        }
        Node prev = current.getPrev();
        Node next = current.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        if (current == first) {
            first = next;
        }
        if (current == last) {
            last = prev;
        }
        counter--;
    }

    boolean isEmpty() {
        return counter == 0;
    }

    int size() {
        return counter;
    }

    public final ListIterator begin() {
        return new ListIterator(first, this);
    }

    public final ListIterator end() {
        return new ListIterator(last, this);
    }

    public final ListIterator rbegin() {
        return new ListReverseIterator(last, this);
    }

    public final ListIterator rend() {
        return new ListReverseIterator(first, this);
    }

    @Override
    public Iterator<String> iterator() {
        return begin();
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        if (first == null) {
            return;
        }
        Node it = first;
        for (int i = 0; i < counter; i++) {
            action.accept(it.getValue());
            it = first.getNext();
        }
    }
}