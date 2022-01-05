package clases;

import java.util.ArrayList;

public class Pasajero extends Persona {
	
	int telefono;
	String direccion;
	String rutaFoto;
	
	
	public Pasajero(String dni, String nombre, String apellido, int edad, int telefono, String direccion, String rutaFoto) {
		super(dni, nombre, apellido, edad);
		
		this.telefono=telefono;
		this.direccion=direccion;
		this.rutaFoto=rutaFoto;
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
	

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	@Override
	public String toString() {
		return getDni();
	}

	
	
	
	

}
