package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import bd.BD;
import bd.DBException;
import clases.Vuelo;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
	private ImageIcon imagenReloj;
	private static final String PATTERN =
			"([01]?[0-9]|2[0-3]):[0-5][0-9]";
	
	private static String valorLlegadaNueva;
	private static String valorSalidaNueva;
	private JLabel lblSeEsperanRetrasos;
	private static String llegadaObtenidaDeBBDD;
	private static String salidaObtenidaDeBBDD;
	public VerVuelos() {
		
		v = null;
		con = null;
		panelScroll = new JScrollPane();
		imagenBorrar = new ImageIcon("img/papelera.png");
		imagenCancelar = new ImageIcon("img/Cancelar.png");
		imagenReloj = new ImageIcon("img/clock.png");
		btnCancelar = new JButton();

		modeloTablaVuelos = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (column == 4 || column == 5) {
					return true;
				}
				return false;
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

		tabla = new JTable(modeloTablaVuelos);
		tabla.getModel().addTableModelListener(new TableModelListener() {
		
		@Override
		public void tableChanged(TableModelEvent e) {
			
			int fil = e.getFirstRow();
			int col= e.getColumn();
			
			 try {
				 con=BD.initBD("Aeropuerto.db");
				llegadaObtenidaDeBBDD=BD.obtenerHoraLlegadaDeVuelo(con, (String) modeloTablaVuelos.getValueAt(fil, 0));
				 salidaObtenidaDeBBDD=BD.obtenerHoraSalidaDeVuelo(con, (String) modeloTablaVuelos.getValueAt(fil, 0));
				 BD.closeBD(con);
			} catch (DBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
				 valorSalidaNueva = (String) modeloTablaVuelos.getValueAt(fil, 4);
				 valorLlegadaNueva = (String) modeloTablaVuelos.getValueAt(fil, 5);
				
				if (col==4) {
					
					boolean salidaCorrecta=ComprobarHoraInsertada(valorSalidaNueva);
					// si el dato es correcto, se queda en la tabla
					if(salidaCorrecta) {
						try {
							con=BD.initBD("Aeropuerto.db");
							String id = (String) modeloTablaVuelos.getValueAt(fil, 0);
							BD.actualizarHoraDespegueYAterrizaje(con, id, valorSalidaNueva, llegadaObtenidaDeBBDD);
							
							BD.closeBD(con);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					// si el dato es incorrecto, se carga el dato que se encontraba antes del cambio
					else {
						/* Queriamos eliminar las filas de la tabla, pero nada funcionaba.
						 * 
						 *  
						 *  Hemos probado:
						 *  
						 *  modeloTablaVuelos.setRowCount(0);
						 *  
						 *  DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
								dtm.setRowCount(0);
						 *  
						*  DefaultTableModel dm = (DefaultTableModel) tabla.getModel();
									int filas = dm.getRowCount();
								
									for (int i = filas - 1; i >= 0; i--) {
									    dm.removeRow(i);
									}
							 *  
							 *  
							 *  
						 *  Asi que optamos por cerrar la ventana y abrirla de nuevo, cargandola asi con todos sus datos anteriores*/
						dispose();
						VerVuelos v = new VerVuelos();
						boolean resultadoAdministradorActivo = VentanaAdministrador.VentanaAdminEstaActiva();
						boolean resultadoAzafatoActivo = VentanaAzafato.VentanaAzafatoEstaActiva();

						if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
							VentanaAdministrador.panelEscritorio.add(v);
							v.setVisible(true);

						} else {
							VentanaAzafato.panelEscritorio.add(v);
							v.setVisible(true);
						}
						
						
						
					}
				}
				if (col==5) {
					
					boolean llegadaCorrecta=ComprobarHoraInsertada(valorLlegadaNueva);
					
					if(llegadaCorrecta) {
						try {
							con=BD.initBD("Aeropuerto.db");
							String id4 = (String) modeloTablaVuelos.getValueAt(fil, 0);

							BD.actualizarHoraDespegueYAterrizaje(con, id4, salidaObtenidaDeBBDD, valorLlegadaNueva);
						
							BD.closeBD(con);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						
						dispose();
						VerVuelos v = new VerVuelos();
						boolean resultadoAdministradorActivo = VentanaAdministrador.VentanaAdminEstaActiva();
						boolean resultadoAzafatoActivo = VentanaAzafato.VentanaAzafatoEstaActiva();

						if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
							VentanaAdministrador.panelEscritorio.add(v);
							v.setVisible(true);

						} else {
							VentanaAzafato.panelEscritorio.add(v);
							v.setVisible(true);
						}
				}
		}
				
		
		}
	});
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
		
		//Renderer a la tabla
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 0) {
					c.setBackground(Color.LIGHT_GRAY);
				}else {
					c.setBackground(Color.WHITE);
				}

				if(column == 7) {
					int cant = Integer.parseInt(String.valueOf(value));
					if(cant <=10) {
						c.setBackground(Color.YELLOW);
						
					}else if(cant >10 && cant<=30) {
						c.setBackground(Color.cyan);
						
					}
					else {
						c.setBackground(Color.green);
					}
				}
				
				return c;
			}
		});

		
		panelScroll.setViewportView(tabla);
		tabla.setBackground(SystemColor.info);

		
		
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
		
		JLabel lblReloj = new JLabel();
		lblReloj.setIcon(imagenReloj);
		
		lblSeEsperanRetrasos = new JLabel("Se esperan retrasos? Modifica las horas de salida y llegada.");
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 812, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSeEsperanRetrasos, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, layout.createSequentialGroup()
									.addGap(67)
									.addComponent(lblReloj, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addComponent(btnEliminarVuelo, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
					.addGap(111))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(29)
					.addComponent(panelScroll, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblSeEsperanRetrasos, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addGap(1)
							.addComponent(lblReloj, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(33, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminarVuelo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGap(30))))
		);
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
	private boolean ComprobarHoraInsertada(String str) {
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(str);
		if(matcher.matches()){
			return true;
	
		}else{
			return false;
		
		}
		
	}
	
}
