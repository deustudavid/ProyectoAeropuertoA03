package ventanas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import bd.BD;
import bd.DBException;
import clases.Pasajero;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.*;

public class CreadorEquipajes extends javax.swing.JInternalFrame {
	private JButton btnRegistrarEquipaje;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JButton btnVisorEquipajes;
	private JLabel lblDescripcion;
	private JLabel lblPeso;
	private JLabel lblLargo;
	private JLabel lblAltura;
	private JLabel lblDNIPasajero;
	private JLabel lblAnchura;
	private JPanel panelIzquierda;
	private JTextField txtDNI;
	private static JTextField txtDescripcion;
	private static JSpinner txtPeso ;
	private static JSpinner txtAnchura ;
	private static JSpinner txtLargo ;
	private static JSpinner txtAltura ;


	private ImageIcon imagenCancelar;
	private ImageIcon imagenVisorEquipajes;
	private ImageIcon imagenGuardar;
	private ImageIcon imagenBuscar;

	private static Connection con;
	private static String dniAbuscar;
	private static String dniAguardar;
	private static boolean correctaDescripcion;
	private boolean seHaPulsadoBuscar;

	public CreadorEquipajes() {

		imagenCancelar = new ImageIcon("img/Cancelar.png");
		con = null;
		dniAbuscar = "";
		dniAguardar="";
		correctaDescripcion = false;
		
		seHaPulsadoBuscar = false;
		imagenGuardar = new ImageIcon("img/guardar.png");
		imagenBuscar = new ImageIcon("img/lupa.png");
		imagenVisorEquipajes = new ImageIcon("img/maleta.png");

		panelIzquierda = new JPanel();
		lblDescripcion = new JLabel();
		lblPeso = new JLabel();
		lblLargo = new JLabel();
		lblAltura = new JLabel();
		
		txtPeso = new JSpinner();
		txtPeso.setEnabled(false);
		txtPeso.setModel(new SpinnerNumberModel(0.1, 0.1, 20.0, 0.1));
		
		 txtLargo = new JSpinner();
		txtLargo.setEnabled(false);
		txtLargo.setModel(new SpinnerNumberModel(50.0, 50.0, 1000.0, 0.5));
		
		txtAltura = new JSpinner();
		txtAltura.setEnabled(false);
		txtAltura.setModel(new SpinnerNumberModel(50.0, 50.0, 1500.0, 2.5));
		
		 txtAnchura = new JSpinner();
		txtAnchura.setEnabled(false);
		txtAnchura.setModel(new SpinnerNumberModel(50.0, 50.0, 1000.0, 0.5));
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		
		lblDNIPasajero = new JLabel();
		btnRegistrarEquipaje = new JButton();

		btnRegistrarEquipaje.setIcon(imagenGuardar);

		btnCancelar = new JButton();
		btnCancelar.setIcon(imagenCancelar);
		txtDNI = new JTextField();
		btnBuscar = new JButton();

		btnBuscar.setIcon(imagenBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dniAbuscar = txtDNI.getText();

				try {
					con = BD.initBD("Aeropuerto.db");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					try {
						if (BD.existePasajero(con, dniAbuscar)) {
							
							seHaPulsadoBuscar = true;
							txtDescripcion.setEnabled(true);
							txtPeso.setEnabled(true);
							txtAltura.setEnabled(true);
							txtAnchura.setEnabled(true);
							txtLargo.setEnabled(true);
							dniAguardar=txtDNI.getText();
							
						} else {
							JOptionPane.showMessageDialog(null, "No se ha encontrado el pasajero con ese dni", "Error",
									JOptionPane.WARNING_MESSAGE);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		panelIzquierda.setBackground(new Color(51, 0, 255));

		lblDescripcion.setFont(new Font("Tahoma", 1, 11));
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setText("Descripcion");

		lblPeso.setFont(new Font("Tahoma", 1, 11));
		lblPeso.setForeground(new Color(255, 255, 255));
		lblPeso.setText("Peso(kg)");

		lblLargo.setFont(new Font("Tahoma", 1, 11));
		lblLargo.setForeground(new Color(255, 255, 255));
		lblLargo.setText("Largo(cm)");

		lblAltura.setFont(new Font("Tahoma", 1, 11));
		lblAltura.setForeground(new Color(255, 255, 255));
		lblAltura.setText("Altura(cm)");
		lblAnchura = new JLabel();
		
				lblAnchura.setFont(new Font("Tahoma", 1, 11));
				lblAnchura.setForeground(new Color(255, 255, 255));
				lblAnchura.setText("Anchura(cm)");
		
		JLabel lblDatosDelEquipaje = new JLabel();
		lblDatosDelEquipaje.setText("Datos del equipaje");
		lblDatosDelEquipaje.setForeground(Color.WHITE);
		lblDatosDelEquipaje.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		

		GroupLayout glPanelIzquierda = new GroupLayout(panelIzquierda);
		glPanelIzquierda.setHorizontalGroup(
			glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup()
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addGap(26)
							.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescripcion)
								.addComponent(lblPeso)
								.addComponent(lblLargo)
								.addComponent(lblAltura)
								.addComponent(lblAnchura))
							.addGap(47)
							.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
								.addComponent(txtAnchura, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAltura, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLargo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPeso, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addGap(233)
							.addComponent(lblDatosDelEquipaje, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		glPanelIzquierda.setVerticalGroup(
			glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDelEquipaje)
					.addGap(12)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeso)
						.addComponent(txtPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLargo)
						.addComponent(txtLargo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAltura)
						.addComponent(txtAltura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAnchura)
						.addComponent(txtAnchura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(82))
		);
		panelIzquierda.setLayout(glPanelIzquierda);

		lblDNIPasajero.setFont(new Font("Tahoma", 1, 18));
		lblDNIPasajero.setText("DNI del pasajero: ");

		btnRegistrarEquipaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!txtDescripcion.getText().isBlank()) {
					correctaDescripcion=true;
				}
				if (correctaDescripcion ) {

					try {
						con = BD.initBD("Aeropuerto.db");
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						BD.insertarEquipaje(con, 0, dniAguardar, txtDescripcion.getText(), Double.parseDouble(txtPeso.getValue().toString()), Double.parseDouble(txtLargo.getValue().toString()),Double.parseDouble(txtAltura.getValue().toString()),Double.parseDouble(txtAnchura.getValue().toString()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Equipaje del pasajero registrado correctamente");

					try {
						BD.closeBD(con);
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Quedan campos vacíos o incorrectos.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnRegistrarEquipaje.setText("Registrar Equipaje");

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				boolean resultadoAdministradorActivo = VentanaAdministrador.VentanaAdminEstaActiva();
				boolean resultadoAzafatoActivo = VentanaAzafato.VentanaAzafatoEstaActiva();

				if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
					VentanaAdministrador.desbloquearBotones();

				} else {
					VentanaAzafato.desbloquearBotones();

				}

			}
		});
		Runnable hiloBuscarPulsado = new Runnable() {
			public void run() {
				while (!seHaPulsadoBuscar) {

					txtDescripcion.setEnabled(false);
					txtAltura.setEnabled(false);
					txtAnchura.setEnabled(false);
					txtLargo.setEnabled(false);
					txtPeso.setEnabled(false);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};
		Thread t2 = new Thread(hiloBuscarPulsado);
		t2.start();

		btnBuscar.setText("Buscar");
		
		btnVisorEquipajes = new JButton();
		btnVisorEquipajes.setIcon(imagenVisorEquipajes);
		btnVisorEquipajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisorEquipajes v = new VisorEquipajes();
				v.setVisible(true);
				
				
			}
		});
		btnVisorEquipajes.setText("Visor equipajes");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblDNIPasajero)
							.addGap(29)
							.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(btnRegistrarEquipaje, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVisorEquipajes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(342, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDNIPasajero)
						.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(38)
					.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegistrarEquipaje, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVisorEquipajes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		getContentPane().setLayout(layout);

		pack();

	}
}
