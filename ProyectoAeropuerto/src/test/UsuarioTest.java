package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	@Test
	public void testGetContrase�a() {
		Usuario u = new Usuario("diegoburzaco", "contrase�a");
		assertEquals("contrase�a", u.getContrasenya());
	}

	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario("diegoburzaco", "contrase�a");
		assertEquals("diegoburzaco", u.getUsuario());
	}

}
