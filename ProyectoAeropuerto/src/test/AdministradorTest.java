package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Administrador;

public class AdministradorTest {

	Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
	Administrador b = new Administrador();

	@Test
	public void testGetDni() {
		assertEquals("11111111A", a.getDni());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Diego", a.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals("Burzaco", a.getApellido());
	}

	@Test
	public void testGetEdad() {
		assertEquals(3, a.getEdad());
	}

	@Test
	public void testGetCargo() {
		assertEquals("Admin", a.getCargo());
	}

	@Test
	public void testSetCargo() {
		b.setCargo("Admin");
		assertEquals("Admin", b.getCargo());
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
		b.setEdad(10);
		assertEquals(10, b.getEdad());
	}

	@Test
	public void testSetToString() {
		assertEquals("Administrador [cargo=" + a.getCargo() + ", getDni()=" + a.getDni() + ", getNombre()="
				+ a.getNombre() + ", getEdad()=" + a.getEdad() + "]", a.toString());
	}

}
