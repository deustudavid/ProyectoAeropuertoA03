package ventanas;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;

public class ReservarTicket extends JInternalFrame {

	private JLabel lblIdVuelo;
	private JButton btnReservar;
	private JButton btnCancelar;
	private JButton btnBuscadorDestinos;
	private JButton btnBuscarPasajero;
	private JLabel lblOrigen;
	private JLabel lblIDVuelo;
	private JLabel lblHoraSalida;
	private JLabel lblClase;
	private JLabel lblPrecio;
	private JLabel lblAsientos;
	private JLabel lblDestino;
	private JLabel lblTicketNum;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTelefono;
	private JPanel panelBusquedaVuelo;
	private JPanel panelVuelo;
	private JPanel panelBusquedaPasajero;
	private JScrollPane jScrollPane1;
	private JTable tablaVuelos;
	private JComboBox<String> txtClaseVuelo;
	private JTextField txtDni;
	private JDateChooser txtdate;
	private JComboBox<String> txtDestino;
	private JLabel txtHoraSalida;
	private JLabel txtNombre;
	private JLabel txtApellido;
	private JLabel txtTelefono;
	private JTextField txtPrecio;
	private JSpinner txtAsientos;
	private JComboBox<String> txtOrigen;
	private JLabel txtTicketNum;
	private JLabel txtPrecioTotal;

	public ReservarTicket() {

		panelBusquedaVuelo = new JPanel();
		txtOrigen = new JComboBox<>();
		txtDestino = new JComboBox<>();
		lblOrigen = new JLabel();
		lblDestino = new JLabel();
		btnBuscadorDestinos = new JButton();
		jScrollPane1 = new JScrollPane();
		tablaVuelos = new JTable();
		lblTicketNum = new JLabel();
		txtTicketNum = new JLabel();
		panelBusquedaPasajero = new JPanel();
		lblDni = new JLabel();
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		txtDni = new JTextField();
		lblTelefono = new JLabel();
		txtNombre = new JLabel();
		txtApellido = new JLabel();
		txtTelefono = new JLabel();
		btnBuscarPasajero = new JButton();
		panelVuelo = new JPanel();
		lblIDVuelo = new JLabel();
		lblHoraSalida = new JLabel();
		lblClase = new JLabel();
		lblPrecio = new JLabel();
		lblAsientos = new JLabel();
		lblIdVuelo = new JLabel();
		txtHoraSalida = new JLabel();
		txtClaseVuelo = new JComboBox<>();
		txtPrecio = new JTextField();
		txtAsientos = new JSpinner();
		txtdate = new com.toedter.calendar.JDateChooser();
		btnReservar = new JButton();
		btnCancelar = new JButton();

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				boolean resultadoAdministradorActivo = ventanas.VentanaAdministrador.VentanaAdminEstaActiva();
				boolean resultadoAzafatoActivo = ventanas.VentanaAzafato.VentanaAzafatoEstaActiva();

				if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
					VentanaAdministrador.desbloquearBotones();

				} else {
					VentanaAzafato.desbloquearBotones();

				}

			}
		});
		txtPrecioTotal = new JLabel();

		panelBusquedaVuelo.setBorder(BorderFactory.createTitledBorder(null, "Selecciona pais",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Tahoma", 1, 12))); // NOI18N

		txtOrigen.setModel(new DefaultComboBoxModel<>(
				new String[] { "Italia\t", "Srilanka", "UK", "USA", "Canada", "China" }));

		txtDestino.setModel(new DefaultComboBoxModel<>(
				new String[] { "Italia\t", "Srilanka", "UK", "USA", "Canada", "China" }));

		lblOrigen.setText("Origen");

		lblDestino.setText("Destino");

		btnBuscadorDestinos.setText("Buscar para estos lugares");

		GroupLayout glPanelBusquedaVuelo = new GroupLayout(panelBusquedaVuelo);
		panelBusquedaVuelo.setLayout(glPanelBusquedaVuelo);
		glPanelBusquedaVuelo.setHorizontalGroup(glPanelBusquedaVuelo
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addContainerGap()
						.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, 124,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
						.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, 111,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35, 35, 35))
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addGap(54, 54, 54).addComponent(lblOrigen)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDestino).addGap(87, 87, 87))
				.addGroup(GroupLayout.Alignment.TRAILING,
						glPanelBusquedaVuelo.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBuscadorDestinos).addGap(49, 49, 49)));
		glPanelBusquedaVuelo.setVerticalGroup(glPanelBusquedaVuelo
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addGap(32, 32, 32)
						.addGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lblOrigen).addComponent(lblDestino))
						.addGap(18, 18, 18)
						.addGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addComponent(btnBuscadorDestinos).addContainerGap()));

		tablaVuelos.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID Vuelo", "Origen", "Destino", "Fecha", "HoraSalida", "HoraLlegada", "Precio" }));

		jScrollPane1.setViewportView(tablaVuelos);

		lblTicketNum.setText("Ticket num");

		txtTicketNum.setFont(new Font("Tahoma", 1, 24));
		txtTicketNum.setForeground(new Color(255, 0, 0));
		txtTicketNum.setText("Ticket Num");

		lblDni.setText("DNI pasajero");

		lblNombre.setText("Nombre");

		lblApellido.setText("Apellido");

		lblTelefono.setText("Telefono");

		txtNombre.setFont(new Font("Tahoma", 1, 14));
		txtNombre.setForeground(new Color(255, 0, 0));
		txtNombre.setText("lblNombre");

		txtApellido.setFont(new Font("Tahoma", 1, 14));
		txtApellido.setForeground(new Color(255, 0, 0));
		txtApellido.setText("lblApellido");

		txtTelefono.setFont(new Font("Tahoma", 1, 14));
		txtTelefono.setForeground(new Color(255, 0, 0));
		txtTelefono.setText("lblTelefono");

		btnBuscarPasajero.setText("Buscar pasajero");

		GroupLayout glPanelBusquedaPasajero = new GroupLayout(panelBusquedaPasajero);
		panelBusquedaPasajero.setLayout(glPanelBusquedaPasajero);
		glPanelBusquedaPasajero.setHorizontalGroup(glPanelBusquedaPasajero
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaPasajero.createSequentialGroup().addGap(57, 57, 57)
						.addGroup(glPanelBusquedaPasajero.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(glPanelBusquedaPasajero.createSequentialGroup().addComponent(lblDni)
										.addGap(34, 34, 34)
										.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, 151,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15,
												Short.MAX_VALUE)
										.addComponent(btnBuscarPasajero))
								.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
										.addGroup(glPanelBusquedaPasajero
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lblNombre).addComponent(lblApellido)
												.addComponent(lblTelefono))
										.addGap(56, 56, 56)
										.addGroup(glPanelBusquedaPasajero
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(txtTelefono).addComponent(txtApellido)
												.addComponent(txtNombre))))
						.addContainerGap()));
		glPanelBusquedaPasajero.setVerticalGroup(glPanelBusquedaPasajero
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaPasajero.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(glPanelBusquedaPasajero
								.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblDni)
								.addComponent(txtDni, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscarPasajero))
						.addGap(26, 26, 26)
						.addGroup(
								glPanelBusquedaPasajero.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblNombre).addComponent(txtNombre))
						.addGap(31, 31, 31)
						.addGroup(
								glPanelBusquedaPasajero.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblApellido).addComponent(txtApellido))
						.addGap(36, 36, 36)
						.addGroup(
								glPanelBusquedaPasajero.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblTelefono).addComponent(txtTelefono))
						.addContainerGap(37, Short.MAX_VALUE)));

		lblIDVuelo.setText("ID Vuelo");

		lblHoraSalida.setText("Hora salida");

		lblClase.setText("Clase");

		lblPrecio.setText("Precio");

		lblAsientos.setText("Asientos");

		lblIdVuelo.setFont(new Font("Tahoma", 1, 12));
		lblIdVuelo.setForeground(new Color(255, 0, 0));
		lblIdVuelo.setText("lblIdVuelo");

		txtHoraSalida.setFont(new Font("Tahoma", 1, 12));
		txtHoraSalida.setForeground(new Color(255, 0, 0));
		txtHoraSalida.setText("lblHoraSalida");

		txtClaseVuelo.setModel(new DefaultComboBoxModel<>(new String[] { "Economico", "Business" }));

		GroupLayout glPanelVuelo = new GroupLayout(panelVuelo);
		glPanelVuelo.setHorizontalGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addGroup(glPanelVuelo
				.createSequentialGroup().addGap(19)
				.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING).addComponent(lblHoraSalida)
								.addComponent(lblClase, Alignment.LEADING).addComponent(lblPrecio, Alignment.LEADING)
								.addGroup(glPanelVuelo.createSequentialGroup().addComponent(lblIDVuelo).addGap(16)))
						.addGroup(glPanelVuelo.createSequentialGroup().addComponent(lblAsientos).addGap(35)))
				.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addGroup(glPanelVuelo
						.createSequentialGroup().addGap(60)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addComponent(txtHoraSalida)
								.addGroup(glPanelVuelo.createSequentialGroup().addComponent(lblIdVuelo).addGap(34)
										.addComponent(txtdate, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(glPanelVuelo.createSequentialGroup().addGap(50)
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtClaseVuelo, 0, 116, Short.MAX_VALUE).addComponent(txtPrecio)
										.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(26, Short.MAX_VALUE)));
		glPanelVuelo.setVerticalGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelVuelo.createSequentialGroup().addGap(17)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblIDVuelo)
										.addComponent(lblIdVuelo))
								.addComponent(txtdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(74)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblHoraSalida)
								.addComponent(txtHoraSalida))
						.addGap(23)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblClase)
								.addComponent(txtClaseVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblPrecio)
								.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING).addComponent(lblAsientos)
								.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelVuelo.setLayout(glPanelVuelo);

		btnReservar.setText("Reservar");

		btnCancelar.setText("Cancelar");

		txtPrecioTotal.setFont(new Font("Tahoma", 1, 24));
		txtPrecioTotal.setForeground(new Color(255, 0, 0));
		txtPrecioTotal.setText("PrecioTotal");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(32)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(44).addComponent(lblTicketNum))
								.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtTicketNum)))
						.addGap(41).addComponent(panelBusquedaPasajero, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addGap(31))
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 534,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(layout.createSequentialGroup()
										.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 226,
												GroupLayout.PREFERRED_SIZE)
										.addGap(101)))
								.addGroup(layout.createParallelGroup(Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(51)
												.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 138,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 134,
														GroupLayout.PREFERRED_SIZE)
												.addGap(0, 142, Short.MAX_VALUE))
										.addGroup(layout
												.createSequentialGroup().addGap(33).addComponent(panelVuelo,
														GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
												.addContainerGap()))))));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(42).addComponent(panelBusquedaVuelo,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(53).addComponent(lblTicketNum).addGap(18)
								.addComponent(txtTicketNum))
						.addGroup(layout.createSequentialGroup().addGap(31).addComponent(panelBusquedaPasajero,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(18)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addGap(34).addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 24,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(19, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();

		setVisible(true);

	}

}
