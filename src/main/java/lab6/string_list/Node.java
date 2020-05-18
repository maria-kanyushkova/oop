package lab6.string_list;

public class Node {
    private Node prev = null;
    private Node next = null;
    private String value;

    Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
