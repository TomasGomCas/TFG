package modelo;

import modelo.generador.Reader;
import modelo.generador.ReaderSpring;
import modelo.generador.Writter;
import modelo.generador.WritterSpring;

/**
 * The Class Application.
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

	// METHODS
	/**
	 * Generate program spring.
	 */
	public void generateProgramSpring() {
		readerSpring.read();
		writterSpring.write();
	}

	// DELEGATED METHODS

	// GETTERS AND SETTERS
	/**
	 * Gets the single instance of Application.
	 *
	 * @return single instance of Application
	 */
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
