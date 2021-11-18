package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Persona;

public class PersonaTest {

	Persona p = new Persona("11111111A", "Diego", "Burzaco", 3);
	Persona b = new Persona();

	@Test
	public void testGetDni() {
		assertEquals("11111111A", p.getDni());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Diego", p.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals("Burzaco", p.getApellido());
	}

	@Test
	public void testGetEdad() {
		assertEquals(3, p.getEdad());
	}

	@Test
	public void testSetDni() {
		b.setDni("11111111A");
		assertEquals("11111111A", b.getDni());
	}

	@Test
	public void testSetNombre() {
		b.setNombre("Diego");
		assertEquals("Diego", b.getNombre());
	}

	@Test
	public void testSetApellido() {
		b.setApellido("Burzaco");
		assertEquals("Burzaco", b.getApellido());
	}

	@Test
	public void testSetEdad() {
		b.setEdad(3);
		assertEquals(3, b.getEdad());
	}

	@Test
	public void testToString() {
		assertEquals("Persona [dni=" + p.getDni() + ", nombre=" + p.getNombre() + ", apellido=" + p.getApellido()
				+ ", edad=" + p.getEdad() + "]", p.toString());
	}

}
