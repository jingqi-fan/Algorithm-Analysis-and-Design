package networkflow;
import java.util.*;

public class FlowGraph {
	private List<Node> nodeList;
	private Map<Node, List<FlowEdge>> adjacencyList;
	
	public FlowGraph() {
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

	public void addEdge(FlowEdge edge) {
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
			throw new IllegalArgumentException("Don't add a FlowEdge with missing start node....");
		}
		if (nodeEndEdge == null) {
			throw new IllegalArgumentException("Don't add a FlowEdge with missing end node....");
		}

		adjacencyList.get(nodeStartEdge).add(edge);
	}

	public void addEdge(Node startNode, Node endNode, int capacity) {
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
			throw new IllegalArgumentException("Don't add a FlowEdge with missing start node....");
		}
		if (nodeEndEdge == null) {
			throw new IllegalArgumentException("Don't add a FlowEdge with missing end node....");
		}
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity should be larger than 0.");
		}
		
		adjacencyList.get(nodeStartEdge).add(new FlowEdge(nodeStartEdge, nodeEndEdge, capacity));
	}
	
	public void addEdge(String startNode, String endNode, int capacity) {
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
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity should be larger than 0.");
		}

		adjacencyList.get(nodeStartEdge).add(new FlowEdge(nodeStartEdge, nodeEndEdge, capacity));
	}
	
	public List<FlowEdge> getAdjacentEdges(Node n) {
		return this.adjacencyList.get(n);
	}
	
	public List<Node> getNodes() {
		return this.nodeList;
	}
	
	public int calculateMaxFlow(Node start, Node finish) {
//		throw new RuntimeException("You need to implement this...");
		int totalFlow = 0;
		while (true) {
			Map<Node, Integer> levels = bfs(start, finish);
			if (!levels.containsKey(finish)) {
				break;
			}
			Map<Node, Iterator<FlowEdge>> edgeIterators = new HashMap<>();
			for (Node node : nodeList) {
				edgeIterators.put(node, adjacencyList.get(node).iterator());
			}
			int flow;
			do {
				flow = dfs(start, Integer.MAX_VALUE, finish, levels, edgeIterators);
				totalFlow += flow;
			} while (flow > 0);
		}
		return totalFlow;
	}

	public Set<Node> findMinCut(Node start, Node finish) {
		Set<Node> reachable = new HashSet<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(start);
		reachable.add(start);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (FlowEdge edge : adjacencyList.get(node)) {
				if (edge.getCapacity() > edge.getFlow() && !reachable.contains(edge.getEndNode())) {
					reachable.add(edge.getEndNode());
					queue.add(edge.getEndNode());
				}
			}
		}
		Set<Node> minCut = new HashSet<>();
		for (Node u : reachable) {
			for (FlowEdge edge : adjacencyList.get(u)) {

				if (!reachable.contains(edge.getEndNode())) {
					minCut.add(edge.getEndNode());
				}
			}
		}
		return minCut;
	}


	private Map<Node, Integer> bfs(Node start, Node target) {
		Queue<Node> queue = new LinkedList<>();
		Map<Node, Integer> levels = new HashMap<>();
		queue.add(start);
		levels.put(start, 0);
		while (!queue.isEmpty()) {
			Node u = queue.poll();
			for (FlowEdge edge : adjacencyList.get(u)) {
				Node v = edge.getEndNode();
				if (!levels.containsKey(v) && edge.getCapacity() > edge.getFlow()) {
					levels.put(v, levels.get(u) + 1);
					queue.add(v);
				}
			}
		}
		return levels;
	}

	private int dfs(Node u, int flow, Node target, Map<Node, Integer> levels, Map<Node, Iterator<FlowEdge>> edgeIterators) {
		if (u.equals(target)) {
			return flow;
		}
		Iterator<FlowEdge> it = edgeIterators.get(u);
		while (it.hasNext()) {
			FlowEdge edge = it.next();
			Node v = edge.getEndNode();
			if (levels.get(v) == levels.get(u) + 1 && edge.getCapacity() > edge.getFlow()) {
				int currentFlow = Math.min(flow, edge.getCapacity() - edge.getFlow());
				int tmpFlow = dfs(v, currentFlow, target, levels, edgeIterators);
				if (tmpFlow > 0) {
					edge.addFlow(tmpFlow);
					return tmpFlow;
				}
			}
		}
		return 0;
	}

}

