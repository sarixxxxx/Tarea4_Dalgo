package parte3;

public class Calle {
	int esquina1;
	int esquina2;
	int costoDeConstruccion;
	public Calle(int esquina1, int esquina2, int costoDeConstruccion) {
		super();
		this.esquina1 = esquina1;
		this.esquina2 = esquina2;
		this.costoDeConstruccion = costoDeConstruccion;
	}
	public int getEsquina1() {
		return esquina1;
	}
	public int getEsquina2() {
		return esquina2;
	}
	public int getCostoDeConstruccion() {
		return costoDeConstruccion;
	}
	
	
}
