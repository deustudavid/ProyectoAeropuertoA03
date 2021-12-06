package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.border.*;

import bd.BD;
import bd.DBException;
import main.VentanaInicio;


public class VentanaPermisos extends JInternalFrame {

	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelOpciones;
	private JLabel lblUsuario;
	private JLabel lblContrasenia;
	private JLabel labelCerrar;

	private JPasswordField textContrasenia;
	private JTextField textUsuario;

	private JProgressBar progressBarCerrar;
	private JProgressBar progressBarRegistarAdmin;
	private JButton btnRegistrarAdministrador;
	private JButton btnRegistrarAzafato;

	public VentanaPermisos() {

		Connection con = null;
		try {
			con = BD.initBD("Aeropuerto.db");

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 252);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(127, 255, 212));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);

		panelOpciones = new JPanel();
		panelOpciones.setBounds(10, 98, 478, 104);
		panelOpciones.setLayout(null);
		panelOpciones.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Opciones: ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
		panelOpciones.setBackground(Color.WHITE);
		panelCentral.add(panelOpciones);

		btnRegistrarAdministrador = new JButton("DAR PERMISOS ADMIN");
		btnRegistrarAdministrador.setBackground(new Color(255, 218, 185));
		btnRegistrarAdministrador.setBounds(10, 30, 235, 23);
		panelOpciones.add(btnRegistrarAdministrador);

		btnRegistrarAzafato = new JButton("DAR PERMISOS AZAFATO");
		btnRegistrarAzafato.setBackground(new Color(255, 218, 185));
		btnRegistrarAzafato.setBounds(250, 30, 218, 23);
		panelOpciones.add(btnRegistrarAzafato);

		JButton btnCancelar = new JButton("Cerrar");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				boolean resultadoAdministradorActivo = ventanas.VentanaAdministrador.VentanaAdminEstaActiva();
				boolean resultadoAzafatoActivo = ventanas.VentanaAzafato.VentanaAzafatoEstaActiva();

				if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
					VentanaAdministrador.desbloquearBotones();

				} else {
					VentanaAzafato.desbloquearBotones();

				}

			}
		});
		btnCancelar.setBackground(new Color(0, 153, 0));
		btnCancelar.setBounds(178, 70, 119, 23);
		panelOpciones.add(btnCancelar);

		lblContrasenia = new JLabel(" Introduce la contrasenia:");
		lblContrasenia.setBounds(10, 66, 163, 14);
		panelCentral.add(lblContrasenia);
		lblContrasenia.setBackground(Color.DARK_GRAY);

		textContrasenia = new JPasswordField();
		textContrasenia.setBounds(281, 59, 157, 28);
		panelCentral.add(textContrasenia);
		textContrasenia.setColumns(10);

		lblUsuario = new JLabel(" Introduce nombre de usuario:");
		lblUsuario.setBounds(10, 30, 227, 14);
		panelCentral.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBackground(Color.DARK_GRAY);

		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(281, 23, 157, 28);
		panelCentral.add(textUsuario);

		labelCerrar = new JLabel("Cerrando ventana...");
		labelCerrar.setBounds(200, 300, 200, 10);
		labelCerrar.setVisible(false);

		progressBarCerrar = new JProgressBar(0, 100);
		progressBarCerrar.setBounds(415, 360, 146, 14);
		progressBarCerrar.setVisible(false);
		progressBarRegistarAdmin = new JProgressBar(0, 100);
		progressBarRegistarAdmin.setBounds(415, 360, 146, 14);
		progressBarRegistarAdmin.setVisible(false);

		btnRegistrarAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {

					String n = textUsuario.getText();
					String c = textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Aeropuerto.db");
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
						JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							BD.insertarAdministrador(con, n, c);
							BD.insertarAzafato(con, n, c);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "Te has registrado correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
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

				} else {
					JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		btnRegistrarAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textUsuario.getText().equals("") && !textContrasenia.getText().equals("")) {

					String n = textUsuario.getText();
					String c = textContrasenia.getText();
					Connection con = null;
					try {
						con = BD.initBD("Aeropuerto.db");
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
						JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							BD.insertarAzafato(con, n, c);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "Te has registrado correctamente", "Correcto",
								JOptionPane.INFORMATION_MESSAGE);
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

				} else {
					JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		setVisible(true);
	}
}
