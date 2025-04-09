package parte3;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parte2.Partition;
public class ConstruccionesDobleVia {
	
	public Set<Calle> viasAConvertir(List<Calle> listaCalles){
		Set<Calle> A=new HashSet<Calle>();
		Partition particiones=new Partition(countVertex(listaCalles));
		Comparator<Calle> sortCrit = new Comparator<Calle>() {
		    @Override
		    public int compare(Calle c1, Calle c2) {
		        return Integer.compare(c1.getCostoDeConstruccion(), c2.getCostoDeConstruccion());
		    }
		};
		listaCalles.sort(sortCrit);
		for(Calle callecita:listaCalles) {
			System.err.println("u"+callecita.getEsquina1()+","+particiones.find(callecita.getEsquina1())+" v"+callecita.getEsquina2()+","+particiones.find(callecita.getEsquina2()));
			if(particiones.find(callecita.getEsquina1())!=particiones.find(callecita.getEsquina2())) {
				A.add(callecita);
				particiones.union(callecita.getEsquina1(), callecita.getEsquina2());
			}
		}
		return A;
		
	}

	public int countVertex(List<Calle> edgesList) {
		return findMaxVertex(edgesList)+1;
	}
	
	public int findMaxVertex(List<Calle> graph) {
		int max = 0;
		for(Calle edge:graph) {
			max = Math.max(max, edge.getEsquina1());
			max = Math.max(max, edge.getEsquina2());
		}
		return max;
	}
}
