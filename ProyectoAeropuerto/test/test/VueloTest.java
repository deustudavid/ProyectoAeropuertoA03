package test;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import clases.Vuelo;

public class VueloTest {

	long milis = System.currentTimeMillis();

	Date fecha = new Date(milis);
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
	String f = sdf.format(fecha);

	Vuelo v = new Vuelo("1", "Bilbao", "Madrid", f, "2:00", "3:00", 0, 0);
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
		assertEquals(f, v.getFecha());
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
	public void testGetAsientosMax() {
		assertEquals(0, v.getAsientosMax());
	}

	@Test
	public void testGetAsientosRestantes() {
		assertEquals(0, v.getAsientosRestantes());
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
		b.setFecha(f);
		assertEquals(f, b.getFecha());
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
	public void testSetAsientosMax() {
		b.setAsientosMax(0);
		assertEquals(0, b.getAsientosMax());
	}

	@Test
	public void testSetAsientosRestantes() {
		b.setAsientosRestantes(4);
		assertEquals(4, b.getAsientosRestantes());
	}

	@Test
	public void testToString() {
		assertEquals("Vuelo [ID=" + v.getID() + ", origen=" + v.getOrigen() + ", destino=" + v.getDestino() + ", fecha="
				+ v.getFecha() + ", horaSalida=" + v.getHoraSalida() + ", horaLlegada=" + v.getHoraLlegada()
				+ ", asientosMax=" + v.getAsientosMax() + ", asientosRestantes=" + v.getAsientosRestantes() + "]",
				v.toString());
	}

}
