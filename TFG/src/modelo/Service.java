package modelo;

import java.util.LinkedList;

public class Service {

	// ATRIBUTES
	private LinkedList<Data> data;
	private String name;

	// CONSTRUCTORS
	public Service() {
		super();
		this.data = new LinkedList<Data>();
	}

	// METHODS

	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Service [data=" + data + ", name=" + name + "]";
	}

	// GETTERS AND SETTERS
	public LinkedList<Data> getData() {
		return data;
	}

	public void setData(LinkedList<Data> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
