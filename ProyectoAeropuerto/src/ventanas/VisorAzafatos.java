package ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import bd.BD;
import bd.DBException;
import clases.Azafato;
import main.VentanaInicio;

import java.awt.GridLayout;
import java.awt.HeadlessException;

public class VisorAzafatos extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static Connection con;	
	private JTable azafatosJTable;
	private List<Azafato> azafatosObtenidos;
	private JLabel infoLabel;
	JTextField txtFiltro;
	
	
	private JTree arbolExperiencia;
	private DefaultTreeModel modeloArbol;
	
	public VisorAzafatos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		con=null;
		
		azafatosJTable = new JTable();
		azafatosJTable.setCellSelectionEnabled(true);
		azafatosJTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); 

		getContentPane().add(new JScrollPane(azafatosJTable), BorderLayout.CENTER);
		
		
		
		
			try {
				con=BD.initBD("Aeropuerto.db");
				azafatosObtenidos=BD.obtenerAzafatos(con);
			} catch (DBException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
		infoLabel = new JLabel();
		getContentPane().add(infoLabel, BorderLayout.SOUTH);
		
		
		txtFiltro = new JTextField("Caracteres a buscar:");
		JButton btnFiltro = new JButton("FILTRAR FUNCIONES");
		JPanel pNorte = new JPanel();
		pNorte.setLayout(new GridLayout(0, 2, 0, 0));
		pNorte.add(txtFiltro);
		pNorte.add(btnFiltro);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		updateUI(azafatosObtenidos);
		azafatosJTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); 

				
		azafatosJTable.getColumnModel().getColumn(0).setMinWidth(60);
		azafatosJTable.getColumnModel().getColumn(1).setMinWidth(110);
		azafatosJTable.getColumnModel().getColumn(2).setMinWidth(110);
		azafatosJTable.getColumnModel().getColumn(2).setMaxWidth(110);
		azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
		azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);

		azafatosJTable.addKeyListener(new KeyListener() {

	        @Override
	        public void keyTyped(KeyEvent e) {

	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            // TODO Auto-generated method stub
	            if(e.getKeyCode() == KeyEvent.VK_DELETE) {
	            	
					int fil = azafatosJTable.getSelectedRow();
					
					if(fil!=-1) {
						String usuario =  (String) azafatosJTable.getValueAt(fil, 0);
						try {
							int opcion = JOptionPane.showConfirmDialog(null, "Borrar este azafato?");
							if(opcion == JOptionPane.YES_OPTION) { 
								
								BD.eliminarAzafato(con, usuario);
								JOptionPane.showMessageDialog(null, "Se ha eliminado el azafato");
								
							}
							
							

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						List<Azafato> azafatosRestantes;
						try {
							azafatosRestantes = BD.obtenerAzafatos(con);
							updateUI(azafatosRestantes);
							cargarArbol();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
	            }
	        }
	    });
		
		

		azafatosJTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {

				
				int fil = e.getFirstRow();
				
				
				String usuario = (String) azafatosJTable.getValueAt(fil, 0);
				String contrasenia = (String) azafatosJTable.getValueAt(fil, 1);
				int exp = (int) azafatosJTable.getValueAt(fil, 2);
				String funcion = (String) azafatosJTable.getValueAt(fil, 3);

				
					try {
						BD.actualizarAzafato(con, usuario, contrasenia, exp, funcion);
						JOptionPane.showMessageDialog(null, "Se ha actualizado el azafato");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					List<Azafato> azafatosRestantes;
					try {
						azafatosRestantes = BD.obtenerAzafatos(con);
						updateUI(azafatosRestantes);
						cargarArbol();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
			
			}

			
		});
		
	

		setSize(1300, 600);
		
		
		arbolExperiencia = new JTree();
		cargarArbol();
		getContentPane().add(new JScrollPane(arbolExperiencia),BorderLayout.WEST);
		
		
		arbolExperiencia.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath tp = e.getPath();
				Object[] ruta = tp.getPath();
				String numero = ruta[ruta.length-1].toString();
				String[] arrayDelTituloNodo=numero.split(" ");
				String datoNumerico=arrayDelTituloNodo[0];
				List<Azafato> azafatosConseguidos;
				try {
					if(numero.equals("EXPERIENCIA DE AZAFATOS"))
						azafatosConseguidos = BD.obtenerAzafatos(con);
					else {
						azafatosConseguidos = BD.ObtenerAzafatosSegunExperiencia(con, Integer.parseInt(datoNumerico));
					}
					updateUI(azafatosConseguidos);
					azafatosJTable.getColumnModel().getColumn(0).setMinWidth(60);
					azafatosJTable.getColumnModel().getColumn(1).setMinWidth(110);
					azafatosJTable.getColumnModel().getColumn(2).setMinWidth(110);
					azafatosJTable.getColumnModel().getColumn(2).setMaxWidth(110);
					azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
					azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		btnFiltro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String caracteresInsertados = txtFiltro.getText();
				List<Azafato> azafatosConseguidos;
				try {
					azafatosConseguidos = BD.obtenerFuncionAzafatoSegunTexto(con,caracteresInsertados);
					updateUI(azafatosConseguidos);
					azafatosJTable.getColumnModel().getColumn(0).setMinWidth(60);
					azafatosJTable.getColumnModel().getColumn(1).setMinWidth(110);
					azafatosJTable.getColumnModel().getColumn(2).setMinWidth(110);
					azafatosJTable.getColumnModel().getColumn(2).setMaxWidth(110);
					azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
					azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		
		setVisible(true);
		
	}
	
	private void updateUI(List<Azafato> azafatosObtenidos) {
		azafatosJTable.setModel(new AzafatosTableModel(azafatosObtenidos));
		azafatosJTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); 

		infoLabel.setText(String.format("%d azafatos", azafatosObtenidos.size()));
	}
	
	
	/*
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VisorAzafatos();				
			}
		});

	}
	 */
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		
		
			try {
				BD.closeBD(con);
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
		
	}

	@Override
	public void windowClosed(WindowEvent e) {


		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private void cargarArbol() {
		try {
			TreeSet<Integer> aniosDeExperiencia = BD.obtenerTodosLosAnyosDeExperiencia(con);
			DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("EXPERIENCIA DE AZAFATOS");
			modeloArbol = new DefaultTreeModel(raiz);
			int i=0;
			for(Integer n: aniosDeExperiencia) {
				modeloArbol.insertNodeInto(new DefaultMutableTreeNode(n + " años"), raiz, i);
				i++;
			}
			arbolExperiencia.setModel(modeloArbol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	

}
