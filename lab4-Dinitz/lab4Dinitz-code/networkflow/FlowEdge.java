package networkflow;

public class FlowEdge {
	private Node startNode;
	private Node endNode;
	private int capacity;
	private int flow;
	
	public FlowEdge(Node startNode, Node endNode, int capacity) {
		if (startNode == null) {
			throw new NullPointerException("startNode shouldn't be null.");
		}
		if (endNode == null) {
			throw new NullPointerException("endNode shouldn't be null.");
		}
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity should be larger than 0.");
		}
		
		this.startNode = startNode;
		this.endNode = endNode;
		this.capacity = capacity;
		this.flow = 0;
	}
	
	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		if (flow < 0) {
			throw new IllegalArgumentException("flow should not be less than 0.");
		}
		if (flow > capacity) {
			throw new IllegalArgumentException("flow should be larger less then capacity.");
		}		
		this.flow = flow;
	}

	public void addFlow(int change) {
		if (flow + change < 0) {
			throw new IllegalArgumentException("flow should not be less than 0.");
		}
		if (flow + change > capacity) {
			throw new IllegalArgumentException("flow should be larger less then capacity.");
		}		
		flow = flow + change;
	}

	public void removeFlow(int change) {
		if (flow - change < 0) {
			throw new IllegalArgumentException("flow should not be less than 0.");
		}
		if (flow - change > capacity) {
			throw new IllegalArgumentException("flow should be larger less then capacity.");
		}		
		flow = flow - change;
	}

}
