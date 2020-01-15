package modelo;

/**
 * The Class DataOutput.
 */
public class DataOutput extends Data{

	// ATRIBUTES
	/** The operation. */
	private Operation operation;
	
	// CONSTRUCTORS
	/**
	 * Instantiates a new data output.
	 */
	public DataOutput() {
		super();
		this.operation = new Operation();
	}	
	
	/**
	 * Instantiates a new data output.
	 *
	 * @param operation the operation
	 */
	public DataOutput(Operation operation) {
		super();
		this.operation = operation;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "DataOutput [operation=" + operation + "]";
	}
	
	// GETTERS AND SETTERS
	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public Operation getOperation() {
		return operation;
	}

	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
