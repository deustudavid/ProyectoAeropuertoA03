
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	
	/**
	 * Método que crea la conexión con la BBD
	 * @param nombreBD El nombre de la BBDD
	 * @return El objeto Conexión
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	/**
	 * Método que cierra la conexión con la BBDD
	 * @param con Objecto Connection
	 */
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
	/**
	 * Método que recibe los datos de un Usuario y comprueba que está registrado en la BBDD
	 * @param correo correo del usuario
	 * @param contras contraseña del usuario
	 * @return 0 si el usuario no está registrado
	 * 		   1 si el usuario está registrado pero la contraseña no es correcta
	 * 		   2 si el usuario está registrado y la contraseña es correcta
	 */
	public static int obtenerUsuario(Connection con, String correo, String contras) {
		String sentencia = "SELECT con FROM Usuario WHERE correo = '"+correo+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estará apuntando a una tupla
			if(rs.next()) {
				if(rs.getString("contrasenya").equals(contras)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * Método que crear la tabla Usuario si no existe
	 * @param con Conexión con la BBDD
	 */
	public static void crearTablas(Connection con) {
		String sentencia1 = "CREATE TABLE IF NOT EXISTS Usuario (correo String, contrasenya String)";

		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * Método que inserta los datos de un Usuario (si no está repetido) en la BBDD 
	 * @param con Conexión con la BBDD
	 * @param correo Correo del usuario
	 * @param contra Contraseña del usuario
	 */
	public static void insertarUsuario(Connection con, String correo, String contra) {
		
		String sentencia = "INSERT INTO Usuario VALUES('"+correo+"','"+contra+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

