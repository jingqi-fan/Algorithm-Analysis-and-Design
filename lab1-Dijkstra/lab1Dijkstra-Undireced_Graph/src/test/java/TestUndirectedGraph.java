import dijkstra.*;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestUndirectedGraph {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");

        // Adding nodes
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);

        // Adding edges for an undirected graph
        graph.addEdge(nodeA, nodeB, 6);
        graph.addEdge(nodeA, nodeC, 1);
        graph.addEdge(nodeB, nodeC, 5);
        graph.addEdge(nodeB, nodeE, 2);
        graph.addEdge(nodeC, nodeD, 1);
        graph.addEdge(nodeD, nodeE, 1);
    }

    @Test
    public void testDijkstra() {
        Node startNode = graph.getNodes().stream().filter(node -> node.getLabel().equals("A")).findFirst().orElse(null);
        Node endNode = graph.getNodes().stream().filter(node -> node.getLabel().equals("E")).findFirst().orElse(null);

        Path resultPath = graph.dijkstra(startNode, endNode);

        assertEquals(3, resultPath.getWeight());
        assertEquals("A -> C -> D -> E", pathToString(resultPath));
    }

    private String pathToString(Path path) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < path.getNodesStartToFinish().size(); i++) {
            Node node = path.getNodesStartToFinish().get(i);
            if (i > 0) builder.append(" -> ");
            builder.append(node.getLabel());
        }
        return builder.toString();
    }
}

