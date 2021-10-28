package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ventanas.VentanaInicioSesionAdm;
import ventanas.VentanaInicioSesionAzafato;
import ventanas.VentanaRegistrarAdm;
import ventanas.VentanaRegistrarAzafato;
import ventanasNuevas.VentanaPadre;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
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

	private JFrame ventanaActual;

	private JTextField textUsuario;

	private JPasswordField textContrasenia;

	private JProgressBar progressBar;

	private JButton btnIniciarSesionAdministrador;
	private JButton btnRegistrarAdministrador;
	private JButton btnRegistrarAzafato;
	private JButton btnIniciarSesionAzafato;
	private JButton btnCerrar;

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

		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		panelCentral.add(textUsuario);

		lblContrasenia = new JLabel(" Introduce la contrasenia:");
		lblContrasenia.setBackground(Color.DARK_GRAY);
		panelCentral.add(lblContrasenia);

		labelCerrar = new JLabel("Cerrando ventana...");
		labelCerrar.setBounds(278, 360, 279, 14);
		labelCerrar.setVisible(false);

		textContrasenia = new JPasswordField();
		textContrasenia.setText("");
		textContrasenia.setColumns(10);
		panelCentral.add(textContrasenia);

		progressBar = new JProgressBar(0, 100);
		progressBar.setBounds(415, 360, 146, 14);
		progressBar.setVisible(false);

		btnCerrar = new JButton("Cerrar");
		panelSur.add(btnCerrar);

		btnIniciarSesionAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioSesionAdm();
				ventanaActual.dispose();
				new VentanaPadre();
			}
		});

		btnIniciarSesionAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioSesionAzafato();
				ventanaActual.dispose();
				new VentanaPadre();

			}

		});

		btnRegistrarAdministrador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistrarAdm();
				ventanaActual.dispose();

			}

		});

		btnRegistrarAzafato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistrarAzafato();
				ventanaActual.dispose();

			}

		});

		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.getContentPane().add(labelCerrar);
				ventanaActual.getContentPane().add(progressBar);

				Thread hilo = new Thread(new Runnable() {

					@Override
					public void run() {
						labelCerrar.setVisible(true);
						progressBar.setVisible(true);

						for (int i = 0; i <= 100; i++) {
							progressBar.setValue(i);
							try {
								Thread.sleep(7);
							} catch (InterruptedException el) {
								el.printStackTrace();
							}
						}

						labelCerrar.setVisible(false);
						progressBar.setVisible(false);
						ventanaActual.dispose();

					}
				});

				hilo.start();
			}

		});

		setVisible(true);
	}

}
