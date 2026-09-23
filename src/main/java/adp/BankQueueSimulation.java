package adp;

import adp.elementary_data_structures.CircularQueue;

/**
 * BankQueueSimulation
 * -----------------------------------------------------------
 * Part C demonstration: using CircularQueue to simulate a single-teller bank queue.
 *
 * Customers arrive and are enqueued (join the back of the line).
 * The teller serves customers by dequeuing from the front, which is exactly the fairness rule a real queue enforces: first in,
 * first served.
 *
 * Also demonstrates why the CIRCULAR design matters in practice: as customers are served and new ones arrive, the queue reuses
 * array slots freed near the front instead of running out of room, even though the underlying array never grows.
 * -----------------------------------------------------------
 */
public class BankQueueSimulation {

    public static void main(String[] args) {
        // A small teller counter that can only hold 3 waiting customers
        // at once, to make the "full queue" and wrap-around behaviour visible.
        CircularQueue<String> tellerLine = new CircularQueue<>(3);

        System.out.println("--- Bank Queue Simulation (capacity = 3) ---\n");

        // Morning: three customers arrive back to back.
        enqueueCustomer(tellerLine, "Thandiwe");
        enqueueCustomer(tellerLine, "Sipho");
        enqueueCustomer(tellerLine, "Naledi");

        // The line is now full - a fourth arrival must wait until space opens.
        enqueueCustomer(tellerLine, "Johan"); // expected to fail: queue full

        // The teller serves the first customer.
        serveCustomer(tellerLine);

        // With a slot free, a new customer can now join the (wrapped-around) queue.
        enqueueCustomer(tellerLine, "Johan");

        // Continue serving until the line is empty.
        serveCustomer(tellerLine);
        serveCustomer(tellerLine);
        serveCustomer(tellerLine);

        // Attempting to serve an empty queue.
        serveCustomer(tellerLine); // expected to fail: queue empty
    }

    private static void enqueueCustomer(CircularQueue<String> queue, String name) {
        try {
            queue.enqueue(name);
            System.out.println(name + " joined the line. (Currently waiting: " + queue.size() + ")");
        } catch (RuntimeException e) {
            System.out.println(name + " could NOT join the line: " + e.getMessage());
        }
    }

    private static void serveCustomer(CircularQueue<String> queue) {
        try {
            String served = queue.dequeue();
            System.out.println("Teller is now serving: " + served + ". (Still waiting: " + queue.size() + ")");
        } catch (RuntimeException e) {
            System.out.println("Teller idle: " + e.getMessage());
        }
    }
}
