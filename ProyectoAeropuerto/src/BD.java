
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	
	/**
	 * M�todo que crea la conexi�n con la BBD
	 * @param nombreBD El nombre de la BBDD
	 * @return El objeto Conexi�n
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
	 * M�todo que cierra la conexi�n con la BBDD
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
	 * M�todo que recibe los datos de un Usuario y comprueba que est� registrado en la BBDD
	 * @param correo correo del usuario
	 * @param contras contrase�a del usuario
	 * @return 0 si el usuario no est� registrado
	 * 		   1 si el usuario est� registrado pero la contrase�a no es correcta
	 * 		   2 si el usuario est� registrado y la contrase�a es correcta
	 */
	public static int obtenerUsuario(Connection con, String correo, String contras) {
		String sentencia = "SELECT con FROM Usuario WHERE correo = '"+correo+"'";
		Statement st = null;
		int resul = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estar� apuntando a una tupla
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
	 * M�todo que crear la tabla Usuario si no existe
	 * @param con Conexi�n con la BBDD
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
	 * M�todo que inserta los datos de un Usuario (si no est� repetido) en la BBDD 
	 * @param con Conexi�n con la BBDD
	 * @param correo Correo del usuario
	 * @param contra Contrase�a del usuario
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

