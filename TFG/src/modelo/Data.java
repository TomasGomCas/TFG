package modelo;

public class Data {

	// ATRIBUTES
	private String name;

	// CONSTRUCTORS
	public Data() {
		super();
	}

	// METHODS

	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Data [name=" + name + "]";
	}

	// GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
