package lanzador; 

 import java.util.LinkedList;
public class PostBody {

private LinkedList<String> nombre;

private LinkedList<String> dinero;

	public LinkedList<String> getnombre() {
		return nombre;
	}

	public LinkedList<String> getdinero() {
		return dinero;
	}

	public void setnombre(LinkedList<String> nombre) {
		this.nombre = nombre;
	}

 	public void setdinero(LinkedList<String> dinero) {
		this.dinero = dinero;
	}

 
}