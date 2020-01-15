package modelo;

import java.util.LinkedList;

/**
 * The Class Operation.
 */
public class Operation {
	
	// ATRIBUTES
	/** The operation type. */
	private OperationType operationType;
	
	/** The data input. */
	private LinkedList<DataInput> dataInput;
	
	// CONSTRUCTORS
	/**
	 * Instantiates a new operation.
	 */
	public Operation() {
		super();
		this.dataInput = new LinkedList<DataInput>();
		this.operationType = OperationType.math;
	}	
	
	/**
	 * Instantiates a new operation.
	 *
	 * @param operationType the operation type
	 * @param dataInput the data input
	 */
	public Operation(OperationType operationType, LinkedList<DataInput> dataInput) {
		super();
		this.operationType = operationType;
		this.dataInput = dataInput;
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
		return "Operation [operationType=" + operationType + ", dataInput=" + dataInput + "]";
	}
	
	// GETTERS AND SETTERS
	/**
	 * Gets the operation type.
	 *
	 * @return the operation type
	 */
	public OperationType getOperationType() {
		return operationType;
	}

	/**
	 * Sets the operation type.
	 *
	 * @param operationType the new operation type
	 */
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	/**
	 * Gets the data input.
	 *
	 * @return the data input
	 */
	public LinkedList<DataInput> getDataInput() {
		return dataInput;
	}

	/**
	 * Sets the data input.
	 *
	 * @param dataInput the new data input
	 */
	public void setDataInput(LinkedList<DataInput> dataInput) {
		this.dataInput = dataInput;
	}
	

}
