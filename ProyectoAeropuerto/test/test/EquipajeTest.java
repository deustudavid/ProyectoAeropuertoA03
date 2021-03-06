package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import clases.Equipaje;

public class EquipajeTest {

	Equipaje e = new Equipaje(2, "00000000Q","Descripcion", 22.0, 10.4, 40.3, 30.2);
	Equipaje b = new Equipaje();
	
	

	@Test
	public void testGetEquipajeNum() {
		assertEquals(2, e.getEquipajeNum());
	}
	
	@Test
	public void testGetDniPasajero() {
		assertEquals("00000000Q", e.getDniPasajero());
	}
	
	@Test
	public void testGetDescripcion() {
		assertEquals("Descripcion", e.getDescripcion());
	}

	@Test
	public void testGetPeso() {
		assertEquals(22.0 ,e.getPeso(),0.00f);
	}

	@Test
	public void testGetLargo() {
		assertEquals(10.4 , e.getLargo(),0.00f);
	}

	@Test
	public void testGetAltura() {
		assertEquals(40.3,e.getAltura(),0.00f);
	}

	@Test
	public void testGetAnchura() {
		assertEquals(30.2 ,e.getAnchura(),0.00f);
	}
	
	@Test
	public void testGetDescripcion2() {
		assertEquals("Descripcion", e.getDescripcion());
	}

	@Test
	public void testGetPeso2() {
		assertEquals(22.0 ,e.getPeso(),0.00f);
	}

	@Test
	public void testGetLargo2() {
		assertEquals(10.4 , e.getLargo(),0.00f);
	}

	@Test
	public void testGetAltura2() {
		assertEquals(40.3,e.getAltura(),0.00f);
	}

	@Test
	public void testGetAnchura2() {
		assertEquals(30.2 ,e.getAnchura(),0.00f);
	}

	@Test
	public void testSetDniPasajero() {
		b.setDniPasajero("00000000Q");
		assertEquals("00000000Q", b.getDniPasajero());
	}
	
	@Test
	public void testSetEquipajeNum() {
		b.setEquipajeNum(2);
		assertEquals(2, b.getEquipajeNum());
	}
	
	@Test
	public void testSetDescripcion() {
		b.setDescripcion("Descripcion");
		assertEquals("Descripcion", b.getDescripcion());
	}

	@Test
	public void testSetPeso() {
		b.setPeso(22.0);
		assertEquals(22.0 , b.getPeso(),0.00f);
	}

	@Test
	public void testSetLargo() {
		b.setLargo(10.4);
		assertEquals(10.4, b.getLargo(),0.00f);
	}

	@Test
	public void testSetAltura() {
		b.setAltura(40.3);
		assertEquals(40.3, b.getAltura(),0.00f);
	}

	@Test
	public void testSetAnchura() {
		b.setAnchura(30.2);
		assertEquals(30.2 , b.getAnchura(),0.00f);
	}

	@Test
	public void testToString() {
		assertEquals("Equipaje [equipajeNum=" + e.getEquipajeNum() + ", dniPasajero=" + e.getDniPasajero() + ", descripcion=" + e.getDescripcion()
				+ ", peso=" + e.getPeso() + ", largo=" + e.getLargo() + ", altura=" + e.getAltura() + ", anchura=" + e.getAnchura() + "]", e.toString());
	}
}
