package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.BD;
import bd.DBException;
import ventanas.VentanaAdministrador;
import ventanas.VentanaAzafato;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JPanel panelSur;
	private JPanel panelCentral;

	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	private JLabel labelCerrar;
	private JLabel labelregistrarAdministrador;
	private JFrame ventanaActual;

	private JTextField textUsuario;

	private JPasswordField textContrasenia;

	private JProgressBar progressBarCerrar;
	private JProgressBar progressBarRegistarAdmin;

	private JButton btnIniciarSesionAdministrador;
	private JButton btnRegistrarAdministrador;
	private JButton btnRegistrarAzafato;
	private JButton btnIniciarSesionAzafato;
	private JButton btnCerrar;
	
	/* LOGGER */
	
	public static Logger logger = initLogger();
	private static long MAX_SIZE_FICHERO_LOG = 5 * 1024;  // 5 Kb es el tamaño maximo del fichero log para reiniciarlo
	private static final String NOMBRE_FICHERO_LOG = "logBaseDatos";
	private static final String EXT_FICHERO_LOG = ".log"; // extension del fichero log
	
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
				logger.addHandler( new FileHandler( NOMBRE_FICHERO_LOG + EXT_FICHERO_LOG, true ) );  // Y saca el log a fichero .log (añadiendo al log previo)
			} catch (Exception e) {
				JOptionPane.showMessageDialog( null, "Error, no se puede crear fichero de log.", 
						"Error al crear fichero", JOptionPane.ERROR_MESSAGE );
			}
		}
		return logger;
	}

	
	
	/* MAIN */
	public static void main(String[] args) {
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
			con = BD.initBD("Usuario.db");
			
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			BD.crearTablas(con);
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		setBounds(100, 100, 920, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelSur = new JPanel();
		panelSur.setBackground(Color.GRAY);
		contentPane.add(panelSur, BorderLayout.SOUTH);

		btnIniciarSesionAdministrador = new JButton("INICIAR SESION ADMINISTRADOR");
		panelSur.add(btnIniciarSesionAdministrador);

		btnRegistrarAdministrador = new JButton("REGISTRAR ADMINISTRADOR");
		panelSur.add(btnRegistrarAdministrador);

		btnRegistrarAzafato = new JButton("REGISTRAR AZAFATO");
		panelSur.add(btnRegistrarAzafato);

		btnIniciarSesionAzafato = new JButton("INICIAR SESION AZAFATO");
		panelSur.add(btnIniciarSesionAzafato);

		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));

		lblUsuario = new JLabel(" Introduce tu nombre de Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBackground(Color.DARK_GRAY);
		panelCentral.add(lblUsuario);

		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		panelCentral.add(textUsuario);

		lblContrasenia = new JLabel(" Introduce la contrasenia:");
		lblContrasenia.setBackground(Color.DARK_GRAY);
		panelCentral.add(lblContrasenia);

		labelCerrar = new JLabel("Cerrando ventana...");
		labelCerrar.setBounds(200, 300, 200, 10);
		labelCerrar.setVisible(false);

		textContrasenia = new JPasswordField();
		textContrasenia.setText("");
		textContrasenia.setColumns(10);
		panelCentral.add(textContrasenia);

		progressBarCerrar = new JProgressBar(0, 100);
		progressBarCerrar.setBounds(415, 360, 146, 14);
		progressBarCerrar.setVisible(false);
		progressBarRegistarAdmin = new JProgressBar(0, 100);
		progressBarRegistarAdmin.setBounds(415, 360, 146, 14);
		progressBarRegistarAdmin.setVisible(false);

		btnCerrar = new JButton("Cerrar");
		panelSur.add(btnCerrar);

		btnIniciarSesionAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {
					
					String n= textUsuario.getText();
					String c= textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Usuario.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						switch (BD.obtenerAdministrador(con,n,c)) {
							case 0:
								JOptionPane.showMessageDialog(null, "Ese administrador no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
								break;
							case 1:
								JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
								break;
							case 2:
								JOptionPane.showMessageDialog(null, "Datos correctos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
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
					con = BD.initBD("Usuario.db");
					VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					switch (BD.obtenerAzafato(con,n,c)) {
						case 0:
							JOptionPane.showMessageDialog(null, "Ese azafato no está registrado", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "Datos correctos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
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

		btnRegistrarAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {
					
					String n= textUsuario.getText();
					String c= textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Usuario.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int resul = 0;
					try {
						resul = BD.obtenerAdministrador(con, n, c);
					} catch (DBException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (resul != 0) {
						JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						
						try {
							BD.insertarAdministrador(con, n, c);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente" , "Correcto", JOptionPane.INFORMATION_MESSAGE );
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

		btnRegistrarAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {
					
					String n= textUsuario.getText();
					String c= textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Usuario.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					int resul = 0;
					try {
						resul = BD.obtenerAzafato(con, n, c);
					} catch (DBException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (resul != 0) {
						JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						
						try {
							BD.insertarAzafato(con, n, c);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Te has registrado correctamente" , "Correcto", JOptionPane.INFORMATION_MESSAGE );
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
		/* HILO A ARREGLAR
		btnRegistrarAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.getContentPane().add(labelregistrarAdministrador);
				ventanaActual.getContentPane().add(progressBarRegistarAdmin);

				Thread hiloCrearAdmin = new Thread(new Runnable() {

					@Override
					public void run() {
						labelregistrarAdministrador.setVisible(true);
						progressBarRegistarAdmin.setVisible(true);

						for (int i = 0; i <= 100; i++) {
							progressBarRegistarAdmin.setValue(i);
							try {
								Thread.sleep(7);
							} catch (InterruptedException el) {
								el.printStackTrace();
							}
						}

						labelregistrarAdministrador.setVisible(false);
						progressBarRegistarAdmin.setVisible(false);
					
						

					}
				});

				hiloCrearAdmin.start();
			}

		});
	*/
		

		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.getContentPane().add(labelCerrar);
				ventanaActual.getContentPane().add(progressBarCerrar);

				Thread hiloCerrar = new Thread(new Runnable() {

					@Override
					public void run() {
						labelCerrar.setVisible(true);
						progressBarCerrar.setVisible(true);

						for (int i = 0; i <= 100; i++) {
							progressBarCerrar.setValue(i);
							try {
								Thread.sleep(7);
							} catch (InterruptedException el) {
								el.printStackTrace();
							}
						}

						labelCerrar.setVisible(false);
						progressBarCerrar.setVisible(false);
						ventanaActual.dispose();
						JOptionPane.showMessageDialog(null, "Se ha cerrado correctamente");

					}
				});

				hiloCerrar.start();
			}

		});

		setVisible(true);
	}

}
