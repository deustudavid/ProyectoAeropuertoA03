package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;

import clases.Vuelo;

public class VueloTest {

	long milis = System.currentTimeMillis();
	Date fecha = new Date(milis);

	Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
	Vuelo b = new Vuelo();

	@Test
	public void testGetId() {
		assertEquals("1", v.getID());
	}

	@Test
	public void testGetOrigen() {
		assertEquals("Bilbao", v.getOrigen());
	}

	@Test
	public void testGetDestino() {
		assertEquals("Madrid", v.getDestino());
	}

	@Test
	public void testGetFecha() {
		assertEquals(fecha, v.getFecha());
	}

	@Test
	public void testGetHoraSalida() {
		assertEquals("2:00", v.getHoraSalida());
	}

	@Test
	public void testGetHoraLlegada() {
		assertEquals("3:00", v.getHoraLlegada());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(v.getPrecio() == 20.0,0.01);
	}

	@Test
	public void testSetId() {
		b.setID("1");
		assertEquals("1", b.getID());
	}

	@Test
	public void testSetOrigen() {
		b.setOrigen("Bilbao");
		assertEquals("Bilbao", b.getOrigen());
	}

	@Test
	public void testSetDestino() {
		b.setDestino("Madrid");
		assertEquals("Madrid", b.getDestino());
	}

	@Test
	public void testSetFecha() {
		b.setFecha(fecha);
		assertEquals(fecha, b.getFecha());
	}

	@Test
	public void testSetHoraSalida() {
		b.setHoraSalida("2:00");
		assertEquals("2:00", b.getHoraSalida());
	}

	@Test
	public void testSetHoraLlegada() {
		b.setHoraLlegada("3:00");
		assertEquals("3:00", b.getHoraLlegada());
	}

	@Test
	public void testSetPrecio() {
		b.setPrecio(20.0);
		assertEquals(b.getPrecio() == 20.0,0.01);
	}

	@Test
	public void testToString() {
		assertEquals("Vuelo [ID=" + v.getID() + ", origen=" + v.getOrigen() + ", destino=" + v.getDestino() + ", fecha="
				+ v.getFecha() + ", horaSalida=" + v.getHoraSalida() + ", horaLlegada=" + v.getHoraLlegada()
				+ ", precio=" + v.getPrecio() + "]", v.toString());
	}

}
