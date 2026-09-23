package adp;

import adp.elementary_data_structures.ArrayStack;

/**
 * StringReverserDemo
 * -----------------------------------------------------------
 * Part C demonstration: using ArrayStack to reverse a string.
 *
 * Because a stack is LIFO, pushing every character of a string
 * on and then popping them all off naturally yields the
 * characters in reverse order.
 *
 * Time complexity:  O(n) — each of the n characters is pushed
 *                    once and popped once.
 * Space complexity: O(n) — the stack holds all n characters at
 *                    its fullest point.
 * -----------------------------------------------------------
 */
public class StringReverserDemo {

    /** Reverses a string using ArrayStack<Character>. */
    public static String reverse(String input) {
        ArrayStack<Character> stack = new ArrayStack<>();

        // Push every character onto the stack.
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Pop them all off; LIFO order reconstructs the string reversed.
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }

        return reversed.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "ADP470S",
                "Recursion",
                "CPUT",
                "A"
        };

        System.out.println("--- Stack-based String Reversal ---");
        for (String test : testCases) {
            System.out.println("\"" + test + "\" -> \"" + reverse(test) + "\"");
        }
    }
}
