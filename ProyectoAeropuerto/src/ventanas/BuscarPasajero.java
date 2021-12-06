package ventanas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.*;

public class BuscarPasajero extends javax.swing.JInternalFrame {

	private JButton btnBuscarFoto;
	private JButton btnActualizar;
	private JButton btnCancelar;
	private JButton btnBuscar;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblDNIPasajero;
	private JLabel lblEdad;
	private JLabel lblGenero;
	private JPanel panelIzquierda;
	private JPanel panelCentral;
	private JScrollPane jScrollPane1;
	private JRadioButton radioHombre;
	private JRadioButton radioMujer;
	private JTextArea txtDireccion;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JLabel txtFoto;
	private JTextField txtEdad;

	private ImageIcon imagenGuardar;
	private ImageIcon imagenBuscar;

	public BuscarPasajero() {

		imagenGuardar = new ImageIcon("img/guardar.png");
		imagenBuscar = new ImageIcon("img/lupa.png");

		panelIzquierda = new JPanel();
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		lblDni = new JLabel();
		lblTelefono = new JLabel();
		lblDireccion = new JLabel();
		txtApellido = new JTextField();
		txtNombre = new JTextField();
		txtDni = new JTextField();
		txtTelefono = new JTextField();
		jScrollPane1 = new JScrollPane();
		txtDireccion = new JTextArea();
		lblDNIPasajero = new JLabel();
		panelCentral = new JPanel();
		lblEdad = new JLabel();
		lblGenero = new JLabel();
		radioHombre = new JRadioButton();
		radioMujer = new JRadioButton();
		txtFoto = new JLabel();
		btnBuscarFoto = new JButton();
		btnActualizar = new JButton();

		btnActualizar.setIcon(imagenGuardar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar = new JButton();
		txtDNI = new JTextField();
		btnBuscar = new JButton();
		btnBuscar.setIcon(imagenBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		panelIzquierda.setBackground(new Color(51, 0, 255));

		lblNombre.setFont(new Font("Tahoma", 1, 11));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setText("Nombre");

		lblApellido.setFont(new Font("Tahoma", 1, 11));
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setText("Apellido");

		lblDni.setFont(new Font("Tahoma", 1, 11));
		lblDni.setForeground(new Color(255, 255, 255));
		lblDni.setText("DNI");

		lblTelefono.setFont(new Font("Tahoma", 1, 11));
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setText("Tfno");

		lblDireccion.setFont(new Font("Tahoma", 1, 11));
		lblDireccion.setForeground(new Color(255, 255, 255));
		lblDireccion.setText("Direccion");

		txtDireccion.setColumns(20);
		txtDireccion.setRows(5);
		jScrollPane1.setViewportView(txtDireccion);

		GroupLayout glPanelIzquierda = new GroupLayout(panelIzquierda);
		panelIzquierda.setLayout(glPanelIzquierda);
		glPanelIzquierda.setHorizontalGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup().addGap(26, 26, 26).addGroup(
						glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(glPanelIzquierda
								.createSequentialGroup().addGroup(glPanelIzquierda
										.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addGroup(glPanelIzquierda
												.createSequentialGroup().addComponent(lblNombre).addGap(47, 47, 47)
												.addComponent(txtNombre))
										.addGroup(glPanelIzquierda.createSequentialGroup()
												.addGroup(glPanelIzquierda
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(lblDireccion).addComponent(lblTelefono)
														.addComponent(lblDni))
												.addGap(38, 38, 38)
												.addGroup(glPanelIzquierda
														.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(jScrollPane1).addComponent(txtTelefono)
														.addComponent(txtDni))))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
										glPanelIzquierda.createSequentialGroup().addComponent(lblApellido)
												.addGap(48, 48, 48).addComponent(txtApellido,
														GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, Short.MAX_VALUE)))));
		glPanelIzquierda.setVerticalGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup().addGap(37, 37, 37)
						.addGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lblNombre).addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(22, 22, 22)
						.addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblApellido).addComponent(txtApellido, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lblDni).addComponent(txtDni, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lblTelefono).addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(glPanelIzquierda.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lblDireccion)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGap(34, 34, 34)));

		lblDNIPasajero.setFont(new Font("Tahoma", 1, 18));
		lblDNIPasajero.setText("DNI del pasajero: ");

		panelCentral.setBackground(new Color(51, 0, 255));

		lblEdad.setFont(new Font("Tahoma", 1, 11));
		lblEdad.setForeground(new Color(255, 255, 255));
		lblEdad.setText("Edad");

		lblGenero.setFont(new Font("Tahoma", 1, 11));
		lblGenero.setForeground(new Color(255, 255, 255));
		lblGenero.setText("Genero");

		radioHombre.setText("Hombre");

		radioMujer.setText("Mujer");

		txtEdad = new JTextField();

		GroupLayout glPanelCentral = new GroupLayout(panelCentral);
		glPanelCentral.setHorizontalGroup(glPanelCentral.createParallelGroup(Alignment.LEADING).addGroup(glPanelCentral
				.createSequentialGroup().addGap(22)
				.addGroup(glPanelCentral.createParallelGroup(Alignment.LEADING, false)
						.addGroup(glPanelCentral.createSequentialGroup().addComponent(lblEdad)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(glPanelCentral.createSequentialGroup().addComponent(lblGenero).addGap(46)
								.addComponent(radioHombre).addGap(18).addComponent(radioMujer)))
				.addContainerGap(41, Short.MAX_VALUE)));
		glPanelCentral.setVerticalGroup(glPanelCentral.createParallelGroup(Alignment.LEADING).addGroup(glPanelCentral
				.createSequentialGroup().addGap(35)
				.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE).addComponent(lblEdad).addComponent(
						txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE).addComponent(lblGenero)
						.addComponent(radioHombre).addComponent(radioMujer))
				.addContainerGap(160, Short.MAX_VALUE)));
		panelCentral.setLayout(glPanelCentral);

		txtFoto.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

		btnBuscarFoto.setText("Seleccionar foto");
		btnBuscarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					JFileChooser picchooser = new JFileChooser();
					picchooser.showOpenDialog(null);

					if (picchooser.getSelectedFile() != null) {

						File pic = picchooser.getSelectedFile();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg");
						picchooser.addChoosableFileFilter(filter);

						String path = pic.getAbsolutePath();
						BufferedImage img;
						img = ImageIO.read(picchooser.getSelectedFile());
						ImageIcon imageIcon = new ImageIcon(
								new ImageIcon(img).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
						txtFoto.setIcon(imageIcon);

						File image = new File(path);
						FileInputStream fis = new FileInputStream(image);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buff = new byte[1024];
						for (int readNum; (readNum = fis.read(buff)) != -1;) {
							baos.write(buff, 0, readNum);
						}
						byte[] userimage = baos.toByteArray();

					} else {
						JOptionPane.showMessageDialog(picchooser, "Ninguna foto fue seleccionada");
					}

				} catch (IOException ex) {
					Logger.getLogger(CreadorPasajeros.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});
		btnActualizar.setText("Actualizar");

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

		btnBuscar.setText("Buscar");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout
										.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
												.addComponent(lblDNIPasajero).addGap(29).addComponent(txtDNI,
														GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
										.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout
										.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
												.addGap(18).addGroup(layout.createParallelGroup(Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE,
																		156, GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(btnCancelar,
																		GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(layout
																.createSequentialGroup().addGap(337)
																.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE,
																		157, GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup()
																.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(txtFoto,
																		GroupLayout.PREFERRED_SIZE, 250,
																		GroupLayout.PREFERRED_SIZE))))
										.addGroup(layout.createSequentialGroup().addGap(30).addComponent(btnBuscar,
												GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(20, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap(25, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblDNIPasajero)
								.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addGap(38)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addGap(25)
										.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtFoto, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
												.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(18)
										.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(49)));
		getContentPane().setLayout(layout);

		pack();

	}

}
