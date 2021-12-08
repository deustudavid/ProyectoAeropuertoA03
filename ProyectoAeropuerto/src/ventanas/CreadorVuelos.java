package ventanas;


import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JTextFieldDateEditor;

import bd.BD;
import bd.DBException;
import clases.Vuelo;
import main.VentanaInicio;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;



public class CreadorVuelos extends JInternalFrame {
	
	   
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JLabel lblTituloVentana;
    private JLabel lbIIDVuelo;
    private JLabel lblOrigen;
    private JLabel lblDestino;
    private JLabel lblFecha;
    private JLabel lblHoraSalida;
    private JLabel lblHoraLlegada;
    private JPanel panelCreacionVuelo;
    private com.toedter.calendar.JDateChooser txtFecha;
    private JComboBox<String> txtDestino;
    private JTextField txtIDVuelo;
    private JComboBox<String> txtOrigen;
    private ImageIcon imagenGuardar;
    private JSpinner spinnerAsientos;
  
    private static boolean correctoID,correctoFecha;
    private static String id;
    private static String fechaStr;
    private JLabel lblMensajeID;

    public static Connection con;
    
    public CreadorVuelos() {
    	 spinnerAsientos = new JSpinner();
         spinnerAsientos.setModel(new SpinnerNumberModel(1, 1, 50, 1));
         fechaStr="";
    	con=null;
    	correctoID=false;
    	correctoFecha=false;
    	id="";
    	imagenGuardar = new ImageIcon("img/guardar.png"); 

        panelCreacionVuelo = new JPanel();
        lblTituloVentana = new JLabel();
        lbIIDVuelo = new JLabel();
        lblOrigen = new JLabel();
        lblDestino = new JLabel();
        txtIDVuelo = new JTextField();
        txtIDVuelo.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyReleased(KeyEvent e) {
	    		String erID = "^[A-Za-z0-9]+";//solo contiene letras y numeros
				String IDIntroducida= txtIDVuelo.getText();
				  correctoID = IDIntroducida.matches(erID);
				if (correctoID) {
					lblMensajeID.setText("*");
					 id= txtIDVuelo.getText();
				}else {
					lblMensajeID.setText("Solo letras y números");
				}
	    	}
	    });
        lblFecha = new JLabel();
        // HACER QUE EL TEXTFIELD DONDE APARECE LA FECHA TRAS SELECCIONARLA CON JFILECHOOSER NO SE PUEDA EDITAR
        txtFecha = new com.toedter.calendar.JDateChooser();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) txtFecha.getDateEditor();
        editor.setEditable(false);
        // solo se puede seleccionar una fecha a partir de la de hoy
        txtFecha.setMinSelectableDate(new Date(System.currentTimeMillis()));
       
        lblHoraSalida = new JLabel();
        lblHoraLlegada = new JLabel();
        btnGuardar = new JButton();
        btnGuardar.setIcon(imagenGuardar);
 
        btnCancelar = new JButton();
        txtOrigen = new JComboBox<>();
        txtDestino = new JComboBox<>();

        panelCreacionVuelo.setBackground(new Color(51, 51, 255));

        lblTituloVentana.setFont(new Font("Tahoma", Font.BOLD, 13)); 
        lblTituloVentana.setForeground(new Color(255, 255, 255));
        lblTituloVentana.setText("Vuelo");

        lbIIDVuelo.setFont(new Font("Tahoma", 1, 11));
        lbIIDVuelo.setForeground(new Color(255, 255, 255));
        lbIIDVuelo.setText("ID Vuelo");

        lblOrigen.setFont(new Font("Tahoma", 1, 11)); 
        lblOrigen.setForeground(new Color(255, 255, 255));
        lblOrigen.setText("Origen");

        lblDestino.setFont(new Font("Tahoma", 1, 11)); 
        lblDestino.setForeground(new Color(255, 255, 255));
        lblDestino.setText("Destino");

        lblFecha.setForeground(new Color(255, 255, 255));
        lblFecha.setText("Fecha");

        lblHoraSalida.setForeground(new Color(255, 255, 255));
        lblHoraSalida.setText("Hora Salida");

        lblHoraLlegada.setForeground(new Color(255, 255, 255));
        lblHoraLlegada.setText("Hora Llegada");
        
        
        JComboBox<String> opcionesHoraSalida = new JComboBox<String>();
        opcionesHoraSalida.setModel(new DefaultComboBoxModel<>(new String[] { "6:00\t", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00" }));
       
        JComboBox<String> opcionesHoraLlegada = new JComboBox<String>();
        opcionesHoraLlegada.setModel(new DefaultComboBoxModel<>(new String[] { "15:00\t", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00" }));

        btnGuardar.setText("Guardar");
        
     
        
        //Procedimiento que guarda un vuelo en la base de datos
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            
            	Date fecha=null; 
        		fecha= txtFecha.getDate();
        		if (!(fecha == null)) {
        			
        			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            		fechaStr = sdf.format(fecha);
            		correctoFecha=true;
				}
        	
            	if (correctoID &&  correctoFecha ) {
	            	try {
						con = BD.initBD("Aeropuerto.db");
					} catch (DBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		
        			
						try {
							if (!BD.existeVuelo(con, id)) {
								
									con=BD.initBD("Aeropuerto.db");
									
							    	
							    	Object destino = txtDestino.getSelectedItem();
							    	String destinoStr = destino.toString();
							    	
							    	Object origen = txtOrigen.getSelectedItem();
							    	String origenStr = origen.toString();
							       
							    	
							    	Object llegada = opcionesHoraLlegada.getSelectedItem();
							    	String llegadaStr = llegada.toString();
							    	
							    	
							    	Object salida = opcionesHoraSalida.getSelectedItem();
							    	String salidaStr = salida.toString();
							    
							    	int numAsientos = (Integer) spinnerAsientos.getValue();
							    	int asientosDisponibles=numAsientos;
							    	
									try {
										BD.insertarVuelo(con, id,origenStr,destinoStr,fechaStr,salidaStr,llegadaStr,numAsientos,asientosDisponibles);
									} catch (DBException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "Vuelo guardado correctamente");
									try {
										BD.closeBD(con);
									} catch (DBException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									

								
								
							}else {
								try {
									BD.closeBD(con);
								} catch (DBException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Ya existe un vuelo con ese id", "Error", JOptionPane.WARNING_MESSAGE);
							}
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DBException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			

            	
        		
        		
            }
            	else {
     				JOptionPane.showMessageDialog(null, "Quedan campos vacíos o incorrectos.", "Error", JOptionPane.WARNING_MESSAGE);
     			}
        
        }});
      

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	dispose();
            	boolean resultadoAdministradorActivo=ventanas.VentanaAdministrador.VentanaAdminEstaActiva();
            	boolean resultadoAzafatoActivo=ventanas.VentanaAzafato.VentanaAzafatoEstaActiva();
            	
            	if (resultadoAdministradorActivo==true && resultadoAzafatoActivo==false) {
            		VentanaAdministrador.desbloquearBotones();
					
				}else {
	            	VentanaAzafato.desbloquearBotones();

				}
               
            }
        });

        txtOrigen.setModel(new DefaultComboBoxModel<>(new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York", "Canada", "China", "Espana", "Londres", "Japon", "Marruecos", "Sydney", "Francia" }));

        txtDestino.setModel(new DefaultComboBoxModel<>(new String[] { "Italia", "Srilanka", "Brasilia", "Nueva York", "Canada", "China", "Espana", "Londres", "Japon", "Marruecos", "Sydney", "Francia" }));

        
        JLabel lblNumAsientosDisponibles = new JLabel();
        lblNumAsientosDisponibles.setText("Num. asientos");
        lblNumAsientosDisponibles.setForeground(Color.WHITE);
        
       
        
        lblMensajeID = new JLabel("*");
        lblMensajeID.setForeground(Color.ORANGE);
        
     
        
        
        
        GroupLayout glPanelCreacionVuelo = new GroupLayout(panelCreacionVuelo);
        glPanelCreacionVuelo.setHorizontalGroup(
        	glPanelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addGap(34)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, glPanelCreacionVuelo.createSequentialGroup()
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTituloVentana)
        						.addComponent(lbIIDVuelo))
        					.addGap(28))
        				.addComponent(lblOrigen)
        				.addComponent(lblDestino))
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(txtDestino, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(txtOrigen, 0, 136, Short.MAX_VALUE)
        				.addComponent(lblMensajeID, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        				.addComponent(txtIDVuelo))
        			.addGap(103)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblFecha)
        				.addComponent(lblHoraSalida)
        				.addComponent(lblHoraLlegada)
        				.addComponent(lblNumAsientosDisponibles, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
        			.addGap(40)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addComponent(opcionesHoraLlegada, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        				.addComponent(opcionesHoraSalida, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
        				.addComponent(spinnerAsientos, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(103, Short.MAX_VALUE))
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addContainerGap(470, Short.MAX_VALUE)
        			.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
        			.addGap(39))
        );
        glPanelCreacionVuelo.setVerticalGroup(
        	glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(36)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        						.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        							.addComponent(lblTituloVentana)
        							.addComponent(lblFecha))
        						.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(49)
        					.addComponent(lblMensajeID)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbIIDVuelo)
        						.addComponent(txtIDVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblHoraSalida))
        					.addGap(28)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblOrigen)
        						.addComponent(lblHoraLlegada)
        						.addComponent(opcionesHoraLlegada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(opcionesHoraSalida, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        			.addGap(24)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblDestino)
        				.addComponent(lblNumAsientosDisponibles)
        				.addComponent(spinnerAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        			.addGap(43))
        );
        panelCreacionVuelo.setLayout(glPanelCreacionVuelo);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(panelCreacionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCreacionVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }
}