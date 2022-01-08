package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bd.BD;
import bd.DBException;
import clases.Clase;
import clases.Equipaje;
import clases.Pasajero;
import clases.Ticket;
import clases.Vuelo;


public class BDTest {

	
    // El usuario admin, de contraseña admin, está tanto en la tabla Azafato como en la Administrador
	static String usuario="admin";
	static String contrasenia="admin";
	
	static String usuarioInexistente="ch";
	static String contraseniaIncorrecta="davbaV";
	static Connection con;

	@Test
	public void testObtenerAzafato() {
		try {
			con = BD.initBD("Aeropuerto.db");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
			int rs = 99;
			int rs2=99;
			int rs3=99;
			
			try {
				rs = BD.obtenerAzafato(con,usuario,contrasenia);
				rs2 = BD.obtenerAzafato(con,usuario,contraseniaIncorrecta);
				rs3 = BD.obtenerAzafato(con,usuarioInexistente,contrasenia);
				
				
					assertTrue(rs==2);
					assertTrue(rs2==1);
					assertTrue(rs3==0);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			BD.closeBD(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObtenerAdministrador() {
		try {
			con = BD.initBD("Aeropuerto.db");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
			int rs = 99;
			int rs2=99;
			int rs3=99;
			
			try {
				rs = BD.obtenerAdministrador(con,usuario,contrasenia);
				rs2 = BD.obtenerAdministrador(con,usuario,contraseniaIncorrecta);
				rs3 = BD.obtenerAdministrador(con,usuarioInexistente,contrasenia);
				
				
					assertTrue(rs==2);
					assertTrue(rs2==1);
					assertTrue(rs3==0);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			BD.closeBD(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertarAzafato() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarAzafatos(con);
				BD.insertarAzafato(con,"A", "1");
				con=BD.initBD("Aeropuerto.db");
				contDespuesDeInsertar = BD.contarAzafatos(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);

				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	@Test
	public void testInsertarAdministrador() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarAdministradores(con);
				BD.insertarAdministrador(con,"A", "1");
				con=BD.initBD("Aeropuerto.db");
				contDespuesDeInsertar = BD.contarAdministradores(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);

				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}
	@Test
	public void testInsertarPasajero() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarPasajeros(con);
				BD.insertarPasajero(con,"98769876Q", "Pepe", "Herras", 13, 929929929, "Madrid", "fotos/empty.png");
				con=BD.initBD("Aeropuerto.db");
				contDespuesDeInsertar = BD.contarPasajeros(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	@Test
	public void testInsertarEquipaje() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarEquipajes(con);
				BD.insertarEquipaje(con,19, "98769876Q", "Equipaje x" , 60.0, 60.0, 60.0, 60.0);
				con=BD.initBD("Aeropuerto.db");
				contDespuesDeInsertar = BD.contarEquipajes(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);
				BD.closeBD(con);


			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	@Test
	public void testInsertarVuelo() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarVuelos(con);
				BD.insertarVuelo(con,"A33", "Italia", "Canada", "24-12-2021", "6:00", "15:00", 45, 45 );
				con=BD.initBD("Aeropuerto.db");
				contDespuesDeInsertar = BD.contarVuelos(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);
				BD.closeBD(con);


			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	@Test
	public void testInsertarTicket() {
		int contAntesDeIEliminar = 0;
		int contDespuesDeInsertar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarTickets(con);
				BD.insertarTicket(con,34, "00000000Q","A33", Clase.valueOf("ESTANDAR"), 30, 1,"17-12-2021");
				con=BD.initBD("Aeropuerto.db");

				contDespuesDeInsertar = BD.contarTickets(con);
				assertTrue(contAntesDeIEliminar == contDespuesDeInsertar - 1);
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	@Test
	public void testGetMaxTicketNum() {
		int maxTicketNum = 0;
		int ticketNumTrasCrearTicket = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				maxTicketNum = BD.getMaxTicketNum(con);
				BD.insertarTicket(con,0, "00000000Q","A33", Clase.valueOf("LUJO"), 30, 1,"17-12-2021");

				con=BD.initBD("Aeropuerto.db");
				ticketNumTrasCrearTicket = BD.getMaxTicketNum(con);
				
				assertTrue(maxTicketNum == ticketNumTrasCrearTicket - 1);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	@Test
	public void testGetMaxEquipajeNum() {
		int maxEquipajeNum = 0;
		int equipajeNumTrasCrearEquipaje = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				maxEquipajeNum = BD.getMaxEquipajeNum(con);
				BD.insertarEquipaje(con,0, "98769876Q", "Equipaje y" , 60.0, 60.0, 60.0, 60.0);
				con=BD.initBD("Aeropuerto.db");
				equipajeNumTrasCrearEquipaje = BD.getMaxEquipajeNum(con);
				
				assertTrue(maxEquipajeNum == equipajeNumTrasCrearEquipaje - 1);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testExistePasajero() {
		boolean existePasajeroExistente=false;
		boolean existePasajeroInexistente=false;
			try {
				con=BD.initBD("Aeropuerto.db");
				ArrayList<Pasajero> al= BD.obtenerPasajeros(con);
				String dniQueExiste=al.get(al.size()-1).getDni();
				existePasajeroExistente=BD.existePasajero(con, dniQueExiste);//este dni existe
				existePasajeroInexistente=BD.existePasajero(con, "31R");//este dni NO existe
				assertTrue(existePasajeroExistente== true);
				assertFalse(existePasajeroInexistente);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testExisteVuelo() {
		boolean existeVueloExistente=false;
		boolean existeVueloInexistente=false;
			try {
				con=BD.initBD("Aeropuerto.db");
				existeVueloExistente=BD.existeVuelo(con, "A33");//este vuelo existe
				existeVueloInexistente=BD.existeVuelo(con, "31434113r14R");//este vuelo NO existe
				assertTrue(existeVueloExistente== true);
				assertTrue(existeVueloInexistente==false);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	@Test
	public void testModificarPasajero() {
		
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarPasajero(con,"10101010A", "Ana", "Salas", 13, 929929929, "Madrid", "fotos/empty.png");

				con=BD.initBD("Aeropuerto.db");
				BD.modificarPasajero(con, "10101010A", "Nombre diferente", "Apellido diferente", 7, 112233445, "fotos/pasajero11.png");

				Pasajero pasajeroActualizado= BD.buscarPasajero(con, "10101010A");
				assertEquals("Nombre diferente", pasajeroActualizado.getNombre());
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testActualizarAsientosDeVuelo() {
		
			try {
				con=BD.initBD("Aeropuerto.db");
				Vuelo v=new Vuelo("AAA", "Italia", "Canada", "24-12-2021", "6:00", "15:00", 45, 30 );
				BD.insertarVuelo(con, v.getID(),v.getOrigen(),v.getDestino(),v.getFecha(),v.getHoraSalida(),v.getHoraLlegada(),v.getAsientosMax(),v.getAsientosRestantes());
				con=BD.initBD("Aeropuerto.db");

				BD.actualizarAsientosDeVuelo(con, v, 15);//asientos disponibles:15

				ArrayList<Vuelo> vuelosBD=BD.obtenerVuelos(con);
				int asientosDisponiblesDevueloActualizado=vuelosBD.get(vuelosBD.size()-1).getAsientosRestantes();
				assertEquals(15, asientosDisponiblesDevueloActualizado);
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	@Test
	public void testActualizarFotoDePasajero() {
		
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarPasajero(con,"10120987A", "Xx", "Xx", 13, 929929929, "Madrid", "fotos/empty.png");

				con=BD.initBD("Aeropuerto.db");
				BD.actualizarFotoDePasajero(con, "10120987A", "fotos/pasajero11.png");

				Pasajero pasajeroActualizado= BD.buscarPasajero(con, "10120987A");
				assertEquals("fotos/pasajero11.png", pasajeroActualizado.getRutaFoto());
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testEliminarVuelo() {
		int contAntesDeEliminar = 0;
		int contDespuesDeEliminar = 0;
			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeEliminar = BD.contarVuelos(con);
				BD.insertarVuelo(con,"A34", "Canada", "Canada", "24-12-2021", "6:00", "15:00", 45, 45 );

				con=BD.initBD("Aeropuerto.db");
				BD.eliminarVuelo(con, "A34");

				contDespuesDeEliminar = BD.contarVuelos(con);
				assertTrue(contAntesDeEliminar == contDespuesDeEliminar);
				BD.closeBD(con);


			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	@Test
	public void testEliminarTickets() {
		int contDespuesDeEliminar = 0;
		int contAntesDeIEliminar = 0;

			try {
				con=BD.initBD("Aeropuerto.db");
				contAntesDeIEliminar = BD.contarTickets(con);
				
				ArrayList<Ticket>al=BD.obtenerTickets(con);
				int ultimoTicketNum=al.get(al.size()-1).getTicketNum();
				BD.eliminarTickets(con, ultimoTicketNum);

				contDespuesDeEliminar = BD.contarTickets(con);
				assertEquals(contAntesDeIEliminar , contDespuesDeEliminar+1);
				BD.closeBD(con);

			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	
	@Test
	public void testObtenerFotoPasajero() {
		String rutaFoto="";
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarPasajero(con,"29421073B", "Hades", "Mitologico", 13, 929929929, "Madrid", "fotos/empty.png");

				con=BD.initBD("Aeropuerto.db");

				rutaFoto=BD.obtenerFotoPasajero(con, "29421073B");

				assertEquals("fotos/empty.png", rutaFoto);
				
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testObtenerEquipajesDePasajero() {
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarEquipaje(con, 0, "29421073B", "...", 0, 0, 0, 0);

				con=BD.initBD("Aeropuerto.db");
				ArrayList<Equipaje> equipajes= BD.obtenerEquipajesDePasajero(con,"29421073B");
				
				Equipaje e=equipajes.get(0);
				
				assertEquals("...", e.getDescripcion());
				
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}

	@Test
	public void testObtenerPasajeros() {
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarPasajero(con,"97868652K", "Pepe", "Herras", 13, 929929929, "Madrid", "fotos/empty.png");

				con=BD.initBD("Aeropuerto.db");
				ArrayList<Pasajero> pasajeros= BD.obtenerPasajeros(con);
				
				Pasajero e=pasajeros.get((pasajeros.size()-1));
				
				assertEquals("97868652K", e.getDni());
				
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testObtenerTickets() {
			try {
				con=BD.initBD("Aeropuerto.db");
				BD.insertarTicket(con,1030, "00000000Q","A33", Clase.valueOf("ESTANDAR"), 30, 1,"26-02-2021");

				con=BD.initBD("Aeropuerto.db");
				ArrayList<Ticket> tickets= BD.obtenerTickets(con);
				
				Ticket e=tickets.get((tickets.size()-1));
				
				assertEquals("26-02-2021", e.getFecha());
				BD.eliminarTickets(con, 1030);
				
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
			
		
	}
	
	@Test
	public void testObtenerVuelosSegunOrigenDestino() {
			try {
				con=BD.initBD("Aeropuerto.db");
				ArrayList<Vuelo> vuelos= BD.obtenerVuelosSegunOrigenDestino(con, "Italia", "Italia");
				Vuelo e=vuelos.get((0));
				
				assertEquals("Italia", e.getOrigen());
				
				BD.closeBD(con);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
			
		
	}
}


