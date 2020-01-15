package modelo;

import java.util.LinkedList;

/**
 * The Class ProgramRest.
 */
public class ProgramRest {

	// ATRIBUTES
	/** The services. */
	private LinkedList<Service> services;

	// CONSTRUCTORS
	/**
	 * Instantiates a new program rest.
	 */
	public ProgramRest() {
		super();
		services = new LinkedList<Service>();
	}
	
	/**
	 * Instantiates a new program rest.
	 *
	 * @param services the services
	 */
	public ProgramRest(LinkedList<Service> services) {
		super();
		this.services = services;
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
		return "Rest [services=" + services + "]";
	}
	
	// GETTERS AND SETTERS
	/**
	 * Gets the services.
	 *
	 * @return the services
	 */
	public LinkedList<Service> getServices() {
		return services;
	}

	/**
	 * Sets the services.
	 *
	 * @param services the new services
	 */
	public void setServices(LinkedList<Service> services) {
		this.services = services;
	}
	
	
}
