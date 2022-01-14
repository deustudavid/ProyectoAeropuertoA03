package ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import bd.BD;
import bd.DBException;
import clases.Azafato;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class VisorAzafatos extends JInternalFrame  {

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
	private JButton btnCancelar;
	private JLabel lblInfo;
	
	public VisorAzafatos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		con=null;
		JPanel pNorte = new JPanel();
		pNorte.setLayout(new GridLayout(0, 2, 0, 0));
		
		azafatosJTable = new JTable();
		azafatosJTable.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			
				int fila = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
				String usuario=(String) azafatosJTable.getModel().getValueAt(fila, 0);
				
				lblInfo.setText("Gestor de azafatos. Usuario :"+usuario);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		azafatosJTable.setCellSelectionEnabled(true);
	

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
		btnFiltro.setBackground(Color.CYAN);
		
		pNorte.add(txtFiltro);
		pNorte.add(btnFiltro);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.ORANGE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean resultadoAdministradorActivo = ventanas.VentanaAdministrador.VentanaAdminEstaActiva();
				boolean resultadoAzafatoActivo = ventanas.VentanaAzafato.VentanaAzafatoEstaActiva();

				if (resultadoAdministradorActivo == true && resultadoAzafatoActivo == false) {
					VentanaAdministrador.desbloquearBotones();

				} else {
					VentanaAzafato.desbloquearBotones();

				}
				
				
					try {
						BD.closeBD(con);
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				dispose();
			}
		});
		
		lblInfo = new JLabel("Gestor de azafatos. Usuario :");
		lblInfo.setForeground(Color.BLUE);
		lblInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		pNorte.add(lblInfo);
		pNorte.add(btnCancelar);
		
		updateUI(azafatosObtenidos);
		 

				
		azafatosJTable.getColumnModel().getColumn(0).setMinWidth(60);
		azafatosJTable.getColumnModel().getColumn(1).setMinWidth(110);
		azafatosJTable.getColumnModel().getColumn(2).setMinWidth(110);
		azafatosJTable.getColumnModel().getColumn(2).setMaxWidth(110);
		azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
		azafatosJTable.getColumnModel().getColumn(3).setMinWidth(130);
		
		azafatosJTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				//Toda la fila en gris para aquellos cuya experiencia sea menor a 15 años
				int num=(int) azafatosJTable.getModel().getValueAt(row, 2);
				if(num<=15) {
					c.setBackground(Color.LIGHT_GRAY);
				}else {
					c.setBackground(Color.WHITE);
				}
				
				
				return c;
			}
		});

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
	
	/**
	 * actualiza los datos de la tabla
	 * @param List<Azafato> azafatosObtenidos
	 */
	private void updateUI(List<Azafato> azafatosObtenidos) {
		azafatosJTable.setModel(new AzafatosTableModel(azafatosObtenidos));
		infoLabel.setText(String.format("%d azafatos. ", azafatosObtenidos.size()) );
	}
	
	

	/**
	 * Carga en el tree de la parte izquierda todos los diferentes anyos de experiencia de los azafatos de la BBDD
	 */
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
