package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Pasajero;
import clases.Persona;

public class PasajeroTest {
	
	Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "aaa");
	Pasajero b = new Pasajero();
	
	@Test
	public void testGetDni() {
		assertEquals("11111111A", pa.getDni());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Diego", pa.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals("Burzaco", pa.getApellido());
	}

	@Test
	public void testGetEdad() {
		assertEquals(3, pa.getEdad());
	}
	
	@Test
	public void testGetTelefono() {
		assertEquals(0, pa.getTelefono());
	}
	
	@Test
	public void testGetDireccion() {
		assertEquals("aaa", pa.getDireccion());		
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
	public void testSetTelefono() {
		b.setTelefono(0);
		assertEquals(0, b.getTelefono());
	}
	
	@Test
	public void testSetDireccion() {
		b.setDireccion("aaa");
		assertEquals("aaa", b.getDireccion());		
	}
	
	@Test
	public void testToString() {
		assertEquals("Pasajero [telefono=" + pa.getTelefono() + ", direccion=" + pa.getDireccion() + ", getDni()=" + pa.getDni()
		+ ", getNombre()=" + pa.getNombre() + ", getApellido()=" + pa.getApellido() + ", getEdad()=" + pa.getEdad()
		+ "]", pa.toString());		
	}
}
