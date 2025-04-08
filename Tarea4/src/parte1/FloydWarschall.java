package parte1;

import java.util.List;

public class FloydWarschall {
	//necesito encontrar la matriz de costos mínimos de
	//caminos entre todos los vértices fuente y todos los vértices destino de un grafo
	//dirigido de n vértices con costos en los números naturales
	AdjMatrix builderAdjMatrix=new AdjMatrix();
	static final int INFINITY = 1000000; 
	int f=0;
	
	public int[][] floydWarschallImplementation(List<DirectedEdge> graph){
		int[][] adjMatrix= builderAdjMatrix.createAdjMatrix(graph);
		int V=builderAdjMatrix.countVertex(graph);
		int[][] matrizDP=new int[V][V];
		
		int i=0;
		int j=0;
		int k=0;
		while(k<V) {
			i=0;
			while(i<V) {
				j=0;
				while(j<V) {
					if(k==0) {
						matrizDP[i][j]=c(i,j,adjMatrix);
					}else if(k>0 && i!=k-1&&j!=k-1) {
						matrizDP[i][j]=Math.min(matrizDP[i][j], matrizDP[i][k-1]+matrizDP[k-1][j]);	
					}
				j++;
				}
				i++;
			}
			k++;
		}
		
		return matrizDP;
	}

	private int c(int v1, int v2, int[][] adjMatrix) {
		// TODO Auto-generated method stub
		if(adjMatrix[v1][v2]==-1) {
			return INFINITY;
		} 
		return adjMatrix[v1][v2];
	}
	
}
