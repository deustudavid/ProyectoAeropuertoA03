package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import clases.Pasajero;
import clases.Persona;

public class PasajeroTest {
	
	@Test
	public void testGetDni() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "");
		assertEquals("11111111A", pa.getDni());
	}

	@Test
	public void testGetNombre() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "");
		assertEquals("Diego", pa.getNombre());
	}

	@Test
	public void testGetApellido() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "");
		assertEquals("Burzaco", pa.getApellido());
	}

	@Test
	public void testGetEdad() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "");
		assertEquals(3, pa.getEdad());
	}
	
	@Test
	public void testGetTelefono() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "");
		assertEquals(0, pa.getTelefono());
	}
	
	@Test
	public void testGetDireccion() {
		Pasajero pa = new Pasajero("11111111A", "Diego", "Burzaco", 3, 0, "aaa");
		assertEquals("aaa", pa.getDireccion());		
	}
}
