package modelo;

public class Data {

	// ATRIBUTES
	private String name;
	private String address;
	private boolean formula;
	private String formulaValue;

	// CONSTRUCTORS
	public Data() {
		super();
		formula = false;
		formulaValue = null;
		address = null;
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

	public boolean isFormula() {
		return formula;
	}

	public void setFormula(boolean formula) {
		this.formula = formula;
	}

	public String getFormulaValue() {
		return formulaValue;
	}

	public void setFormulaValue(String formulaValue) {
		this.formulaValue = formulaValue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
