package clases;

public class Equipaje {
	String descripcion;
	double peso;
	double largo;
	double altura;
	double anchura;

	public Equipaje(String descripcion, double peso, double largo, double altura, double anchura) {
		super();
		this.descripcion = descripcion;
		this.peso = peso;
		this.largo = largo;
		this.altura = altura;
		this.anchura = anchura;
	}

	public Equipaje() {
		super();
		this.descripcion = "";
		this.peso = 0.0;
		this.largo = 0.0;
		this.altura = 0.0;
		this.anchura = 0.0;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getAnchura() {
		return anchura;
	}

	public void setAnchura(double anchura) {
		this.anchura = anchura;
	}

	@Override
	public String toString() {
		return "Equipaje [descripcion=" + descripcion + ", peso=" + peso + ", largo=" + largo + ", altura=" + altura
				+ ", anchura=" + anchura + "]";
	}

}
