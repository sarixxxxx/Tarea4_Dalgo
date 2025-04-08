package parte1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dijkstra {
	//por default yo defino al vertice inicial como 0
	int f=0;
	//con esta clase construyo la matriz de adyacencia
	AdjMatrix builderAdjMatrix=new AdjMatrix();
	static final int INFINITY = 1000000; 
	
	
	
	public int[][] dijkstraImplementation(List<DirectedEdge> graph) {
		int[][] adjMatrix= builderAdjMatrix.createAdjMatrix(graph);
		int[][] respuesta=new int[adjMatrix.length][2]; //el idx es el destino, [0] el predecesor y [1] costo
		Set<Integer> verticesProcesados=new HashSet();
		verticesProcesados.add(this.f);
		
		for (int i=0; i<respuesta.length;i++) {
			if(adjMatrix[this.f][i]==-1) {
				respuesta[i][0]=-1;
				respuesta[i][1]=INFINITY;
			} else {
				respuesta[i][0]=this.f;
				respuesta[i][1]=adjMatrix[this.f][i];
			}
		}
		while(verticesProcesados.size()<adjMatrix.length) {
			int w=this.f;
			for(int u=0;u<respuesta.length;u++){
				if(!verticesProcesados.contains(u) &&(w==this.f||respuesta[u][1]<respuesta[w][1])){
					w=u;
					
				}
			}
			verticesProcesados.add(w);
			for (int v=0;v<respuesta.length;v++) {
				if(!verticesProcesados.contains(v)) {
					respuesta[v][1]=Math.min(respuesta[v][1], respuesta[w][1]+c(w,v,adjMatrix));
					if(respuesta[v][1]==respuesta[w][1]+c(w,v,adjMatrix)) {
						respuesta[v][0]=w;
					}
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
