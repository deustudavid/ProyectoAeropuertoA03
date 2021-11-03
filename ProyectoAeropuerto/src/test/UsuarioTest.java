package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	@Test
	public void testGetContrasena() {
		Usuario u = new Usuario("diegoburzaco", "contrasena");
		assertEquals("contrasena", u.getContrasenya());
	}

	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario("diegoburzaco", "contrasena");
		assertEquals("diegoburzaco", u.getUsuario());
	}

}
