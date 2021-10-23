
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


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
		usuario1.setCorreo("aa");
		usuario2.setCorreo("bb");
		usuario3.setCorreo("cc");
		assertEquals("aa", usuario1.getCorreo());
		assertEquals("bb", usuario2.getCorreo());
		assertEquals("cc", usuario3.getCorreo());
	}

}
