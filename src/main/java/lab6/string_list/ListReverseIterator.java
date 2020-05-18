package lab6.string_list;

import java.util.NoSuchElementException;

public class ListReverseIterator extends ListIterator {
    ListReverseIterator(Node current, StringList list) {
        super(current, list);
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public String next() {
        Node prev = current;
        if (prev == null) {
            throw new NoSuchElementException("Iterator doesn't point to prev element!");
        }
        current = current.getPrev();
        return prev.getValue();
    }
}
