package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import bd.DBException;
import clases.Vuelo;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VerVuelos extends JInternalFrame {

	private JButton btnCancelar;
	private JScrollPane panelScroll;
	private JTable tabla;
	private DefaultTableModel modeloTablaVuelos;
	private ArrayList<Vuelo> v;
	private static Connection con;

	public VerVuelos() {

		v = null;
		con = null;
		panelScroll = new JScrollPane();

		btnCancelar = new JButton();

		modeloTablaVuelos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
		};
		String[] nombreColumnas = { "ID", "origen", "destino", "fecha", "horaSalida", "horaLlegada", "AsientosTotales" };
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

		for (Vuelo vuelo : v) {
			String[] fila = { vuelo.getID(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha(),
					vuelo.getHoraSalida(), vuelo.getHoraLlegada() ,String.valueOf(vuelo.getAsientosMax()) };
			modeloTablaVuelos.addRow(fila);
		}

		tabla = new JTable(modeloTablaVuelos);
		tabla.getColumnModel().getColumn(0).setMinWidth(120);
		tabla.getColumnModel().getColumn(0).setMaxWidth(120);
		tabla.getColumnModel().getColumn(1).setMinWidth(100);
		tabla.getColumnModel().getColumn(1).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMinWidth(80);
		tabla.getColumnModel().getColumn(3).setMaxWidth(80);
		tabla.getColumnModel().getColumn(4).setMinWidth(90);
		tabla.getColumnModel().getColumn(4).setMaxWidth(90);
		tabla.getColumnModel().getColumn(5).setMinWidth(90);
		tabla.getColumnModel().getColumn(5).setMaxWidth(90);
		tabla.getColumnModel().getColumn(6).setMinWidth(120);
		tabla.getColumnModel().getColumn(6).setMaxWidth(120);
		
		tabla.setBackground(SystemColor.info);

		panelScroll.setViewportView(tabla);

		btnCancelar.setText("Cancelar");
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

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(281)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelScroll, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
					.addGap(29)
					.addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		getContentPane().setLayout(layout);

		pack();
	}
}
