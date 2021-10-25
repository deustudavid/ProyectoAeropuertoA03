package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.BD;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JPanel panelSur;
	private JPanel panelCentral;

	private JLabel lblUsuario;
	private JLabel lblContrasenia;

	private JFrame ventanaActual;

	private JTextField textNombre;

	private JPasswordField textContrasenia;

	private JButton btnIniciarSesionAdministrador;
	private JButton btnRegistrarAdministrador;
	private JButton btnRegistrarAzafato;
	private JButton btnIniciarSesionAzafato;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio vi = new VentanaInicio();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {

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

		textNombre = new JTextField();
		textNombre.setColumns(10);
		panelCentral.add(textNombre);

		lblContrasenia = new JLabel(" Introduce la contrasenia:");
		lblContrasenia.setBackground(Color.DARK_GRAY);
		panelCentral.add(lblContrasenia);

		textContrasenia = new JPasswordField();
		textContrasenia.setText("");
		textContrasenia.setColumns(10);
		panelCentral.add(textContrasenia);
		
		/* EVENTOS */
		
		btnIniciarSesionAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		btnRegistrarAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = textNombre.getText();
				
				char [] ac = textContrasenia.getPassword();
				String c = String.valueOf(ac);
				//String c = textContrasenia.getText();
				Connection con = BD.initBD("newton.db");
				int resul = BD.obtenerUsuario(con, n, c);
				if(resul != 0) {
					JOptionPane.showMessageDialog(null, "ERROR! Ese nombre de administrador ya existe");
				}else {
					BD.insertarUsuario(con, n, c);
					JOptionPane.showMessageDialog(null, "Administrador registrado correctamente");
				}
				BD.closeBD(con);
				textNombre.setText("");
				textContrasenia.setText("");
			}
		});
		
		btnIniciarSesionAzafato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearPasajero();//es para probar
				ventanaActual.setVisible(false);
			
				
			}
			
		});
		
		btnRegistrarAzafato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = textNombre.getText();
				char [] ac = textContrasenia.getPassword();
				String c = String.valueOf(ac);
				//String c = textContrasenia.getText();
				Connection con = BD.initBD("newton.db");
				int resul = BD.obtenerUsuario(con, n, c);
				if(resul != 0) {
					JOptionPane.showMessageDialog(null, "ERROR! Ese nombre de azafato ya existe");
				}else {
					BD.insertarUsuario(con, n, c);
					JOptionPane.showMessageDialog(null, "Azafato registrado correctamente");
				}
				BD.closeBD(con);
				textNombre.setText("");
				textContrasenia.setText("");
				
			}
		});
		
				
			
			
		
		setVisible(true);
	
	}
}
