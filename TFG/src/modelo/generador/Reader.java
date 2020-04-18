package modelo.generador;

import java.io.IOException;

/**
 * The Interface Reader.
 */
public interface Reader {

	/**
	 * Read.
	 *
	 * @param rutaEntrada the ruta entrada
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// METHODS
	public void read(String rutaEntrada) throws IOException;

}
