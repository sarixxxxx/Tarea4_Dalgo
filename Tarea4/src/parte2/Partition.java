package parte2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a structure to store partitions of the numbers from 0 to n-1
 * It corresponds to the structures named disjoint sets or union-find 
 */
public class Partition {
	int n;
	ArrayList<Integer> representantes;
	
	//TODO: Define attributes to implement structure
	
	
	/**
	 * Builds a new partition for n elements.
	 * Assigns each element to a different subset
	 * @param n Number of elements to consider
	 */
	public Partition (int n) {
		this.n = n;
		//TODO: finish implementation
		representantes= new ArrayList<>();
		
		for (int i=0;i<n;i++) {
			this.representantes.add(i);
		}
	}
	/**
	 * Modifies the partition merging into a single subset the subsets to which the given elements belong
	 * If the two elements belong to the same subset it leaves the structure unchanged
	 * @param e1 Element belonging to the first subset. 0<=e1<n
	 * @param e2 Element belonging to the second subset. 0<=e2<=n
	 */
	public void union (int e1, int e2) {
		int s1 = find(e1);
		int s2 = find(e2);
		if(s1==s2) return;
		this.representantes.set(s2, s1);
		return;
		//TODO: Finish immplementation
		
	}
	/**
	 * Returns the identifier of the subset to which the given element belongs
	 * @param e Element to find. 0<=e<n
	 * @return int Subset to which the element belongs
	 */
	public int find (int e) {
		//TODO: Implement
		int representante=this.representantes.get(e);
		if (representante ==e) {
			return representante;
		} else {
			return find(representante);
		}
	}
	/**
	 * Determines if the two elements belong to the same subset.
	 * @param e1 First element
	 * @param e2 Second element
	 * @return boolean true if e1 and e2 belong to the same subset. False otherwise
	 */
	public boolean sameSubsets(int e1, int e2) {
		return find(e1)==find(e2);
	}
	public int getN() {
		return n;
	}
	public ArrayList<Integer> getRepresentantes() {
		return representantes;
	}
	
	
}
