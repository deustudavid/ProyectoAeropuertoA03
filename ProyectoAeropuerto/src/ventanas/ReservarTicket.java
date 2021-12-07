package ventanas;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import bd.BD;
import bd.DBException;
import clases.Clase;
import clases.Pasajero;
import clases.Vuelo;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReservarTicket extends JInternalFrame {
	
	private Connection con;

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
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTelefono;
	private JPanel panelBusquedaVuelo;
	private JPanel panelVuelo;
	private JPanel panelBusquedaPasajero;
	private JScrollPane scrollTabla;
	private JTable tablaVuelos;
	private DefaultTableModel modeloTablaVuelos;
	private JTextField txtDni;
	private JDateChooser txtFecha;
	
	private JComboBox<String> txtDestino;
	private JLabel txtHoraSalida;
	private JLabel txtNombre;
	private JLabel txtApellido;
	private JLabel txtTelefono;
	private JSpinner txtAsientos;
	private JComboBox<String> txtOrigen;
	private JLabel txtPrecioTotal;
	private JComboBox<Clase> opcionesClase;
	private ArrayList<Vuelo> a;

	private static String origen;

	private static String destino;
	private JLabel lblEdad;
	private JLabel lblDireccion;
	private JLabel txtEdad;
	private JLabel lblHoraLlegada;
	private JLabel txtHoraLlegada;
	private JLabel lblOrigenMostrado;
	private JLabel lblDestinoMostrado;
	private JLabel txtOrigenMostrado;
	private JLabel txtDestinoMostrado;
	private JLabel txtPrecio;

	public ReservarTicket() {
		
        // HACER QUE EL TEXTFIELD DONDE APARECE LA FECHA TRAS SELECCIONARLA CON JFILECHOOSER NO SE PUEDA EDITAR
        txtFecha = new com.toedter.calendar.JDateChooser();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) txtFecha.getDateEditor();
        editor.setEditable(false);
        
   
		
		a=null;
		con=null;
		origen="";
		destino="";
		JScrollPane scrollDireccion = new JScrollPane();

		JTextArea txtDireccion = new JTextArea();
		txtDireccion.setEditable(false);
		txtDireccion.setRows(5);
		txtDireccion.setColumns(20);
		scrollDireccion.setViewportView(txtDireccion);

		panelBusquedaVuelo = new JPanel();
		txtOrigen = new JComboBox<>();
		txtDestino = new JComboBox<>();
		lblOrigen = new JLabel();
		lblDestino = new JLabel();
		btnBuscadorDestinos = new JButton();
	
		scrollTabla = new JScrollPane();
		
		
		modeloTablaVuelos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
		};
		String [] nombresColumnas = { "ID Vuelo", "Origen", "Destino", "Fecha", "HoraSalida", "HoraLlegada", "AsientosTotales" };
		modeloTablaVuelos.setColumnIdentifiers(nombresColumnas);
		
		tablaVuelos = new JTable(modeloTablaVuelos);
		tablaVuelos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tablaVuelos.getSelectedRow();
				TableModel modelo=(DefaultTableModel) tablaVuelos.getModel();
				lblIdVuelo.setText((String) modelo.getValueAt(i, 0));
				txtOrigenMostrado.setText((String) modelo.getValueAt(i, 1));
				txtDestinoMostrado.setText((String) modelo.getValueAt(i, 2));
				editor.setText((String) modelo.getValueAt(i, 3));
				txtHoraSalida.setText((String) modelo.getValueAt(i, 4));
				txtHoraLlegada.setText((String) modelo.getValueAt(i, 5));
				
				

			}
		});
		tablaVuelos.getColumnModel().getColumn(0).setMinWidth(110);
		tablaVuelos.getColumnModel().getColumn(0).setMaxWidth(110);
		tablaVuelos.getColumnModel().getColumn(1).setMinWidth(90);
		tablaVuelos.getColumnModel().getColumn(1).setMaxWidth(90);
		tablaVuelos.getColumnModel().getColumn(2).setMinWidth(90);
		tablaVuelos.getColumnModel().getColumn(2).setMaxWidth(90);
		tablaVuelos.getColumnModel().getColumn(3).setMinWidth(80);
		tablaVuelos.getColumnModel().getColumn(3).setMaxWidth(80);
		tablaVuelos.getColumnModel().getColumn(4).setMinWidth(90);
		tablaVuelos.getColumnModel().getColumn(4).setMaxWidth(90);
		tablaVuelos.getColumnModel().getColumn(5).setMinWidth(90);
		tablaVuelos.getColumnModel().getColumn(5).setMaxWidth(90);
		tablaVuelos.getColumnModel().getColumn(6).setMinWidth(100);
		tablaVuelos.getColumnModel().getColumn(6).setMaxWidth(100);
		


		scrollTabla.setViewportView(tablaVuelos);
		panelBusquedaPasajero = new JPanel();
		lblDni = new JLabel();
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		txtDni = new JTextField();
		lblTelefono = new JLabel();
		txtNombre = new JLabel();
		txtNombre.setEnabled(false);
		txtApellido = new JLabel();
		txtApellido.setEnabled(false);
		txtTelefono = new JLabel();
		txtTelefono.setEnabled(false);
		btnBuscarPasajero = new JButton();
		btnBuscarPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String dniAbuscar = txtDni.getText();
				
				try {
					con = BD.initBD("Aeropuerto.db");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (BD.buscarPasajero(con, dniAbuscar)!=null) {
						
						Pasajero p = BD.buscarPasajero(con, dniAbuscar);
						txtNombre.setText(p.getNombre());
						txtApellido.setText(p.getApellido());
						txtDni.setText(p.getDni());
						int tf=p.getTelefono();
						txtTelefono.setText(String.valueOf(tf));
						txtDireccion.setText(p.getDireccion());
						int edad=p.getEdad();
						txtEdad.setText(String.valueOf(edad));
					}else {
						JOptionPane.showMessageDialog(null, "No se ha encontrado el pasajero con ese dni", "Error", JOptionPane.WARNING_MESSAGE);

					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panelVuelo = new JPanel();
		lblIDVuelo = new JLabel();
		lblHoraSalida = new JLabel();
		lblClase = new JLabel();
		lblPrecio = new JLabel();
		lblAsientos = new JLabel();
		lblIdVuelo = new JLabel();
		lblIdVuelo.setEnabled(false);
		txtHoraSalida = new JLabel();
		txtHoraSalida.setEnabled(false);
		txtAsientos = new JSpinner();
		txtAsientos.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		txtFecha.getCalendarButton().setEnabled(false);
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
		txtPrecioTotal.setEnabled(false);

		panelBusquedaVuelo.setBorder(BorderFactory.createTitledBorder(null, "Selecciona pais",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Tahoma", 1, 12))); 

		txtOrigen.setModel(new DefaultComboBoxModel<>(
				new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York", "Canadá", "China", "España", "Londres", "Japón", "Marruecos", "Sydney", "Francia" }));


		txtDestino.setModel(new DefaultComboBoxModel<>(
				new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York", "Canadá", "China", "España", "Londres", "Japón", "Marruecos", "Sydney", "Francia" }));


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

		lblDni.setText("DNI pasajero");

		lblNombre.setText("Nombre");

		lblApellido.setText("Apellido");

		lblTelefono.setText("Telefono");

		txtNombre.setFont(new Font("Tahoma", 1, 14));
		txtNombre.setForeground(new Color(255, 0, 0));
		txtNombre.setText(" ");

		txtApellido.setFont(new Font("Tahoma", 1, 14));
		txtApellido.setForeground(new Color(255, 0, 0));
		txtApellido.setText(" ");

		txtTelefono.setFont(new Font("Tahoma", 1, 14));
		txtTelefono.setForeground(new Color(255, 0, 0));
		txtTelefono.setText(" ");

		btnBuscarPasajero.setText("Buscar pasajero");
		
		lblEdad = new JLabel();
		lblEdad.setText("Edad");
		
		lblDireccion = new JLabel();
		lblDireccion.setText("Direccion");
		
		txtEdad = new JLabel();
		txtEdad.setEnabled(false);
		txtEdad.setText(" ");
		txtEdad.setForeground(Color.RED);
		txtEdad.setFont(new Font("Tahoma", Font.BOLD, 14));
		

		GroupLayout glPanelBusquedaPasajero = new GroupLayout(panelBusquedaPasajero);
		glPanelBusquedaPasajero.setHorizontalGroup(
			glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
					.addGap(57)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addComponent(lblDni)
							.addGap(34)
							.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(btnBuscarPasajero))
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblApellido)
								.addComponent(lblTelefono))
							.addGap(56)
							.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
								.addComponent(txtNombre)
								.addComponent(txtApellido)
								.addComponent(txtTelefono))
							.addGap(63)
							.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollDireccion, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
									.addComponent(lblEdad, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		glPanelBusquedaPasajero.setVerticalGroup(
			glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
					.addGap(20)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDni)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarPasajero))
					.addGap(26)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre)
						.addComponent(lblEdad)
						.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido)
						.addComponent(lblDireccion))
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addGap(36)
							.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono)
								.addComponent(txtTelefono)))
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollDireccion, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		
		panelBusquedaPasajero.setLayout(glPanelBusquedaPasajero);

		lblIDVuelo.setText("ID Vuelo");

		lblHoraSalida.setText("Hora salida");

		lblClase.setText("Clase");

		lblPrecio.setText("Precio");

		lblAsientos.setText("Asientos");
		


		lblIdVuelo.setFont(new Font("Tahoma", 1, 12));
		lblIdVuelo.setForeground(new Color(255, 0, 0));
		lblIdVuelo.setText(" ");

		txtHoraSalida.setFont(new Font("Tahoma", 1, 12));
		txtHoraSalida.setForeground(new Color(255, 0, 0));
		txtHoraSalida.setText(" ");
		
		opcionesClase = new JComboBox<Clase>();
		opcionesClase.setBackground(SystemColor.info);
		opcionesClase.setModel(new DefaultComboBoxModel(new Object[] {"ESTANDAR", "BARATO", "BUSINESS", "LUJO"}));
		opcionesClase.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	switch (Clase.valueOf(opcionesClase.getSelectedItem().toString())) {
				case BARATO:
					
					txtPrecio.setText("20.0");
					
					break;
				case ESTANDAR:
				txtPrecio.setText("30.0");
					break;
				case BUSINESS:
					
					txtPrecio.setText("40.0");
					break;
				 case LUJO:
					txtPrecio.setText("50.0");
					break;

				default :
					txtPrecio.setText("");
					break;
				}
		    }
		});
	
	
		
		btnBuscadorDestinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 origen =  txtOrigen.getSelectedItem().toString();
				 destino =  txtDestino.getSelectedItem().toString();
				
					
						try {
							con = BD.initBD("Aeropuerto.db");
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						a = BD.obtenerVuelosSegunOrigenDestino(con,origen,destino);
					

					
						try {
							BD.closeBD(con);
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				
				//Cargamos el ArrayList de Vuelos en el modelo de la tabla
				for(Vuelo vu: a) {
					String [] fila = {vu.getID(),vu.getOrigen(),vu.getDestino(), vu.getFecha(),vu.getHoraSalida(),vu.getHoraLlegada(),String.valueOf(vu.getAsientosMax())};
					modeloTablaVuelos.addRow(fila);
				}
				
				
			}
		});
		
		lblHoraLlegada = new JLabel();
		lblHoraLlegada.setText("Hora llegada");
		
		txtHoraLlegada = new JLabel();
		txtHoraLlegada.setEnabled(false);
		txtHoraLlegada.setText(" ");
		txtHoraLlegada.setForeground(Color.RED);
		txtHoraLlegada.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		lblOrigenMostrado = new JLabel();
		lblOrigenMostrado.setText("Origen");
		
		lblDestinoMostrado = new JLabel();
		lblDestinoMostrado.setText("Destino");
		
		txtOrigenMostrado = new JLabel();
		txtOrigenMostrado.setText(" ");
		txtOrigenMostrado.setForeground(Color.RED);
		txtOrigenMostrado.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtOrigenMostrado.setEnabled(false);
		
		txtDestinoMostrado = new JLabel();
		txtDestinoMostrado.setText(" ");
		txtDestinoMostrado.setForeground(Color.RED);
		txtDestinoMostrado.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDestinoMostrado.setEnabled(false);
		
		txtPrecio = new JLabel();
		txtPrecio.setEnabled(false);
		txtPrecio.setText(" ");

		GroupLayout glPanelVuelo = new GroupLayout(panelVuelo);
		glPanelVuelo.setHorizontalGroup(
			glPanelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelVuelo.createSequentialGroup()
					.addGap(19)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHoraSalida)
						.addGroup(glPanelVuelo.createSequentialGroup()
							.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAsientos)
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblClase, Alignment.LEADING)
									.addComponent(lblPrecio, Alignment.LEADING)
									.addGroup(glPanelVuelo.createSequentialGroup()
										.addComponent(lblIDVuelo)
										.addGap(16))))
							.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
								.addGroup(glPanelVuelo.createSequentialGroup()
									.addGap(50)
									.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
										.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
										.addComponent(opcionesClase, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
								.addGroup(glPanelVuelo.createSequentialGroup()
									.addGap(60)
									.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
										.addGroup(glPanelVuelo.createSequentialGroup()
											.addComponent(lblIdVuelo)
											.addGap(34)
											.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
										.addGroup(glPanelVuelo.createSequentialGroup()
											.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
												.addComponent(txtHoraSalida)
												.addComponent(txtHoraLlegada, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
											.addGap(49)
											.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
												.addComponent(lblOrigenMostrado, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDestinoMostrado, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
												.addComponent(txtDestinoMostrado, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtOrigenMostrado, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))))))
						.addComponent(lblHoraLlegada, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		glPanelVuelo.setVerticalGroup(
			glPanelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelVuelo.createSequentialGroup()
					.addGap(17)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblIDVuelo)
							.addComponent(lblIdVuelo))
						.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHoraSalida)
						.addComponent(txtHoraSalida)
						.addComponent(lblOrigenMostrado)
						.addComponent(txtOrigenMostrado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHoraLlegada)
						.addComponent(txtHoraLlegada, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDestinoMostrado)
						.addComponent(txtDestinoMostrado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClase)
						.addComponent(opcionesClase, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecio)
						.addComponent(txtPrecio))
					.addGap(18)
					.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAsientos))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelVuelo.setLayout(glPanelVuelo);

		btnReservar.setText("Reservar");

		btnCancelar.setText("Cancelar");

		txtPrecioTotal.setFont(new Font("Tahoma", 1, 24));
		txtPrecioTotal.setForeground(new Color(255, 0, 0));
		txtPrecioTotal.setText("PrecioTotal");

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(32)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
									.addGap(101))
								.addGroup(layout.createSequentialGroup()
									.addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addGap(51)
									.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addGap(111)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(panelBusquedaPasajero, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
										.addComponent(panelVuelo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))))
							.addContainerGap())))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(31)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelBusquedaVuelo, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addComponent(panelBusquedaPasajero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addGap(11)
							.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);

		pack();

		setVisible(true);

	}
}
