package ventanas;
import bd.BD;
import bd.DBException;
import clases.Equipaje;
import clases.Pasajero;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class VisorEquipajes extends JFrame implements WindowListener {

	
	private static final long serialVersionUID = 1L;
	private static Connection con;

	private JScrollPane panelScroll;
	
	
	private DefaultTableModel equipajesTableModel; // modelo del JTable de pasajeros
	private JList<Pasajero> pasajeroJList; // JList de pasajeros
	private DefaultListModel<Pasajero> modelopasajeroJList;
	private DatosPasajeroPanel DatosPasajeroPanel; // panel de informacion
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
				
				return false;
			}
		};
		String[] nombreColumnas = { "NumEquipaje" ,"Descripcion", "Peso", "Largo", "Altura", "Anchura"};
		equipajesTableModel.setColumnIdentifiers(nombreColumnas);

		
		prepararListaPasajeros();
		prepararPanelInfoPasajero();
	}

	/**
	 * Prepara el panel sobre la tabla en el que se muestran los datos del usuario correspondiente
	 * al dni seleccionado en la JList de la izquierda
	 */
	private void prepararPanelInfoPasajero() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel exportPanel = new JPanel();
		mainPanel.add(exportPanel, BorderLayout.SOUTH);
		
		DatosPasajeroPanel = new DatosPasajeroPanel();
		mainPanel.add(DatosPasajeroPanel, BorderLayout.NORTH);
		
		equipajesTable = new JTable(equipajesTableModel);
		equipajesTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				DefaultTableModel df = (DefaultTableModel) equipajesTable.getModel();
				
				/*Solo la celda en la que el peso < 10 */
				if(column==2 && (double)df.getValueAt(row, 2)<10) {
					c.setBackground(Color.GREEN);
					c.setFont(new Font("Arial", Font.BOLD, 15));
				}else {
					c.setBackground(Color.WHITE);
				
				}
				
			
				return c;
			}
		});
		
		
		panelScroll.setViewportView(equipajesTable);
		mainPanel.add(panelScroll, BorderLayout.CENTER);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}


	/**
	 * cierra la conexion con la BBDD y la ventana
	 */
	private void exit() {
		try {
			BD.closeBD(con);
			dispose();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * prepara la JList con los dni de cada pasajero en la BBDD
	 */
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
	
	/**
	 * Actualiza la tabla con la informacion de los equipajes correspondientes
	 * al dni del pasajero que se haya seleccionado en la JList
	 */
	private void actualizarDatos() {
		if (pasajeroJList.getSelectedIndex() != -1) {
			Pasajero Pasajero = pasajeroJList.getSelectedValue();
			DatosPasajeroPanel.setpasajero(Pasajero);
			

				try {
					con = BD.initBD("Aeropuerto.db");
					t = BD.obtenerEquipajesDePasajero(con,Pasajero.getDni());
					
					equipajesTableModel.setRowCount(0);
					for (Equipaje maleta : t) {
						equipajesTableModel.addRow(new Object[] { maleta.getEquipajeNum(), maleta.getDescripcion(), maleta.getPeso(),
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
	
	
	/**
	 * se insertan en la JList todos los dni de los pasajeros en la BBDD
	 */
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

