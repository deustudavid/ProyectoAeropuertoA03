package ventanas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import clases.Pasajero;

public class DatosPasajeroPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel infoApellido;
	private JLabel infoNombre;
	private JLabel infoEdad;
	private JLabel infoDireccion;
	private JLabel infoTelefono;

	public DatosPasajeroPanel() {
		new GridLayout(3, 2);
		
		Border border = BorderFactory.createTitledBorder("Informacion");
		setBorder(border);
		
		JPanel apellidoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel apellidoLabel = new JLabel("Apellido: ");
		infoApellido = new JLabel();
		
		apellidoPanel.add(apellidoLabel);
		apellidoPanel.add(infoApellido);
		
		add(apellidoPanel);
		
		JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nombreLabel = new JLabel("Nombre: ");
		infoNombre = new JLabel();
		
		nombrePanel.add(nombreLabel);
		nombrePanel.add(infoNombre);
		
		add(nombrePanel);
		
		JPanel telefonoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel telefonoLabel = new JLabel("Telefono: ");
		infoTelefono = new JLabel();
		
		telefonoPanel.add(telefonoLabel);
		telefonoPanel.add(infoTelefono);
		
		add(telefonoPanel);
		
		JPanel edadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel edadLabel = new JLabel("Edad: ");
		infoEdad = new JLabel();
		
		edadPanel.add(edadLabel);
		edadPanel.add(infoEdad);
		
		add(edadPanel);
		
		JPanel direccionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel direccionLabel = new JLabel("Direccion: ");
		infoDireccion = new JLabel();
		
		direccionPanel.add(direccionLabel);
		direccionPanel.add(infoDireccion);
		
		add(direccionPanel);
	}
	
	/**
	 * Se insertan los datos del pasajero recibido por parametro en sus campos correspondientes
	 * @param pasajero
	 */
	public void setpasajero(Pasajero pasajero) {
		infoNombre.setText(pasajero.getNombre());
		infoApellido.setText(pasajero.getApellido());
		infoEdad.setText(String.valueOf(pasajero.getEdad()));
		infoDireccion.setText(pasajero.getDireccion());
		infoTelefono.setText(String.valueOf(pasajero.getTelefono()));
	}
	
	/**
	 * Se borra el texto de los campos
	 */
	public void clear() {
		infoNombre.setText("");
		infoApellido.setText("");
		infoEdad.setText("");
		infoDireccion.setText("");
		infoTelefono.setText("");
	}
}

