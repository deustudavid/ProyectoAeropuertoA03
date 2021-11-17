package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Avion;

public class AvionTest {

	Avion a = new Avion("Airbus", "1234");
	Avion b = new Avion();

	@Test
	public void testGetModelo() {
		assertEquals("Airbus", a.getModelo());
	}

	@Test
	public void testGetMatricula() {
		assertEquals("1234", a.getMatricula());
	}

	@Test
	public void testSetModelo() {
		b.setModelo("Boing 747");
		assertEquals("Boing 747", b.getModelo());
	}

	@Test
	public void teststMatricula() {
		b.setMatricula("3134");
		assertEquals("3134", b.getMatricula());
	}

	@Test
	public void testToString() {
		assertEquals("Avion [modelo=" + a.getModelo() + ", matricula=" + a.getMatricula() + "]", a.toString());
	}

}
