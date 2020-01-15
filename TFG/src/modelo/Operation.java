package modelo;

import java.util.LinkedList;

public class Operation {
	
	// ATRIBUTES
	private OperationType operationType;
	private LinkedList<DataInput> dataInput;
	
	// CONSTRUCTORS
	public Operation() {
		super();
		this.dataInput = new LinkedList<DataInput>();
		this.operationType = OperationType.math;
	}	
	
	public Operation(OperationType operationType, LinkedList<DataInput> dataInput) {
		super();
		this.operationType = operationType;
		this.dataInput = dataInput;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Operation [operationType=" + operationType + ", dataInput=" + dataInput + "]";
	}
	
	// GETTERS AND SETTERS
	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public LinkedList<DataInput> getDataInput() {
		return dataInput;
	}

	public void setDataInput(LinkedList<DataInput> dataInput) {
		this.dataInput = dataInput;
	}
	

}
