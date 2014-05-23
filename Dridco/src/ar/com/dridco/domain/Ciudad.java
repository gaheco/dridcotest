package ar.com.dridco.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.dridco.exception.NoExisteRutaException;

public class Ciudad {

	private String nombreCiudad;
	private Map<Ciudad,Recorrido> recorridosDestino;
	private List<Recorrido> recorridosDestinoList;
	
	public Ciudad(String nombreCiudad) {
		super();
		this.nombreCiudad = nombreCiudad;
		
		this.recorridosDestino = new HashMap<Ciudad,Recorrido>();
		this.recorridosDestinoList = new ArrayList<Recorrido>();
	}

	public void addRecorrido(Ciudad ciudadDestino, int distancia){

		Recorrido recorrido = new Recorrido(
											this,
											ciudadDestino,
											distancia
										);

		
		this.recorridosDestino.put(ciudadDestino,recorrido);
		this.recorridosDestinoList.add(recorrido);
	}
	
	public int getDistanciaDestino(Ciudad ciudadDestino) throws NoExisteRutaException{
		if (this.recorridosDestino.get(ciudadDestino) != null){
			return this.recorridosDestino.get(ciudadDestino).getDistancia();
		}else{
			throw new NoExisteRutaException("La ciudad " + this.nombreCiudad + " no tiene como ciudad destino a " + ciudadDestino.getNombreCiudad());
		}
	}

	public Recorrido getRecorridoDestino(Ciudad ciudadDestino) throws NoExisteRutaException {
		if (this.recorridosDestino.get(ciudadDestino) != null){
			return this.recorridosDestino.get(ciudadDestino);
		}else{
			throw new NoExisteRutaException("La ciudad " + this.nombreCiudad + " no tiene como ciudad destino a " + ciudadDestino.getNombreCiudad());
		}
	}
	
	public String getNombreCiudad() {
		return nombreCiudad;
	}
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	public Map<Ciudad, Recorrido> getRecorridosDestino() {
		return recorridosDestino;
	}
	public void setRecorridosDestino(Map<Ciudad, Recorrido> recorridosDestino) {
		this.recorridosDestino = recorridosDestino;
	}

	public List<Recorrido> getRecorridosDestinoList() {
		return recorridosDestinoList;
	}

	public void setRecorridosDestinoList(List<Recorrido> recorridosDestinoList) {
		this.recorridosDestinoList = recorridosDestinoList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombreCiudad == null) ? 0 : nombreCiudad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (nombreCiudad == null) {
			if (other.nombreCiudad != null)
				return false;
		} else if (!nombreCiudad.equals(other.nombreCiudad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ciudad [nombreCiudad=" + nombreCiudad + "]";
	}
	
}
