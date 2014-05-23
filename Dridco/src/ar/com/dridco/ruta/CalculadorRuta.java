package ar.com.dridco.ruta;

import java.util.ArrayList;
import java.util.List;

import ar.com.dridco.domain.Ciudad;
import ar.com.dridco.domain.Recorrido;
import ar.com.dridco.domain.Ruta;

public class CalculadorRuta {

	private List<Recorrido> caminoActual;
	private List<Ruta> rutasPosibles;

	public List<Ruta> getRutasPosibles(Ciudad ciudadInicial,Ciudad ciudadFinal){
		this.rutasPosibles = new ArrayList<Ruta>();
		this.caminoActual = new ArrayList<Recorrido>();
		List<Ciudad> visitados = new ArrayList<Ciudad>();
		
		this.recorrerRecursivoMinimo(ciudadInicial, ciudadFinal, visitados);
		
		return rutasPosibles;
	}
	
	private void recorrerRecursivoMinimo (Ciudad ciudad,Ciudad ciudadFinal,List<Ciudad> visitados) {
		visitados.add(ciudad);
		
		List<Recorrido> recorridosCiudad =  ciudad.getRecorridosDestinoList();
		for(int i=0; i < recorridosCiudad.size(); i++){
			Ciudad ciudadDeRecorrido = recorridosCiudad.get(i).getCiudadDestiono();
			
			if(ciudadDeRecorrido.equals(ciudadFinal) || visitados.contains(ciudadDeRecorrido)){
				visitados = new ArrayList<Ciudad>();
				this.caminoActual.add(recorridosCiudad.get(i));
				
				if (ciudadDeRecorrido.equals(ciudadFinal)){
					List<Recorrido> caminoAGuardar = this.copiaLista(caminoActual);
					
					this.rutasPosibles.add(new Ruta(caminoAGuardar));
				}
				
			}else{
				this.caminoActual.add(recorridosCiudad.get(i));
				this.recorrerRecursivoMinimo(ciudadDeRecorrido, ciudadFinal, visitados);
			}
			
			this.caminoActual.remove(this.caminoActual.size()-1);
		}
	}
	
	public List<Ruta> getRutasPosiblesRepiteCiudades(Ciudad ciudadInicial,Ciudad ciudadFinal, int cantMaxParadas){
		this.rutasPosibles = new ArrayList<Ruta>();
		this.caminoActual = new ArrayList<Recorrido>();
		
		this.recorrerRecursivoRepiteCiudades(ciudadInicial, ciudadFinal, cantMaxParadas);
		
		return rutasPosibles;
	}
	
	private void recorrerRecursivoRepiteCiudades(Ciudad ciudad,Ciudad ciudadFinal,int cantMaxParadas) {
		List<Recorrido> recorridosCiudad =  ciudad.getRecorridosDestinoList();
		for(int i=0; i < recorridosCiudad.size(); i++){
			Ciudad ciudadDeRecorrido = recorridosCiudad.get(i).getCiudadDestiono();
			this.caminoActual.add(recorridosCiudad.get(i));
			
			if(ciudadDeRecorrido.equals(ciudadFinal) || this.caminoActual.size() == cantMaxParadas){
				
				if (ciudadDeRecorrido.equals(ciudadFinal)){
					if (this.caminoActual.size() == cantMaxParadas){
						List<Recorrido> caminoAGuardar = this.copiaLista(caminoActual);
						
						this.rutasPosibles.add(new Ruta(caminoAGuardar));
					}
					
					if (this.caminoActual.size() < cantMaxParadas){
						this.recorrerRecursivoRepiteCiudades(ciudadDeRecorrido, ciudadFinal, cantMaxParadas);
					}
				}
				
			}else{
				this.recorrerRecursivoRepiteCiudades(ciudadDeRecorrido, ciudadFinal, cantMaxParadas);
			}
			
			this.caminoActual.remove(this.caminoActual.size()-1);
		}
	}
	
	public List<Ruta> getRutasPosiblesCiudadesRepetidasDistanciaMenor(Ciudad ciudadInicial,Ciudad ciudadFinal, int distanciaMax){
		this.rutasPosibles = new ArrayList<Ruta>();
		this.caminoActual = new ArrayList<Recorrido>();
		
		this.recorrerRecursivoDistancia(ciudadInicial, ciudadFinal, distanciaMax);
		
		return rutasPosibles;
	}
	
	private void recorrerRecursivoDistancia(Ciudad ciudad,Ciudad ciudadFinal, int distanciaMax)  {
		List<Recorrido> recorridosCiudad =  ciudad.getRecorridosDestinoList();
		for(int i=0; i < recorridosCiudad.size(); i++){
			Ciudad ciudadDeRecorrido = recorridosCiudad.get(i).getCiudadDestiono();
			this.caminoActual.add(recorridosCiudad.get(i));
			int distanciaCamino = this.getDistanciaTotalCamino(this.caminoActual);

			if(ciudadDeRecorrido.equals(ciudadFinal) || distanciaCamino > distanciaMax){
				
				if (ciudadDeRecorrido.equals(ciudadFinal)){
					if (distanciaCamino < distanciaMax){
						List<Recorrido> caminoAGuardar = this.copiaLista(caminoActual);
						this.rutasPosibles.add(new Ruta(caminoAGuardar));
						
						this.recorrerRecursivoDistancia(ciudadDeRecorrido, ciudadFinal, distanciaMax);
					}
				}
				
			}else{
				this.recorrerRecursivoDistancia(ciudadDeRecorrido, ciudadFinal, distanciaMax);
			}
			
			this.caminoActual.remove(this.caminoActual.size()-1);
		}
	}

	private int getDistanciaTotalCamino(List<Recorrido> camino){
		int distanciaTotal = 0;

		for(int i=0;i<camino.size();i++){
			distanciaTotal += camino.get(i).getDistancia();
		}
		
		return distanciaTotal;
	}
	
	private List<Recorrido> copiaLista(List<Recorrido> listaCopiar){
		List<Recorrido> listaNueva = new ArrayList<Recorrido>();		

		for(int i=0;i < listaCopiar.size();i++){
			listaNueva.add(listaCopiar.get(i));
		}
		
		return listaNueva;
	}

}
