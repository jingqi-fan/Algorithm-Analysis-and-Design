package dijkstra;

public class Edge {
	private Node startNode;
	private Node endNode;
	private int weight;
	
	public Edge(Node startNode, Node endNode, int weight) {
		if (startNode == null) {
			throw new NullPointerException("startNode shouldn't be null.");
		}
		if (endNode == null) {
			throw new NullPointerException("endNode shouldn't be null.");
		}
		if (weight < 0) {
			throw new NullPointerException("weight should be larger than 0.");
		}
		
		this.startNode = startNode;
		this.endNode = endNode;
		this.weight = weight;
	}
	
	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}
	
	public int getWeight() {
		return weight;
	}
}
