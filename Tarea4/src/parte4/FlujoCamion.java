package parte4;

public class FlujoCamion {
	Camion camion;
	int flujo;
	public FlujoCamion(Camion camion, int flujo) {
		super();
		this.camion = camion;
		this.flujo = flujo;
	}
	public Camion getCamion() {
		return camion;
	}
	public int getFlujo() {
		return flujo;
	}
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	public void setFlujo(int flujo) {
		this.flujo = flujo;
	}
	
}
