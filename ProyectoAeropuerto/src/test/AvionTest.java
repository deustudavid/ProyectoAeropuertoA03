package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Avion;

public class AvionTest {

	@Test
	public void testGetModelo() {
		Avion a = new Avion("Airbus","1234");
		assertEquals("Airbus", a.getModelo());
	}
	
	@Test
	public void testGetMatricula() {
		Avion a = new Avion("Airbus","1234");
		assertEquals("1234", a.getMatricula());
	}
}
