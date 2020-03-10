package lanzador;

public class VentasBody {

	private String Nombre;

	private String Fecha;

	public VentasBody(String Nombre, String Fecha) {
		this.Nombre = Nombre;
		this.Fecha = Fecha;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public void setFecha(String Fecha) {
		this.Fecha = Fecha;
	}

}