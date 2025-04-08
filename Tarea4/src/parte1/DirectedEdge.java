package parte1;

public class DirectedEdge {
	private int source;
	private int destiny;
	private int weight;
	public DirectedEdge(int source, int destiny, int weight) {
		super();
		this.source = source;
		this.destiny = destiny;
		this.weight = weight;
	}
	public int getSource() {
		return source;
	}
	public int getDestiny() {
		return destiny;
	}
	public int getWeight() {
		return weight;
	}

}
