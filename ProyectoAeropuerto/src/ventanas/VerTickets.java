package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import bd.DBException;
import clases.Ticket;
import javax.swing.GroupLayout.Alignment;

public class VerTickets extends JInternalFrame {

	private JButton btnCancelar;
	private JButton btnEliminarTicket;
	private JScrollPane panelScroll;
	private JTable tabla;
	private DefaultTableModel modeloTablaTickets;
	private ArrayList<Ticket> t;
	private static Connection con;
	private ImageIcon imagenBorrar;
	private ImageIcon imagenCancelar;

	public VerTickets() {

		t = null;
		con = null;
		imagenBorrar = new ImageIcon("img/papelera.png");
		imagenCancelar = new ImageIcon("img/Cancelar.png");
		panelScroll = new JScrollPane();
		btnCancelar = new JButton();

		modeloTablaTickets = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
		};
		String[] nombreColumnas = { "TicketNum", "ID Vuelo", "DNI Pasajero", "Clase", "Precio", "nº Asientos",
				"Fecha" };
		modeloTablaTickets.setColumnIdentifiers(nombreColumnas);

		try {
			con = BD.initBD("Aeropuerto.db");
			t = BD.obtenerTickets(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			BD.closeBD(con);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Ticket ticket : t) {
			modeloTablaTickets.addRow(new Object[] { ticket.getTicketNum(), ticket.getIDVuelo(),
					ticket.getDNIPasajero(), ticket.getClase().toString(), ticket.getPrecio(), ticket.getAsientos(),
					ticket.getFecha() });
		}

		tabla = new JTable(modeloTablaTickets);
		tabla.setEnabled(false);
		tabla.setBackground(SystemColor.info);
		panelScroll.setViewportView(tabla);
		
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				

				double precio = (double)modeloTablaTickets.getValueAt(row, 4);
					if(precio <= 50) {
						c.setBackground(Color.GREEN);
					}else if (precio >50 && precio <=300) {
						c.setBackground(Color.CYAN);
					}else {
						c.setBackground(Color.BLACK);
						c.setForeground(Color.ORANGE);
					}
				
				return c;
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.setIcon(imagenCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				boolean resultadoAdministradorActivo = VentanaAdministrador.VentanaAdminEstaActiva();
				boolean resultadoAzafatoActivo = VentanaAzafato.VentanaAzafatoEstaActiva();

				if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
					VentanaAdministrador.desbloquearBotones();

				} else {
					VentanaAzafato.desbloquearBotones();

				}

			}
		});

		btnEliminarTicket = new JButton();
		btnEliminarTicket.setIcon(imagenBorrar);
		btnEliminarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fil = tabla.getSelectedRow();
				if (fil != -1) {
					int ticketNum = (int) modeloTablaTickets.getValueAt(fil, 0);
					try {
						con = BD.initBD("Aeropuerto.db");
					} catch (DBException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						BD.eliminarTickets(con, ticketNum);

						try {
							BD.closeBD(con);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					modeloTablaTickets.removeRow(fil);

				}

			}
		});
		btnEliminarTicket.setText("Eliminar Vuelo");
		if (VentanaAzafato.VentanaAzafatoEstaActiva()) {
			btnEliminarTicket.setEnabled(false);
		}

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(panelScroll,
										GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(86)
										.addComponent(btnEliminarTicket, GroupLayout.PREFERRED_SIZE, 155,
												GroupLayout.PREFERRED_SIZE)
										.addGap(65).addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 155,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(35, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(29).addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				.addGap(35)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminarTicket, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(45, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}
}
