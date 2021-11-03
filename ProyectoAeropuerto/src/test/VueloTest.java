package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;

import clases.Vuelo;

public class VueloTest {

	long milis = System.currentTimeMillis();
	Date fecha = new Date(milis);

	@Test
	public void testGetId() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals("1", v.getID());
	}

	@Test
	public void testGetOrigen() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals("Bilbao", v.getOrigen());
	}

	@Test
	public void testGetDestino() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals("Madrid", v.getDestino());
	}

	@Test
	public void testGetFecha() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals(fecha, v.getFecha());
	}

	@Test
	public void testGetHoraSalida() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals("2:00", v.getHoraSalida());
	}

	@Test
	public void testGetHoraLlegada() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20);
		assertEquals("3:00", v.getHoraLlegada());
	}

	@Test
	public void testGetPrecio() {
		Vuelo v = new Vuelo("1", "Bilbao", "Madrid", fecha, "2:00", "3:00", 20.0);
		assertTrue(v.getPrecio() == 20.0);
	}

}
