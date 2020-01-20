package modelo;


// TODO: Auto-generated Javadoc
/**
 * The Class Data.
 */
public class Data {
	
	// ATRIBUTES
	/** The name */
	private String name;
	
	/** The value. */
	private String value;
	
	/** The data type. */
	private DataType dataType;
	
	/** The address. */
	private String address;
	
	// CONSTRUCTORS
	/**
	 * Instantiates a new data.
	 */
	public Data() {
		super();
		dataType= DataType.String;
	}
	
	/**
	 * Instantiates a new data.
	 *
	 * @param name the name
	 * @param value the value
	 * @param dataType the data type
	 * @param address the address
	 */
	public Data(String name, String value, DataType dataType, String address) {
		super();
		this.name = name;
		this.value = value;
		this.dataType = dataType;
		this.address = address;
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
		return "Data [name=" + name + ","+ ", value=" + value + ", dataType=" + dataType + ", address="
				+ address + "]";
	}
	
	// GETTERS AND SETTERS
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

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the data type.
	 *
	 * @return the data type
	 */
	public DataType getDataType() {
		return dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType the new data type
	 */
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}
