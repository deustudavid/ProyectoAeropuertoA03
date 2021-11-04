package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Administrador;

public class AdministradorTest {

	@Test
	public void testGetDni() {
		Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
		assertEquals("11111111A", a.getDni());
	}

	@Test
	public void testGetNombre() {
		Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
		assertEquals("Diego", a.getNombre());
	}

	@Test
	public void testGetApellido() {
		Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
		assertEquals("Burzaco", a.getApellido());
	}

	@Test
	public void testGetEdad() {
		Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
		assertEquals(3, a.getEdad());
	}

	@Test
	public void testGetCargo() {
		Administrador a = new Administrador("11111111A", "Diego", "Burzaco", 3, "Admin");
		assertEquals("Admin", a.getCargo());
	}

}
