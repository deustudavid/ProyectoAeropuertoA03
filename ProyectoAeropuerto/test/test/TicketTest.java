package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Clase;
import clases.Ticket;

public class TicketTest {

	Ticket t = new Ticket(1, "ID", "11111111A", Clase.BARATO, 0.0, 2, "12-02-2021");
	Ticket ti = new Ticket();

	@Test
	public void testGetTicket() {
		assertEquals(1, t.getTicketNum());
	}

	@Test
	public void testGetIDVuelo() {
		assertEquals("ID", t.getIDVuelo());
	}

	@Test
	public void testDNIPasajero() {
		assertEquals("11111111A", t.getDNIPasajero());
	}

	@Test
	public void testGetClase() {
		assertEquals(Clase.BARATO, t.getClase());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(0.0, t.getPrecio(), 0.0f);
	}

	@Test
	public void testGetAsientos() {
		assertEquals(2, t.getAsientos());
	}

	@Test
	public void testGetFecha() {
		assertEquals("12-02-2021", t.getFecha());
	}

	@Test
	public void testSetTicket() {
		ti.setTicketNum(2);
		assertEquals(2, ti.getTicketNum());
	}

	@Test
	public void testSetIDVuelo() {
		ti.setIDVuelo("ID");
		assertEquals("ID", ti.getIDVuelo());
	}

	@Test
	public void testSNIPasajero() {
		ti.setDNIPasajero("222222B");
		assertEquals("222222B", ti.getDNIPasajero());
	}

	@Test
	public void testSetClase() {
		ti.setClase(Clase.BARATO);
		assertEquals(Clase.BARATO, ti.getClase());
	}

	@Test
	public void testSetPrecio() {
		ti.setPrecio(1.1);
		assertEquals(1.1, ti.getPrecio(), 0.0f);
	}

	@Test
	public void testSetAsientos() {
		ti.setAsientos(3);
		assertEquals(3, ti.getAsientos());
	}

	@Test
	public void testSetFecha() {
		ti.setFecha("12-03-2021");
		assertEquals("12-03-2021", ti.getFecha());
	}
	
	@Test
	public void testToString() {
		assertEquals("Ticket [TicketNum=" + t.getTicketNum() + ", IDVuelo=" + t.getIDVuelo() + ", DNIPasajero=" + t.getDNIPasajero() + ", clase="
				+ t.getClase() + ", precio=" + t.getPrecio() + ", asientos=" + t.getAsientos() + ", fecha=" + t.getFecha() + "]", t.toString());
	}

}
