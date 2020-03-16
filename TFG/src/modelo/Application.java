package modelo;

import java.io.IOException;

import modelo.generador.Reader;
import modelo.generador.ReaderSpring;
import modelo.generador.Writter;
import modelo.generador.WritterSpring;

public class Application {

	// ATRIBUTES
	private static final Application INSTANCE = new Application();
	private ProgramRest program = new ProgramRest();
	private Reader readerSpring = new ReaderSpring();
	private Writter writterSpring = new WritterSpring();

	// CONSTRUCTORS
	private Application() {
	}

	// METHODS
	public void generateProgramSpring(String rutaEntrada, String rutaSalida) throws IOException {
		readerSpring.read(rutaEntrada);
		writterSpring.write(rutaSalida);

	}

	// DELEGATED METHODS

	// GETTERS AND SETTERS
	public static Application getInstance() {
		return INSTANCE;
	}

	public ProgramRest getProgramRest() {
		return program;
	}

	public void setProgramRest(ProgramRest program) {
		this.program = program;
	}

}
