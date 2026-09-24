package adp.graphs;

import adp.elementary_data_structures.CircularQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * GraphAdjMatrix
 * -----------------------------------------------------------
 * Part E of ADP470S Programming Assignment.
 *
 * An undirected, unweighted graph represented using an adjacency
 * matrix: an n x n array where matrix[u][v] == 1 means an edge
 * exists between vertex u and vertex v (and matrix[v][u] == 1 too,
 * since the graph is undirected).
 *
 * Vertices are identified by index (0 .. numVertices-1); an
 * optional array of labels lets a real-world use case (e.g. city
 * names in a transport network) print human-readable output.
 *
 * Complexity summary (V = number of vertices, E = number of edges):
 *   addEdge          : O(1)   — a single array write (two, for both directions).
 *   DFS / BFS         : O(V^2) with this representation, because
 *                        finding a vertex's neighbours means scanning
 *                        its entire row of length V, and this is done
 *                        for each of the V vertices visited.
 *                        (An adjacency LIST would instead give
 *                        O(V + E), since it only ever looks at a
 *                        vertex's actual neighbours - the trade-off
 *                        for the adjacency matrix's O(1) edge lookup
 *                        and simplicity is this quadratic traversal
 *                        cost, which matters most on sparse graphs.)
 *   Space            : O(V^2) regardless of how many edges actually
 *                        exist, since the full matrix is always
 *                        allocated.
 * -----------------------------------------------------------
 */
public class GraphAdjMatrix {

    private final int numVertices;
    private final int[][] matrix;
    private final String[] labels; // optional human-readable vertex names

    public GraphAdjMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.matrix = new int[numVertices][numVertices];
        this.labels = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            labels[i] = String.valueOf(i); // default label is just the index
        }
    }

    public GraphAdjMatrix(String[] labels) {
        this(labels.length);
        System.arraycopy(labels, 0, this.labels, 0, labels.length);
    }

    /** Adds an undirected edge between vertices u and v. O(1). */
    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }

    public String labelOf(int vertex) {
        return labels[vertex];
    }

    public int numVertices() {
        return numVertices;
    }

    /**
     * Depth-First Search, recursive, starting from the given vertex.
     * Visits as far as possible along each branch before backtracking.
     *
     * @return the order in which vertices were visited.
     */
    public List<Integer> dfs(int start) {
        boolean[] visited = new boolean[numVertices];
        List<Integer> order = new ArrayList<>();
        dfsRecursive(start, visited, order);
        return order;
    }

    private void dfsRecursive(int current, boolean[] visited, List<Integer> order) {
        visited[current] = true;
        order.add(current);

        for (int neighbour = 0; neighbour < numVertices; neighbour++) {
            if (matrix[current][neighbour] == 1 && !visited[neighbour]) {
                dfsRecursive(neighbour, visited, order);
            }
        }
    }

    /**
     * Breadth-First Search, using a queue (CircularQueue from Part C),
     * starting from the given vertex. Visits all immediate neighbours
     * before moving further out, level by level.
     *
     * @return the order in which vertices were visited.
     */
    public List<Integer> bfs(int start) {
        boolean[] visited = new boolean[numVertices];
        List<Integer> order = new ArrayList<>();

        // Capacity = numVertices is always enough: no vertex is enqueued more than once.
        CircularQueue<Integer> queue = new CircularQueue<>(numVertices);

        visited[start] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            order.add(current);

            for (int neighbour = 0; neighbour < numVertices; neighbour++) {
                if (matrix[current][neighbour] == 1 && !visited[neighbour]) {
                    visited[neighbour] = true; // mark visited at enqueue time to avoid duplicate enqueues
                    queue.enqueue(neighbour);
                }
            }
        }
        return order;
    }

    /**
     * Finds the shortest path (fewest edges/hops) between start and end,
     * using BFS. BFS is the right tool here because it explores the
     * graph level by level, so the first time it reaches 'end' is
     * guaranteed to be via a shortest path, in an unweighted graph.
     *
     * @return the sequence of vertices from start to end (inclusive),
     *         or an empty list if end is not reachable from start.
     */
    public List<Integer> shortestPath(int start, int end) {
        boolean[] visited = new boolean[numVertices];
        int[] parent = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            parent[i] = -1;
        }

        CircularQueue<Integer> queue = new CircularQueue<>(numVertices);
        visited[start] = true;
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            int current = queue.dequeue();

            if (current == end) {
                break; // shortest path to 'end' found - reconstruct it below
            }

            for (int neighbour = 0; neighbour < numVertices; neighbour++) {
                if (matrix[current][neighbour] == 1 && !visited[neighbour]) {
                    visited[neighbour] = true;
                    parent[neighbour] = current;
                    queue.enqueue(neighbour);
                }
            }
        }

        if (!visited[end]) {
            return new ArrayList<>(); // end is unreachable from start
        }

        // Walk parent pointers backwards from 'end' to 'start', then reverse.
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = parent[at]) {
            path.add(at);
        }
        java.util.Collections.reverse(path);
        return path;
    }
}
