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
		String[] nombreColumnas = { "ID", "origen", "destino", "fecha", "horaSalida", "horaLlegada" };
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
					vuelo.getHoraSalida(), vuelo.getHoraLlegada() };
			modeloTablaVuelos.addRow(fila);
		}

		tabla = new JTable(modeloTablaVuelos);
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
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(panelScroll,
										GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(226, 226, 226).addComponent(btnCancelar,
										GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(35, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(29, 29, 29)
						.addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
						.addGap(35, 35, 35)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(37, Short.MAX_VALUE)));

		pack();
	}
}
