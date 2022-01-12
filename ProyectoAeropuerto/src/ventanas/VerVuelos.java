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
	private static int horaSalidaInt,horaLlegadaInt,minutoSalidaInt,minutoLlegadaInt;
	private static final String PATTERN =
			"([01]?[0-9]|2[0-3]):[0-5][0-9]";
	
	private static String valorLlegadaAnterior;
	private static String valorSalidaAnterior;
	private static String valorLlegadaNueva;
	private static String valorSalidaNueva;
	public VerVuelos() {
		
		v = null;
		con = null;
		panelScroll = new JScrollPane();
		imagenBorrar = new ImageIcon("img/papelera.png");
		imagenCancelar = new ImageIcon("img/Cancelar.png");
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
		/*
		 * for (Vuelo vuelo : v) { String[] fila = { vuelo.getID(), vuelo.getOrigen(),
		 * vuelo.getDestino(), vuelo.getFecha(), vuelo.getHoraSalida(),
		 * vuelo.getHoraLlegada()
		 * ,String.valueOf(vuelo.getAsientosMax()),String.valueOf(vuelo.
		 * getAsientosRestantes()) }; modeloTablaVuelos.addRow(fila); }
		 */

		tabla = new JTable(modeloTablaVuelos);
		tabla.getModel().addTableModelListener(new TableModelListener() {
		
		@Override
		public void tableChanged(TableModelEvent e) {
			
			int fil = e.getFirstRow();
			int col= e.getColumn();
			
			 try {
				 con=BD.initBD("Aeropuerto.db");
				valorLlegadaAnterior= BD.obtenerHoraLlegadaDeVuelo(con, (String) modeloTablaVuelos.getValueAt(fil, 0));
				valorSalidaAnterior= BD.obtenerHoraSalidaDeVuelo(con, (String) modeloTablaVuelos.getValueAt(fil, 0));

			} catch (DBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//while(sePermiteCambiarDeCelda) {
				
				String id = (String) modeloTablaVuelos.getValueAt(fil, 0);
				 valorSalidaNueva = (String) modeloTablaVuelos.getValueAt(fil, 4);
				 valorLlegadaNueva = (String) modeloTablaVuelos.getValueAt(fil, 5);
				
				if (col==4) {
					boolean salidaCorrecta=ComprobarHoraInsertada(valorSalidaNueva, fil,4);
					
					if(salidaCorrecta) {
						try {
							con=BD.initBD("Aeropuerto.db");
							BD.actualizarHoraDespegueYAterrizaje(con, id, valorSalidaNueva, valorLlegadaNueva);
							BD.closeBD(con);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				if (col==5) {
					boolean llegadaCorrecta=ComprobarHoraInsertada(valorLlegadaNueva, fil ,5);
					
					if(llegadaCorrecta) {
						try {
							con=BD.initBD("Aeropuerto.db");
							BD.actualizarHoraDespegueYAterrizaje(con, id, valorSalidaNueva, valorLlegadaNueva);
							BD.closeBD(con);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DBException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
		//}
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
	private boolean ComprobarHoraInsertada(String str, int fila, int columna) {
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(str);
		if(matcher.matches()){
			JOptionPane.showMessageDialog(null, "Formato correcto, vuelo actualizado", "Error",
			        JOptionPane.WARNING_MESSAGE);
			System.out.println("Time "+ str +" is valid 24Hours Format");
			return true;
	
		}else{
			
			modeloTablaVuelos.setValueAt(valorSalidaAnterior,fila,4);
			modeloTablaVuelos.setValueAt(valorLlegadaAnterior,fila,5);
			JOptionPane.showMessageDialog(null, "Formato de la hora de llegada incorrecto", "Error",
			        JOptionPane.WARNING_MESSAGE);
			System.out.println("Time "+ str +" is invalid 24Hours Format");
			
			
			
			
			return false;
		
		}
		
	}
	
	
}
