package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Azafato;

public class AzafatoTest {
	@Test
	public void testGetDni() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals("11111111A", a.getDni());
	}

	@Test
	public void testGetNombre() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals("Diego", a.getNombre());
	}
	
	@Test
	public void testGetApellido() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals("Burzaco", a.getApellido());
	}
	
	@Test
	public void testGetEdad() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals(3, a.getEdad());
	}
	
	@Test
	public void testGetAniosExpe() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals(10, a.getAnyosExperiencia());
	}
	
	@Test
	public void testGetfuncion() {
		Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
		assertEquals("Trabajar", a.getFuncion());
	}
	
	
}
