package modelo.rest;

import java.util.LinkedList;

import modelo.Program;
import modelo.Service;

public class ProgramRest extends Program{

	// ATRIBUTES
	private LinkedList<Service> services;

	// CONSTRUCTORS
	public ProgramRest() {
		super();
		services = new LinkedList< 	Service>();
	}
	
	public ProgramRest(LinkedList<Service> services) {
		super();
		this.services = services;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Rest [services=" + services + "]";
	}
	
	// GETTERS AND SETTERS
	public LinkedList<Service> getServices() {
		return services;
	}

	public void setServices(LinkedList<Service> services) {
		this.services = services;
	}
	
	
}
