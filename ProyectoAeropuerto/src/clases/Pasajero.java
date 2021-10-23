package clases;
public class Pasajero extends Persona {
	Equipaje equipaje;

	public Pasajero(String dni, String nombre, int edad, Equipaje equipaje) {
		super(dni, nombre, edad);
		this.equipaje = equipaje;
	}
	
	public Pasajero() {
		super();
		this.equipaje = null;
	}

	public Equipaje getEquipaje() {
		return equipaje;
	}

	public void setEquipaje(Equipaje equipaje) {
		this.equipaje = equipaje;
	}

	@Override
	public String toString() {
		return "Pasajero [equipaje=" + equipaje + ", getDni()=" + getDni() + ", getNombre()=" + getNombre()
				+ ", getEdad()=" + getEdad() + "]";
	}
	
	

}
