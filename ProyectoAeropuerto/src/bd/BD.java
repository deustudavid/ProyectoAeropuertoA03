package bd;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

import clases.Clase;
import clases.Pasajero;
import clases.Ticket;
import clases.Vuelo;
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
	 * Método que cierra la conexi�n con la BBDD
	 * @param con Objeto Connection
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
	 * Método que recibe los datos de un Azafato y comprueba que est� registrado en la BBDD
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
	 * Método que recibe los datos de un Administrador y comprueba que est� registrado en la BBDD
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
	 * Método que crea la tabla Azafato y Administrador si no existen

	 */
	public static void crearTablas(Connection con) throws DBException {
		String sentencia1 = "CREATE TABLE IF NOT EXISTS Azafato (usuario String, contrasenya String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia1 );
		String sentencia2 = "CREATE TABLE IF NOT EXISTS Administrador (usuario String, contrasenya String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia2 );
		String sentencia3 = "CREATE TABLE IF NOT EXISTS Vuelo (ID String, origen String, destino String, fecha String, horaSalida String, horaLlegada String, asientosMax int, asientosDisponibles int)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia3 );
		String sentencia4 = "CREATE TABLE IF NOT EXISTS Pasajero(dni String , nombre String ,apellido String, edad int, telefono int, direccion String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia4 );
		String sentencia5 = "CREATE TABLE IF NOT EXISTS Ticket(ticketNum INTEGER PRIMARY KEY AUTOINCREMENT, dniPasajero String ,idVuelo String , clase String,  precio double, numAsientos int, fecha String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia5 );

		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia1);
			st.executeUpdate(sentencia2);
			st.executeUpdate(sentencia3);
			st.executeUpdate(sentencia4);
			st.executeUpdate(sentencia5);
			
		
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
	
	
	public static void insertarVuelo(Connection con, Vuelo vuelo) throws DBException {
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO vuelo (id, origen, destino, fecha, horaSalida, horaLlegada) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
			Statement stmtForId = con.createStatement()) {
			
			stmt.setString(1, vuelo.getID());
			stmt.setString(2, vuelo.getOrigen());
			stmt.setString(3, vuelo.getDestino());
			stmt.setString(4, vuelo.getFecha());
			stmt.setString(5, vuelo.getHoraSalida());
			stmt.setString(6, vuelo.getHoraLlegada());
			stmt.setInt(7, vuelo.getAsientosMax());

	
			stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un vuelo");
			stmt.close();
			 
		} catch (SQLException | DateTimeParseException e) {
			throw new DBException("Error al insertar vuelo'", e);
		}
	}
	
	/**
	 * M�todo que inserta los datos de un Administrador (si no est� repetido) en la BBDD 
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
	
	public static void insertarPasajero(Connection con,String dni, String nombre, String apellido, int edad, int telefono, String direccion) throws DBException {
		
		String sentencia = "INSERT INTO Pasajero VALUES('" + dni + "','" + nombre + "','" + apellido + "'," + edad + "," + telefono + ",'" + direccion + "')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un pasajero");
			st.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un pasajero");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el pasajero en la BBDDr");
			
			
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
	
	public static void insertarVuelo(Connection con,String id, String origen, String destino, String fecha, String horaSalida, String horaLlegada, int asientosMax) throws DBException {
		
		String sentencia = "INSERT INTO Vuelo VALUES('" + id + "','" + origen + "','" + destino + "','" + fecha + "','" + horaSalida + "','" + horaLlegada + "'," + asientosMax + ")";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un vuelo");
			st.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un vuelo");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el vuelo en la BBDDr");
			
			
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
	
	
	public static void insertarTicket(Connection con,int ticketNum , String dniPasajero  ,String idVuelo  ,Clase clase , double precio , int numAsientos ,String fecha ) throws DBException {
		
		
		
		switch (clase) {
		case BARATO :
			
			precio=20.0;
			
			break;
		case ESTANDAR:
			precio=30.0;
			break;
		case BUSINESS:
			
			precio=40.0;
			break;
		case LUJO:
			precio=50.0;
			break;

		default :
			precio=0.0;
			break;
		}
		
		String sentencia = "INSERT INTO Ticket VALUES(" + ticketNum + ",'" + dniPasajero + "','" + idVuelo + "','" + clase + "'," + precio + "," + numAsientos + ",'" + fecha + "')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia);
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un vuelo");
			st.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un vuelo");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el vuelo en la BBDDr");
			
			
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
	
	
	
	 //Comprueba si existe un pasajero de un determinado dni
	public static boolean existePasajero(Connection con, String dni) throws SQLException {
		
		String sent = "select * from Pasajero where dni='"+dni+"'";
		Statement st = null;
		st = con.createStatement();

		ResultSet rs = st.executeQuery(sent);
		boolean existe = false;
		if(rs.next())
			existe = true;
		rs.close();
		return existe;
	}
	
	 //Comprueba si existe un vuelo de un determinado id
	public static boolean existeVuelo(Connection con, String ID) throws SQLException {
		
		String sent = "select * from Vuelo where ID='"+ID+"'";
		Statement st = null;
		st = con.createStatement();

		ResultSet rs = st.executeQuery(sent);
		boolean existe = false;
		if(rs.next())
			existe = true;
		rs.close();
		return existe;
	}
	
	public static void cargarPasajerosdeFichero(Connection con) throws DBException, SQLException {
			initBD("Aeropuerto.db");
			Statement st=null;
			st=con.createStatement();
			
			
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader("pasajerosIberia.txt"));
				String linea = br.readLine();
				while(linea!=null) {
					//Tratamiento de la línea
					String [] datos = linea.split("\t");
					
					String dni = datos[0];
					String nom = datos[1];
					String apellido = datos[2];
					int edad = Integer.parseInt(datos[3]);
					int tfno = Integer.parseInt(datos[4]);
					String dir = datos[5];
					if (!existePasajero(con, dni)) {
						String sentencia = "insert into Pasajero (dni, nombre, apellido, edad, telefono, direccion) values ('" + dni + "','" + nom + "','" + apellido + "'," + edad + "," + tfno + ",'" + dir + "');";
						VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia );
						st.executeUpdate( sentencia );
						linea = br.readLine();
					} else {
						linea = br.readLine();
					}
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
	
	}	
	public static void InsertarPasajeroEnFichero(Connection con,String dni, String nombre, String apellido, int edad, int telefono, String direccion) {
		
		
		
		PrintWriter pw = null;
		try {
			FileWriter fw = null;
			try {
				fw = new FileWriter("pasajerosIberia.txt", true);//true= escribe después del contenido, no borra todo lo que había antes
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			
			String dniParaFichero=dni;
			String nombreParaFichero=nombre;
			String apellidoParaFichero=apellido;
			String edadParaFichero= String.valueOf(edad) ;
			String TelefonoParaFichero= String.valueOf(telefono) ;
			String direccionParaFichero= direccion;
			String direccionParaFicheroSinTabuladores=direccionParaFichero.replace("\t", " ");
			
			pw.println(dniParaFichero + "\t" + nombreParaFichero + "\t" + apellidoParaFichero + "\t" + edadParaFichero + "\t" + TelefonoParaFichero + "\t" + direccionParaFicheroSinTabuladores);
		} finally {
			pw.flush();
			pw.close();
			
			
		}
	}
	public static void modificarPasajero(Connection con,String dni, String nombre, String apellido, int edad, int telefono, String direccion) throws SQLException  {
		Statement statement = con.createStatement();
		String sent = "update Pasajero set  nombre='"+nombre+"',apellido='"+apellido+"',edad="+edad+",telefono="+telefono+",direccion='"+direccion+"' where dni='"+dni+"'";
		statement.executeUpdate(sent);
		VentanaInicio.logger.log(Level.INFO, "Se ha modificado el pasajero de dni: " + dni);
	}
	
	public static Pasajero buscarPasajero(Connection con,String dni) throws SQLException {
		String sentencia = "SELECT * FROM Pasajero WHERE dni = '"+dni+"'";
		Statement st = null;
		Pasajero p=null;
		
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sentencia);
			//Si la sentencia nos ha devuelto al menos un valor, rs estar� apuntando a una tupla
			if(rs.next()) {
					String dniPasajero= rs.getString("dni");
					String nombrePasajero=rs.getString("nombre");
					String apellidoPasajero=rs.getString("apellido");
					int edadPasajero=rs.getInt("edad");
					int tfnoPasajero=rs.getInt("telefono");
					String direccionPasajero=rs.getString("direccion");
					
					p= new Pasajero(dniPasajero,nombrePasajero,apellidoPasajero,edadPasajero, tfnoPasajero, direccionPasajero);
					VentanaInicio.logger.log(Level.INFO, "Se ha encontrado el pasajero de dni: " + dni);
			
			
		
		}
			rs.close();
			return p;
	}
	public static ArrayList<Vuelo> obtenerVuelos(Connection con) {
        try (Statement statement = con.createStatement()) {
            ArrayList<Vuelo> ret = new ArrayList<>();
            String sent = "select * from Vuelo;";
            VentanaInicio.logger.log( Level.INFO, "Statement: " + sent );
            ResultSet rs = statement.executeQuery( sent );
            while( rs.next() ) { // Leer el resultset
                String id = rs.getString("ID");
                String origen= rs.getString("origen");
                String destino= rs.getString("destino");
                String fecha = rs.getString("fecha");
                String horaSalida=rs.getString("horaSalida");
                String horaLlegada=rs.getString("horaLlegada");
                int asientosMax=rs.getInt("asientosMax");
                int asientosDisponibles=rs.getInt("asientosDisponibles");
                ret.add( new Vuelo ( id, origen, destino, fecha,horaSalida, horaLlegada,asientosMax, asientosDisponibles) );
            }
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }


	public static ArrayList<Ticket> obtenerTickets(Connection con) {
        try (Statement statement = con.createStatement()) {
            ArrayList<Ticket> ret = new ArrayList<>();
            String sent = "select * from Ticket;";
            VentanaInicio.logger.log( Level.INFO, "Statement: " + sent );
            ResultSet rs = statement.executeQuery( sent );
            while( rs.next() ) { // Leer el resultset
            	int ticketNum = rs.getInt("ticketNum");
            	 String idVuelo= rs.getString("idVuelo");
                String dniPasajero= rs.getString("dniPasajero");
                String claseStr = rs.getString("clase");
                double precio=rs.getDouble("precio");
                int numAsientos=rs.getInt("numAsientos");
                String fecha=rs.getString("fecha");

                ret.add( new Ticket ( ticketNum, idVuelo, dniPasajero, claseStr, precio, numAsientos, fecha) );
            }
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }
	//"SELECT * FROM Pasajero WHERE dni = '"+dni+"'";
	public static ArrayList<Vuelo> obtenerVuelosSegunOrigenDestino(Connection con, String origen, String destino) {
        try (Statement statement = con.createStatement()) {
            ArrayList<Vuelo> ret = new ArrayList<>();
            String sent = "SELECT * FROM Vuelo WHERE origen = '"+origen.trim()+"' AND destino = '"+destino.trim()+"'";
            VentanaInicio.logger.log( Level.INFO, "Statement: " + sent );
            ResultSet rs = statement.executeQuery( sent );
            while( rs.next() ) { // Leer el resultset
                String id = rs.getString("ID");
                String origenAbuscar= rs.getString("origen");
                String destinoAbuscar= rs.getString("destino");
                String fecha = rs.getString("fecha");
                String horaSalida=rs.getString("horaSalida");
                String horaLlegada=rs.getString("horaLlegada");
                int asientosMax=rs.getInt("asientosMax");
                int asientosDisponibles=rs.getInt("asientosDisponibles");
                ret.add( new Vuelo ( id, origenAbuscar, destinoAbuscar, fecha,horaSalida, horaLlegada,asientosMax,asientosDisponibles) );
                VentanaInicio.logger.log(Level.INFO, "Se ha encontrado el vuelo de origen: " + origen + " y destino: "+ destino);
            }
           
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }
	
	public static void actualizarAsientosDeVuelo(Connection con,Vuelo v, int asientosAreservar) throws SQLException  {
		Statement statement = con.createStatement();
		int resul=v.getAsientosRestantes()- asientosAreservar;
		String sent = "update Vuelo set  asientosDisponibles="+resul+" where ID='"+v.getID()+"'";
		statement.executeUpdate(sent);
		VentanaInicio.logger.log(Level.INFO, "Se ha actualizado el numero de asientos restantes en el vuelo: " + v.getID());
	}
	
}