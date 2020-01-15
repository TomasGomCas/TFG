package modelo;

public class DataOutput extends Data{

	// ATRIBUTES
	private Operation operation;
	
	// CONSTRUCTORS
	public DataOutput() {
		super();
		this.operation = new Operation();
	}	
	
	public DataOutput(Operation operation) {
		super();
		this.operation = operation;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "DataOutput [operation=" + operation + "]";
	}
	
	
	// GETTERS AND SETTERS
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
