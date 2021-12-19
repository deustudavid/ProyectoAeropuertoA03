package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BD;
import bd.DBException;
import clases.Vuelo;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VerVuelos extends JInternalFrame {

	private JButton btnCancelar;
	private JButton btnEliminarVuelo;
	private JScrollPane panelScroll;
	private JTable tabla;
	private static DefaultTableModel modeloTablaVuelos;
	private ArrayList<Vuelo> v;
	private static Connection con;
	private ImageIcon imagenBorrar;
	private ImageIcon imagenCancelar;
	

	public VerVuelos() {

		v = null;
		con = null;
		panelScroll = new JScrollPane();
		imagenBorrar = new ImageIcon("img/papelera.png");
		imagenCancelar = new ImageIcon("img/Cancelar.png");
		btnCancelar = new JButton();

		modeloTablaVuelos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
		};
		String[] nombreColumnas = { "ID", "origen", "destino", "fecha", "horaSalida", "horaLlegada", "AsientosTotales",
				"AsientosDisponibles" };
		modeloTablaVuelos.setColumnIdentifiers(nombreColumnas);

		try {
			con = BD.initBD("Aeropuerto.db");
			v = BD.obtenerVuelos(con);
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
		cargarVuelosRecursivamente(v, 0);
		/*
		 * for (Vuelo vuelo : v) { String[] fila = { vuelo.getID(), vuelo.getOrigen(),
		 * vuelo.getDestino(), vuelo.getFecha(), vuelo.getHoraSalida(),
		 * vuelo.getHoraLlegada()
		 * ,String.valueOf(vuelo.getAsientosMax()),String.valueOf(vuelo.
		 * getAsientosRestantes()) }; modeloTablaVuelos.addRow(fila); }
		 */

		tabla = new JTable(modeloTablaVuelos);
		tabla.getColumnModel().getColumn(0).setMinWidth(110);
		tabla.getColumnModel().getColumn(0).setMaxWidth(110);
		tabla.getColumnModel().getColumn(1).setMinWidth(100);
		tabla.getColumnModel().getColumn(1).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMinWidth(80);
		tabla.getColumnModel().getColumn(3).setMaxWidth(80);
		tabla.getColumnModel().getColumn(4).setMinWidth(80);
		tabla.getColumnModel().getColumn(4).setMaxWidth(80);
		tabla.getColumnModel().getColumn(5).setMinWidth(83);
		tabla.getColumnModel().getColumn(5).setMaxWidth(83);
		tabla.getColumnModel().getColumn(6).setMinWidth(120);
		tabla.getColumnModel().getColumn(6).setMaxWidth(120);
		tabla.getColumnModel().getColumn(7).setMinWidth(120);
		tabla.getColumnModel().getColumn(7).setMaxWidth(120);

		tabla.setBackground(SystemColor.info);

		panelScroll.setViewportView(tabla);

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
		
			
		
			btnEliminarVuelo = new JButton();
			btnEliminarVuelo.setIcon(imagenBorrar);
			btnEliminarVuelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fil = tabla.getSelectedRow();
					if (fil != -1) {
						String id = (String) modeloTablaVuelos.getValueAt(fil, 0);
						try {
							con = BD.initBD("Aeropuerto.db");
						} catch (DBException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							BD.eliminarVuelo(con, id);
	
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
						modeloTablaVuelos.removeRow(fil);
	
					}
				}
			});
			btnEliminarVuelo.setText("Eliminar Vuelo");
			if (VentanaAzafato.VentanaAzafatoEstaActiva()) {
				btnEliminarVuelo.setEnabled(false);
		}
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(layout.createSequentialGroup()
						.addComponent(panelScroll, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE).addContainerGap())
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnEliminarVuelo, GroupLayout.PREFERRED_SIZE, 155,
										GroupLayout.PREFERRED_SIZE)
								.addGap(56)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addGap(229)))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(29).addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				.addGap(33)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminarVuelo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		getContentPane().setLayout(layout);

		pack();
	}

	private static void cargarVuelosRecursivamente(ArrayList<Vuelo> v, int i) {
		if (i < v.size()) {
			String[] fila = { v.get(i).getID(), v.get(i).getOrigen(), v.get(i).getDestino(), v.get(i).getFecha(),
					v.get(i).getHoraSalida(), v.get(i).getHoraLlegada(), String.valueOf(v.get(i).getAsientosMax()),
					String.valueOf(v.get(i).getAsientosRestantes()) };
			modeloTablaVuelos.addRow(fila);
			cargarVuelosRecursivamente(v, i + 1);
		}
	}
}
