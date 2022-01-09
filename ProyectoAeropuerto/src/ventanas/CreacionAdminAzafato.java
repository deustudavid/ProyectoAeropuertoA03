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


public class CreacionAdminAzafato extends JInternalFrame {
	private JInternalFrame ventanaActual,ventanaAnterior;
	private static boolean admin,azafato;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelOpciones;
	private JLabel lblCargo;
	private JLabel lblExperiencia;
	private JLabel labelCerrar;
	private static Connection con;
	private static JTextField textCargo;
	private static JSpinner txtAnyos;
	private JProgressBar progressBarCerrar;
	private JProgressBar progressBarRegistarAdmin;
	private static JButton btnRegistrarAdministrador;
	private static JButton btnRegistrarAzafato;
	private static JTextField textFuncion;

	public CreacionAdminAzafato(JInternalFrame va, boolean seQuiereAdmin, boolean seQuiereAzafato) {
		ventanaActual = this;
		ventanaAnterior = va;
		admin=seQuiereAdmin;
		azafato=seQuiereAzafato;
		 con = null;
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

		setTitle("VENTANA DATOS");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 290);
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
		panelOpciones.setBounds(10, 143, 478, 104);
		panelOpciones.setLayout(null);
		panelOpciones.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones: ", TitledBorder.CENTER, TitledBorder.TOP, null, Color.CYAN));
		panelOpciones.setBackground(Color.BLACK);
		panelCentral.add(panelOpciones);

		btnRegistrarAdministrador = new JButton("ACEPTAR ADMIN");
		btnRegistrarAdministrador.setEnabled(false);
		btnRegistrarAdministrador.setBackground(new Color(255, 218, 185));
		btnRegistrarAdministrador.setBounds(10, 30, 235, 23);
		panelOpciones.add(btnRegistrarAdministrador);

		btnRegistrarAzafato = new JButton("ACEPTAR AZAFATO");
		btnRegistrarAzafato.setEnabled(false);
		btnRegistrarAzafato.setBackground(new Color(255, 218, 185));
		btnRegistrarAzafato.setBounds(250, 30, 218, 23);
		panelOpciones.add(btnRegistrarAzafato);

		JButton btnCancelar = new JButton("Volver");

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				VentanaPermisos.siguienteVentanaAdministrador=false;
				VentanaPermisos.siguienteVentanaAzafato=false;
				ventanaActual.dispose();
				VentanaPermisos.textContrasenia.setText("");
				VentanaPermisos.textUsuario.setText("");
				ventanaAnterior.setVisible(true);

			}
		});
		btnCancelar.setBackground(new Color(0, 153, 0));
		btnCancelar.setBounds(178, 70, 119, 23);
		panelOpciones.add(btnCancelar);

		lblExperiencia = new JLabel("Anyos de experiencia del azafato:");
		lblExperiencia.setBounds(10, 66, 215, 14);
		panelCentral.add(lblExperiencia);
		lblExperiencia.setBackground(Color.DARK_GRAY);

		lblCargo = new JLabel("Cargo del administrador: ");
		lblCargo.setBounds(10, 30, 227, 14);
		panelCentral.add(lblCargo);
		lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargo.setBackground(Color.DARK_GRAY);

		textCargo = new JTextField();
		textCargo.setEnabled(false);
		textCargo.setColumns(10);
		textCargo.setBounds(185, 23, 262, 28);
		panelCentral.add(textCargo);
		
		txtAnyos=new JSpinner();
		txtAnyos.setEnabled(false);
		txtAnyos.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		txtAnyos.setBounds(286, 63, 64, 20);
		panelCentral.add(txtAnyos);
		
		JLabel lblIntroduceCargoDel = new JLabel("Funcion del azafato: ");
		lblIntroduceCargoDel.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduceCargoDel.setBackground(Color.DARK_GRAY);
		lblIntroduceCargoDel.setBounds(10, 109, 167, 14);
		panelCentral.add(lblIntroduceCargoDel);
		
		textFuncion = new JTextField();
		textFuncion.setEnabled(false);
		textFuncion.setColumns(10);
		textFuncion.setBounds(185, 102, 262, 28);
		panelCentral.add(textFuncion);

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
					
				
				if (!textCargo.getText().equals("") && !VentanaPermisos.textContrasenia.getText().equals("")) {

					String n = VentanaPermisos.textUsuario.getText();
					String c = VentanaPermisos.textContrasenia.getText();
					
					try {
						con = BD.initBD("Aeropuerto.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				try {
					if (BD.obtenerAdministrador(con, n,c)==0){
						
					
						

							try {
								BD.insertarAdministrador(con, n, c, textCargo.getText());
							} catch (DBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "Administrador nuevo registrado", "Correcto",
									JOptionPane.INFORMATION_MESSAGE);
							try {
								BD.closeBD(con);
								VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos cerrada");
							} catch (DBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}else {
						JOptionPane.showMessageDialog(null, "Ya has registrado ese administrador", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				} else {
					JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		btnRegistrarAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!textFuncion.getText().equals("") ) {

					String n = VentanaPermisos.textUsuario.getText();
					String c = VentanaPermisos.textContrasenia.getText();
					
					try {
						con = BD.initBD("Aeropuerto.db");
						VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						if (BD.obtenerAzafato(con, n,c)==0){

						
						try {
							BD.insertarAzafato(con, n, c, (int) txtAnyos.getValue(),textFuncion.getText());
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


						
							JOptionPane.showMessageDialog(null, "Azafato nuevo registrado", "Correcto",
									JOptionPane.INFORMATION_MESSAGE);
						try {
							BD.closeBD(con);
							VentanaInicio.logger.log(Level.INFO, "Conexion con la base de datos cerrada");
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else {
							JOptionPane.showMessageDialog(null, "Ya has registrado ese azafato", "Error", JOptionPane.ERROR_MESSAGE);
							
						
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		
		Runnable hiloEnabled = new Runnable() {
			public void run() {
				while (true) {

					comprobarCampos();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t2 = new Thread(hiloEnabled);
		t2.start();
	}
	public static void comprobarCampos() {
		
		if (admin) {
			textCargo.setEnabled(true);
			btnRegistrarAdministrador.setEnabled(true);
		}else if (azafato) {
			txtAnyos.setEnabled(true);
			textFuncion.setEnabled(true);
			btnRegistrarAzafato.setEnabled(true);
		}
	}

}



