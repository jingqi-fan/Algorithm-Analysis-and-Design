package dijkstra;

import java.util.List;
import java.util.ArrayList;

public class Path {
	private List<Node> nodesStartToFinish;
	private int weight;
	
	public Path() {
		super();
		this.nodesStartToFinish = new ArrayList<>();
		this.weight = 0;
	}

	public List<Node> getNodesStartToFinish() {
		return nodesStartToFinish;
	}

	public void setNodesStartToFinish(List<Node> nodesStartToFinish) {
		this.nodesStartToFinish = nodesStartToFinish;
	}

	public void addNode(Node node) {
		this.nodesStartToFinish.add(node);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void addWeight(int weight) {
		this.weight += weight;
	}
	
	
	
}
