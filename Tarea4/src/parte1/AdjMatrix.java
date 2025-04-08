package parte1;

import java.util.Arrays;
import java.util.List;

public class AdjMatrix {
	int[][] matrix;

	public AdjMatrix() {
		super();
	}
	
	public int countVertex(List<DirectedEdge> edgesList) {
		return findMaxVertex(edgesList)+1;
	}
	
	public int findMaxVertex(List<DirectedEdge> graph) {
		int max = 0;
		for(DirectedEdge edge:graph) {
			max = Math.max(max, edge.getSource());
			max = Math.max(max, edge.getDestiny());
		}
		return max;
	}
	
	public int[][] createAdjMatrix(List<DirectedEdge> graph){
		int v=countVertex(graph);
		int[][] adjMatrix= new int[v][v]; //como el grafo puede no ser disperso, entonces se hace una matriz de adyacencia
		//esto es [de donde vengo][a donde voy]
		for (int i = 0; i < v; i++) {
		    Arrays.fill(adjMatrix[i], -1);
		}

		for (DirectedEdge edge: graph) {
			adjMatrix[edge.getSource()][edge.getDestiny()]=edge.getWeight();
		}
		this.setMatrix(adjMatrix);
		return adjMatrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
}
