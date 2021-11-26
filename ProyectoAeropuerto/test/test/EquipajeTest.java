package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import clases.Equipaje;

public class EquipajeTest {

	Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
	Equipaje b = new Equipaje();

	@Test
	public void testGetDescripcion() {
		assertEquals("Descripcion", e.getDescripcion());
	}

	@Test
	public void testGetPeso() {
		assertEquals(22.0 == e.getPeso(),true);
	}

	@Test
	public void testGetLargo() {
		assertEquals(10.4 == e.getLargo(),true);
	}

	@Test
	public void testGetAltura() {
		assertEquals(40.3 == e.getAltura(),true);
	}

	@Test
	public void testGetAnchura() {
		assertEquals(30.2 == e.getAnchura(),true);
	}

	@Test
	public void testSetDescripcion() {
		b.setDescripcion("Descripcion");
		assertEquals("Descripcion", b.getDescripcion());
	}

	@Test
	public void testSetPeso() {
		b.setPeso(22.0);
		assertEquals(22.0 == b.getPeso(),true);
	}

	@Test
	public void testSetLargo() {
		b.setLargo(10.4);
		assertEquals(10.4 == b.getLargo(),true);
	}

	@Test
	public void testSetAltura() {
		b.setAltura(40.3);
		assertEquals(40.3 == b.getAltura(),true);
	}

	@Test
	public void testSetAnchura() {
		b.setAnchura(30.2);
		assertEquals(30.2 == b.getAnchura(),true);
	}

	@Test
	public void testToString() {
		assertEquals("Equipaje [descripcion=" + e.getDescripcion() + ", peso=" + e.getPeso() + ", largo=" + e.getLargo()
				+ ", altura=" + e.getAltura() + ", anchura=" + e.getAnchura() + "]", e.toString());
	}
}
