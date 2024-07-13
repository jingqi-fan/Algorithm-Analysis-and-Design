package networkflow;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of the FlowGraph
        FlowGraph graph = new FlowGraph();

        // Nodes in the bipartite graph
        Node source = new Node("source");
        Node sink = new Node("sink");

        // Left partition nodes (U)
        Node u1 = new Node("u1");
        Node u2 = new Node("u2");
        Node u3 = new Node("u3");

        // Right partition nodes (V)
        Node v1 = new Node("v1");
        Node v2 = new Node("v2");
        Node v3 = new Node("v3");

        // Adding nodes to the graph
        graph.addNode(source);
        graph.addNode(sink);
        graph.addNode(u1);
        graph.addNode(u2);
        graph.addNode(u3);
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);

        // Connecting source to left partition nodes
        graph.addEdge(source, u1, 1); // capacity of 1 for matching problem
        graph.addEdge(source, u2, 1);
        graph.addEdge(source, u3, 1);

        // Connecting left partition nodes to right partition nodes
        graph.addEdge(u1, v1, 1);
        graph.addEdge(u1, v2, 1);
        graph.addEdge(u2, v2, 1);
        graph.addEdge(u3, v2, 1);
        graph.addEdge(u2, v3, 1);
        graph.addEdge(u3, v3, 1);

        // Connecting right partition nodes to sink
        graph.addEdge(v1, sink, 1);
        graph.addEdge(v2, sink, 1);
        graph.addEdge(v3, sink, 1);

        // Calculate maximum flow which corresponds to the maximum bipartite matching
        int maxFlow = graph.calculateMaxFlow(source, sink);
        System.out.println("The flow in the network is:");
        System.out.println(maxFlow);

        // Find the minimum cut if needed (not necessary for the matching problem output)
        Set<Node> minCut = graph.findMinCut(source, sink);
        System.out.println("Nodes in the minimum cut:");
        for (Node node : minCut) {
            System.out.println(node.getLabel());
        }

        // Print detailed information about the matching
        System.out.println("Bipartite Graph Matching Details:");
        printMatchingDetails(graph, u1);
        printMatchingDetails(graph, u2);
        printMatchingDetails(graph, u3);

    }

    private static void printMatchingDetails(FlowGraph graph, Node... nodes) {
        for (Node u : nodes) {
            for (FlowEdge edge : graph.getAdjacentEdges(u)) {
                if (edge.getFlow() > 0) {
                    System.out.println(u.getLabel() + " matched with " + edge.getEndNode().getLabel());
                }
            }
        }
    }

}
