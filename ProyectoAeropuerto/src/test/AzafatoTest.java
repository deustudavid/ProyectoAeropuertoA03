package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Azafato;

public class AzafatoTest {

	Azafato a = new Azafato("11111111A", "Diego", "Burzaco", 3, 10, "Trabajar");
	Azafato b = new Azafato();

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
	public void testGetAniosExpe() {
		assertEquals(10, a.getAnyosExperiencia());
	}

	@Test
	public void testGetFuncion() {
		assertEquals("Trabajar", a.getFuncion());
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
		b.setEdad(3);
		assertEquals(3, b.getEdad());
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

	@Test
	public void testToString() {
		assertEquals("Azafato [anyosExperiencia=" + a.getAnyosExperiencia() + ", funcion=" + a.getFuncion()
				+ ", getDni()=" + a.getDni() + ", getNombre()=" + a.getNombre() + ", getApellido()=" + a.getApellido()
				+ ", getEdad()=" + a.getEdad() + "]", a.toString());
	}

}
