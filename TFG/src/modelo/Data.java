package modelo;

public class Data {
	
	// ATRIBUTES
	private String name;
	private String value;
	private DataType dataType;
	private String address;
	
	// CONSTRUCTORS
	public Data() {
		super();
		dataType= DataType.String;
	}
	
	public Data(String name, String value, DataType dataType, String address) {
		super();
		this.name = name;
		this.value = value;
		this.dataType = dataType;
		this.address = address;
	}

	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Data [name=" + name + ","+ ", value=" + value + ", dataType=" + dataType + ", address="
				+ address + "]";
	}
	
	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
