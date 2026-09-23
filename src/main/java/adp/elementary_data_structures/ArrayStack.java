package adp.elementary_data_structures;

/**
 * ArrayStack
 * -----------------------------------------------------------
 * Part C of ADP470S Programming Assignment.
 *
 * A generic Last-In-First-Out (LIFO) stack, implemented from
 * scratch using a plain array (no java.util.Stack used).
 *
 * The array starts at a fixed initial capacity and automatically
 * doubles in size if it fills up, so the stack behaves as
 * "unbounded" from the caller's point of view while still being
 * array-based underneath, as the brief requires.
 *
 * Complexity summary (n = number of elements currently stored):
 *   push  : O(1) amortised — occasionally O(n) when the internal
 *           array must grow and every element is copied across.
 *   pop   : O(1)
 *   peek  : O(1)
 *   Space : O(n)
 * -----------------------------------------------------------
 */
public class ArrayStack<T> {

    private Object[] data;
    private int top; // index of the next free slot (also = current size)

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        data = new Object[DEFAULT_CAPACITY];
        top = 0;
    }

    /** Pushes a value onto the top of the stack. */
    public void push(T value) {
        if (top == data.length) {
            resize(data.length * 2);
        }
        data[top] = value;
        top++;
    }

    /**
     * Removes and returns the top value.
     * @throws RuntimeException if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot pop from an empty stack.");
        }
        top--;
        T value = (T) data[top];
        data[top] = null; // avoid holding a stale reference
        return value;
    }

    /**
     * Returns (without removing) the top value.
     * @throws RuntimeException if the stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot peek an empty stack.");
        }
        return (T) data[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    /** Grows the backing array when it runs out of space. */
    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, top);
        data = newData;
    }
}
