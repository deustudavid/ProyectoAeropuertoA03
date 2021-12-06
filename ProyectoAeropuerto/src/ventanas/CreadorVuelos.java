package ventanas;


import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import bd.BD;
import bd.DBException;
import clases.Vuelo;
import main.VentanaInicio;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
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
    private JLabel lblPrecio;
    private JPanel panelCreacionVuelo;
    private com.toedter.calendar.JDateChooser txtFecha;
    private JComboBox<String> txtDestino;
    private JTextField txtPrecio;
    private JTextField txtIDVuelo;
    private JComboBox<String> txtOrigen;
    private ImageIcon imagenGuardar;
  

    public CreadorVuelos() {
    	
    	imagenGuardar = new ImageIcon("img/guardar.png"); 

        panelCreacionVuelo = new JPanel();
        lblTituloVentana = new JLabel();
        lbIIDVuelo = new JLabel();
        lblOrigen = new JLabel();
        lblDestino = new JLabel();
        txtIDVuelo = new JTextField();
        lblFecha = new JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lblHoraSalida = new JLabel();
        lblHoraLlegada = new JLabel();
        lblPrecio = new JLabel();
        txtPrecio = new JTextField();
        btnGuardar = new JButton();
        btnGuardar.setIcon(imagenGuardar);
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
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

        lblPrecio.setForeground(new Color(255, 255, 255));
        lblPrecio.setText("Precio");
        
        
        JComboBox<String> opcionesHoraSalida = new JComboBox<String>();
        opcionesHoraSalida.setModel(new DefaultComboBoxModel<>(new String[] { "6:00\t", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00" }));
       
        JComboBox<String> opcionesHoraLlegada = new JComboBox<String>();
        opcionesHoraLlegada.setModel(new DefaultComboBoxModel<>(new String[] { "15:00\t", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00" }));

        btnGuardar.setText("Guardar");
        
        //Procedimiento que guarda un vuelo en la base de datos
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	BD db = new BD();
            	

            	try {
            		
            		db.initBD(title);
            		
            		Vuelo vuelo = new Vuelo();
                	
                	Object selectedItem = txtDestino.getSelectedItem();
                	String destinoDB = selectedItem.toString();
                	vuelo.setDestino(destinoDB);
                	
                	Object selectedItem2 = txtOrigen.getSelectedItem();
                	String origenDB = selectedItem2.toString();
                	vuelo.setOrigen(origenDB);
                   
                	
                	Object selectedItem3 = opcionesHoraLlegada.getSelectedItem();
                	String hLlegadaDB = selectedItem3.toString();
                	vuelo.setHoraLlegada(hLlegadaDB);
                	
                	
                	Object selectedItem4 = opcionesHoraSalida.getSelectedItem();
                	String hSalidaDB = selectedItem4.toString();
                	vuelo.setHoraSalida(hSalidaDB);
                	
                	
                	Date date = txtFecha.getDate();
                    vuelo.setFecha(date);
                    
					
                	vuelo.setID(txtIDVuelo.getText());
                	vuelo.setPrecio( Double.valueOf(txtPrecio.getText()));
                	
	
                	Connection con = null;      	
                	
					con = BD.initBD("Usuario.db");
					//CreadorVuelos.logger.log(Level.INFO, "Conexion con la base de datos abierta");
					BD.insertarVuelo(con, vuelo);
					JOptionPane.showMessageDialog(null, "Vuelo guardado correctamente");
					BD.closeBD(con);


				} catch (Exception e) {
					// TODO: handle exception
				}
            }	
        
        });
      

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

        txtOrigen.setModel(new DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));

        txtDestino.setModel(new DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));
        
        
        
        GroupLayout glPanelCreacionVuelo = new GroupLayout(panelCreacionVuelo);
        glPanelCreacionVuelo.setHorizontalGroup(
        	glPanelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addGap(34)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTituloVentana)
        						.addComponent(lbIIDVuelo))
        					.addGap(28)
        					.addComponent(txtIDVuelo, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblOrigen)
        						.addComponent(lblDestino))
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(txtOrigen, 0, 136, Short.MAX_VALUE)
        						.addComponent(txtDestino, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        			.addGap(103)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblFecha)
        						.addComponent(lblHoraSalida)
        						.addComponent(lblHoraLlegada))
        					.addGap(62)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(opcionesHoraLlegada, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        						.addComponent(opcionesHoraSalida, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addComponent(lblPrecio)
        					.addGap(52)
        					.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(103, Short.MAX_VALUE))
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addContainerGap(455, Short.MAX_VALUE)
        			.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
        			.addGap(39))
        );
        glPanelCreacionVuelo.setVerticalGroup(
        	glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        		.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        			.addGap(36)
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        				.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblTituloVentana)
        					.addComponent(lblFecha))
        				.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(18)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lbIIDVuelo)
        						.addComponent(txtIDVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblHoraSalida))
        					.addGap(28)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblOrigen)
        						.addComponent(lblHoraLlegada)
        						.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(opcionesHoraLlegada, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(18)
        					.addComponent(opcionesHoraSalida, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(24)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblDestino)
        						.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(glPanelCreacionVuelo.createSequentialGroup()
        					.addGap(34)
        					.addGroup(glPanelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblPrecio)
        						.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
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