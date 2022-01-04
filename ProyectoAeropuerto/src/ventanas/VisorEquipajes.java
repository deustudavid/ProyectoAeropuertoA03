package ventanas;
import bd.BD;
import bd.DBException;
import clases.Equipaje;
import clases.Pasajero;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class VisorEquipajes extends JFrame implements WindowListener {

	
	private static final long serialVersionUID = 1L;
	private static Connection con;

	private JScrollPane panelScroll;
	
	
	private DefaultTableModel equipajesTableModel; // modelo del JTable de pasajeros
	private JList<Pasajero> pasajeroJList; // JList de pasajeros
	private DefaultListModel<Pasajero> modelopasajeroJList;
	private DatosPasajeroPanel DatosPasajeroPanel; // panel de información
	private JTable equipajesTable ;
	private static ArrayList<Equipaje> t;

	public VisorEquipajes() {
		panelScroll = new JScrollPane();
		con = null;
		t=null;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(720, 524);
		setTitle("Visor de equipajes");
		
		addWindowListener(this);
		
		equipajesTableModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
		};
		String[] nombreColumnas = { "Descripcion", "Peso", "Largo", "Altura", "Anchura"};
		equipajesTableModel.setColumnIdentifiers(nombreColumnas);

		
		prepararListaPasajeros();
		prepararPanelInfoPasajero();
	}

	private void prepararPanelInfoPasajero() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel exportPanel = new JPanel();
		mainPanel.add(exportPanel, BorderLayout.SOUTH);
		
		DatosPasajeroPanel = new DatosPasajeroPanel();
		mainPanel.add(DatosPasajeroPanel, BorderLayout.NORTH);
		
		equipajesTable = new JTable(equipajesTableModel);
		
		panelScroll.setViewportView(equipajesTable);
		mainPanel.add(panelScroll, BorderLayout.CENTER);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}


	
	private void exit() {
		try {
			BD.closeBD(con);
			dispose();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void prepararListaPasajeros() {
		modelopasajeroJList = new DefaultListModel<>();
		pasajeroJList = new JList<Pasajero>(modelopasajeroJList);
		
		cargarListaPasajeros();
		JScrollPane scrollLista = new JScrollPane(pasajeroJList);
		
		
		
		JPanel pasajerosPanel = new JPanel();
		pasajerosPanel.setLayout(new BorderLayout());
		JPanel centeringPanel = new JPanel(new GridBagLayout());
		centeringPanel.add(new JLabel("Pasajeros"));
		pasajerosPanel.add(centeringPanel, BorderLayout.NORTH);
		pasajerosPanel.add(scrollLista, BorderLayout.CENTER);
		
	
		
		getContentPane().add(pasajerosPanel, BorderLayout.WEST);
		
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
			DatosPasajeroPanel.setpasajero(Pasajero);
			
				
				try {
					con = BD.initBD("Aeropuerto.db");
					t = BD.obtenerEquipajesDePasajero(con,Pasajero.getDni());
					
					equipajesTableModel.setRowCount(0);
					for (Equipaje maleta : t) {
						equipajesTableModel.addRow(new Object[] { maleta.getDescripcion(), maleta.getPeso(),
								maleta.getLargo(), maleta.getAltura(), maleta.getAnchura()});
					}
					equipajesTable.setModel(equipajesTableModel);
					
					panelScroll.setViewportView(equipajesTable);
					
				} catch (DBException e1) {
					JOptionPane.showMessageDialog(VisorEquipajes.this, e1.getMessage(), "Error al cargar equipajes", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				
				
				try {
					BD.closeBD(con);
				} catch (DBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} else {
			DatosPasajeroPanel.clear();
			equipajesTable.setModel(equipajesTableModel);
		}
	}
	
	private void cargarListaPasajeros() {
		
		
		try {
			con = BD.initBD("Aeropuerto.db");
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ArrayList<Pasajero> pasajeros=BD.obtenerPasajeros(con);
		for (Pasajero p :pasajeros) {
			modelopasajeroJList.addElement(p);
		}
		
		try {
			BD.closeBD(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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

