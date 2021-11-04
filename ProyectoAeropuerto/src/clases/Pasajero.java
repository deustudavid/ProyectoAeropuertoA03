package clases;

import java.util.ArrayList;

public class Pasajero extends Persona {
	
	int telefono;
	String direccion;
	ArrayList<Equipaje>ALmaletas = new ArrayList<>();
	
	
	public Pasajero(String dni, String nombre, String apellido, int edad, int telefono, String direccion) {
		super(dni, nombre, apellido, edad);
		
		this.telefono=telefono;
		this.direccion=direccion;
	}

	public Pasajero() {
		super();
		this.telefono=0;
		this.direccion="";
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Pasajero [telefono=" + telefono + ", direccion=" + direccion + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getEdad()=" + getEdad()
				+ "]";
	}

	
	
	
	

}
