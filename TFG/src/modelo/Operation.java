package modelo;

import java.util.LinkedList;

public class Operation {
	
	// ATRIBUTES
	private LinkedList<Data> data;
	
	// CONSTRUCTORS
	public Operation() {
		super();
		this.data = new LinkedList<Data>();
	}
	
	public Operation(LinkedList<Data> data) {
		super();
		this.data = data;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Operation [data=" + data + "]";
	}
	
	// GETTERS AND SETTERS
	public LinkedList<Data> getData() {
		return data;
	}

	public void setData(LinkedList<Data> data) {
		this.data = data;
	}
	
}
