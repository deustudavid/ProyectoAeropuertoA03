package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;

	@Before
	public void setUp() {
		usuario1 = new Usuario("asierbrizu@opendeusto.es", "ContraseñaFalsa1");
		usuario2 = new Usuario("miguelgarcia@outlook.com", "EstaTampocoExiste2");
		usuario3 = new Usuario("alvaroalvarez@gmail.com", "OroParecePlatanoEs");

	}

	@Test
	public void testCorreo() {
		usuario1.setUsuario("aa");
		usuario2.setUsuario("bb");
		usuario3.setUsuario("cc");
		assertEquals("aa", usuario1.getUsuario());
		assertEquals("bb", usuario2.getUsuario());
		assertEquals("cc", usuario3.getUsuario());
	}

}
