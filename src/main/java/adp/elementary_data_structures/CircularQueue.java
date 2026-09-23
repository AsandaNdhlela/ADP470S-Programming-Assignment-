package adp.elementary_data_structures;

/**
 * CircularQueue
 * -----------------------------------------------------------
 * Part C of ADP470S Programming Assignment.
 *
 * A generic First-In-First-Out (FIFO) queue implemented from
 * scratch as a circular (ring) buffer over a fixed-size array.
 *
 * A plain array-based queue would waste space at the front once
 * elements are dequeued (that space is never reused). The circular
 * design fixes this by wrapping the front and rear indices back to
 * 0 with the modulo operator once they reach the end of the array,
 * so all capacity stays usable no matter how many enqueue/dequeue
 * cycles occur.
 *
 * Complexity summary (n = number of elements currently stored):
 *   enqueue : O(1)
 *   dequeue : O(1)
 *   peek    : O(1)
 *   Space   : O(capacity)
 * -----------------------------------------------------------
 */
public class CircularQueue<T> {

    private final Object[] data;
    private int front; // index of the current front element
    private int rear;  // index of the next free slot to insert into
    private int count; // number of elements currently stored
    private final int capacity;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    /**
     * Adds a value at the rear of the queue.
     * @throws RuntimeException if the queue is already full.
     */
    public void enqueue(T value) {
        if (isFull()) {
            throw new RuntimeException("Cannot enqueue: queue is full (capacity " + capacity + ").");
        }
        data[rear] = value;
        rear = (rear + 1) % capacity; // wrap around to the start if needed
        count++;
    }

    /**
     * Removes and returns the value at the front of the queue.
     * @throws RuntimeException if the queue is empty.
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot dequeue: queue is empty.");
        }
        T value = (T) data[front];
        data[front] = null; // avoid holding a stale reference
        front = (front + 1) % capacity; // wrap around to the start if needed
        count--;
        return value;
    }

    /**
     * Returns (without removing) the value at the front of the queue.
     * @throws RuntimeException if the queue is empty.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot peek: queue is empty.");
        }
        return (T) data[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public int size() {
        return count;
    }
}
