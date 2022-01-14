package ventanas;

import java.awt.*;
import java.awt.event.*;
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
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
public class CreadorPasajeros extends JInternalFrame {

	private JButton btnBuscarFoto;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblTituloVentana;
	private JLabel lblEdad;
	private JLabel lblGenero;
	private JPanel panelIzquierda;
	private JPanel panelCentral;
	private JRadioButton radioHombre;
	private JRadioButton radioMujer;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JLabel txtFoto;
	private JTextField txtEdad;
	private JScrollPane jScrollPane1;
    private JTextArea txtDireccion ;
    
    private ImageIcon imagenCancelar;
	private ImageIcon imagenGuardar;
	
	private static String dni;
	private static String nom;
	private static String apellido;
	private static String dir;
	private static String rutaFotoElegida;
	private static int edadNumerica;
	private static int edad; 
	private static int tfno;
	private static boolean correctoTelefono ,correctoApellido , correctoDireccion, correctoDni ,correctoEdad , correctoNombre;
	private JLabel lblMensajeNombre;
	private JLabel lblMensajeApellido;
	private JLabel lblMensajeDNI;
	private JLabel lblMensajeTfno;
	private JLabel lblMensajeDireccion;
	private JLabel lblMensajeEdad;
	
	private static Connection con;
	
	public CreadorPasajeros() {
		con =null;
		
		imagenCancelar = new ImageIcon("img/Cancelar.png");
		
		rutaFotoElegida="fotos/empty.png";
		edadNumerica=999;
		dni="";
		nom="";
		apellido="";
		dir="";
		edad=1;
		tfno=111111111;
		correctoApellido=false;
		correctoTelefono=false;
		correctoDireccion=false;
		correctoDni=false;
		correctoEdad=false;
		correctoNombre=false;
		
		jScrollPane1 = new JScrollPane();
	    txtDireccion = new JTextArea();
	    txtDireccion.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyReleased(KeyEvent e) {
	    		String erDireccion = "^[A-Z]{1}.*";//que  empieze por letra minuscula,luego lo que sea
				String dirIntroducida= txtDireccion.getText();
				  correctoDireccion = dirIntroducida.matches(erDireccion);
				if (correctoDireccion) {
					lblMensajeDireccion.setText("*");
					 dir= txtDireccion.getText();
				}else {
					lblMensajeDireccion.setText("Empieza por mayúscula");
				}
	    	}
	    });
		imagenGuardar = new ImageIcon("img/guardar.png");
	
		
		
		

		panelIzquierda = new JPanel();
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		lblDni = new JLabel();
		lblTelefono = new JLabel();
		lblDireccion = new JLabel();
		
		 txtDireccion.setColumns(20);
	        txtDireccion.setRows(5);
	        jScrollPane1.setViewportView(txtDireccion);
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erApellido = "^([A-Z]{1}[a-z]+[ ]?){1,2}$";//empieza por una letra mayuscula seguida de una minuscula como minimo. Se permite un espacio, por si pones dos apellidos
				String apellidoIntroducido = txtApellido.getText();
				 correctoApellido = apellidoIntroducido.matches(erApellido);
				if (correctoApellido) {
					lblMensajeApellido.setText("*");
					 apellido = txtApellido.getText();
				}else {
					lblMensajeApellido.setText("Empieza por mayuscula + minuscula");
				}
				
			}
		});
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erNombre = "^([A-Z]{1}[a-z]+[ ]?){1,2}$";//empieza por una letra mayuscula seguida de una minuscula como minimo. Se permite un espacio, por si tienes dos nombres
				String nombreIntroducido = txtNombre.getText();
				 correctoNombre = nombreIntroducido.matches(erNombre);
				if (correctoNombre) {
					lblMensajeNombre.setText("*");
					nom = txtNombre.getText();
				} else {
					lblMensajeNombre.setText("Empieza por mayuscula + minuscula");
				}
				
			}
		});
		txtDni = new JTextField();
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erdni = "^[0-9]{8}[A-Z]$";// 8 digitos seguidos de una letra mayuscula
				String dniIntroducido = txtDni.getText();
				 correctoDni = dniIntroducido.matches(erdni);
				if (correctoDni) {
					lblMensajeDNI.setText("*");
					 dni = txtDni.getText();
				
				} else {
					lblMensajeDNI.setText("8 dígitos seguidos de letra mayúscula");
				}
			}
		});
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String erTelefono = "^[0-9]{9}$";//123456789
				String telefonoIntroducido= txtTelefono.getText();
				correctoTelefono = telefonoIntroducido.matches(erTelefono);
				if (correctoTelefono) {
					lblMensajeTfno.setText("*");
					 tfno= Integer.parseInt(txtTelefono.getText());
				}else {
					lblMensajeTfno.setText("9 dígitos");

				}
			}
		});
		lblTituloVentana = new JLabel();
		panelCentral = new JPanel();
		lblEdad = new JLabel();
		lblGenero = new JLabel();
		radioHombre = new JRadioButton();
		radioMujer = new JRadioButton();
		txtFoto = new JLabel();
		btnBuscarFoto = new JButton();
		btnGuardar = new JButton();
		btnCancelar = new JButton();
		btnCancelar.setIcon(imagenCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		glPanelIzquierda.setHorizontalGroup(
			glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup()
					.addGap(26)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addComponent(lblApellido)
							.addGap(46)
							.addComponent(txtApellido, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDireccion)
								.addComponent(lblTelefono)
								.addComponent(lblDni))
							.addGap(38)
							.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(glPanelIzquierda.createSequentialGroup()
									.addGap(1)
									.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTelefono, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
										.addComponent(txtDni, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))))
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(47)
							.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMensajeDireccion, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
								.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMensajeTfno, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
									.addComponent(lblMensajeDNI, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
									.addComponent(lblMensajeApellido, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
							.addGap(22))
						.addGroup(glPanelIzquierda.createSequentialGroup()
							.addComponent(lblMensajeNombre, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		glPanelIzquierda.setVerticalGroup(
			glPanelIzquierda.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelIzquierda.createSequentialGroup()
					.addGap(23)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMensajeNombre))
					.addGap(8)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(lblMensajeApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMensajeDNI))
					.addGap(24)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMensajeTfno))
					.addGap(18)
					.addGroup(glPanelIzquierda.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireccion)
						.addGroup(glPanelIzquierda.createParallelGroup(Alignment.BASELINE)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMensajeDireccion)))
					.addContainerGap(206, Short.MAX_VALUE))
		);
	        panelIzquierda.setLayout(glPanelIzquierda);

		panelIzquierda.setLayout(glPanelIzquierda);

		lblTituloVentana.setFont(new Font("Tahoma", 1, 18));
		lblTituloVentana.setText("Nuevo pasajero");

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
				
				String erEdad = "^[1-9]{1}[0-9]{0,2}$";//3 digitos como mucho, sin empezar por 0
				String edadIntroducida= txtEdad.getText();
				edadNumerica=Integer.parseInt(txtEdad.getText());
				correctoEdad = edadIntroducida.matches(erEdad);
				if (correctoEdad && edadNumerica<=130) {
					lblMensajeEdad.setText("*");
					 edad= Integer.parseInt(txtEdad.getText());
				} else{
					lblMensajeEdad.setText("No comienza en 0; 3 dígitos; Edad max=130.");
				}
			}
		});
		
		lblMensajeEdad = new JLabel();
		lblMensajeEdad.setText("*");
		lblMensajeEdad.setForeground(Color.ORANGE);
		lblMensajeEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		
	
		
		

		GroupLayout glPanelCentral = new GroupLayout(panelCentral);
		glPanelCentral.setHorizontalGroup(
			glPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelCentral.createSequentialGroup()
					.addGap(22)
					.addGroup(glPanelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMensajeEdad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, glPanelCentral.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGenero)
							.addGap(18)
							.addComponent(radioHombre)
							.addGap(18)
							.addComponent(radioMujer))
						.addGroup(Alignment.LEADING, glPanelCentral.createSequentialGroup()
							.addComponent(lblEdad)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addGap(41))
		);
		glPanelCentral.setVerticalGroup(
			glPanelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelCentral.createSequentialGroup()
					.addGap(21)
					.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEdad)
						.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblMensajeEdad)
					.addGap(13)
					.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGenero)
						.addComponent(radioHombre)
						.addComponent(radioMujer))
					.addContainerGap(163, Short.MAX_VALUE))
		);
		panelCentral.setLayout(glPanelCentral);

		txtFoto.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

		btnBuscarFoto.setText("Seleccionar foto");
		btnBuscarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					JFileChooser picchooser = new JFileChooser("fotos");
					picchooser.showOpenDialog(null);

					if (picchooser.getSelectedFile() != null) {

						File pic = picchooser.getSelectedFile();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg", "JPG & JFIF", "jpg", "jfif");
						picchooser.addChoosableFileFilter(filter);

						rutaFotoElegida = pic.getAbsolutePath();
						BufferedImage img;
						img = ImageIO.read(picchooser.getSelectedFile());
						ImageIcon imageIcon = new ImageIcon(
								new ImageIcon(img).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
						txtFoto.setIcon(imageIcon);

						
					} else {
						JOptionPane.showMessageDialog(picchooser, "Ninguna foto fue seleccionada");
					}

				} catch (IOException ex) {
					Logger.getLogger(CreadorPasajeros.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});
// comprobaciones de los datos introducidos por el usuario, antes de poder pulsar "Guardar"
		
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon(imagenGuardar);
		
		
		
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
									
					if (correctoTelefono && correctoApellido && correctoDireccion && correctoDni && correctoEdad && edadNumerica<=130 && correctoNombre) {
						
							try {
								con = BD.initBD("Aeropuerto.db");
							} catch (DBException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
								try {
									if (!BD.existePasajero(con, dni) ) {
										BD.insertarPasajero(con, dni, nom, apellido, edad, tfno, dir, rutaFotoElegida);
										BD.InsertarPasajeroEnFichero(con, dni, nom, apellido, edad, tfno, dir, rutaFotoElegida);
										JOptionPane.showMessageDialog(null, "Pasajero creado correctamente");
										BD.closeBD(con);
									}else {
										BD.closeBD(con);
										JOptionPane.showMessageDialog(null, "Ya existe un pasajero con ese dni", "Error", JOptionPane.WARNING_MESSAGE);
									}
								} catch (HeadlessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DBException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						
					}else {
						JOptionPane.showMessageDialog(null, "Quedan campos vacíos o incorrectos.", "Error", JOptionPane.WARNING_MESSAGE);
					}
					
					
					
						

					


				}	
			});
	
		btnCancelar.setText("Cancelar");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(25)
							.addComponent(lblTituloVentana))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
									.addGap(12)
									.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(lblTituloVentana)
					.addGap(50)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(panelIzquierda, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addGap(54))))
		);
		getContentPane().setLayout(layout);

		pack();
	}
}
