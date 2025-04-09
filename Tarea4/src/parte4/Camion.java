package parte4;

public class Camion {
	int fuente;
	String tipoFuente;
	//F si es Fábrica, B si es bodega o L si es librería 
	int destino;
	String tipoDestino;
	int libros;
	public Camion(int fuente, String tipoFuente, int destino, String tipoDestino) {
		super();
		this.fuente = fuente;
		this.tipoFuente = tipoFuente;
		this.destino = destino;
		this.tipoDestino = tipoDestino;
		this.libros=1000000;
	}
	public Camion(int fuente2, String tipoFuente2, int destino2, String tipoDestino2, int libros2) {
		// TODO Auto-generated constructor stub
		super();

		this.fuente = fuente2;
		this.tipoFuente = tipoFuente2;
		this.destino = destino2;
		this.tipoDestino = tipoDestino2;
		this.libros=libros2;
		
	}
	public int getFuente() {
		return fuente;
	}
	public String getTipoFuente() {
		return tipoFuente;
	}
	public int getDestino() {
		return destino;
	}
	public String getTipoDestino() {
		return tipoDestino;
	}
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}
	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}
	
	public void setLibros(int maximos) {
		this.libros=maximos;
	}
	public int getLibros() {
		return libros;
	}
	
	
	
	
	
}
