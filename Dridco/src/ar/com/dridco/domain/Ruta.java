package ar.com.dridco.domain;

import java.util.List;

public class Ruta {

	private List<Recorrido> trayectosDeRuta;

	public Ruta(List<Recorrido> trayectosDeRuta) {
		super();
		this.trayectosDeRuta = trayectosDeRuta;
	}

	public Ciudad getOrigenRuta(){
		return this.trayectosDeRuta.get(0).getCiudadOrigen();
	}

	public Ciudad getDestinoRuta(){
		return this.trayectosDeRuta.get(this.trayectosDeRuta.size()-1).getCiudadDestiono();
	}
	
	public List<Recorrido> getTrayectosDeRuta() {
		return trayectosDeRuta;
	}

	public void setTrayectosDeRuta(List<Recorrido> trayectosDeRuta) {
		this.trayectosDeRuta = trayectosDeRuta;
	}
	
	public int getDistanciaTotalRuta(){
		int distanciaTotal = 0;
		
		for(int i=0; i < this.trayectosDeRuta.size(); i++ ){
			Recorrido recorrido = this.trayectosDeRuta.get(i);
			
			distanciaTotal += recorrido.getDistancia();
		}
		
		return distanciaTotal;
	}

	@Override
	public String toString() {
		return "Ruta [trayectosDeRuta=" + trayectosDeRuta + "]";
	}
	
	
}
