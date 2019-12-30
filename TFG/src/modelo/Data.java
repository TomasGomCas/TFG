package modelo;

public class Data {
	
	// ATRIBUTES
	private boolean input;
	private String value;
	
	// CONSTRUCTORS
	public Data() {
		super();
	}
	public Data(boolean input, String value) {
		super();
		this.input = input;
		this.value = value;
	}
	
	// METHODS
	
	// DELEGATED METHODS
	@Override
	public String toString() {
		return "Data [input=" + input + ", value=" + value + "]";
	}
	
	// GETTERS AND SETTERS
	public boolean isEntrada() {
		return input;
	}
	public void setEntrada(boolean entrada) {
		this.input = entrada;
	}
	public String getValor() {
		return value;
	}
	public void setValor(String valor) {
		this.value = valor;
	}
	
}
