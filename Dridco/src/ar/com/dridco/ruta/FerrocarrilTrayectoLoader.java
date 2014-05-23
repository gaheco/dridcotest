package ar.com.dridco.ruta;

import ar.com.dridco.domain.Ferrocarril;

public class FerrocarrilTrayectoLoader {

	private Ferrocarril ferrocarril;
	
	public FerrocarrilTrayectoLoader(Ferrocarril ferrocarril){
		this.ferrocarril = ferrocarril;
	}
	
	public void loadTrayectosFerrocarril(String grafoRecorridos){
		String[] trayectoString = grafoRecorridos.split(",");

		for(int i=0; i < trayectoString.length; i++){
			String nombreCiudadOrigen = extraerCiudadOrigen(trayectoString[i]);
			String nombreCiudadDestino = extraerCiudadDestino(trayectoString[i]);
			int distancia = extraerDistancia(trayectoString[i]);
			
			ferrocarril.agregaRecorrido(nombreCiudadOrigen, nombreCiudadDestino, distancia);
		}
	}
	
	private String extraerCiudadOrigen(String trayecto){
		return trayecto.trim().substring(0, 1);
	}
	
	private String extraerCiudadDestino(String trayecto){
		return trayecto.trim().substring(1, 2);
	}

	private int extraerDistancia(String trayecto){
		return Integer.parseInt(trayecto.trim().substring(2, 3));
	}

}
