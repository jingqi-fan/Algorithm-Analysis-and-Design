package dijkstra;
import java.util.*;

public class Graph {
	private List<Node> nodeList;
	private Map<Node, List<Edge>> adjacencyList;

	public Graph() {
		this.nodeList = new ArrayList<>();
		this.adjacencyList = new HashMap<>();
	}

	public void addNode(Node node) {
		assert node != null;

		if (nodeList.contains(node)) {
			throw new IllegalArgumentException("Don't add nodes twice....");
		}
		nodeList.add(node);
		adjacencyList.put(node, new ArrayList<>());
	}

	public void addNode(String label) {
		this.addNode(new Node(label));
	}

	public void addEdge(Edge edge) {
		assert edge != null;

		Node nodeStartEdge = null;
		Node nodeEndEdge = null;
		for(Node node: nodeList) {
			if (node.getLabel().equals(edge.getStartNode().getLabel())) {
				nodeStartEdge = node;
			}
			if (node.getLabel().equals(edge.getEndNode().getLabel())) {
				nodeEndEdge = node;
			}
		}
		if (nodeStartEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing start node....");
		}
		if (nodeEndEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing end node....");
		}

		adjacencyList.get(nodeStartEdge).add(edge);
	}

	public void addEdge(Node startNode, Node endNode, int weight) {
		assert startNode != null;
		assert endNode != null;
		assert weight >= 0;

		Node nodeStartEdge = null;
		Node nodeEndEdge = null;
		for(Node node: nodeList) {
			if (node.getLabel().equals(startNode.getLabel())) {
				nodeStartEdge = node;
			}
			if (node.getLabel().equals(endNode.getLabel())) {
				nodeEndEdge = node;
			}
		}
		if (nodeStartEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing start node....");
		}
		if (nodeEndEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing end node....");
		}

		adjacencyList.get(nodeStartEdge).add(new Edge(nodeStartEdge, nodeEndEdge, weight));
	}

	public void addEdge(String startNode, String endNode, int weight) {
		assert startNode != null;
		assert endNode != null;
		assert weight >= 0;

		Node nodeStartEdge = null;
		Node nodeEndEdge = null;
		for(Node node: nodeList) {
			if (node.getLabel().equals(startNode)) {
				nodeStartEdge = node;
			}
			if (node.getLabel().equals(endNode)) {
				nodeEndEdge = node;
			}
		}
		if (nodeStartEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing start node....");
		}
		if (nodeEndEdge == null) {
			throw new IllegalArgumentException("Don't add an edge with missing end node....");
		}

		adjacencyList.get(nodeStartEdge).add(new Edge(nodeStartEdge, nodeEndEdge, weight));
	}

	public List<Edge> getAdjacentEdges(Node n) {
		return this.adjacencyList.get(n);
	}

	public List<Node> getNodes() {
		return this.nodeList;
	}

//	public Path dijkstra(Node start, Node finish) {
//		throw new RuntimeException("You need to implement this...");
//	}

	public Path dijkstra(Node start, Node finish) {
		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
		start.setDistance(0);
		queue.add(start);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();

			if (currentNode.equals(finish)) {
				break;
			}

			List<Edge> currentEdges = adjacencyList.get(currentNode);
			for (Edge edge : currentEdges) {
				Node adjacent = edge.getEndNode();
				int newDist = currentNode.getDistance() + edge.getWeight();

				if (newDist < adjacent.getDistance()) {
					adjacent.setDistance(newDist);
					adjacent.setPreviousNode(currentNode);
					queue.add(adjacent);
				}
			}
		}

		return buildPath(finish);
	}

	private Path buildPath(Node target) {
		List<Node> nodes = new ArrayList<>();
		Path path = new Path();
		for (Node node = target; node != null; node = node.getPreviousNode()) {
			nodes.add(node);
		}
		Collections.reverse(nodes);
		path.setNodesStartToFinish(nodes);
		path.setWeight(target.getDistance());
		return path;
	}
}
