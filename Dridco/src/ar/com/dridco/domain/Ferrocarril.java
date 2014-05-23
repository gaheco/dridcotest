package ar.com.dridco.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.dridco.exception.NoExisteRutaException;
import ar.com.dridco.ruta.CalculadorRuta;
import ar.com.dridco.ruta.FerrocarrilTrayectoLoader;

public class Ferrocarril {

	private Map<String,Ciudad> ciudadesRecorridas;
	
	private CalculadorRuta calculadorRuta;
	
	public Ferrocarril(String grafoRecorridos){
		this.ciudadesRecorridas = new HashMap<String,Ciudad>();
		this.calculadorRuta = new CalculadorRuta();
		FerrocarrilTrayectoLoader ferrocarrilTrayectoLoader = new FerrocarrilTrayectoLoader(this);
		
		ferrocarrilTrayectoLoader.loadTrayectosFerrocarril(grafoRecorridos);
	}
	
	public void agregaRecorrido(String nombreCiudadOrigen,String nombreCiudadDestino, int distancia){
		Ciudad ciudadOrigen = this.getCiudadPorNombre(
				nombreCiudadOrigen
			);
		if (ciudadOrigen == null){
			ciudadOrigen = new Ciudad(nombreCiudadOrigen);
			this.ciudadesRecorridas.put(nombreCiudadOrigen, ciudadOrigen);
		}
		
		Ciudad ciudadDestino = this.getCiudadPorNombre(
				nombreCiudadDestino
			);
		if (ciudadDestino == null){
			ciudadDestino = new Ciudad(nombreCiudadDestino);
			this.ciudadesRecorridas.put(nombreCiudadDestino, ciudadDestino);
		}
		
		ciudadOrigen.addRecorrido(ciudadDestino, distancia);
	}

	public String getDistanciaRutaDeterminadaString(List<String> nombreCiudadesVisitadasEnOrden) {
		try{
			return String.valueOf(this.getDistanciaRutaDeterminada(nombreCiudadesVisitadasEnOrden));
		}catch(NoExisteRutaException e){
			return "SIN RUTA";
		}
	}
	
	private int getDistanciaRutaDeterminada(List<String> nombreCiudadesVisitadasEnOrden) throws NoExisteRutaException{
		int distanciaTotal = 0;
		
		if (nombreCiudadesVisitadasEnOrden.size() >=2 ){
			for(int i=0;i<nombreCiudadesVisitadasEnOrden.size()-1;i++){
				
				Ciudad ciudadOrigen = this.getCiudadPorNombre(
						nombreCiudadesVisitadasEnOrden.get(i)
					);
				Ciudad ciudadDestino = this.getCiudadPorNombre(
						nombreCiudadesVisitadasEnOrden.get(i+1)
					);
				
				if (ciudadOrigen != null && ciudadDestino != null){
					distanciaTotal += ciudadOrigen.getDistanciaDestino(ciudadDestino);
				}else{
					throw new NoExisteRutaException("La ciudad Origen y/o destino del recorrido es desconocida para el ferrocarril");
				}
			}
		}else{
			throw new NoExisteRutaException("La ciudad Origen y/o destino del recorrido es desconocida para el ferrocarril");
		}
		
		return distanciaTotal;
	}

	public List<Ruta> recorrer(String nombreCiudadInicial,String nombreCiudadFinal){
		Ciudad ciudadInicial = this.getCiudadPorNombre(nombreCiudadInicial);
		Ciudad ciudadFinal = this.getCiudadPorNombre(nombreCiudadFinal);
		
		return this.recorrer(ciudadInicial, ciudadFinal);
	}
	
	public int cantRutasConCantMaximaViajes(String nombreCiudadInicial,String nombreCiudadFinal, int cantMaximaViajes){
		Ciudad ciudadInicial = this.getCiudadPorNombre(nombreCiudadInicial);
		Ciudad ciudadFinal = this.getCiudadPorNombre(nombreCiudadFinal);
		int cantRutasConCantMaximaViajes = 0;
		
		List<Ruta> rutasEntreCiudades = this.recorrer(ciudadInicial, ciudadFinal);
		for (int i=0; i < rutasEntreCiudades.size(); i++){
			Ruta ruta = rutasEntreCiudades.get(i);
			if (ruta.getTrayectosDeRuta().size() <= cantMaximaViajes){
				cantRutasConCantMaximaViajes++;
			}
		}
		
		return cantRutasConCantMaximaViajes;
	}

	public int cantRutasConCantViajes(String nombreCiudadInicial,String nombreCiudadFinal, int cantExactaViajes){
		Ciudad ciudadInicial = this.getCiudadPorNombre(nombreCiudadInicial);
		Ciudad ciudadFinal = this.getCiudadPorNombre(nombreCiudadFinal);
		
		List<Ruta> rutasEntreCiudadesRepetidas = this.calculadorRuta.getRutasPosiblesRepiteCiudades(ciudadInicial, ciudadFinal, cantExactaViajes);
		
		return rutasEntreCiudadesRepetidas.size();
	}
	
	public int getDistanciaRutaMasCortaEntreCiudades(String nombreCiudadInicial,String nombreCiudadFinal){
		Ciudad ciudadInicial = this.getCiudadPorNombre(nombreCiudadInicial);
		Ciudad ciudadFinal = this.getCiudadPorNombre(nombreCiudadFinal);
		
		List<Ruta> rutasEntreCiudades = this.recorrer(ciudadInicial, ciudadFinal);
		int distanciaRutaMasCortaEntreCiudades = rutasEntreCiudades.get(0).getDistanciaTotalRuta();
		
		for(int i=1; i < rutasEntreCiudades.size(); i++){
			Ruta ruta = rutasEntreCiudades.get(i);
			
			if (ruta.getDistanciaTotalRuta() < distanciaRutaMasCortaEntreCiudades){
				distanciaRutaMasCortaEntreCiudades = ruta.getDistanciaTotalRuta();
			}
		}
		
		return distanciaRutaMasCortaEntreCiudades;
	}
	
	public int cantRutasConMaxDistancia(String nombreCiudadInicial,String nombreCiudadFinal, int cantMaximaDistancia){
		Ciudad ciudadInicial = this.getCiudadPorNombre(nombreCiudadInicial);
		Ciudad ciudadFinal = this.getCiudadPorNombre(nombreCiudadFinal);
		
		List<Ruta> rutasEntreCiudades = this.recorrerCiudadesRepetidasDistancia(ciudadInicial, ciudadFinal, cantMaximaDistancia);
		
		return rutasEntreCiudades.size();
	}
	
	private Ciudad getCiudadPorNombre(String nombreCiudad){
		return this.ciudadesRecorridas.get(nombreCiudad);
	}

	private List<Ruta> recorrer(Ciudad ciudadInicial,Ciudad ciudadFinal){
		return this.calculadorRuta.getRutasPosibles(ciudadInicial, ciudadFinal);
	}

	private List<Ruta> recorrerCiudadesRepetidasDistancia(Ciudad ciudadInicial,Ciudad ciudadFinal,int distanciaMax){
		return this.calculadorRuta.getRutasPosiblesCiudadesRepetidasDistanciaMenor(ciudadInicial, ciudadFinal, distanciaMax);
	}
	
}
