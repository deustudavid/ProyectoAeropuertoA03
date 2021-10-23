import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual;
	private JTextField textCorreo;
	private JPasswordField textContrasenia;
	
	
 
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
				ventanaActual=this;
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 920, 300);
				contentPane = new JPanel();
				contentPane.setBackground(Color.BLACK);
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				setContentPane(contentPane);
				
				JPanel panelSur = new JPanel();
				panelSur.setBackground(Color.GRAY);
				contentPane.add(panelSur, BorderLayout.SOUTH);
				
				JButton btnIniciarSesionAdministrador = new JButton("INICIAR SESION ADMINISTRADOR");
				panelSur.add(btnIniciarSesionAdministrador);
				
				JButton btnRegistrarAdministrador = new JButton("REGISTRAR ADMINISTRADOR");
				panelSur.add(btnRegistrarAdministrador);
				btnRegistrarAdministrador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				
				JButton btnRegistrarAzafato = new JButton("REGISTRAR AZAFATO");
				panelSur.add(btnRegistrarAzafato);
				
				JButton btnIniciarSesionAzafato = new JButton("INICIAR SESION AZAFATO");
				panelSur.add(btnIniciarSesionAzafato);
				
				JPanel panelCentral = new JPanel();
				contentPane.add(panelCentral, BorderLayout.CENTER);
				panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblCorreo = new JLabel(" Introduce tu correo:");
				lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
				lblCorreo.setBackground(Color.DARK_GRAY);
				panelCentral.add(lblCorreo);
				
				textCorreo = new JTextField();
				textCorreo.setColumns(10);
				panelCentral.add(textCorreo);
				
				JLabel lblContrasenia = new JLabel(" Introduce la contrasenia:");
				lblContrasenia.setBackground(Color.DARK_GRAY);
				panelCentral.add(lblContrasenia);
				
				textContrasenia = new JPasswordField();
				textContrasenia.setText("");
				textContrasenia.setColumns(10);
				panelCentral.add(textContrasenia);
				btnIniciarSesionAdministrador.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				setVisible(true);
			}

		
}

		
	
	


