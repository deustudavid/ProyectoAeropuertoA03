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
import clases.CoordenadasAeropuerto;
import clases.Pasajero;
import clases.Vuelo;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ReservarTicket extends JInternalFrame {

	private Connection con;
	
	private static double latitudOrigen ;
	private static double longitudOrigen;
	private static double latitudDestino ;
	private static double longitudDestino ;


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
	private JLabel txtPrecioBase;
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
	private static double precioIndividual;
	private static double precioViaje;

	private Vuelo vueloObtenido;
	private JLabel txtImpuestos;
	private JLabel txtImpuestosaMostrar;
	private JLabel txtPrecioTotal;
	private JLabel txtPrecioFinalaMostrar;
	
	private ImageIcon imagenCancelar;

	public ReservarTicket() {
		
		imagenCancelar = new ImageIcon("img/Cancelar.png");
		
		latitudOrigen = 0.0;
		longitudOrigen = 0.0;
		latitudDestino = 0.0;
		longitudDestino = 0.0;


		JLabel txtPrecioBaseaMostrar = new JLabel();
		txtPrecioBaseaMostrar.setEnabled(false);
		txtPrecioBaseaMostrar.setText("30.0");
		txtPrecioBaseaMostrar.setForeground(Color.RED);
		txtPrecioBaseaMostrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		precioIndividual=0.0;
		precioViaje=0.0;

		// HACER QUE EL TEXTFIELD DONDE APARECE LA FECHA TRAS SELECCIONARLA CON
		// JFILECHOOSER NO SE PUEDA EDITAR
		txtFecha = new com.toedter.calendar.JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) txtFecha.getDateEditor();
		editor.setEditable(false);

		vueloObtenido = new Vuelo();
		a = null;
		con = null;
		origen = "";
		destino = "";
		JScrollPane scrollDireccion = new JScrollPane();

		JTextArea txtDireccion = new JTextArea();
		txtDireccion.setEditable(false);
		txtDireccion.setRows(5);
		txtDireccion.setColumns(20);
		scrollDireccion.setViewportView(txtDireccion);
		
		txtImpuestosaMostrar = new JLabel();
		txtImpuestosaMostrar.setText("0.0");
		txtImpuestosaMostrar.setForeground(Color.RED);
		txtImpuestosaMostrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtImpuestosaMostrar.setEnabled(false);
		
		txtPrecioFinalaMostrar = new JLabel();
		txtPrecioFinalaMostrar.setText("0.0");
		txtPrecioFinalaMostrar.setForeground(Color.RED);
		txtPrecioFinalaMostrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtPrecioFinalaMostrar.setEnabled(false);
		

		txtPrecio = new JLabel();
		txtPrecio.setEnabled(false);
		txtPrecio.setText("30.0");
		
		/*HILOS*/
		Runnable r1 = new Runnable() {
			public void run() {
				while(true) {
					txtPrecioFinalaMostrar.setForeground(Color.GREEN);
					double impuestos=Double.parseDouble(txtImpuestosaMostrar.getText());
					double precioBase=Double.parseDouble(txtPrecioBaseaMostrar.getText());
					double resultado=precioBase+impuestos;
					txtPrecioFinalaMostrar.setText(String.valueOf(resultado));
					
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				
				}
			
		};
		Thread t1 = new Thread(r1);
		t1.start();
		
		opcionesClase = new JComboBox<Clase>();
		opcionesClase.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txtAsientos.setValue(1);
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

				default:
					txtPrecio.setText("30.0");
					break;
				}
				txtPrecioBaseaMostrar.setText(txtPrecio.getText());
				
			}
		});
		opcionesClase.setBackground(SystemColor.info);
		opcionesClase.setModel(new DefaultComboBoxModel(new Object[] { "ESTANDAR", "BARATO", "BUSINESS", "LUJO" }));
		opcionesClase.addActionListener(new ActionListener() {
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

				default:
					txtPrecio.setText("30.0");
					break;
				}
			}
		});


		panelBusquedaVuelo = new JPanel();
		txtOrigen = new JComboBox<>();
		txtDestino = new JComboBox<>();
		lblOrigen = new JLabel();
		lblDestino = new JLabel();
		btnBuscadorDestinos = new JButton();

		scrollTabla = new JScrollPane();

		modeloTablaVuelos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		String[] nombresColumnas = { "ID Vuelo", "Origen", "Destino", "Fecha", "HoraSalida", "HoraLlegada",
				"AsientosTotales","AsientosDisponibles" };
		modeloTablaVuelos.setColumnIdentifiers(nombresColumnas);
		
		txtAsientos = new JSpinner();
		txtAsientos.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		txtAsientos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int asientosComprados = (int) txtAsientos.getValue();
				precioIndividual = Double.parseDouble(txtPrecio.getText());
				precioViaje = asientosComprados * precioIndividual;
				double precioEnPantalla= Double.parseDouble(txtPrecioBaseaMostrar.getText());
				// Ahora veremos si se aplica algun descuento
				if(asientosComprados >= 2 && asientosComprados <= 5 ) {
					// 10 % de descuento
				
					precioViaje = precioViaje * 0.9;
					txtPrecioBaseaMostrar.setText(String.valueOf(precioViaje));
					

				
				} else if (asientosComprados >= 6 && asientosComprados <= 10 ) {
					// 15 % de descuento

					precioViaje=precioViaje * 0.85;
					txtPrecioBaseaMostrar.setText(String.valueOf(precioViaje));
				} else if(asientosComprados >= 11 && asientosComprados <= 14  ) {
					// 20 % de descuento
					precioViaje = precioViaje * 0.8;
					txtPrecioBaseaMostrar.setText(String.valueOf(precioViaje));
				} else if(asientosComprados > 14  ) {
					// 45 % de descuento
					precioViaje = precioViaje * 0.55;
					txtPrecioBaseaMostrar.setText(String.valueOf(precioViaje));
					
				} else if(asientosComprados==1){
				txtPrecioBaseaMostrar.setText(String.valueOf(precioIndividual));
		}
					}
			
		});
		
		
		
		
		btnBuscadorDestinos.setText("Buscar para estos lugares");
		btnBuscadorDestinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				origen = txtOrigen.getSelectedItem().toString();
				destino = txtDestino.getSelectedItem().toString();

				try {
					con = BD.initBD("Aeropuerto.db");
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				a = BD.obtenerVuelosSegunOrigenDestino(con, origen, destino);

				try {
					BD.closeBD(con);
				} catch (DBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// vaciar la tabla cuando se selecciona un nuevo origen-destino
				while (modeloTablaVuelos.getRowCount() > 0) {
					int filas = tablaVuelos.getRowCount();
					if (0 < filas) {
						modeloTablaVuelos.removeRow(0);
					}

				}

				// Cargamos el ArrayList de Vuelos en el modelo de la tabla
				for (Vuelo vu : a) {
					String[] fila = { vu.getID(), vu.getOrigen(), vu.getDestino(), vu.getFecha(), vu.getHoraSalida(),
							vu.getHoraLlegada(), String.valueOf(vu.getAsientosMax()),String.valueOf(vu.getAsientosRestantes()) };
					modeloTablaVuelos.addRow(fila);
				}
				
				
				
			}
		});

		tablaVuelos = new JTable(modeloTablaVuelos);
		tablaVuelos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tablaVuelos.getSelectedRow();
				TableModel modelo = (DefaultTableModel) tablaVuelos.getModel();
				lblIdVuelo.setText((String) modelo.getValueAt(i, 0));
				txtOrigenMostrado.setText((String) modelo.getValueAt(i, 1));
				txtDestinoMostrado.setText((String) modelo.getValueAt(i, 2));
				editor.setText((String) modelo.getValueAt(i, 3));
				txtHoraSalida.setText((String) modelo.getValueAt(i, 4));
				txtHoraLlegada.setText((String) modelo.getValueAt(i, 5));

				vueloObtenido.setID((String) modelo.getValueAt(i, 0));
				vueloObtenido.setOrigen((String) modelo.getValueAt(i, 1));
				vueloObtenido.setDestino((String) modelo.getValueAt(i, 2));
				vueloObtenido.setFecha((String) modelo.getValueAt(i, 3));
				vueloObtenido.setHoraSalida((String) modelo.getValueAt(i, 4));
				vueloObtenido.setHoraLlegada((String) modelo.getValueAt(i, 5));
				vueloObtenido.setAsientosMax(Integer.parseInt((String) modelo.getValueAt(i, 6)) );
				vueloObtenido.setAsientosRestantes(Integer.parseInt((String) modelo.getValueAt(i, 7)) );	
				
				//mirar encarecimiento del precio segun el origen y destino
				String origen = txtOrigenMostrado.getText();
				String destino = txtDestinoMostrado.getText();
				/* Calcular distancia entre el origen y destino */
				CoordenadasAeropuerto coorItalia = new CoordenadasAeropuerto(42.76698, 12.493823);
				CoordenadasAeropuerto coorSrilanka = new CoordenadasAeropuerto(7.7891335, 80.680725);
				CoordenadasAeropuerto coorBrasilia = new CoordenadasAeropuerto(-15.77843, -47.92865);
				CoordenadasAeropuerto coorNuevaYork = new CoordenadasAeropuerto(40.71455, -74.00712);
				CoordenadasAeropuerto coorCanada = new CoordenadasAeropuerto(56.130366, -106.346771);
				CoordenadasAeropuerto coorChina = new CoordenadasAeropuerto(36.553085, 103.97543);
				CoordenadasAeropuerto coorEspana = new CoordenadasAeropuerto(40.463667, -3.74922);
				CoordenadasAeropuerto coorLondres = new CoordenadasAeropuerto(51.500153, -0.1262362);
				CoordenadasAeropuerto coorJapon = new CoordenadasAeropuerto(36.281647, 139.07727);
				CoordenadasAeropuerto coorMarruecos = new CoordenadasAeropuerto(31.925692, -6.229583);
				CoordenadasAeropuerto coorSydney = new CoordenadasAeropuerto(-33.8696, 151.20695);
				CoordenadasAeropuerto coorFrancia = new CoordenadasAeropuerto(46.63728, 2.3382623);

				

				switch (origen) {
				case "Italia":
					latitudOrigen = coorItalia.getLatitud();
					longitudOrigen = coorItalia.getLongitud();
					break;
				case "Srilanka":
					latitudOrigen = coorSrilanka.getLatitud();
					longitudOrigen = coorSrilanka.getLongitud();
					break;
				case "Brasilia":
					latitudOrigen = coorBrasilia.getLatitud();
					longitudOrigen = coorBrasilia.getLongitud();
					break;
				case "Nueva York":
					latitudOrigen = coorNuevaYork.getLatitud();
					longitudOrigen = coorNuevaYork.getLongitud();
					break;
				case "Canada":
					latitudOrigen = coorCanada.getLatitud();
					longitudOrigen = coorCanada.getLongitud();
					break;
				case "China":
					latitudOrigen = coorChina.getLatitud();
					longitudOrigen = coorChina.getLongitud();
					break;
				case "Espana":
					latitudOrigen = coorEspana.getLatitud();
					longitudOrigen = coorEspana.getLongitud();
					break;
				case "Londres":
					latitudOrigen = coorLondres.getLatitud();
					longitudOrigen = coorLondres.getLongitud();
					break;
				case "Japon":
					latitudOrigen = coorJapon.getLatitud();
					longitudOrigen = coorJapon.getLongitud();
					break;
				case "Marruecos":
					latitudOrigen = coorMarruecos.getLatitud();
					longitudOrigen = coorMarruecos.getLongitud();
					break;
				case "Sydney":
					latitudOrigen = coorSydney.getLatitud();
					longitudOrigen = coorSydney.getLongitud();
					break;
				case "Francia":
					latitudOrigen = coorFrancia.getLatitud();
					longitudOrigen = coorFrancia.getLongitud();
					break;
				default:
					latitudOrigen = 0.0;
					longitudOrigen = 0.0;
					break;
				}

				switch (destino) {
				case "Italia":
					latitudDestino = coorItalia.getLatitud();
					longitudDestino = coorItalia.getLongitud();
					break;
				case "Srilanka":
					latitudDestino = coorSrilanka.getLatitud();
					longitudDestino = coorSrilanka.getLongitud();
					break;
				case "Brasilia":
					latitudDestino = coorBrasilia.getLatitud();
					longitudDestino = coorBrasilia.getLongitud();
					break;
				case "Nueva York":
					latitudDestino = coorNuevaYork.getLatitud();
					longitudDestino = coorNuevaYork.getLongitud();
					break;
				case "Canada":
					latitudDestino = coorCanada.getLatitud();
					longitudDestino = coorCanada.getLongitud();
					break;
				case "China":
					latitudDestino = coorChina.getLatitud();
					longitudDestino = coorChina.getLongitud();
					break;
				case "Espana":
					latitudDestino = coorEspana.getLatitud();
					longitudDestino = coorEspana.getLongitud();
					break;
				case "Londres":
					latitudDestino = coorLondres.getLatitud();
					longitudDestino = coorLondres.getLongitud();
					break;
				case "Japon":
					latitudDestino = coorJapon.getLatitud();
					longitudDestino = coorJapon.getLongitud();
					break;
				case "Marruecos":
					latitudDestino = coorMarruecos.getLatitud();
					longitudDestino = coorMarruecos.getLongitud();
					break;
				case "Sydney":
					latitudDestino = coorSydney.getLatitud();
					longitudDestino = coorSydney.getLongitud();
					break;
				case "Francia":
					latitudDestino = coorFrancia.getLatitud();
					longitudDestino = coorFrancia.getLongitud();
					break;
				default:
					latitudDestino = 0.0;
					longitudDestino = 0.0;
					break;
				}	
					
					
			// si el pais de origen y el de destino son diferentes, se calcula el precio por
			// lejania
			if (!destino.equals(origen)) {
						double distanciaEntre2Aeropuertos = CoordenadasAeropuerto.calcularDistanciaPuntosSuperficieTierra(latitudOrigen, longitudOrigen,latitudDestino, longitudDestino);
						int primeros3digitos = Integer.parseInt(String.valueOf(distanciaEntre2Aeropuertos).substring(0, 3));
						//precioViaje = precioViaje * (primeros3digitos / 10);
						txtImpuestosaMostrar.setText(String.valueOf((primeros3digitos / 10)));
						
				} 
			else {
				txtImpuestosaMostrar.setText("0.0");
				}

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
		tablaVuelos.getColumnModel().getColumn(6).setMinWidth(120);
		tablaVuelos.getColumnModel().getColumn(6).setMaxWidth(120);
		tablaVuelos.getColumnModel().getColumn(7).setMinWidth(130);
		tablaVuelos.getColumnModel().getColumn(7).setMaxWidth(130);


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
					if (BD.buscarPasajero(con, dniAbuscar) != null) {

						Pasajero p = BD.buscarPasajero(con, dniAbuscar);
						txtNombre.setText(p.getNombre());
						txtApellido.setText(p.getApellido());
						txtDni.setText(p.getDni());
						int tf = p.getTelefono();
						txtTelefono.setText(String.valueOf(tf));
						txtDireccion.setText(p.getDireccion());
						int edad = p.getEdad();
						txtEdad.setText(String.valueOf(edad));
					} else {
						JOptionPane.showMessageDialog(null, "No se ha encontrado el pasajero con ese dni", "Error",
								JOptionPane.WARNING_MESSAGE);

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
	

		txtFecha.getCalendarButton().setEnabled(false);
		btnReservar = new JButton();
		btnReservar.setText("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtTelefono.getText().isBlank()) {

					int asientosComprados = (int) txtAsientos.getValue();
					String origen = txtOrigenMostrado.getText();
					String destino = txtDestinoMostrado.getText();

					if (!origen.isBlank() & !destino.isBlank()) {
						
				
						
						try {
							con=BD.initBD("Aeropuerto.db");
						} catch (DBException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						// ver si la cantidad de asientos que se quiere reservar es correcta
						int asientosDisponibles = Vuelo.CalcularAsientosRestantes(vueloObtenido, asientosComprados);
						if (asientosDisponibles != -1) {
							try {
								BD.actualizarAsientosDeVuelo(con, vueloObtenido, asientosDisponibles);
								
								// actualizar la tabla con la nueva informacion del vuelo, con un numero nuevo de asientos disponibles
								
								a = BD.obtenerVuelosSegunOrigenDestino(con, origen, destino);
			
								// vaciar la tabla cuando se selecciona un nuevo origen-destino
								while (modeloTablaVuelos.getRowCount() > 0) {
									int filas = tablaVuelos.getRowCount();
									if (0 < filas) {
										modeloTablaVuelos.removeRow(0);
									}

								}

								// Cargamos el ArrayList de Vuelos en el modelo de la tabla
								for (Vuelo vu : a) {
									String[] fila = { vu.getID(), vu.getOrigen(), vu.getDestino(), vu.getFecha(), vu.getHoraSalida(),
											vu.getHoraLlegada(), String.valueOf(vu.getAsientosMax()),String.valueOf(vu.getAsientosRestantes()) };
									modeloTablaVuelos.addRow(fila);
								}
								try {
									BD.closeBD(con);
								} catch (DBException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							
							}
						} else {
							try {
								BD.closeBD(con);
							} catch (DBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "No hay asientos libres suficientes. Quedan " + vueloObtenido.getAsientosRestantes(), "Error",
									JOptionPane.WARNING_MESSAGE);
							
							
						}
						
						

						// si el pais de origen y el de destino son diferentes, se calcula el precio por
						// lejania
						if (!destino.equals(origen)) {
							
							try {
								con=BD.initBD("Aeropuerto.db");
								BD.insertarTicket(con, 0,txtDni.getText(), lblIdVuelo.getText(), Clase.valueOf(opcionesClase.getSelectedItem().toString()), Double.parseDouble(txtPrecioFinalaMostrar.getText()), asientosComprados, editor.getText());
								JOptionPane.showMessageDialog (null, "Reserva realizada", "Correcto", JOptionPane.INFORMATION_MESSAGE);
								BD.closeBD(con);
							} catch (DBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							try {
								con=BD.initBD("Aeropuerto.db");
								BD.insertarTicket(con, 0,txtDni.getText(), lblIdVuelo.getText(), Clase.valueOf(opcionesClase.getSelectedItem().toString()), Double.parseDouble(txtPrecioFinalaMostrar.getText()), asientosComprados, editor.getText());
								JOptionPane.showMessageDialog (null, "Reserva realizada", "Correcto", JOptionPane.INFORMATION_MESSAGE);
								BD.closeBD(con);
							} catch (DBException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona un vuelo de la tabla", "Faltan datos",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Busca un pasajero primero", "Faltan datos",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnCancelar = new JButton();
		btnCancelar.setIcon(imagenCancelar);
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
		txtPrecioBase = new JLabel();
		txtPrecioBase.setEnabled(false);

		panelBusquedaVuelo.setBorder(BorderFactory.createTitledBorder(null, "Selecciona pais",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 12)));

		txtOrigen.setModel(new DefaultComboBoxModel<>(new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York",
				"Canada", "China", "Espana", "Londres", "Japon", "Marruecos", "Sydney", "Francia" }));

		txtDestino.setModel(new DefaultComboBoxModel<>(new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York",
				"Canada", "China", "Espana", "Londres", "Japon", "Marruecos", "Sydney", "Francia" }));

		lblOrigen.setText("Origen");

		lblDestino.setText("Destino");

		

		GroupLayout glPanelBusquedaVuelo = new GroupLayout(panelBusquedaVuelo);
		panelBusquedaVuelo.setLayout(glPanelBusquedaVuelo);
		glPanelBusquedaVuelo.setHorizontalGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addContainerGap()
						.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
						.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addGap(35, 35, 35))
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addGap(54, 54, 54).addComponent(lblOrigen)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblDestino).addGap(87, 87, 87))
				.addGroup(GroupLayout.Alignment.TRAILING,
						glPanelBusquedaVuelo.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBuscadorDestinos).addGap(49, 49, 49)));
		glPanelBusquedaVuelo.setVerticalGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(glPanelBusquedaVuelo.createSequentialGroup().addGap(32, 32, 32)
						.addGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lblOrigen).addComponent(lblDestino))
						.addGap(18, 18, 18)
						.addGroup(glPanelBusquedaVuelo.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
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
					.addGap(19)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDni)
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addComponent(lblApellido)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtApellido, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addComponent(lblTelefono)
							.addGap(18)
							.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnBuscarPasajero))
						.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
							.addGap(84)
							.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
								.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
									.addComponent(lblEdad, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(8)
									.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollDireccion, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
										.addGroup(glPanelBusquedaPasajero.createSequentialGroup()
											.addGap(55)
											.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))))
					.addGap(80))
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
						.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre)
						.addComponent(lblEdad)
						.addComponent(txtNombre))
					.addGap(31)
					.addGroup(glPanelBusquedaPasajero.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccion)
						.addComponent(lblApellido)
						.addComponent(txtApellido))
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

		GroupLayout glPanelVuelo = new GroupLayout(panelVuelo);
		glPanelVuelo.setHorizontalGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addGroup(glPanelVuelo
				.createSequentialGroup().addGap(19)
				.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addComponent(lblHoraSalida)
						.addGroup(glPanelVuelo.createSequentialGroup()
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING).addComponent(lblAsientos)
										.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblClase, Alignment.LEADING)
												.addComponent(lblPrecio, Alignment.LEADING)
												.addGroup(glPanelVuelo.createSequentialGroup().addComponent(lblIDVuelo)
														.addGap(16))))
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
										.addGroup(glPanelVuelo.createSequentialGroup().addGap(50)
												.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
														.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, 64,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(opcionesClase, GroupLayout.PREFERRED_SIZE, 136,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtPrecio, GroupLayout.PREFERRED_SIZE, 41,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(glPanelVuelo.createSequentialGroup().addGap(60).addGroup(glPanelVuelo
												.createParallelGroup(Alignment.LEADING)
												.addGroup(glPanelVuelo.createSequentialGroup().addComponent(lblIdVuelo)
														.addGap(34).addComponent(txtFecha, GroupLayout.PREFERRED_SIZE,
																118, GroupLayout.PREFERRED_SIZE))
												.addGroup(glPanelVuelo.createSequentialGroup()
														.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
																.addComponent(txtHoraSalida).addComponent(
																		txtHoraLlegada, GroupLayout.PREFERRED_SIZE, 79,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(49)
														.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
																.addComponent(lblOrigenMostrado,
																		GroupLayout.PREFERRED_SIZE, 53,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblDestinoMostrado,
																		GroupLayout.PREFERRED_SIZE, 103,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
																.addComponent(txtDestinoMostrado,
																		GroupLayout.PREFERRED_SIZE, 79,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(txtOrigenMostrado,
																		GroupLayout.PREFERRED_SIZE, 79,
																		GroupLayout.PREFERRED_SIZE)))))))
						.addComponent(lblHoraLlegada, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(51, Short.MAX_VALUE)));
		glPanelVuelo.setVerticalGroup(glPanelVuelo.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelVuelo.createSequentialGroup().addGap(17)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.TRAILING)
								.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblIDVuelo)
										.addComponent(lblIdVuelo))
								.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblHoraSalida)
								.addComponent(txtHoraSalida).addComponent(lblOrigenMostrado).addComponent(
										txtOrigenMostrado, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblHoraLlegada)
								.addComponent(txtHoraLlegada, GroupLayout.PREFERRED_SIZE, 15,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestinoMostrado).addComponent(txtDestinoMostrado,
										GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblClase)
								.addComponent(opcionesClase, GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE).addComponent(lblPrecio)
								.addComponent(txtPrecio))
						.addGap(18)
						.addGroup(glPanelVuelo.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAsientos))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelVuelo.setLayout(glPanelVuelo);

		

		btnCancelar.setText("Cancelar");

		txtPrecioBase.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPrecioBase.setForeground(new Color(255, 0, 0));
		txtPrecioBase.setText("Precio con descuento:");
		
		txtImpuestos = new JLabel();
		txtImpuestos.setText("Impuestos (en vuelos internacionales):");
		txtImpuestos.setForeground(Color.RED);
		txtImpuestos.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtImpuestos.setEnabled(false);
		
	
		txtPrecioTotal = new JLabel();
		txtPrecioTotal.setText("Total:");
		txtPrecioTotal.setForeground(Color.RED);
		txtPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPrecioTotal.setEnabled(false);
		


		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(32)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 795, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPrecioBaseaMostrar, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrecioBase, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
							.addGap(25)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtImpuestos, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtImpuestosaMostrar, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(txtPrecioFinalaMostrar, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addGap(48)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
									.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(24)
									.addComponent(panelVuelo, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))))
						.addGroup(layout.createSequentialGroup()
							.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
							.addComponent(panelBusquedaPasajero, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)))
					.addGap(559))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(17, Short.MAX_VALUE)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBusquedaPasajero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollTabla, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtPrecioBase, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtImpuestos, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtImpuestosaMostrar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtPrecioFinalaMostrar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPrecioBaseaMostrar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);

		pack();

		setVisible(true);

	}
}
