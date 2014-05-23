package ar.com.dridco.domain;

public class Recorrido {

	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestiono;
	private int distancia;
	
	public Recorrido(Ciudad ciudadOrigen, Ciudad ciudadDestiono, int distancia) {
		super();
		this.ciudadDestiono = ciudadDestiono;
		this.ciudadOrigen = ciudadOrigen;
		this.distancia = distancia;
	}
	
	public Ciudad getCiudadDestiono() {
		return ciudadDestiono;
	}
	
	public void setCiudadDestiono(Ciudad ciudadDestiono) {
		this.ciudadDestiono = ciudadDestiono;
	}
	
	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}
	
	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	
	public int getDistancia() {
		return distancia;
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "Recorrido [ciudadOrigen=" + ciudadOrigen + ", ciudadDestiono="
				+ ciudadDestiono + ", distancia=" + distancia + "]";
	}
	
	

}
