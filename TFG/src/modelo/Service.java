package modelo;

import java.util.LinkedList;

/**
 * The Class Service.
 */
public class Service {

	// ATRIBUTES
	/** The tipo servicio. */
	private ServiceType tipoServicio;

	/** The data. */
	private LinkedList<Data> data;

	/** The name. */
	private String name;

	private Body body;

	// CONSTRUCTORS
	/**
	 * Instantiates a new service.
	 */
	public Service() {
		super();
		this.data = new LinkedList<Data>();
		this.body = new Body();
	}

	/**
	 * Instantiates a new service.
	 *
	 * @param tipoServicio the tipo servicio
	 * @param data         the data
	 * @param name         the name
	 */
	public Service(ServiceType tipoServicio, LinkedList<Data> data, String name) {
		super();
		this.tipoServicio = tipoServicio;
		this.data = data;
		this.name = name;
		this.body = new Body();
	}

	// METHODS
	/**
	 * Gets the data inputs.
	 *
	 * @return the data inputs
	 */
	public LinkedList<DataInput> getDataInputs() {

		LinkedList<DataInput> dataInputs = new LinkedList<DataInput>();

		for (Data data : this.data) {
			if (data instanceof DataInput)
				dataInputs.add((DataInput) data);
		}

		return dataInputs;
	}

	/**
	 * Gets the data input by address.
	 *
	 * @param address the address
	 * @return the data input by address
	 */
	public DataInput getDataInputByAddress(String address) {

		for (DataInput data : getDataInputs()) {
			if (data.getAddress().equalsIgnoreCase(address))
				return data;
		}

		return null;
	}

	/**
	 * Gets the data output.
	 *
	 * @return the data output
	 */
	public DataOutput getDataOutput() {

		for (Data data : this.data) {
			if (data instanceof DataOutput)
				return (DataOutput) data;
		}
		return null;
	}

	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Service [tipoServicio=" + tipoServicio + ", data=" + data + ", name=" + name + ", body=" + body + "]";
	}

	// GETTERS AND SETTERS
	/**
	 * Gets the tipo servicio.
	 *
	 * @return the tipo servicio
	 */
	public ServiceType getTipoServicio() {
		return tipoServicio;
	}

	/**
	 * Sets the tipo servicio.
	 *
	 * @param tipoServicio the new tipo servicio
	 */
	public void setTipoServicio(ServiceType tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
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

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
