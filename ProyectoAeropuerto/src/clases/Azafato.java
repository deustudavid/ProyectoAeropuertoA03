package clases;

public class Azafato extends Usuario{
	int anyosExperiencia;
	String funcion;

	
	
	
	public Azafato(String usuario, String contrasenya, int anyosExperiencia, String funcion) {
		super(usuario, contrasenya);
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

	

	

	

}
