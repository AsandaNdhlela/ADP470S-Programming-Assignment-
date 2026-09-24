Programming Assignment: ADP470S
Weight: 20% of Final Grade
Deadline: 9 October 2026
Objective
This assignment evaluates your ability to:
• Implement recursive algorithms.
• Apply searching and sorting techniques.
• Analyze algorithmic complexity using Big-O notation.
• Implement and use fundamental data structures (Lists, Stacks, Queues, Binary Search Trees, Graphs).
• Solve real-world problems using appropriate data structures and algorithms.
Instructions
1. You may use Java, C++, C#, or Python. Ensure your code is object-oriented.
2. Code must be well-documented with comments and proper indentation.
3. Provide a short report (2–3 pages) explaining design decisions and complexity analysis for each part.
4. Submit both source code and report.
Part A: Recursive Algorithms (15%)
1. Write a recursive algorithm to:
a) Compute the factorial of a number.
b) Generate the nth Fibonacci number.
In your report, explain the time and space complexity of each recursive solution, and compare them with iterative
approaches.
Part B: Searching and Sorting (20%)
1. Implement the following sorting algorithms:
a) Selection Sort (iterative).
b) Quick Sort (recursive).
c) Heap Sort.
2. Implement Binary Search (recursive and iterative).
Compare the sorting algorithms experimentally on a dataset of 1,000 random integers, and provide an analysis of
their performance. Present the results in a table or graph, and analyze them in terms of Big-O complexity and
practical performance. (I suggest using Excel for this purpose — i.e., to generate the random numbers and then
observe each algorithm's execution time on the same dataset.)
Part C: Elementary Data Structures (20%)
1. Implement the following from scratch:
a) Singly Linked List (with insert, delete, search).
b) Stack (array-based).
c) Queue (circular array implementation).
2. Demonstrate usage with test cases (e.g., reversing a string using a stack, simulating a queue at a bank).
Part D: Trees (25%)
1. Implement a Binary Search Tree (BST) with insert, search, delete, and in-order, pre-order, and post-order
traversal.
Report on the differences in complexity (average-case O(log n) vs. worst-case O(n)).
Part E: Graphs (20%)
1. Represent a graph using an Adjacency Matrix.
2. Implement graph traversal algorithms:
a) Depth-First Search (DFS) – recursive.
b) Breadth-First Search (BFS) – using a queue.
3. Apply your algorithms to solve a real-world problem: find the shortest path (in terms of number of edges)
between two cities in a transport network.
Deliverables
1. Source code (organized by part).
2. Report (PDF or DOCX) including:
a) Explanation of the data structures and algorithms used.
b) Complexity analysis of each algorithm.
c) Experimental results (tables/graphs) where necessary
d) Reflections on the differences between theory and practice.
Marking Criteria
Section Marks Criteria
Part A 15 Correct recursive implementations + complexity discussion
Part B 20 Correct sorting/searching + performance analysis
Part C 20 Proper implementation of lists, stacks, queues + applications
Part D 25 BST implementation + comparison analysis
Part E 20 Graph representations, BFS/DFS correctness, real-world application
Total 100

Part A: recursive factorial and Fibonacci, each paired with an iterative version so you can compare them directly, with comments explaining the logic and complexity inline.
Factorial
•	Recursive: O(n) time (one call per decrement to the base case), O(n) space (n stack frames stacked up before unwinding).
•	Iterative: O(n) time, but O(1) space, no call stack growth, just a loop counter and accumulator.
•	Comparison: same time complexity, but iterative wins on space since it avoids stack overhead entirely. For large n, recursion also risks a StackOverflowError in Java, which the iterative version can't hit.
Fibonacci
•	Recursive (naive): O(2ⁿ) time,  the call tree branches in two at every step, and the same subproblems (e.g. fib(n-2)) get recomputed many times over. Space is O(n), since only the depth of the tree matters for stack usage, not its total size.
•	Iterative: O(n) time, O(1) space, it just walks forward once, keeping two running values.
•	Comparison: this is the sharper contrast, naive recursive Fibonacci is exponential and becomes impractical past roughly n = 40, while the iterative version handles large n instantly. This is a good place in your report to mention that memorization (top-down) or dynamic programming (bottom-up) would bring the recursive approach back down to O(n) time, worth a sentence even though the brief only asks for the plain recursive version.
Part B: Selection Sort, Quick Sort (Lomuto partition), Heap Sort , Binary Search recursive and iterative versions. Sort Benchmark, runs all three sorts on identical random datasets from n=100 to n=20,000, verifies correctness, and exports a CSV 
What's in each file:
•	SortingAlgorithms.java: Selection Sort, Quick Sort (Lomuto partition), Heap Sort
•	BinarySearch.java: recursive and iterative versions
•	SortBenchmark.java: runs all three sorts on identical random datasets from n=100 to n=20,000, verifies correctness, and exports a CSV
•	Part_B_Sorting_Benchmark.xlsx: the results table plus a line chart of time vs. n
The actual results (averaged, milliseconds):
n	Selection Sort	Quick Sort	Heap Sort
100	0.031	0.005	0.013
1,000	0.324	0.071	0.149
4,000	4.268	0.284	0.964
8,000	17.130	0.635	1.809
20,000	92.239	1.659	2.278


•  Selection Sort is O(n²) in every case:  no matter how the input is arranged, it always scans the full remaining unsorted portion to find the minimum. The data confirms this: going from n=2,000 to n=20,000 (10×) increases time by roughly 90×, matching the n² prediction. 
•  Quick Sort averages O(n log n) and is the clear winner here, random input means the pivot splits are close to balanced on average, so it scales far more gently than Selection Sort. Worth noting in your report: its worst case is still O(n²) (e.g. already-sorted input with this last-element pivot choice), which random data doesn't expose. 
•  Heap Sort guarantees O(n log n) in all cases (best, average, worst), it's slightly slower than Quick Sort's average case here because of heap-maintenance overhead, but it doesn't have Quick Sort's bad-case vulnerability. That trade-off (guaranteed bound vs. usually-faster-but-riskier) is a good point to make explicitly. 
•  Binary Search: both versions are O(log n) time; the recursive version trades O(1) space for O(log n) stack space, which is worth mentioning since it's the same theme as your Part A comparison.

Part C: Elementary Data Structures
What's here:
•	SinglyLinkedList.java: generic, with insertAtFront, insertAtEnd, delete, search
•	ArrayStack.java: generic, backed by a plain array that auto-doubles when full
•	CircularQueue.java: generic, fixed-capacity ring buffer using modulo wraparound
•	LinkedListDemo.java: exercises insert/delete/search, including deleting the head node
•	StringReverserDemo.java: reverses strings using the stack (LIFO order naturally reverses a sequence)
•	BankQueueSimulation.java: simulates a 3-seat teller line: fills it, rejects a 4th arrival, serves customers, shows the freed slot getting reused (the wraparound behaviour that's the whole point of a circular queue), and hits the empty-queue case too.
•  Linked List vs. array-based list: insertion at the front is O(1) here, versus O(n) for an array-based list (which would need to shift every element). The trade-off is no random access, finding the nth element is O(n), where an array gives O(1). 
•  Why circular, specifically: a naive array queue with a fixed front index wastes every slot a dequeued element leaves behind, the array's "used" region only ever grows until you hit the end, even with lots of free space near the front. The bank simulation output makes this concrete: capacity stays exactly 3 usable slots forever, no matter how many customers cycle through. 
•  Stack for reversal works because push/pop is LIFO: the last character pushed is the first one popped, which is exactly what reversing needs. That's also the standard justification the report will want for why a stack (not a queue) is the right structure for that specific task.

Part D: Trees
What's here:
•	BinarySearchTree.java: generic BST with insert, search, delete (handling all three delete cases: leaf, one child, two children via in-order successor), plus inOrder, preOrder, postOrder, and a height() helper
•	BSTDemo.java: basic walkthrough: builds a small tree, runs all three traversals, then deletes a leaf, a one-child node, and finally the root itself, printing the tree after each step
•	BSTComplexityDemo.java: the experiment that makes the complexity claim concrete rather than just asserted
Real results from that experiment (n = 10,000 values):
Insertion order	Tree height	Search time (1,000 lookups)
Random (average case)	29	2.757 ms
Sorted (worst case)	9,999	41.879 ms
For your report:
•	Why insertion order matters: a BST's height directly determines the cost of every operation, because insert/search/delete all just walk one root-to-leaf path. Inserting in random order tends to keep the tree bushy, height stays proportional to log₂(n) (here, 29 vs. a theoretical log₂(10,000) ≈ 13.3, a bit higher than the ideal minimum, which is expected since a randomly-built BST isn't perfectly balanced, but still logarithmic, not linear).
•	The worst case is a real, common scenario, not just a theoretical curiosity: inserting already-sorted data (e.g. reading in a sorted file or IDs that arrive in increasing order) makes every new node attach as the rightmost child, degenerating the tree into what is structurally a linked list, height n−1, and every operation becomes O(n).
•	The timing confirms it in practice: searches on the degenerate tree took roughly 15× longer than on the balanced one, for the exact same number of elements, a good concrete number to cite in your report rather than just stating the Big-O.
•	Worth a closing line in your report: this is exactly the motivation for self-balancing trees (AVL, Red-Black) which guarantee O(log n) worst-case by rebalancing after every insert/delete, outside this assignment's scope, but shows you understand why they exist.
Part E: Graphs
What's here:
•	GraphAdjMatrix.java: undirected graph on an adjacency matrix, with recursive dfs, queue-based bfs, and shortestPath (BFS with parent-pointer backtracking to reconstruct the actual route, not just the hop count)
•	TransportNetworkDemo.java: an 8-city South African transport network, showing DFS and BFS traversal orders from Cape Town, then three shortest-route queries
Real output from the demo:
•	Cape Town → Durban: Cape Town → Port Elizabeth → East London → Durban (3 connections)
•	Cape Town → Polokwane: Cape Town → Bloemfontein → Johannesburg → Polokwane (3 connections)
•	Port Elizabeth → Polokwane: 4 connections, routed back through Cape Town
Report:
•	Why the adjacency matrix costs O(V²) for traversal: finding a vertex's neighbours means scanning its entire row (length V) even if it only has one or two actual edges, there's no way to jump straight to the neighbours the way an adjacency list would. That's the direct trade-off for O(1) edge-existence checks (matrix[u][v]) and a simpler implementation. Worth naming explicitly in your report as the matrix-vs-list trade-off.
•	Why BFS, not DFS, solves "shortest path in an unweighted graph": BFS explores the graph level by level (all 1-hop neighbours, then all 2-hop neighbours, and so on), so the first time it reaches the target vertex is guaranteed to be via the fewest possible edges. DFS finds a path (it dives straight down one branch), but has no such guarantee, you can see this in the demo: DFS's traversal order from Cape Town reaches Durban after wandering through Port Elizabeth and East London, purely by the order edges happen to be checked, with no shortest-path guarantee behind it.
•	The parent-pointer trick: recording which vertex discovered each new vertex during BFS, then walking those pointers backward from the destination, is the standard way to recover the actual path rather than just its length, a good thing to call out since it's what turns "BFS visits nodes" into "BFS finds routes."
<img width="468" height="634" alt="image" src="https://github.com/user-attachments/assets/26810a81-9f60-44fd-8ed8-f35841e826fe" />

