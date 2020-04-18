package modelo;

import java.io.IOException;

import modelo.generador.Reader;
import modelo.generador.ReaderSpring;
import modelo.generador.Writter;
import modelo.generador.WritterSpring;

/**
 * The Class Application. Se trata de una clase singleton orquestadora.
 */
public class Application {

	// ATRIBUTES
	/** The Constant INSTANCE. */
	private static final Application INSTANCE = new Application();

	/** The program. */
	private ProgramRest program = new ProgramRest();

	/** The reader spring. */
	private Reader readerSpring = new ReaderSpring();

	/** The writter spring. */
	private Writter writterSpring = new WritterSpring();

	// CONSTRUCTORS
	/**
	 * Instantiates a new application.
	 */
	private Application() {
	}

	/**
	 * Generate program spring.
	 *
	 * @param rutaEntrada the ruta entrada
	 * @param rutaSalida  the ruta salida
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// METHODS
	public void generateProgramSpring(String rutaEntrada, String rutaSalida) throws IOException {
		readerSpring.read(rutaEntrada);
		writterSpring.write(rutaSalida);

	}

	// DELEGATED METHODS

	/**
	 * Gets the single instance of Application.
	 *
	 * @return single instance of Application
	 */
	// GETTERS AND SETTERS
	public static Application getInstance() {
		return INSTANCE;
	}

	/**
	 * Gets the program rest.
	 *
	 * @return the program rest
	 */
	public ProgramRest getProgramRest() {
		return program;
	}

	/**
	 * Sets the program rest.
	 *
	 * @param program the new program rest
	 */
	public void setProgramRest(ProgramRest program) {
		this.program = program;
	}

}
