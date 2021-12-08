package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.CoordenadasAeropuerto;

public class CoordenadasAeropuertoTest {

	CoordenadasAeropuerto c = new CoordenadasAeropuerto(10.0, 20.0);
	CoordenadasAeropuerto co = new CoordenadasAeropuerto();

	@Test
	public void testGetLatitud() {
		assertEquals(10.0, c.getLatitud(), 0.0f);
	}

	@Test
	public void testGetLongitud() {
		assertEquals(20.0, c.getLongitud(), 0.0f);
	}

	@Test
	public void testSetLatitud() {
		co.setLatitud(10.0);
		assertEquals(10.0, co.getLatitud(), 0.0f);
	}

	@Test
	public void testSetLongitud() {
		co.setLongitud(20.0);
		assertEquals(20.0, co.getLongitud(), 0.0f);
	}

}
