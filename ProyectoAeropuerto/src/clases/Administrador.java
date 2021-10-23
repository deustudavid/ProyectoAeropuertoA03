package clases;

public class Administrador extends Persona {
	String cargo;

	public Administrador(String dni, String nombre, int edad, String cargo) {
		super(dni, nombre, edad);
		this.cargo = cargo;
	}

	public Administrador() {
		super();
		this.cargo = "";
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public String toString() {
		return "Administrador [cargo=" + cargo + ", getDni()=" + getDni() + ", getNombre()=" + getNombre()
				+ ", getEdad()=" + getEdad() + "]";
	}

}
