package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class VentanaInicioSesionAdm extends JFrame{
	private JPanel panelUsuario;
	private JTextField usuario;
	private JLabel labelUsuario = new JLabel("        Usuario: ");

	private JPanel panelcontrase�a;
	private JPasswordField contrase�a;
	private JLabel labelcontrase�a = new JLabel("  Contrase�a: ");

	private JButton aceptar;
	private JButton crearUsuario;
	private JPanel panelBotonera;

	private JPanel panelDatos;

	private JPanel panelDecorativo;
	private JLabel labelDecorativo;


	private static String usuarioEscogido;



	public void VentanaLoginAdministrador() {
		setTitle("Inicio");
		setSize(310,260);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		

		panelDecorativo = new JPanel();
		panelDecorativo.setLayout(new GridLayout(1,1));
		panelDecorativo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); //TOC
		
		panelDecorativo.setBackground(Color.WHITE);
		panelDecorativo.add(labelDecorativo);
		add(panelDecorativo);


		panelDatos = new JPanel();
		panelDatos.setLayout(new GridLayout(3,1));
		panelDatos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelDatos.setSize(new Dimension(100,100));
		panelDatos.setBackground(new Color(117, 162, 214));
		panelDatos.setPreferredSize(new Dimension(280,100));
		add(panelDatos);

		panelUsuario = new JPanel();
		usuario = new JTextField();
		usuario.setPreferredSize(new Dimension(100,25));
		panelUsuario.add(labelUsuario);
		panelUsuario.add(usuario);
		panelUsuario.setBackground(new Color(117, 162, 214));
		panelDatos.add(panelUsuario);

		panelcontrase�a = new JPanel();
		contrase�a = new JPasswordField();
		contrase�a.setPreferredSize(new Dimension(100,25));
		panelcontrase�a.add(labelcontrase�a);
		panelcontrase�a.add(contrase�a);
		panelcontrase�a.setBackground(new Color(117, 162, 214));
		panelDatos.add(panelcontrase�a);

		panelBotonera = new JPanel();
		aceptar = new JButton("Iniciar Sesi�n");
		crearUsuario = new JButton("Crear Nuevo Usuario");
		aceptar.setEnabled(false);
		crearUsuario.setEnabled(false);
		panelBotonera.add(aceptar);
		panelBotonera.add(crearUsuario);
		panelBotonera.setBackground(new Color(117, 162, 214));
		panelDatos.add(panelBotonera);

		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comprobar()) {
					panelDatos.removeAll();
					JLabel bienvenidaLabel = new JLabel("Bienvenid@ " + usuarioEscogido + ".");
					bienvenidaLabel.setHorizontalAlignment(JLabel.CENTER);
					panelDatos.add(bienvenidaLabel);
					JButton botonInicio = new JButton("Iniciar");
					panelDatos.add(botonInicio);
					botonInicio.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
							new VentanaAdministrador();					
							

						}
							
					});

					JButton botonEliminar = new JButton("Eliminar Usuario");
					botonEliminar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int selectedOption = JOptionPane.showConfirmDialog(null,  "�Seguro que quieres borrar el usuario: " + usuarioEscogido + "?", "Elige:", JOptionPane.YES_NO_OPTION);
							if(selectedOption == 0) {
								eliminarUsuario();
								dispose();
								JOptionPane.showMessageDialog(null, "Se ha eliminado el usuario " + usuarioEscogido + ".");
								new VentanaAdministrador();
							} 

						}
					});
					panelDatos.add(botonEliminar);


					repaint();
					validate();					

				} else {
					JOptionPane.showMessageDialog(null, "Usuario y/o contrase�a incorrectas");
					
				}
			}
		});

		crearUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearUsuario();

			}
		});


		usuario.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}
		});

		contrase�a.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}
		});		


		setVisible(true);		
	}
	

	public void changed() {
		if (contrase�a.getText().equals("") || contrase�a.getText().contains(" ") || usuario.getText().equals("") || usuario.getText().contains(" ")){
			aceptar.setEnabled(false);
			crearUsuario.setEnabled(false);
		}
		else {
			aceptar.setEnabled(true);
			crearUsuario.setEnabled(true);
		}
	}

	public boolean comprobar() {
		try {
			
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:Administrador.db");
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from usuarios");

			
			while(rs.next()) {
				String usuarioBD = rs.getString("usuario");
				String contrase�aBD = rs.getString("contrase�a");

				if (usuarioBD.equals(usuario.getText()) && contrase�aBD.equals(contrase�a.getText())) {
					usuarioEscogido = usuarioBD;
					
					
					return true;
				}

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}


	public void crearUsuario() {

		try {
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:Administrador.db");
			Statement stmt = (Statement) conn.createStatement();

			ResultSet rs = stmt.executeQuery("Select * from usuarios");

			ArrayList<String> usuariosBD = new ArrayList<>();

			while(rs.next()) {
				String usuarioBD = rs.getString("usuario");
				String contrase�aBD = rs.getString("contrase�a");
				usuariosBD.add(usuarioBD);
			}

			if (!usuariosBD.contains(usuario.getText())) {
				String instruccion = "INSERT INTO usuarios (usuario, contrase�a) VALUES ('" + usuario.getText()  + "','" + contrase�a.getText() + "');";
				File directorioPersonal = new File("clientes\\" + usuario.getText() + "\\galeria\\");
				directorioPersonal.mkdirs();
				JOptionPane.showMessageDialog(null, "Usuario creado con �xito");
				int rs2 = stmt.executeUpdate(instruccion);
				
			} else {
				JOptionPane.showMessageDialog(null, "Este usuario ya existe");
			}
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Se ha ejecutado el metodo de crear usuario a pesar del error");
		}	

	} 


	public void eliminarUsuario() {
		try {
			
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:Administrador.db");
			Statement stmt = (Statement) conn.createStatement();
			

			String instruccion = "Delete from usuarios where usuario='" + usuarioEscogido + "';" ;
			int rs = stmt.executeUpdate(instruccion);

			File file = new File("clientes/" + usuarioEscogido + "/");

			deleteDirectory(file);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean deleteDirectory(File directoryToBeDeleted) {	//METODO RECURSIVO
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	
	
	
	public static String getUsuarioEscogido() {
		return usuarioEscogido;
	}
	public static void setUsuarioEscogido(String usuarioEscogido) {
		VentanaInicioSesionAdm.usuarioEscogido = usuarioEscogido;
	}


}
