package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Persona;

public class PersonaTest {

	@Test
	public void testGetDni() {
		Persona p = new Persona("11111111A", "Diego", "Burzaco", 3);
		assertEquals("11111111A", p.getDni());
	}

	@Test
	public void testGetNombre() {
		Persona p = new Persona("11111111A", "Diego", "Burzaco", 3);
		assertEquals("Diego", p.getNombre());
	}

	@Test
	public void testGetApellido() {
		Persona p = new Persona("11111111A", "Diego", "Burzaco", 3);
		assertEquals("Burzaco", p.getApellido());
	}

	@Test
	public void testGetEdad() {
		Persona p = new Persona("11111111A", "Diego", "Burzaco", 3);
		assertEquals(3, p.getEdad());
	}

}
