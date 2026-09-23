package adp.elementary_data_structures;

/**
 * SinglyLinkedList
 * -----------------------------------------------------------
 * Part C of ADP470S Programming Assignment.
 *
 * A generic singly linked list implemented from scratch (no
 * java.util.LinkedList used). Each node holds a value and a
 * reference to the next node only.
 *
 * Complexity summary (n = number of elements currently stored):
 *   insertAtFront : O(1)      — no traversal needed.
 *   insertAtEnd   : O(n)      — must walk to the last node
 *                               (a tail pointer would make this O(1);
 *                               omitted here to keep the structure a
 *                               plain textbook singly linked list).
 *   delete        : O(n)      — must find the node/value first.
 *   search        : O(n)      — worst case scans every node.
 *   Space         : O(n) total, O(1) extra per operation.
 * -----------------------------------------------------------
 */
public class SinglyLinkedList<T> {

    /** A single node in the list: a value plus a pointer to the next node. */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /** Inserts a new value at the front of the list. O(1). */
    public void insertAtFront(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Inserts a new value at the end of the list. O(n). */
    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Deletes the first node containing the given value.
     *
     * @return true if a matching node was found and removed, false otherwise.
     */
    public boolean delete(T value) {
        if (head == null) {
            return false;
        }

        // Special case: the value to remove is in the head node.
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return true;
        }

        // General case: keep a trailing pointer so we can unlink 'current'.
        Node<T> previous = head;
        Node<T> current = head.next;
        while (current != null) {
            if (current.data.equals(value)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false; // value not found
    }

    /** Searches for a value. @return true if present, false otherwise. O(n). */
    public boolean search(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
