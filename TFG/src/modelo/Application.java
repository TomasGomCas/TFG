package modelo;

public class Application {

	// ATRIBUTES
    private static final Application INSTANCE = new Application();
    private ProgramRest program;
    
    // CONSTRUCTORS
    private Application() { 	
    }

    // METHODS
    
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
