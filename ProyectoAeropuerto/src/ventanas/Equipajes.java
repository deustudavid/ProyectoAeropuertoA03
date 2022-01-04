package ventanas;
import bd.BD;
import bd.DBException;
import clases.Equipaje;
import clases.Pasajero;
import clases.Ticket;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 * Esta clase contiene la ventana principal de la aplicación.
 *
 */
public class Equipajes extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection con;

	
	private PasajerosListModel pasajeroListModel; // modelo del JList de pasajeros
	private JList<Pasajero> pasajeroJList; // JList de pasajeros
	private PasajeroInfoPanel PasajeroInfoPanel; // panel de información
	private JButton exportButton; // boton para exportar información
	private JLabel totalStarsInfo; // label con información del número de estrellas totales
	private static ArrayList<Pasajero> arrayPasajeros;
	private JTable equipajesTable = new JTable(new EquipajesTableModel());

	public Equipajes() {
		con = null;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(720, 480);
		setTitle("Visor de equipajes");
		
		addWindowListener(this);
		

		

		
		// se prepara la UI
		
		prepareConstellationList();
		prepareMainPanel();
		
		// se carga la lista de constelaciones
		cargarListaPasajeros();
		
		setVisible(true);
	}

	private void prepareMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel exportPanel = new JPanel();		
		exportButton = new JButton("Exportar estadísticas");
		exportButton.setEnabled(false);
		
		exportPanel.add(exportButton);
		mainPanel.add(exportPanel, BorderLayout.SOUTH);
		
		PasajeroInfoPanel = new PasajeroInfoPanel();
		mainPanel.add(PasajeroInfoPanel, BorderLayout.NORTH);
		
	
		
		JScrollPane startTableScroll = new JScrollPane(equipajesTable); 
		mainPanel.add(startTableScroll, BorderLayout.CENTER);
		
		add(mainPanel, BorderLayout.CENTER);
	}


		
		
		
	
	

	
	private void exit() {
		try {
			BD.closeBD(con);
			System.exit(0);
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void prepareConstellationList() {
		pasajeroListModel = new PasajerosListModel();
		pasajeroJList = new JList<Pasajero>(pasajeroListModel);
		pasajeroJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		JScrollPane constellationScrollPane = new JScrollPane(pasajeroJList);
		
		JPanel pasajerosPanel = new JPanel();
		pasajerosPanel.setLayout(new BorderLayout());
		JPanel centeringPanel = new JPanel(new GridBagLayout());
		centeringPanel.add(new JLabel("Constelaciones"));
		pasajerosPanel.add(centeringPanel, BorderLayout.NORTH);
		pasajerosPanel.add(constellationScrollPane, BorderLayout.CENTER);
		
		JPanel totalMaletasPanel = new JPanel();
		JLabel totalMaletasLabel = new JLabel(" nº Maletas: ");
		totalStarsInfo = new JLabel();
		totalMaletasPanel.add(totalMaletasLabel);
		totalMaletasPanel.add(totalStarsInfo);
		
		pasajerosPanel.add(totalMaletasPanel, BorderLayout.SOUTH);
		
		add(pasajerosPanel, BorderLayout.WEST);
		
		pasajeroJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualizarDatos();
			}
		});
	}
	
	private void actualizarDatos() {
		if (pasajeroJList.getSelectedIndex() != -1) {
			Pasajero Pasajero = pasajeroJList.getSelectedValue();
			PasajeroInfoPanel.setpasajero(Pasajero);
			try {
				
				try {
					con = BD.initBD("Aeropuerto.db");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				List<Equipaje> maletasDelPasajero = BD.obtenerEquipajesDePasajero(con,Pasajero);
				equipajesTable.setModel(new EquipajesTableModel(maletasDelPasajero));
				
				try {
					BD.closeBD(con);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exportButton.setEnabled(true);
			} catch (DBException ex) {
				JOptionPane.showMessageDialog(Equipajes.this, ex.getMessage(), "Error al cargar equipajes", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			PasajeroInfoPanel.clear();
			exportButton.setEnabled(false);
			equipajesTable.setModel(new EquipajesTableModel());
		}
	}
	
	private void cargarListaPasajeros() {
		
		try {
			con = BD.initBD("Aeropuerto.db");
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for (Pasajero Pasajero : BD.obtenerPasajeros(con)) {
			pasajeroListModel.addElement(Pasajero);
		}
		
		try {
			BD.closeBD(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Equipajes();
			}
		
		});
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		exit();		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
