package parte1;

import java.util.List;

public class BellmanFord {
	int f=0;
	AdjMatrix builderAdjMatrix=new AdjMatrix();
	static final int INFINITY = 1000000; 
	
	public int[][] bellmanFordImplementation(List<DirectedEdge> graph){
//		int[][] adjMatrix= builderAdjMatrix.createAdjMatrix(graph);
		int n=builderAdjMatrix.countVertex(graph);
		int[][] respuesta=new int[n][2]; //el idx es el destino, [0] el predecesor y [1] costo
		for (int i=0;i<n;i++) {
			respuesta[i][0]=-1;
			respuesta[i][1]=INFINITY;
		}
		respuesta[this.f][1]=0;
		for(int i=0;i<n-1;i++) {
			//para bellan ford no necesito matriz?? solo recorro aristas
			for(DirectedEdge edge:graph) {
				respuesta[edge.getDestiny()][1]=Math.min(respuesta[edge.getDestiny()][1],respuesta[edge.getSource()][1]+edge.getWeight());
				if(respuesta[edge.getDestiny()][1]==respuesta[edge.getSource()][1]+edge.getWeight()) {
					respuesta[edge.getDestiny()][0]=edge.getSource();
				}
			}
		}
		
		return respuesta;
	}
	
	public int c(int v1, int v2, int[][] adjMatrix) {
		if(adjMatrix[v1][v2]==-1) {
			return INFINITY;
		} 
		return adjMatrix[v1][v2];
		
	}
}
