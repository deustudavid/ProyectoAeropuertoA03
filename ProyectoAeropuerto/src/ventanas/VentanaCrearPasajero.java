package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaCrearPasajero extends JFrame {

	private JPanel contentPane, panelSur;
	private JButton btnVolver;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelCentral;
	private JLabel lblDni;
	private JTextField textDni;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblEdad;
	private JTextField textEdad;
	private JButton btnRegistrarPersona;

	/**
	 * Create the frame.
	 */
	 public VentanaCrearPasajero() {
		
	 
		setTitle("VENTANA PARA CREAR PASAJEROS");
		//ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		btnRegistrarPersona = new JButton("REGISTRAR PERSONA");
		btnRegistrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String erdni = "[0-9]{8}[A-Z]";
				String d = textDni.getText();
				boolean correctoDni = Pattern.matches(erdni, d);
				if(correctoDni) {
					String n = textNombre.getText();
					int ed = Integer.parseInt(textEdad.getText());
		
					JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
					vaciarCampos();
				}else {
					JOptionPane.showMessageDialog(null, "El dni no es correcto", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelSur.add(btnRegistrarPersona);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblDni = new JLabel("Introduce tu dni:");
		panelCentral.add(lblDni);
		
		textDni = new JTextField();
		panelCentral.add(textDni);
		textDni.setColumns(10);
		
		lblNombre = new JLabel("Introduce tu nombre:");
		panelCentral.add(lblNombre);
		
		textNombre = new JTextField();
		panelCentral.add(textNombre);
		textNombre.setColumns(10);
		
		lblEdad = new JLabel("Introduce tu edad:");
		panelCentral.add(lblEdad);
		
		textEdad = new JTextField();
		panelCentral.add(textEdad);
		textEdad.setColumns(10);
		
		/*EVENTOS*/
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		setVisible(true);
	}
	
	private void vaciarCampos() {
		textDni.setText("");
		textNombre.setText("");
		textEdad.setText("");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
