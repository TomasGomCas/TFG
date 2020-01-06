package modelo;

import java.util.LinkedList;

public class Service {
	
	// ATRIBUTES
	private ServiceType tipoServicio;
	private LinkedList<Data> data;
	private String name;
	
	// CONSTRUCTORS
	public Service() {
		super();
		this.data = new LinkedList<Data>();
	}
	
	public Service(ServiceType tipoServicio, LinkedList<Data> data, String name) {
		super();
		this.tipoServicio = tipoServicio;
		this.data = data;
		this.name = name;
	}

	// METHODS

	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Service [tipoServicio=" + tipoServicio + ", data=" + data + ", name=" + name + "]";
	}
	
	// GETTERS AND SETTERS
	public ServiceType getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(ServiceType tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

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
