package lab6.string_list;

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
        if (first == null) {
            first = new Node(value);
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
        Node node = new Node(value);
        if (iterator.getCurrentNode() == first) {
            Node curr = first;
            first = node;
            node.setNext(curr);
            curr.setPrev(node);
        } else if (!iterator.hasNext()) {
            Node prev = last;
            last = node;
            last.setPrev(prev);
            prev.setNext(last);
        } else {
            Node curr = iterator.getCurrentNode();
            Node prev = curr.getPrev();
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(curr);
            curr.setPrev(node);
        }
        counter++;
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

    String get(int index) {
        if (index > counter || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (first == null) {
            throw new NullPointerException("List is empty!");
        }
        Node next = first;
        for (int i = 0; i < index; i++) {
            next = next.getNext();
        }
        return next.getValue();
    }

    void erase(ListIterator iterator) throws Exception {
        if (iterator.getList() != this) {
            throw new Exception("Incorrect iterator!");
        }
        Node curr = iterator.getCurrentNode();
        Node prev = curr.getPrev();
        Node next = curr.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        if (curr == first) {
            first = next;
        }
        counter--;
    }

    boolean isEmpty() {
        return counter == 0;
    }

    int size() {
        return counter;
    }

    @Override
    public ListIterator iterator() {
        return new ListIterator(first, this);
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