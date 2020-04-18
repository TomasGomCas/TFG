package modelo;

import java.util.LinkedList;

/**
 * The Class Service. Encargada de representar los servicios.
 */
public class Service {

	/** The data. */
	// ATRIBUTES
	private LinkedList<Data> data;

	/** The name. */
	private String name;

	/**
	 * Instantiates a new service.
	 */
	// CONSTRUCTORS
	public Service() {
		super();
		this.data = new LinkedList<Data>();
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
		return "Service [data=" + data + ", name=" + name + "]";
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	// GETTERS AND SETTERS
	public LinkedList<Data> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(LinkedList<Data> data) {
		this.data = data;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
