package parte2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import parte1.DirectedEdge;

public class BFS {
	static final int INFINITY = 1000000; 

	public Partition bfsImplementation(List<UndirectedWeightedEdge> graph) {
		int V = countVertex(graph);
		List<List<UndirectedWeightedEdge>> listaAdj = listaAdyacencia(graph, V);
		int[] procesado = new int[V]; // 0: blanco, 1: gris, 2: negro
		Partition particion = new Partition(V);

		for (int i = 0; i < V; i++) {
			if (procesado[i] == 0) { // Si no ha sido visitado
				Queue<Integer> cola = new LinkedList<>();
				cola.add(i);
				procesado[i] = 1;
				while (!cola.isEmpty()) {
					int u = cola.poll();
					for (UndirectedWeightedEdge adjEdge : listaAdj.get(u)) {
						int v = (u == adjEdge.getV1()) ? adjEdge.getV2() : adjEdge.getV1();

						if (procesado[v] == 0) {
							procesado[v] = 1;
							particion.union(u, v);
							cola.add(v);
						}
					}
					procesado[u] = 2;
				}
			}
		}

		return particion;
	}
	
	private List<List<UndirectedWeightedEdge>> listaAdyacencia(List<UndirectedWeightedEdge> listaArcos, int V) {
		List<List<UndirectedWeightedEdge>> adj= new ArrayList<>();
		for (int i=0;i<V; i++) {
			adj.add(new ArrayList<UndirectedWeightedEdge>());
		}
		
		for (UndirectedWeightedEdge arco:listaArcos) {
			adj.get(arco.getV1()).add(arco);
			adj.get(arco.getV2()).add(arco);
		}
		return adj;
	}
	
	public int countVertex(List<UndirectedWeightedEdge> edgesList) {
		return findMaxVertex(edgesList)+1;
	}
	
	public int findMaxVertex(List<UndirectedWeightedEdge> graph) {
		int max = 0;
		for(UndirectedWeightedEdge edge:graph) {
			max = Math.max(max, edge.getV1());
			max = Math.max(max, edge.getV2());
		}
		return max;
	}
}
