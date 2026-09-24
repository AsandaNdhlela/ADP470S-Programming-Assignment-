package adp;

import adp.graphs.GraphAdjMatrix;

import java.util.List;

/**
 * TransportNetworkDemo
 * -----------------------------------------------------------
 * Part E demonstration: models a small South African transport
 * network as a graph, where vertices are cities and an edge means
 * a direct route (road/rail) exists between them. Applies DFS,
 * BFS, and BFS-based shortest-path to answer the real-world
 * question: "what is the shortest route (fewest connections)
 * between city A and city B?"
 * -----------------------------------------------------------
 */
public class TransportNetworkDemo {

    // Vertex indices, matched to the labels array passed into the graph below.
    private static final int CAPE_TOWN = 0;
    private static final int PORT_ELIZABETH = 1;
    private static final int EAST_LONDON = 2;
    private static final int DURBAN = 3;
    private static final int BLOEMFONTEIN = 4;
    private static final int KIMBERLEY = 5;
    private static final int JOHANNESBURG = 6;
    private static final int POLOKWANE = 7;

    public static void main(String[] args) {
        String[] cities = {
                "Cape Town", "Port Elizabeth", "East London", "Durban",
                "Bloemfontein", "Kimberley", "Johannesburg", "Polokwane"
        };
        GraphAdjMatrix network = new GraphAdjMatrix(cities);

        // Direct routes between cities (undirected: a route works both ways).
        network.addEdge(CAPE_TOWN, PORT_ELIZABETH);
        network.addEdge(CAPE_TOWN, BLOEMFONTEIN);
        network.addEdge(PORT_ELIZABETH, EAST_LONDON);
        network.addEdge(EAST_LONDON, DURBAN);
        network.addEdge(BLOEMFONTEIN, KIMBERLEY);
        network.addEdge(BLOEMFONTEIN, JOHANNESBURG);
        network.addEdge(JOHANNESBURG, DURBAN);
        network.addEdge(JOHANNESBURG, POLOKWANE);

        System.out.println("--- Transport Network: DFS Traversal from Cape Town ---");
        printCityOrder(network, network.dfs(CAPE_TOWN));

        System.out.println("\n--- Transport Network: BFS Traversal from Cape Town ---");
        printCityOrder(network, network.bfs(CAPE_TOWN));

        System.out.println("\n--- Shortest Route (fewest connections) ---");
        findAndPrintRoute(network, CAPE_TOWN, DURBAN);
        findAndPrintRoute(network, CAPE_TOWN, POLOKWANE);
        findAndPrintRoute(network, PORT_ELIZABETH, POLOKWANE);
    }

    private static void printCityOrder(GraphAdjMatrix graph, List<Integer> order) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.size(); i++) {
            sb.append(graph.labelOf(order.get(i)));
            if (i < order.size() - 1) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    private static void findAndPrintRoute(GraphAdjMatrix graph, int from, int to) {
        List<Integer> path = graph.shortestPath(from, to);
        if (path.isEmpty()) {
            System.out.println(graph.labelOf(from) + " -> " + graph.labelOf(to) + " : no route found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(graph.labelOf(path.get(i)));
            if (i < path.size() - 1) sb.append(" -> ");
        }
        int hops = path.size() - 1;
        System.out.println(sb + "  (" + hops + " connection" + (hops == 1 ? "" : "s") + ")");
    }
}
