package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void testCalcularDistanciaPuntosSuperficieTierra() {
		double latitud = 0.0;
		double latitud2 = 0.0;
		double longitud = 0.0;
		double longitud2 = 0.0;

		assertEquals(10008.559105973554,
				CoordenadasAeropuerto.calcularDistanciaPuntosSuperficieTierra(latitud, latitud2, longitud, longitud2), 0.0f);
	}

}
