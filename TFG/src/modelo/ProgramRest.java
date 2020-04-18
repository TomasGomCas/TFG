package modelo;

import java.util.LinkedList;

/**
 * The Class ProgramRest. Encargada de representar la estrutura del programa
 * REST
 */
public class ProgramRest {

	/** The services. */
	// ATRIBUTES
	private LinkedList<Service> services;

	/**
	 * Instantiates a new program rest.
	 */
	// CONSTRUCTORS
	public ProgramRest() {
		super();
		services = new LinkedList<Service>();
	}

	// METHODS

	/**
	 * To string.
	 *
	 * @return the string
	 */
	// DELEGATED METHODS
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
