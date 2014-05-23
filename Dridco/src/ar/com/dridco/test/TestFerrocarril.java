package ar.com.dridco.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.dridco.domain.Ferrocarril;

public class TestFerrocarril {

	private static Ferrocarril ferrocarril;
	
	@BeforeClass
    public static void setUp() {
		String grafoRecorridos = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		
		ferrocarril = new Ferrocarril(grafoRecorridos);
    }

	@Test
	public void testCaso1() {
		List<String>  nombreCiudadesVisitadasEnOrden = new ArrayList<String>();
		
		nombreCiudadesVisitadasEnOrden.add("A");
		nombreCiudadesVisitadasEnOrden.add("B");
		nombreCiudadesVisitadasEnOrden.add("C");
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaDeterminadaString(nombreCiudadesVisitadasEnOrden), "9");
	}

	@Test
	public void testCaso2() {
		List<String>  nombreCiudadesVisitadasEnOrden = new ArrayList<String>();
		
		nombreCiudadesVisitadasEnOrden.add("A");
		nombreCiudadesVisitadasEnOrden.add("D");
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaDeterminadaString(nombreCiudadesVisitadasEnOrden), "5");
	}

	@Test
	public void testCaso3() {
		List<String>  nombreCiudadesVisitadasEnOrden = new ArrayList<String>();
		
		nombreCiudadesVisitadasEnOrden.add("A");
		nombreCiudadesVisitadasEnOrden.add("D");
		nombreCiudadesVisitadasEnOrden.add("C");
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaDeterminadaString(nombreCiudadesVisitadasEnOrden), "13");
	}

	@Test
	public void testCaso4() {
		List<String>  nombreCiudadesVisitadasEnOrden = new ArrayList<String>();
		
		nombreCiudadesVisitadasEnOrden.add("A");
		nombreCiudadesVisitadasEnOrden.add("E");
		nombreCiudadesVisitadasEnOrden.add("B");
		nombreCiudadesVisitadasEnOrden.add("C");
		nombreCiudadesVisitadasEnOrden.add("D");
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaDeterminadaString(nombreCiudadesVisitadasEnOrden), "22");
	}

	@Test
	public void testCaso5() {
		List<String>  nombreCiudadesVisitadasEnOrden = new ArrayList<String>();
		
		nombreCiudadesVisitadasEnOrden.add("A");
		nombreCiudadesVisitadasEnOrden.add("E");
		nombreCiudadesVisitadasEnOrden.add("D");
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaDeterminadaString(nombreCiudadesVisitadasEnOrden), "SIN RUTA");
	}

	@Test
	public void testCaso6() {
		String rutaInicial = "C";
		String rutaFinal = "C";
		int cantMaximaParadas = 3;
		
		Assert.assertEquals(ferrocarril.cantRutasConCantMaximaViajes(rutaInicial,rutaFinal,cantMaximaParadas), 2);
	}

	@Test
	public void testCaso7() {
		String rutaInicial = "A";
		String rutaFinal = "C";
		int cantParadas = 4;
		
		Assert.assertEquals(ferrocarril.cantRutasConCantViajes(rutaInicial,rutaFinal,cantParadas), 3);
	}

	@Test
	public void testCaso8() {
		String rutaInicial = "A";
		String rutaFinal = "C";
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaMasCortaEntreCiudades(rutaInicial,rutaFinal), 9);
	}

	@Test
	public void testCaso9() {
		String rutaInicial = "B";
		String rutaFinal = "B";
		
		Assert.assertEquals(ferrocarril.getDistanciaRutaMasCortaEntreCiudades(rutaInicial,rutaFinal), 9);
	}

	@Test
	public void testCaso10() {
		String rutaInicial = "C";
		String rutaFinal = "C";
		
		Assert.assertEquals(ferrocarril.cantRutasConMaxDistancia(rutaInicial,rutaFinal, 30), 7);
	}

}
