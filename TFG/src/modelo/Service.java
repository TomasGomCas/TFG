package modelo;

import java.util.Iterator;
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
	public LinkedList<DataInput> getDataInputs(){
		
		LinkedList<DataInput> dataInputs = new LinkedList<DataInput>();
		
		for (Data data : this.data) {
			if(data instanceof DataInput) dataInputs.add((DataInput) data);
		}
	
		return dataInputs;
	}

	public DataInput getDataInputByAddress(String address) {
		
		for (DataInput data : getDataInputs()) {
			if(data.getAddress().equalsIgnoreCase(address)) return data;
		}
		
		return null;
	}
	
	public DataOutput getDataOutput() {
			
		for (Data data : this.data) {
			if(data instanceof DataOutput) return (DataOutput) data;
		}
		return null;
	}
	
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
