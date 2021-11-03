package bd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import main.VentanaInicio;

public class BD {
	
	/**
	 * Método que crea la conexión con la BBD
	 * @param nombreBD El nombre de la BBDD
	 * @return El objeto Conexión
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
	 * Método que cierra la conexión con la BBDD
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
	 * Método que recibe los datos de un Azafato y comprueba que está registrado en la BBDD
	 * @param usuario Nombre de usuario del azafato
	 * @param contras contraseña del azafato
	 * @return 0 si el nombre de usuario del azafato no está registrado
	 * 		   1 si el nombre de usuario del azafato está registrado pero la contraseña no es correcta
	 * 		   2 si el nombre de usuario del azafato está registrado y la contraseña es correcta
	 * @throws DBException 
	 */
	public static int obtenerAzafato(Connection con, String usuario, String contra) throws DBException {
		String sentencia = "SELECT contrasenya FROM Azafato WHERE usuario = '"+usuario+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estará apuntando a una tupla
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
	 * Método que recibe los datos de un Administrador y comprueba que está registrado en la BBDD
	 * @param usuario Nombre de usuario del administrador
	 * @param contras contraseña del administrador
	 * @return 0 si el administrador no está registrado
	 * 		   1 si el administrador está registrado pero la contraseña no es correcta
	 * 		   2 si el administrador está registrado y la contraseña es correcta
	 * @throws DBException 
	 */
	public static int obtenerAdministrador(Connection con, String usuario, String contra) throws DBException {
		String sentencia = "SELECT contrasenya FROM Administrador WHERE usuario = '"+usuario+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estará apuntando a una tupla
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
	 * Método que crea la tabla Azafato y Administrador si no existen
	 * @param con Conexión con la BBDD
	 * @throws DBException 
	 */
	public static void crearTablas(Connection con) throws DBException {
		String sentencia1 = "CREATE TABLE IF NOT EXISTS Azafato (usuario String, contrasenya String)";
		String sentencia2 = "CREATE TABLE IF NOT EXISTS Administrador (usuario String, contrasenya String)";

		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia1);
			st.executeUpdate(sentencia2);
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
	 * Método que inserta los datos de un Azafato (si no está repetido) en la BBDD 
	 * @param con Conexión con la BBDD
	 * @param usuario Nombre de usuario del azafato
	 * @param contra Contraseña del azafato
	 * @throws DBException 
	 */
	public static void insertarAzafato(Connection con, String usuario, String contra) throws DBException {
	
		String sentencia = "INSERT INTO Azafato VALUES('"+usuario+"','"+contra+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un azafato");
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
	
	/**
	 * Método que inserta los datos de un Administrador (si no está repetido) en la BBDD 
	 * @param con Conexión con la BBDD
	 * @param usuario Nombre de usuario del administrador
	 * @param contra Contraseña del administrador
	 * @throws DBException 
	 */
	public static void insertarAdministrador(Connection con, String usuario, String contra) throws DBException {
	
		String sentencia = "INSERT INTO Administrador VALUES('"+usuario+"','"+contra+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un administrador");
			st.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un administrador");
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