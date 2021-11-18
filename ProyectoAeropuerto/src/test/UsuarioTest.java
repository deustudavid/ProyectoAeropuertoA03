package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	Usuario u = new Usuario("diegoburzaco", "contrasena");
	Usuario b = new Usuario();

	@Test
	public void testGetContrasena() {
		assertEquals("contrasena", u.getContrasenya());
	}

	@Test
	public void testGetUsuario() {
		assertEquals("diegoburzaco", u.getUsuario());
	}

	@Test
	public void testSetContrasena() {
		b.setContrasenya("contrasena");
		assertEquals("contrasena", b.getContrasenya());
	}

	@Test
	public void testSetUsuario() {
		b.setUsuario("diegoburzaco");
		assertEquals("diegoburzaco", b.getUsuario());
	}

	@Test
	public void testToString() {
		assertEquals("Usuario [usuario=" + u.getUsuario() + ", contrasenya=" + u.getContrasenya() + "]", u.toString());
	}

}
