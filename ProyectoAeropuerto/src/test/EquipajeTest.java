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
		assertTrue(22.0 == e.getPeso());
	}

	@Test
	public void testGetLargo() {
		assertTrue(10.4 == e.getLargo());
	}

	@Test
	public void testGetAltura() {
		assertTrue(40.3 == e.getAltura());
	}

	@Test
	public void testGetAnchura() {
		assertTrue(30.2 == e.getAnchura());
	}
	
	@Test
	public void testSetDescripcion() {
		b.setDescripcion("Descripcion");
		assertEquals("Descripcion", b.getDescripcion());
	}

	@Test
	public void testSetPeso() {
		b.setPeso(22.0);
		assertTrue(22.0 == b.getPeso());
	}

	@Test
	public void testSetLargo() {
		b.setLargo(10.4);
		assertTrue(10.4 == b.getLargo());
	}

	@Test
	public void testSetAltura() {
		b.setAltura(40.3);
		assertTrue(40.3 == b.getAltura());
	}

	@Test
	public void testSetAnchura() {
		b.setAnchura(30.2);
		assertTrue(30.2 == b.getAnchura());
	}
	
	@Test
	public void testToString() {
		assertEquals("Equipaje [descripcion=" + e.getDescripcion() + ", peso=" + e.getPeso() + ", largo=" + e.getLargo() + ", altura=" + e.getAltura()
				+ ", anchura=" + e.getAnchura() + "]", e.toString());
	}
}
