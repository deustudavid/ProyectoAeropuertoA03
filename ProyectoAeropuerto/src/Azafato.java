
public class Azafato extends Persona {
	int anyosExperiencia;
	String funcion;
	
	public Azafato(String dni, String nombre, int edad, int anyosExperiencia, String funcion) {
		super(dni, nombre, edad);
		this.anyosExperiencia = anyosExperiencia;
		this.funcion = funcion;
	}
	
	public Azafato() {
		super();
		this.anyosExperiencia = 0;
		this.funcion = "";
	}

	public int getAnyosExperiencia() {
		return anyosExperiencia;
	}

	public void setAnyosExperiencia(int anyosExperiencia) {
		this.anyosExperiencia = anyosExperiencia;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	@Override
	public String toString() {
		return "Azafato [anyosExperiencia=" + anyosExperiencia + ", funcion=" + funcion + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getEdad()=" + getEdad() + "]";
	}
	
	
	

}
