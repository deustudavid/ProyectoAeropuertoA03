package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Azafato;

public class AzafatoTest {

	Azafato a = new Azafato("11111111A", "Diego", 10, "Trabajar");
	Azafato b = new Azafato();

	@Test
	public void testGetUsuario() {
		assertEquals("11111111A", a.getUsuario());
	}

	@Test
	public void testGetContraseña() {
		assertEquals("Diego", a.getContrasenya());
	}

	@Test
	public void testGetFuncion() {
		assertEquals("Trabajar", a.getFuncion());
	}

	@Test
	public void testGetAniosExpe() {
		assertEquals(10, a.getAnyosExperiencia());
	}

	@Test
	public void testSetUsuario() {
		b.setUsuario("11111111A");
		assertEquals("11111111A", b.getUsuario());
	}

	@Test
	public void testSetContraseña() {
		b.setContrasenya("Diego");
		assertEquals("Diego", b.getContrasenya());
	}

	@Test
	public void testSetAniosExpe() {
		b.setAnyosExperiencia(10);
		assertEquals(10, b.getAnyosExperiencia());
	}

	@Test
	public void testSetFuncion() {
		b.setFuncion("Trabajar");
		assertEquals("Trabajar", b.getFuncion());
	}

}
