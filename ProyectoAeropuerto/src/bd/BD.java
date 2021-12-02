package bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.logging.Level;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import clases.Vuelo;
import main.VentanaInicio;

public class BD {
	
	/**
	 * M�todo que crea la conexi�n con la BBD
	 * @param nombreBD El nombre de la BBDD
	 * @return El objeto Conexi�n
	 * @throws DBException 
	 */
	public static Connection initBD(String nombreBD) throws DBException {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
			
					
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			throw new DBException("No se pudo cargar el driver de la base de datos", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	/**
	 * M�todo que cierra la conexi�n con la BBDD
	 * @param con Objecto Connection
	 * @throws DBException 
	 */
	public static void closeBD(Connection con) throws DBException {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("No se pudo desconectar correctamente de la base de datos", e);
				
			}
		}
	}


	
	/**
	 * M�todo que recibe los datos de un Azafato y comprueba que est� registrado en la BBDD
	 * @param usuario Nombre de usuario del azafato
	 * @param contras contrase�a del azafato
	 * @return 0 si el nombre de usuario del azafato no est� registrado
	 * 		   1 si el nombre de usuario del azafato est� registrado pero la contrase�a no es correcta
	 * 		   2 si el nombre de usuario del azafato est� registrado y la contrase�a es correcta
	 * @throws DBException 
	 */
	public static int obtenerAzafato(Connection con, String usuario, String contra) throws DBException {
		String sentencia = "SELECT contrasenya FROM Azafato WHERE usuario = '"+usuario+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estar� apuntando a una tupla
			if(rs.next()) {
				if(rs.getString("contrasenya").equals(contra)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No se ha podido comprobar si existe el azafato");
			
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resul;
	}
	
	
	/**
	 * M�todo que recibe los datos de un Administrador y comprueba que est� registrado en la BBDD
	 * @param usuario Nombre de usuario del administrador
	 * @param contras contrase�a del administrador
	 * @return 0 si el administrador no est� registrado
	 * 		   1 si el administrador est� registrado pero la contrase�a no es correcta
	 * 		   2 si el administrador est� registrado y la contrase�a es correcta
	 * @throws DBException 
	 */
	public static int obtenerAdministrador(Connection con, String usuario, String contra) throws DBException {
		String sentencia = "SELECT contrasenya FROM Administrador WHERE usuario = '"+usuario+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estar� apuntando a una tupla
			if(rs.next()) {
				if(rs.getString("contrasenya").equals(contra)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DBException("No se ha podido comprobar si existe el administrador");

		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resul;
	}
	
	/**
	 * M�todo que crea la tabla Azafato y Administrador si no existen

	 */
	public static void crearTablas(Connection con) throws DBException {
		String sentencia1 = "CREATE TABLE IF NOT EXISTS Azafato (usuario String, contrasenya String)";
		String sentencia2 = "CREATE TABLE IF NOT EXISTS Administrador (usuario String, contrasenya String)";
		String sentencia3 = "CREATE TABLE IF NOT EXISTS Vuelo (ID String, origen String, destino String, fecha Date, horaSalida String, horaLlegada String)";

		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia1);
			st.executeUpdate(sentencia2);
			st.executeUpdate(sentencia3);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No se han podido crear las tablas", e);
			
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	/**
	 * M�todo que inserta los datos de un Azafato (si no est� repetido) en la BBDD 
	 * @param con Conexi�n con la BBDD
	 * @param usuario Nombre de usuario del azafato
	 * @param contra Contrase�a del azafato
	 * @throws DBException 
	 */
	public static void insertarAzafato(Connection con, String usuario, String contra) throws DBException {
	
		String sentencia = "INSERT INTO Azafato VALUES('"+usuario+"','"+contra+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha a�adido un azafato");
			st.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DBException("No se ha podido insertar azafato de vuelo");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void insertarVuelo(Connection con, Vuelo vuelo) throws DBException {
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO vuelo (id, origen, destino, fecha, horaSalida, horaLlegada) VALUES (?, ?, ?, ?, ?, ?)"); 
			Statement stmtForId = con.createStatement()) {
			
			stmt.setString(1, vuelo.getID());
			stmt.setString(2, vuelo.getOrigen());
			stmt.setString(3, vuelo.getDestino());
			
			
	        
			Date date = vuelo.getFecha();  
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        String strdate = sdf.format(date);
	        

            
			stmt.setString(4, strdate);
			stmt.setString(5, vuelo.getHoraSalida());
			stmt.setString(6, vuelo.getHoraLlegada());
			

	
			stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha a�adido un azafato");
			stmt.close();
			 
		} catch (SQLException | DateTimeParseException e) {
			throw new DBException("Error al insertar vuelo'", e);
		}
	}
	
	/**
	 * M�todo que inserta los datos de un Administrador (si no est� repetido) en la BBDD 
	 * @param con Conexi�n con la BBDD
	 * @param usuario Nombre de usuario del administrador
	 * @param contra Contrase�a del administrador
	 * @throws DBException 
	 */
	public static void insertarAdministrador(Connection con, String usuario, String contra) throws DBException {
	
		String sentencia = "INSERT INTO Administrador VALUES('"+usuario+"','"+contra+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha a�adido un administrador");
			st.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al a�adir un administrador");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar administrador");
			
			
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}	
		
}