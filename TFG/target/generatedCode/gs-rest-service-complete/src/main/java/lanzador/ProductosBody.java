package lanzador;

public class ProductosBody {

	private String Nombre;

	private String Precio;

	public ProductosBody(String Nombre, String Precio) {
		this.Nombre = Nombre;
		this.Precio = Precio;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getPrecio() {
		return Precio;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public void setPrecio(String Precio) {
		this.Precio = Precio;
	}

}