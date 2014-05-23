package ar.com.dridco.main;

import java.util.List;

import ar.com.dridco.domain.Ferrocarril;
import ar.com.dridco.domain.Ruta;

public class MainProgram {

	public static void main(String[] args) {
		String grafoRecorridos = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		
		Ferrocarril ferrocarril = new Ferrocarril(grafoRecorridos);
		
		List<Ruta> rutasPosibles = ferrocarril.recorrer("C","C");
		for(int i=0; i < rutasPosibles.size(); i++){
			System.out.println("Ruta:" + rutasPosibles.get(i));	
		}
	}

}
