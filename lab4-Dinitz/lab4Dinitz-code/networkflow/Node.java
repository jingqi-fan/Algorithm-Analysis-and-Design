package networkflow;

public class Node {
	private String label;
	
	public Node(String label) {
		if (label == null) {
			throw new NullPointerException("Label shouldn't be null.");
		}

		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Node)) {
			return false;
		} else {
			Node otherNode=(Node)o;
			return otherNode.getLabel().equals(this.label);
		}
	}
		
	@Override
	public int hashCode() {
		return label.hashCode();
	}
}
