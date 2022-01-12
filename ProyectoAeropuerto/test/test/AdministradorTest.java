package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Administrador;

public class AdministradorTest {

	Administrador a = new Administrador("Usuario", "Contraseña", "Jefe");
	Administrador b = new Administrador();

	@Test
	public void testGetUsuario() {
		assertEquals("Usuario", a.getUsuario());
	}

	@Test
	public void testGetContraseña() {
		assertEquals("Contraseña", a.getContrasenya());
	}

	@Test
	public void testGetCargo() {
		assertEquals("Jefe", a.getCargo());
	}

	@Test
	public void testSetCargo() {
		b.setCargo("Admin");
		assertEquals("Admin", b.getCargo());
	}

	@Test
	public void testSetUsuario() {
		b.setUsuario("Usuario");
		assertEquals("Usuario", b.getUsuario());
	}

	@Test
	public void testSetContraseña() {
		b.setContrasenya("Diego");
		assertEquals("Diego", b.getContrasenya());
	}
}
