package modelo;

import java.util.LinkedList;

public class Service {
	
	// ATRIBUTES
	private ServiceType tipoServicio;
	private Operation operation;
	private LinkedList<Data> data;
	
	// CONSTRUCTORS
	public Service() {
		super();
	}
	
	public Service(ServiceType tipoServicio, Operation operation, LinkedList<Data> data) {
		super();
		this.tipoServicio = tipoServicio;
		this.operation = operation;
		this.data = data;
	}

	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Service [tipoServicio=" + tipoServicio + ", operation=" + operation + ", data=" + data + "]";
	}

	
	// GETTERS AND SETTERS
	public ServiceType getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(ServiceType tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public LinkedList<Data> getData() {
		return data;
	}

	public void setData(LinkedList<Data> data) {
		this.data = data;
	}

	
}
