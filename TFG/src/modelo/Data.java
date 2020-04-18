package modelo;

/**
 * The Class Data. Encargada de representar los datos.
 */
public class Data {

	// ATRIBUTES
	/** The name. */
	private String name;

	/** The address. */
	private String address;

	/** The formula. */
	private boolean formula;

	/** The formula value. */
	private String formulaValue;

	// CONSTRUCTORS
	/**
	 * Instantiates a new data.
	 */
	public Data() {
		super();
		formula = false;
		formulaValue = null;
		address = null;
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
		return "Data [name=" + name + "]";
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// GETTERS AND SETTERS
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
	 * Checks if is formula.
	 *
	 * @return true, if is formula
	 */
	public boolean isFormula() {
		return formula;
	}

	/**
	 * Sets the formula.
	 *
	 * @param formula the new formula
	 */
	public void setFormula(boolean formula) {
		this.formula = formula;
	}

	/**
	 * Gets the formula value.
	 *
	 * @return the formula value
	 */
	public String getFormulaValue() {
		return formulaValue;
	}

	/**
	 * Sets the formula value.
	 *
	 * @param formulaValue the new formula value
	 */
	public void setFormulaValue(String formulaValue) {
		this.formulaValue = formulaValue;
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
