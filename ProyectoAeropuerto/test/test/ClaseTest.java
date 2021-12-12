package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Clase;

public class ClaseTest {

	@Test
	public void test() {
		assertEquals(Clase.ESTANDAR, Clase.ESTANDAR);
		assertEquals(Clase.LUJO, Clase.LUJO);
		assertEquals(Clase.BUSINESS, Clase.BUSINESS);
		assertEquals(Clase.BARATO, Clase.BARATO);
	}
}
