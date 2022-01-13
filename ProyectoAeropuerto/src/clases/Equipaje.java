package clases;

public class Equipaje {
	private int equipajeNum;
	private String dniPasajero;
	private String descripcion;
	private double peso;
	private double largo;
	private double altura;
	private double anchura;

	public Equipaje(int equipajeNum, String dniPasajero,String descripcion, double peso, double largo, double altura, double anchura) {
		super();
		this.equipajeNum=equipajeNum;
		this.dniPasajero=dniPasajero;
		this.descripcion = descripcion;
		this.peso = peso;
		this.largo = largo;
		this.altura = altura;
		this.anchura = anchura;
	}

	public Equipaje() {
		super();
		this.equipajeNum=0;
		this.dniPasajero="";
		this.descripcion = "";
		this.peso = 0.0;
		this.largo = 0.0;
		this.altura = 0.0;
		this.anchura = 0.0;
	}

	

	public int getEquipajeNum() {
		return equipajeNum;
	}

	public void setEquipajeNum(int equipajeNum) {
		this.equipajeNum = equipajeNum;
	}

	public String getDniPasajero() {
		return dniPasajero;
	}

	public void setDniPasajero(String dniPasajero) {
		this.dniPasajero = dniPasajero;
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
		return "Equipaje [equipajeNum=" + equipajeNum + ", dniPasajero=" + dniPasajero + ", descripcion=" + descripcion
				+ ", peso=" + peso + ", largo=" + largo + ", altura=" + altura + ", anchura=" + anchura + "]";
	}

	

}
