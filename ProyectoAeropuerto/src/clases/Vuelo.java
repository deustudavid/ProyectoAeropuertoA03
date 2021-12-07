package clases;

import java.util.ArrayList;
import java.util.Date;

public class Vuelo {
	String ID;
	String origen;
	String destino;
	String fecha;
	String horaSalida;
	String horaLlegada;
	double precio;
	ArrayList<Pasajero>Maleta = new ArrayList<>();
	
	public Vuelo(String ID, String origen, String destino, String fecha, String horaSalida, String horaLlegada,
			double precio) {
		super();
		this.ID = ID;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.precio = precio;
	}
	
	
	public Vuelo() {
		super();
		this.ID = "";
		this.origen = "";
		this.destino = "";
		this.fecha = null;
		this.horaSalida = "";
		this.horaLlegada = "";
		this.precio = 0.0;
	}
	
	// constructor sin atributo precio
    public Vuelo(String id2, String origen2, String destino2, String fecha2, String horaSalida2, String horaLlegada2) {
        super();
        this.ID = id2;
        this.origen = origen2;
        this.destino = destino2;
        this.fecha = fecha2;
        this.horaSalida = horaSalida2;
        this.horaLlegada = horaLlegada2;

    }


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}


	public String getHoraLlegada() {
		return horaLlegada;
	}


	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "Vuelo [ID=" + ID + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha + ", horaSalida="
				+ horaSalida + ", horaLlegada=" + horaLlegada + ", precio=" + precio + "]";
	}
	
	
	



	

}