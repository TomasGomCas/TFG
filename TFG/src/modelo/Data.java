package modelo;

public class Data {
	
	// ATRIBUTES
	private boolean entrada;
	private String valor;
	
	// CONSTRUCTORS
	public Data() {
		super();
	}
	public Data(boolean entrada, String valor) {
		super();
		this.entrada = entrada;
		this.valor = valor;
	}
	
	// METHODS
	
	// GETTERS AND SETTERS
	public boolean isEntrada() {
		return entrada;
	}
	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
