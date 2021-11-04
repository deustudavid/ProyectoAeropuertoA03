package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import clases.Equipaje;

public class EquipajeTest {

	@Test
	public void testGetDescripcion() {
		Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
		assertEquals("Descripcion", e.getDescripcion());
	}

	@Test
	public void testGetPeso() {
		Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
		assertTrue(22.0 == e.getPeso());
	}

	@Test
	public void testGetLargo() {
		Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
		assertTrue(10.4 == e.getLargo());
	}

	@Test
	public void testGetAltura() {
		Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
		assertTrue(40.3 == e.getAltura());
	}

	@Test
	public void testGetAnchura() {
		Equipaje e = new Equipaje("Descripcion", 22.0, 10.4, 40.3, 30.2);
		assertTrue(30.2 == e.getAnchura());
	}
}
