package dijkstra;

public class Node {
	private String label;
	private int distance = Integer.MAX_VALUE; // Infinite distance initially
	private Node previousNode = null;

	public Node(String label) {
		if (label == null) {
			throw new NullPointerException("Label shouldn't be null.");
		}
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previousNode) {
		this.previousNode = previousNode;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Node)) {
			return false;
		} else {
			Node otherNode = (Node) o;
			return otherNode.getLabel().equals(this.label);
		}
	}

	@Override
	public int hashCode() {
		return label.hashCode();
	}
}
