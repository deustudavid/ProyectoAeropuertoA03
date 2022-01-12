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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;

import clases.Azafato;
import clases.Clase;
import clases.Equipaje;
import clases.Pasajero;
import clases.Ticket;
import clases.Vuelo;
import main.VentanaInicio;

public class BD {
	
	private static Connection con;
	
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
		String sentencia1 = "CREATE TABLE IF NOT EXISTS Azafato (usuario String, contrasenya String, anyosExperiencia int, funcion String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia1 );
		String sentencia2 = "CREATE TABLE IF NOT EXISTS Administrador (usuario String, contrasenya String, cargo String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia2 );
		String sentencia3 = "CREATE TABLE IF NOT EXISTS Vuelo (ID String, origen String, destino String, fecha String, horaSalida String, horaLlegada String, asientosMax int, asientosDisponibles int)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia3 );
		String sentencia4 = "CREATE TABLE IF NOT EXISTS Pasajero(dni String , nombre String ,apellido String, edad int, telefono int, direccion String, rutaFoto String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia4 );
		String sentencia5 = "CREATE TABLE IF NOT EXISTS Ticket(ticketNum INTEGER PRIMARY KEY AUTOINCREMENT, dniPasajero String ,idVuelo String , clase String,  precio double, numAsientos int, fecha String)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia5 );
		String sentencia6 = "CREATE TABLE IF NOT EXISTS Equipaje(equipajeNum INTEGER PRIMARY KEY AUTOINCREMENT, dniPasajero String, descripcion String ,peso double, largo double, altura double, anchura double)";
		VentanaInicio.logger.log( Level.INFO, "Statement: " + sentencia5 );

		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sentencia1);
			st.executeUpdate(sentencia2);
			st.executeUpdate(sentencia3);
			st.executeUpdate(sentencia4);
			st.executeUpdate(sentencia5);
			st.executeUpdate(sentencia6);
			
		
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
	public static void insertarAzafato(Connection con, String usuario, String contra, int anyosExperiencia , String funcion ) throws DBException {
	
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Azafato (usuario,contrasenya,anyosExperiencia,funcion) VALUES (?,?,?,?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setString(1, usuario);
				stmt.setString(2, contra);
				stmt.setInt(3, anyosExperiencia);
				stmt.setString(4, funcion);
				

		
				stmt.executeUpdate();
				VentanaInicio.logger.log(Level.INFO, "Se ha añadido un azafato a la BBDD");
				stmt.close();
				 
			} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DBException("No se ha podido insertar azafato de vuelo");
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	

	
	/**
	 * M�todo que inserta los datos de un Administrador (si no esta  repetido) en la BBDD 
	 * @param con Conexión con la BBDD
	 * @param usuario Nombre de usuario del administrador
	 * @param contra Contraseña del administrador
	 * @throws DBException 
	 */
	public static void insertarAdministrador(Connection con, String usuario, String contra, String cargo) throws DBException {
	
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Administrador (usuario, contrasenya,cargo) VALUES (?,?, ?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setString(1, usuario);
				stmt.setString(2, contra);
				stmt.setString(3, cargo);
				
			 stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un administrador");
			stmt.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un administrador");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el administrador en la BBDD");
			
			
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}
	
	public static void insertarPasajero(Connection con,String dni, String nombre, String apellido, int edad, int telefono, String direccion, String rutaFoto) throws DBException {
		
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Pasajero (dni, nombre ,apellido , edad , telefono , direccion, rutaFoto ) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setString(1, dni);
				stmt.setString(2, nombre);
				stmt.setString(3, apellido);
				stmt.setInt(4, edad);
				stmt.setInt(5, telefono);
				stmt.setString(6, direccion);
				stmt.setString(7, rutaFoto);
				
			 stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un pasajero");
			stmt.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un pasajero");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el pasajero en la BBDD");
			
			
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}
	
	public static void insertarEquipaje(Connection con , int equipajeNum, String dniPasajero , String descripcion ,double peso , double largo ,double altura , double anchura ) throws DBException {
		int numeroEquipajeAInsertar= getMaxEquipajeNum(con);
		
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Equipaje (equipajeNum,dniPasajero,descripcion,peso,largo,altura,anchura ) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setInt(1, numeroEquipajeAInsertar);
				stmt.setString(2, dniPasajero);
				stmt.setString(3, descripcion);
				stmt.setDouble(4, peso);
				stmt.setDouble(5, largo);
				stmt.setDouble(6, altura);
				stmt.setDouble(7, anchura);
				
				
				stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha insertado un nuevo equipaje");
			stmt.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al registrar equipaje");
			e.printStackTrace();
			throw new DBException("No se ha podido registrar el equipaje en la BBDD");
			
			
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}
	
	public static void insertarVuelo(Connection con,String id, String origen, String destino, String fecha, String horaSalida, String horaLlegada, int asientosMax, int asientosDisponibles) throws DBException {
		
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO vuelo (id, origen, destino, fecha, horaSalida, horaLlegada, asientosMax, asientosDisponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setString(1, id);
				stmt.setString(2, origen);
				stmt.setString(3, destino);
				stmt.setString(4, fecha);
				stmt.setString(5, horaSalida);
				stmt.setString(6, horaLlegada);
				stmt.setInt(7, asientosMax);
				stmt.setInt(8, asientosMax);
			 stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha añadido un vuelo");
			stmt.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al añadir un vuelo");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el vuelo en la BBDD");
			
			
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}
	
	

	public static void insertarTicket(Connection con , int ticketNum, String dniPasajero  ,String idVuelo  ,Clase clase , double precio , int numAsientos ,String fecha ) throws DBException {
		int numeroTicketAInsertar= getMaxTicketNum(con);
		
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Ticket (ticketNum , dniPasajero  ,idVuelo  , clase ,  precio , numAsientos , fecha ) VALUES (?, ?, ?, ?, ?, ?, ?)"); 
				Statement stmtForId = con.createStatement()) {
				
				stmt.setInt(1, numeroTicketAInsertar);
				stmt.setString(2, dniPasajero);
				stmt.setString(3, idVuelo);
				String claseStr=clase.toString();
				stmt.setString(4, claseStr);
				stmt.setDouble(5, precio);
				stmt.setInt(6, numAsientos);
				stmt.setString(7, fecha);
				
			 stmt.executeUpdate();
			VentanaInicio.logger.log(Level.INFO, "Se ha reservado un ticket");
			stmt.close();
		} catch (SQLException e) {
			VentanaInicio.logger.log(Level.SEVERE, "ERROR al reservar un ticket");
			e.printStackTrace();
			throw new DBException("No se ha podido insertar el ticket en la BBDD");
			
			
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
		}
	}
	
	public static int getMaxTicketNum(Connection con) {
		String sent = "select MAX(ticketNum) from Ticket";
		Statement st = null;
		int ultimoTicketNum=0;
		try {
			st = con.createStatement();
			ResultSet rs=st.executeQuery(sent);
			
			if (rs.next()) {
				ultimoTicketNum=rs.getInt(1);
				ultimoTicketNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ultimoTicketNum;

		
	}
	
	public static int getMaxEquipajeNum(Connection con) {
		String sent = "select MAX(equipajeNum) from Equipaje";
		Statement st = null;
		int ultimoEquipajeNum=0;
		try {
			st = con.createStatement();
			ResultSet rs=st.executeQuery(sent);
			
			if (rs.next()) {
				ultimoEquipajeNum=rs.getInt(1);
				ultimoEquipajeNum++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ultimoEquipajeNum;

		
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
					boolean rangoCorrectoEdad=false;
					if (edad>=1 && edad<=130) {
						rangoCorrectoEdad=true;
					}
					int tfno = Integer.parseInt(datos[4]);
					String dir = datos[5];
					String rutaFoto = datos[6];
					if (!existePasajero(con, dni) && rangoCorrectoEdad) {
						String sentencia = "insert into Pasajero (dni, nombre, apellido, edad, telefono, direccion, rutaFoto) values ('" + dni + "','" + nom + "','" + apellido + "'," + edad + "," + tfno + ",'" + dir + "','" + rutaFoto + "');";
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
	public static void InsertarPasajeroEnFichero(Connection con,String dni, String nombre, String apellido, int edad, int telefono, String direccion, String rutaFoto) {
		
		
		
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
			String rutafotoParaFichero= rutaFoto;
			
			pw.println(dniParaFichero + "\t" + nombreParaFichero + "\t" + apellidoParaFichero + "\t" + edadParaFichero + "\t" + TelefonoParaFichero + "\t" + direccionParaFicheroSinTabuladores+ "\t" + rutafotoParaFichero);
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
			//Si la sentencia nos ha devuelto al menos un valor, rs apunta a una tupla
			if(rs.next()) {
					String dniPasajero= rs.getString("dni");
					String nombrePasajero=rs.getString("nombre");
					String apellidoPasajero=rs.getString("apellido");
					int edadPasajero=rs.getInt("edad");
					int tfnoPasajero=rs.getInt("telefono");
					String direccionPasajero=rs.getString("direccion");
					String rutafotoPasajero=rs.getString("rutaFoto");
					
					p= new Pasajero(dniPasajero,nombrePasajero,apellidoPasajero,edadPasajero, tfnoPasajero, direccionPasajero,rutafotoPasajero);
					VentanaInicio.logger.log(Level.INFO, "Se ha encontrado el pasajero de dni: " + dni);
			
			
		
		}
			rs.close();
			return p;
	}
	
	public static List<Azafato> obtenerAzafatos(Connection con) throws SQLException {
		List<Azafato> listaAzafatos = new ArrayList<>();
		try (Statement stmt = con.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT * FROM Azafato")) {
				while(rs.next()) {
					Azafato a = new Azafato(
						rs.getString("usuario"),
						rs.getString("contrasenya"),
						rs.getInt("anyosExperiencia"),
						rs.getString("funcion")
					);
					
					listaAzafatos.add(a);
				}
			}
		}
		
		return listaAzafatos;
	}
	
	public static TreeSet<Integer> obtenerTodosLosAnyosDeExperiencia(Connection con) throws SQLException{
		String sent = "SELECT anyosExperiencia FROM Azafato ORDER BY anyosExperiencia ASC";
		Statement st = con.createStatement();
		TreeSet<Integer> anyos = new TreeSet<>();
		ResultSet rs = st.executeQuery(sent);
		while(rs.next()) {
			int anyo = rs.getInt(1);
			anyos.add(anyo);
		
		}

		rs.close();
		st.close();
		
		return anyos;
		
	}
	
	public static List<Azafato> ObtenerAzafatosSegunExperiencia(Connection con, int anyos) throws SQLException {
		List<Azafato> azafatosObtenidos = new ArrayList<>();
		try (Statement stmt = con.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT * FROM Azafato WHERE anyosExperiencia = "+anyos+"")) {
				while(rs.next()) {
					Azafato a = new Azafato(
							rs.getString("usuario"),
							rs.getString("contrasenya"),
							rs.getInt("anyosExperiencia"),
							rs.getString("funcion")
					);
					
					azafatosObtenidos.add(a);
				}
			}
		}
		
		return azafatosObtenidos;
	}
	
	public static List<Azafato> obtenerFuncionAzafatoSegunTexto(Connection con, String funcion) throws SQLException {
		List<Azafato> azafatosObtenidos = new ArrayList<>();
		try (Statement stmt = con.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT * FROM Azafato WHERE funcion LIKE '%"+funcion+"%'")) {
				while(rs.next()) {
					Azafato launch = new Azafato(
							rs.getString("usuario"),
							rs.getString("contrasenya"),
							rs.getInt("anyosExperiencia"),
							rs.getString("funcion")
					);
					
					azafatosObtenidos.add(launch);
				}
			}
		}
		
		return azafatosObtenidos;
	}
	
	public static boolean esAptoNuevoUsuarioAzafato (Connection con, String usuarioNuevo) throws SQLException{
		boolean esAptoNuevoUsuario=true;
		String sent = "SELECT usuario FROM Azafato";
		Statement st = con.createStatement();
		ArrayList<String> usuarios = new ArrayList<>();
		ResultSet rs = st.executeQuery(sent);
		while(rs.next()) {
			String nombreUsuario = rs.getString(1);
			usuarios.add(nombreUsuario);
		
		}
		for (String string : usuarios) {
			if (usuarioNuevo.equals(string)) {
				esAptoNuevoUsuario=false;
			}
		}
		rs.close();
		st.close();
		
		return esAptoNuevoUsuario;
		
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
	
	
	
	public static ArrayList<Pasajero> obtenerPasajeros(Connection con) {
	
        try (Statement statement = con.createStatement()) {
            ArrayList<Pasajero> ret = new ArrayList<>();
            String sent = "select * from Pasajero;";
            VentanaInicio.logger.log( Level.INFO, "Statement: " + sent );
            ResultSet rs = statement.executeQuery( sent );
            while( rs.next() ) { // Leer el resultset
                String dni = rs.getString("dni");
                String nombre= rs.getString("nombre");
                String apellido= rs.getString("apellido");
                int edad=rs.getInt("edad");
                int telefono=rs.getInt("telefono");
                String direccion = rs.getString("direccion");
                String rutaFoto = rs.getString("rutaFoto");
               
                ret.add( new Pasajero ( dni, nombre, apellido, edad, telefono,direccion,rutaFoto) );
            }
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }

	public static String obtenerHoraSalidaDeVuelo(Connection con, String ID) throws DBException {
		
		try (PreparedStatement stmt = con.prepareStatement("SELECT horaSalida FROM Vuelo WHERE ID= '"+ID+"' ")) {
			String salida="";
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					
					salida=rs.getString("horaSalida");
				}

			}
			
			return salida;
		} catch (SQLException e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            throw new DBException("No se pudo conseguir la hora de salida del vuelo", e);
		}
	}
	
public static String obtenerHoraLlegadaDeVuelo(Connection con, String ID) throws DBException {
		
		try (PreparedStatement stmt = con.prepareStatement("SELECT horaLlegada FROM Vuelo WHERE ID= '"+ID+"' ")) {
			String llegada="";
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					
					llegada=rs.getString("horaLlegada");
				}

			}
			
			return llegada;
		} catch (SQLException e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            throw new DBException("No se pudo conseguir la hora de llegada del vuelo", e);
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
			Clase c=Clase.valueOf(claseStr);
			double precio=rs.getDouble("precio");
			int numAsientos=rs.getInt("numAsientos");
			String fecha=rs.getString("fecha");

                ret.add( new Ticket ( ticketNum, idVuelo, dniPasajero, c, precio, numAsientos, fecha) );
            }
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }
	
	public static ArrayList<Vuelo> obtenerVuelosSegunOrigenDestino(Connection con, String origen, String destino) {
        try (Statement statement = con.createStatement()) {
            ArrayList<Vuelo> ret = new ArrayList<>();
            String sent = "SELECT * FROM Vuelo WHERE origen = '"+origen+"' AND destino = '"+destino+"'";
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
                
            }
            VentanaInicio.logger.log(Level.INFO, "Se han encontrado "+ ret.size()+" vuelos de origen: " + origen + " y destino: "+ destino);
           
            return ret;
        } catch (Exception e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            return null;
        }
    }
	
	public static ArrayList<Equipaje> obtenerEquipajesDePasajero(Connection con, String dni) throws DBException {
		try (PreparedStatement stmt = con.prepareStatement("SELECT descripcion, peso, largo, altura, anchura FROM Equipaje WHERE dniPasajero == '"+dni+"'  ORDER BY peso")) {
			
			ArrayList<Equipaje> equipajes = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Equipaje eq = new Equipaje(
						rs.getString("descripcion"),
						rs.getFloat("peso"),
						rs.getFloat("largo"),
						rs.getFloat("altura"),
						rs.getFloat("anchura")
					);
					
					equipajes.add(eq);

				}
	            VentanaInicio.logger.log(Level.INFO, "Se han encontrado "+ equipajes.size()+" equipajes del pasajero con dni:" + dni);

			}
			
			return equipajes;
		} catch (SQLException e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            throw new DBException("No se pudo conseguir el listado de equipajes asociados al pasajero", e);
		}
	}
	public static String obtenerFotoPasajero(Connection con, String dni) throws DBException {
		try (PreparedStatement stmt = con.prepareStatement("SELECT rutaFoto FROM Pasajero WHERE dni== '"+dni+"' ")) {
			String ruta="fotos/empty.png";
			try (ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					
					ruta=rs.getString("rutaFoto");
				}
				VentanaInicio.logger.log(Level.INFO, "Se ha encontrado foto del pasajero de dni: " + dni);

			}
			
			return ruta;
		} catch (SQLException e) {
            VentanaInicio.logger.log( Level.SEVERE, "Excepción", e );
            throw new DBException("No se pudo conseguir la fotografia del pasajero", e);
		}
	}
	
	public static void actualizarAsientosDeVuelo(Connection con,Vuelo v, int asientosAreservar) throws SQLException  {
		Statement statement = con.createStatement();
		
			String sent = "update Vuelo set  asientosDisponibles="+asientosAreservar+" where ID='"+v.getID()+"'";
			statement.executeUpdate(sent);
			VentanaInicio.logger.log(Level.INFO, "Se ha actualizado el numero de asientos restantes en el vuelo: " + v.getID());
		
		}
	public static void actualizarFotoDePasajero(Connection con, String dni, String rutaNuevaFoto) throws SQLException  {
		Statement statement = con.createStatement();
		
			String sent = "update Pasajero set rutaFoto='"+rutaNuevaFoto+"' where dni='"+dni+"'";
			statement.executeUpdate(sent);
			VentanaInicio.logger.log(Level.INFO, "Se ha actualizado la fotografia del pasajero: " + dni);
		
		}
	
	public static void actualizarAzafato(Connection con, String Usuario, String contrasenya, int experiencia, String funcion) throws SQLException  {
		Statement statement = con.createStatement();
		
			String sent = "update Azafato set contrasenya='"+contrasenya+"',anyosExperiencia="+experiencia+",funcion='"+funcion+"' where usuario='"+Usuario+"'";
			statement.executeUpdate(sent);
			VentanaInicio.logger.log(Level.INFO, "Se ha actualizado el azafato ");
		
		}
	public static void actualizarHoraDespegueYAterrizaje(Connection con, String ID, String horaSalida, String horaLlegada) throws SQLException  {
		Statement statement = con.createStatement();
		
			String sent = "update Vuelo set horaSalida='"+horaSalida+"',horaLlegada='"+horaLlegada+"' where ID='"+ID+"'";
			statement.executeUpdate(sent);
		
		}
	public static void eliminarVuelo(Connection con, String id) throws SQLException {
		Statement stm = con.createStatement();
		String sent = "delete from Vuelo where id ='" + id+"'";
		stm.executeUpdate(sent);
		VentanaInicio.logger.log(Level.INFO, "Se ha eliminado de la BD el vuelo: " + id);
		
	}
	public static void eliminarAzafato(Connection con, String usuario) throws SQLException {
		Statement stm = con.createStatement();
		String sent = "delete from Azafato where usuario ='" + usuario+"'";
		stm.executeUpdate(sent);
		VentanaInicio.logger.log(Level.INFO, "Se ha eliminado de la BD el azafato: " + usuario);
		
	}
	
	public static void eliminarTickets(Connection con, int ticketNum) throws SQLException {
		Statement stm = con.createStatement();
		String sent = "delete from Ticket where ticketNum = "+ticketNum+"";
		stm.executeUpdate(sent);
		VentanaInicio.logger.log(Level.INFO, "Se ha eliminado de la BD el ticket: " + ticketNum);

	}
	
	//Contar el número de azafatos
	public static int contarAzafatos(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Azafato";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	//Contar el número de administradores
	public static int contarAdministradores(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Administrador";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	//Contar el número de vuelos
	public static int contarVuelos(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Vuelo";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	//Contar el número de pasajeros
	public static int contarPasajeros(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Pasajero";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	//Contar el número de tickets
	public static int contarTickets(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Ticket";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	//Contar el número de equipajes 
	public static int contarEquipajes(Connection con) throws SQLException {
		Statement statement = con.createStatement();
		String sent = "select count(*) from Equipaje";
		ResultSet rs = statement.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	
}