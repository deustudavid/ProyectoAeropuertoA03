package clases;

public class Ticket {
	private int TicketNum;
	private String IDVuelo;
	private String DNIPasajero;
	private Clase clase;
	private double precio;
	private int asientos;
	private String fecha;
	
	public Ticket(int ticketNum, String iDVuelo, String dNIPasajero, Clase clase, double precio, int asientos,
			String fecha) {
		super();
		this.TicketNum = ticketNum;
		this.IDVuelo = iDVuelo;
		this.DNIPasajero = dNIPasajero;
		this.clase=clase;
		this.precio=precio;
		this.asientos = asientos;
		this.fecha = fecha;
	}
	
	public Ticket() {
		super();
		this.TicketNum = 0;
		this.IDVuelo = "";
		this.DNIPasajero = "";
		this.clase = Clase.ESTANDAR;
		this.precio = 0.0;
		this.asientos = 0;
		
	}


	public int getTicketNum() {
		return TicketNum;
	}

	public void setTicketNum(int ticketNum) {
		TicketNum = ticketNum;
	}

	public String getIDVuelo() {
		return IDVuelo;
	}

	public void setIDVuelo(String iDVuelo) {
		IDVuelo = iDVuelo;
	}

	public String getDNIPasajero() {
		return DNIPasajero;
	}

	public void setDNIPasajero(String dNIPasajero) {
		DNIPasajero = dNIPasajero;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAsientos() {
		return asientos;
	}

	public void setAsientos(int asiento) {
		this.asientos = asiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Ticket [TicketNum=" + TicketNum + ", IDVuelo=" + IDVuelo + ", DNIPasajero=" + DNIPasajero + ", clase="
				+ clase + ", precio=" + precio + ", asientos=" + asientos + ", fecha=" + fecha + "]";
	}
	
	
	
	
	

}
