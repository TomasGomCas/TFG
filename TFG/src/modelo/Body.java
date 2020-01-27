package modelo;

import java.util.LinkedList;

public class Body {

	// ATRIBUTES
	private LinkedList<DataInput> dataInputs;

	// CONSTRUCTORS
	public Body() {
		super();
		this.dataInputs = new LinkedList<DataInput>();
	}

	public Body(LinkedList<DataInput> dataInputs) {
		super();
		this.dataInputs = dataInputs;
	}

	// METHODS
	@Override
	public String toString() {
		return "Body [dataInputs=" + dataInputs + "]";
	}

	// HEREDATED METHODAS

	// GETTES AND SETTERs
	public LinkedList<DataInput> getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(LinkedList<DataInput> dataInputs) {
		this.dataInputs = dataInputs;
	}

}
