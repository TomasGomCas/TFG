package modelo;

public class Application {

	// ATRIBUTES
    private static final Application INSTANCE = new Application();
    private Program program;
    
    // CONSTRUCTORS
    private Application() { 	
    }

    // METHODS
    
    // DELEGATED METHODS
    
    // GETTERS AND SETTERS
    public static Application getInstance() {
        return INSTANCE;
    }

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
    
    
}
