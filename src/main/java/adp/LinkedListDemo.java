package adp;

import adp.elementary_data_structures.SinglyLinkedList;

/**
 * LinkedListDemo
 * -----------------------------------------------------------
 * Part C demonstration: exercising insert, delete, and search
 * on SinglyLinkedList with simple, readable test cases.
 * -----------------------------------------------------------
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        System.out.println("--- Singly Linked List Demo ---");

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtFront(5);
        System.out.println("After inserts: " + list + " (size=" + list.size() + ")");

        System.out.println("search(20): " + list.search(20));
        System.out.println("search(99): " + list.search(99));

        boolean removed = list.delete(20);
        System.out.println("delete(20) removed? " + removed + " -> " + list + " (size=" + list.size() + ")");

        boolean removedMissing = list.delete(99);
        System.out.println("delete(99) removed? " + removedMissing);

        list.delete(5); // remove head
        System.out.println("After deleting head (5): " + list);

        System.out.println("isEmpty(): " + list.isEmpty());
    }
}
