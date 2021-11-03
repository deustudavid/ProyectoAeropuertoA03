package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	@Test
	public void testGetContraseña() {
		Usuario u = new Usuario("diegoburzaco", "contraseña");
		assertEquals("contraseña", u.getContrasenya());
	}

	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario("diegoburzaco", "contraseña");
		assertEquals("diegoburzaco", u.getUsuario());
	}

}
