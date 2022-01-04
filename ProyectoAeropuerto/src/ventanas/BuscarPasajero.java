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

public class BuscarPasajero extends javax.swing.JInternalFrame {
//
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

	private ImageIcon imagenCancelar;

	private ImageIcon imagenGuardar;
	private ImageIcon imagenBuscar;

	private static Connection con;
	private static String dniAbuscar;
	private static int edadNumerica;
	private static boolean correctoTelefono, correctoApellido, correctoDireccion, correctoDni, correctoEdad,
			correctoNombre;
	private JLabel lblMensajeNombre;
	private JLabel lblMensajeApellido;
	private JLabel lblMensajeDNI;
	private JLabel lblMensajeTfno;
	private JLabel lblMensajeDireccion;
	private JLabel lblMensajeEdad;
	private boolean seHaPulsadoBuscar;

	public BuscarPasajero() {

		imagenCancelar = new ImageIcon("img/Cancelar.png");
		con = null;
		dniAbuscar = "";
		edadNumerica = 1;
		correctoApellido = true;
		correctoTelefono = true;
		correctoDireccion = true;
		correctoDni = true;
		correctoEdad = true;
		correctoNombre = true;
		seHaPulsadoBuscar = false;
		imagenGuardar = new ImageIcon("img/guardar.png");
		imagenBuscar = new ImageIcon("img/lupa.png");

		panelIzquierda = new JPanel();
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		lblDni = new JLabel();
		lblTelefono = new JLabel();
		lblDireccion = new JLabel();
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erApellido = "^([A-Z]{1}[a-z]+[ ]?){1,2}$";// empieza por una letra mayuscula seguida de una
																	// minuscula como minimo. Se permite un espacio, por
																	// si pones dos apellidos
				String apellidoIntroducido = txtApellido.getText();
				correctoApellido = apellidoIntroducido.matches(erApellido);
				if (correctoApellido) {
					lblMensajeApellido.setText("*");
					txtApellido.getText();
				} else {
					correctoApellido = false;
					lblMensajeApellido.setText("Empieza por mayúscula");
				}

			}
		});

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erNombre = "^([A-Z]{1}[a-z]+[ ]?){1,2}$";// empieza por una letra mayuscula seguida de una
																// minuscula como minimo. Se permite un espacio, por si
																// tienes dos nombres
				String nombreIntroducido = txtNombre.getText();
				correctoNombre = nombreIntroducido.matches(erNombre);
				if (correctoNombre) {
					lblMensajeNombre.setText("*");
					txtNombre.getText();
				} else {
					correctoNombre = false;
					lblMensajeNombre.setText("Empieza por mayúscula");
				}

			}
		});

		txtDni = new JTextField();

		txtDni.setEnabled(false);
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erTelefono = "^[0-9]{9}$";// 123456789
				String telefonoIntroducido = txtTelefono.getText();
				correctoTelefono = telefonoIntroducido.matches(erTelefono);
				if (correctoTelefono) {
					lblMensajeTfno.setText("*");
					Integer.parseInt(txtTelefono.getText());
				} else {
					correctoTelefono = false;
					lblMensajeTfno.setText("9 dígitos");

				}

			}
		});
		jScrollPane1 = new JScrollPane();
		txtDireccion = new JTextArea();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erDireccion = "^[A-Z]{1}.*";// que empieze por letra minuscula,luego lo que sea
				String dirIntroducida = txtDireccion.getText();
				correctoDireccion = dirIntroducida.matches(erDireccion);
				if (correctoDireccion) {
					lblMensajeDireccion.setText("*");
					txtDireccion.getText();
				} else {
					correctoDireccion = false;
					lblMensajeDireccion.setText("Empieza por mayúscula");
				}

			}
		});
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
					if (BD.buscarPasajero(con, dniAbuscar) != null) {
						seHaPulsadoBuscar = true;
						txtApellido.setEnabled(true);
						txtNombre.setEnabled(true);
						txtEdad.setEnabled(true);
						txtTelefono.setEnabled(true);
						txtDireccion.setEnabled(true);
						Pasajero p = BD.buscarPasajero(con, dniAbuscar);
						txtNombre.setText(p.getNombre());
						txtApellido.setText(p.getApellido());
						txtDni.setText(p.getDni());
						int tf = p.getTelefono();
						txtTelefono.setText(String.valueOf(tf));
						txtDireccion.setText(p.getDireccion());
						int edad = p.getEdad();
						txtEdad.setText(String.valueOf(edad));
					} else {
						JOptionPane.showMessageDialog(null, "No se ha encontrado el pasajero con ese dni", "Error",
								JOptionPane.WARNING_MESSAGE);

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

		lblMensajeNombre = new JLabel();
		lblMensajeNombre.setText("*");
		lblMensajeNombre.setForeground(Color.ORANGE);
		lblMensajeNombre.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblMensajeApellido = new JLabel();
		lblMensajeApellido.setText("*");
		lblMensajeApellido.setForeground(Color.ORANGE);
		lblMensajeApellido.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblMensajeDNI = new JLabel();
		lblMensajeDNI.setText("*");
		lblMensajeDNI.setForeground(Color.ORANGE);
		lblMensajeDNI.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblMensajeTfno = new JLabel();
		lblMensajeTfno.setText("*");
		lblMensajeTfno.setForeground(Color.ORANGE);
		lblMensajeTfno.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblMensajeDireccion = new JLabel();
		lblMensajeDireccion.setText("*");
		lblMensajeDireccion.setForeground(Color.ORANGE);
		lblMensajeDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));

		GroupLayout glPanelIzquierda = new GroupLayout(panelIzquierda);
		glPanelIzquierda.setHorizontalGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup().addGap(26).addGroup(glPanelIzquierda
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, glPanelIzquierda.createSequentialGroup().addGroup(glPanelIzquierda
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(glPanelIzquierda.createSequentialGroup().addComponent(lblNombre).addGap(47)
										.addComponent(txtNombre))
								.addGroup(glPanelIzquierda.createSequentialGroup()
										.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDireccion).addComponent(lblTelefono)
												.addComponent(lblDni))
										.addGap(38)
										.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING, false)
												.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtTelefono).addComponent(txtDni))))
								.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
										.addGroup(glPanelIzquierda.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
												.addGroup(glPanelIzquierda.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblMensajeNombre, GroupLayout.PREFERRED_SIZE, 257,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMensajeDNI, GroupLayout.PREFERRED_SIZE, 254,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(glPanelIzquierda.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
														.addComponent(lblMensajeDireccion, GroupLayout.PREFERRED_SIZE,
																235, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMensajeTfno, GroupLayout.PREFERRED_SIZE, 254,
																GroupLayout.PREFERRED_SIZE)))))
						.addGroup(Alignment.LEADING,
								glPanelIzquierda.createSequentialGroup().addComponent(lblApellido).addGap(48)
										.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 166,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMensajeApellido,
												GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		glPanelIzquierda.setVerticalGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup().addGap(37)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMensajeNombre))
						.addGap(22)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE).addComponent(lblApellido)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMensajeApellido))
						.addGap(18)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE).addComponent(lblDni)
								.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMensajeDNI))
						.addGap(18)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE).addComponent(lblTelefono)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMensajeTfno))
						.addGap(18)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING).addComponent(lblDireccion)
								.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMensajeDireccion)))
						.addGap(34)));
		panelIzquierda.setLayout(glPanelIzquierda);

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
		txtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erEdad = "^[1-9]{1}[0-9]{0,2}$";// 3 digitos como mucho, sin empezar por 0
				String edadIntroducida = txtEdad.getText();
				edadNumerica = Integer.parseInt(txtEdad.getText());
				correctoEdad = edadIntroducida.matches(erEdad);
				if (correctoEdad && edadNumerica >= 1 && edadNumerica <= 130) {
					lblMensajeEdad.setText("*");
					Integer.parseInt(txtEdad.getText());
				} else {
					correctoEdad = false;
					lblMensajeEdad.setText("No comienza en 0; 3 dígitos; Edad max=130.");
				}
			}
		});

		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (correctoTelefono && correctoApellido && correctoDireccion && correctoDni && correctoEdad
						&& edadNumerica >= 1 && edadNumerica <= 130 && correctoNombre && !txtDni.getText().isBlank()
						&& !txtNombre.getText().isBlank() && !txtApellido.getText().isBlank()
						&& !txtEdad.getText().isBlank() && !txtTelefono.getText().isBlank()
						&& !txtDireccion.getText().isBlank()) {

					try {
						con = BD.initBD("Aeropuerto.db");
					} catch (DBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						BD.modificarPasajero(con, dniAbuscar, txtNombre.getText(), txtApellido.getText(),
								Integer.parseInt(txtEdad.getText()), Integer.parseInt(txtTelefono.getText()),
								txtDireccion.getText());
						JOptionPane.showMessageDialog(null, "Pasajero actualizado correctamente");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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

		lblMensajeEdad = new JLabel();
		lblMensajeEdad.setText("*");
		lblMensajeEdad.setForeground(Color.ORANGE);
		lblMensajeEdad.setFont(new Font("Tahoma", Font.BOLD, 11));

		GroupLayout glPanelCentral = new GroupLayout(panelCentral);
		glPanelCentral.setHorizontalGroup(glPanelCentral.createParallelGroup(Alignment.LEADING).addGroup(glPanelCentral
				.createSequentialGroup().addGap(22)
				.addGroup(glPanelCentral.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMensajeEdad, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addGroup(glPanelCentral.createParallelGroup(Alignment.LEADING, false)
								.addGroup(glPanelCentral.createSequentialGroup().addComponent(lblEdad)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 165,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(glPanelCentral.createSequentialGroup().addComponent(lblGenero).addGap(26)
										.addComponent(radioHombre).addGap(26).addComponent(radioMujer))))
				.addContainerGap(29, Short.MAX_VALUE)));
		glPanelCentral.setVerticalGroup(glPanelCentral.createParallelGroup(Alignment.LEADING).addGroup(glPanelCentral
				.createSequentialGroup().addGap(35)
				.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE).addComponent(lblEdad).addComponent(
						txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblMensajeEdad).addGap(16)
				.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE).addComponent(lblGenero)
						.addComponent(radioHombre).addComponent(radioMujer))
				.addContainerGap(140, Short.MAX_VALUE)));
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
		Runnable hiloBuscarPulsado = new Runnable() {
			public void run() {
				while (!seHaPulsadoBuscar) {

					txtTelefono.setEnabled(false);
					txtDireccion.setEnabled(false);
					txtNombre.setEnabled(false);
					txtEdad.setEnabled(false);
					txtApellido.setEnabled(false);
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

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblDNIPasajero)
							.addGap(29)
							.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGap(337)
							.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDNIPasajero)
						.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(63)
							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtFoto, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
								.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGap(38)
							.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(49))
		);
		getContentPane().setLayout(layout);

		pack();

	}
}
