package ar.com.dridco.exception;

@SuppressWarnings("serial")
public class NoExisteRutaException extends Exception {

	public NoExisteRutaException(String mensaje) {
		super(mensaje);
	}

}
