package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatLightLaf;

import bd.BD;
import bd.DBException;
import ventanas.VentanaAdministrador;
import ventanas.VentanaAzafato;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelOpciones;
	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	private JLabel labelCerrar;
	private JFrame ventanaActual;
	private JPasswordField textContrasenia;
	private JTextField textUsuario;
	
	public static String database;
	public static String milisegundos;
	public static String columnas;
	public static String imagenes;


	private JProgressBar progressBarCerrar;
	private JProgressBar progressBarRegistarAdmin;

	private JButton btnIniciarSesionAdministrador;
	private JButton btnIniciarSesionAzafato;
	private JButton btnCerrar;
	
	/* LOGGER */
	
	public static Logger logger = initLogger();
	private static long MAX_SIZE_FICHERO_LOG = 5 * 1024;  // 5 Kb es el tama??o maximo del fichero log para reiniciarlo
	private static final String NOMBRE_FICHERO_LOG = "logBaseDatos";
	private static final String EXT_FICHERO_LOG = ".log"; // extension del fichero log
	private static Handler handler ;
	private JPanel panelSur;
	
	
	/**
	 * Crea el logger y ciertas cualidades del mismo
	 * @return Logger logger
	 */
	private static Logger initLogger() {
		if (logger==null) {  // Logger por defecto local:
			// Reinicio de fichero de logger si ya muy grande
			File fLoggerAnt = new File( NOMBRE_FICHERO_LOG + EXT_FICHERO_LOG );
			
			if (fLoggerAnt.exists() && fLoggerAnt.length() > MAX_SIZE_FICHERO_LOG ) {
				
				long milisegundos= fLoggerAnt.lastModified();
				Date fecha = new Date(milisegundos);
				String newFicLog = NOMBRE_FICHERO_LOG + "-" + fecha + EXT_FICHERO_LOG;
				try {
					Files.move( fLoggerAnt.toPath(), Paths.get(newFicLog) );  // Renombra el fichero para empezar de nuevo
				} catch (Exception e) {}
			}
			// Creacion de logger asociado a fichero de logger
			logger = Logger.getLogger( VentanaInicio.class.getName() );  // Nombre del logger - el de la clase
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				handler = new FileHandler(NOMBRE_FICHERO_LOG + EXT_FICHERO_LOG, true );// Y saca el log a fichero .log (a??adiendo al log previo)
				handler.setFormatter(new SimpleFormatter());
				logger.addHandler(handler);
			} catch (Exception e) {
				JOptionPane.showMessageDialog( null, "Error, no se puede crear fichero de log.", 
						"Error al crear fichero", JOptionPane.ERROR_MESSAGE );
			}
		}
		
		return logger;
	}

	
	
	
	/**
	 * 
	 * MAIN DEL PROGRAMA, ABRE VENTANAINICIO PARA INICIAR SESION
	 * 
	 * implementa una nueva interfaz gracias al jar flatlaf-2.0.jar
	 * genera un directorio logs en el que van los .log
	 * genera un directorio config
	 * genera los ficheros config.properties y logger.properties dentro del directorio config
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File directory = new File("");

		directory = new File("logs");
		if (!directory.exists()) {
			directory.mkdir();

		}
		directory = new File("config");
		if (!directory.exists()) {
			directory.mkdir();
			BufferedWriter buffWriter;
			try {
				buffWriter = new BufferedWriter(new FileWriter("config/config.properties"));
				buffWriter.write("database=jdbc:sqlite:Aeropuerto.db");
				buffWriter.newLine();
				buffWriter.write("milisegundos=8000");
				buffWriter.newLine();
				buffWriter.write("columnas=7");
				buffWriter.newLine();
				buffWriter.write("imagenes=/fotos");
				
				buffWriter.close();

				buffWriter = new BufferedWriter(new FileWriter("config/logger.properties"));
				buffWriter.write("handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler");
				buffWriter.newLine();
				buffWriter.write(".level = ALL");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.ConsoleHandler.level = INFO");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.pattern = logs/"+logger.getName());
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.level = INFO");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter");
				buffWriter.newLine();
				buffWriter.write("java.util.logging.FileHandler.append = true");
				buffWriter.close();

			} catch (IOException e) {
			}

		}
		
		try (FileInputStream fis = new FileInputStream("config/logger.properties")) {
			LogManager.getLogManager().readConfiguration(fis);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error al leer el fichero de configuracion del logger.");
		}
		 
		try (FileReader reader = new FileReader("config/config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);

			database = properties.getProperty("database");
			milisegundos = properties.getProperty("milisegundos");
			columnas = properties.getProperty("columnas");
			imagenes = properties.getProperty("imagenes");

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error al leer el fichero de configuracion.");
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.log( Level.INFO, "Se ejecuta la ventana de inicio" );
					
					VentanaInicio vi = new VentanaInicio();

				} catch (Exception e) {
					e.printStackTrace();
					VentanaInicio.logger.log( Level.INFO, "Error al crear la ventana de inicio", e );
				}
			}
		});
	}

	public VentanaInicio() {
		
		Connection con =null;
		try {
			con = BD.initBD("Aeropuerto.db");
			
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			try {
				BD.crearTablas(con);
			} catch (DBException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				BD.cargarPasajerosdeFichero(con);
			} catch (DBException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
		try {
			BD.closeBD(con);
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		
		

		setTitle("VENTANA INICIO");
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 361);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);

		panelOpciones = new JPanel();
		panelOpciones.setBounds(10, 104, 478, 111);
		panelOpciones.setLayout(null);
		panelOpciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones: ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 204)));
		panelOpciones.setBackground(new Color(0, 0, 128));
		panelCentral.add(panelOpciones);

		btnIniciarSesionAdministrador = new JButton("INICIAR SESION ADMINISTRADOR");
		btnIniciarSesionAdministrador.setForeground(new Color(255, 250, 205));
		btnIniciarSesionAdministrador.setBackground(Color.BLACK);
		btnIniciarSesionAdministrador.setBounds(233, 36, 235, 23);
		panelOpciones.add(btnIniciarSesionAdministrador);

		btnIniciarSesionAzafato = new JButton("INICIAR SESION AZAFATO");
		btnIniciarSesionAzafato.setForeground(new Color(255, 250, 205));
		btnIniciarSesionAzafato.setBackground(Color.BLACK);
		btnIniciarSesionAzafato.setBounds(10, 36, 213, 23);
		panelOpciones.add(btnIniciarSesionAzafato);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(new Color(0, 153, 0));
		btnCerrar.setBounds(160, 70, 119, 23);
		panelOpciones.add(btnCerrar);

		lblContrasenia = new JLabel(" Introduce la contrasenia:");
		lblContrasenia.setBounds(26, 68, 163, 14);
		panelCentral.add(lblContrasenia);
		lblContrasenia.setBackground(Color.DARK_GRAY);

		textContrasenia = new JPasswordField();
		textContrasenia.setBounds(281, 61, 157, 28);
		panelCentral.add(textContrasenia);
		textContrasenia.setColumns(10);

		lblUsuario = new JLabel(" Introduce tu nombre de usuario:");
		lblUsuario.setBounds(26, 26, 227, 14);
		panelCentral.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBackground(Color.DARK_GRAY);

		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(281, 22, 157, 28);
		panelCentral.add(textUsuario);
		
		JLabel lblAvioncito = new JLabel("AVI\u00D3N");
		lblAvioncito.setBounds(10, 220, 248, 90);
		ImageIcon im = new ImageIcon("img/avioncito.png");
		
		ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(lblAvioncito.getWidth(),lblAvioncito.getHeight(),Image.SCALE_DEFAULT));
		lblAvioncito.setIcon(imagenConDimensiones);
		panelCentral.add(lblAvioncito);
		
		panelSur = new JPanel(null);
		contentPane.add(panelSur, BorderLayout.SOUTH);

		labelCerrar = new JLabel("Cerrando ventana...");
		labelCerrar.setBounds(200, 300, 200, 10);
		labelCerrar.setVisible(false);

		progressBarCerrar = new JProgressBar(0, 100);
		progressBarCerrar.setBounds(415, 360, 146, 14);
		progressBarCerrar.setVisible(false);
		progressBarRegistarAdmin = new JProgressBar(0, 100);
		progressBarRegistarAdmin.setBounds(415, 360, 146, 14);
		progressBarRegistarAdmin.setVisible(false);

		btnIniciarSesionAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {
					
					String n= textUsuario.getText();
					String c= textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Aeropuerto.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						switch (BD.obtenerAdministrador(con,n,c)) {
							case 0:
								JOptionPane.showMessageDialog(null, "Ese administrador no se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
								break;
							case 1:
								JOptionPane.showMessageDialog(null, "Contrasenia incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
								break;
							case 2:
								ventanaActual.dispose();
								new VentanaAdministrador();

								break;
							default:
								break;

}
					} catch (HeadlessException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (DBException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						BD.closeBD(con);
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos cerrada");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textUsuario.setText("");
					textContrasenia.setText("");
					
					}
					else {
						 JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});

		btnIniciarSesionAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {
				
				String n= textUsuario.getText();
				String c= textContrasenia.getText();
				Connection con = null;
				try {
					con = BD.initBD("Aeropuerto.db");
					VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					switch (BD.obtenerAzafato(con,n,c)) {
						case 0:
							JOptionPane.showMessageDialog(null, "Ese azafato no se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Contrasenia incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case 2:
							ventanaActual.dispose();
							new VentanaAzafato();

							break;
						default:
							break;

}
				} catch (HeadlessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (DBException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					BD.closeBD(con);
					VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos cerrada");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textUsuario.setText("");
				textContrasenia.setText("");
				
				}
				else {
					 JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
	

		

		
		

		setVisible(true);
		
		/**
		 * hilo para crear el movimiento de la imagen del avion por la parte baja de la ventana
		 * hace que se mueva la imagen de manera horizontal
		 */
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int inicial = lblAvioncito.getX();
				while(true) {
					while(inicial < 250
							){
						inicial = inicial + 10;
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblAvioncito.setBounds(inicial,lblAvioncito.getY(),lblAvioncito.getWidth(),lblAvioncito.getHeight());
						panelSur.updateUI();
					}
					while(inicial>=10){
						inicial = inicial - 10;
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lblAvioncito.setBounds(inicial,lblAvioncito.getY(),lblAvioncito.getWidth(),lblAvioncito.getHeight());
						panelSur.updateUI();
					
					}
				}		
			}
		};
		
		Thread t = new Thread(r);
		t.start();
	}
}
