package clases;

public class Avion {
	String modelo;
	String matricula;

	public Avion(String modelo, String matricula) {
		super();
		this.modelo = modelo;
		this.matricula = matricula;
	}

	public Avion() {
		super();
		this.modelo = "";
		this.matricula = "";
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Avion [modelo=" + modelo + ", matricula=" + matricula + "]";
	}

}
