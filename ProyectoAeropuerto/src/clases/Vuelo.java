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
	int asientosMax;

	ArrayList<Pasajero>Maleta = new ArrayList<>();
	
	public Vuelo(String ID, String origen, String destino, String fecha, String horaSalida, String horaLlegada, int asientosMax) {
		super();
		this.ID = ID;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.asientosMax=asientosMax;
	}
	
	



	public Vuelo() {
		super();
		this.ID = "";
		this.origen = "";
		this.destino = "";
		this.fecha = "";
		this.horaSalida = "";
		this.horaLlegada = "";
		this.asientosMax=0;
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

	public int getAsientosMax() {
		return asientosMax;
	}


	public void setAsientosMax(int asientosMax) {
		asientosMax = asientosMax;
	}





	@Override
	public String toString() {
		return "Vuelo [ID=" + ID + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha + ", horaSalida="
				+ horaSalida + ", horaLlegada=" + horaLlegada + ", asientosMax=" + asientosMax + "]";
	}




	
	
	



	

}