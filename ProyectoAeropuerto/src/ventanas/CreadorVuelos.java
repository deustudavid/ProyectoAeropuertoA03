package ventanas;


import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CreadorVuelos extends javax.swing.JInternalFrame {
	
	   
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblTituloVentana;
    private javax.swing.JLabel lbIIDVuelo;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHoraSalida;
    private javax.swing.JLabel lblHoraLlegada;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JPanel panelCreacionVuelo;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JComboBox<String> txtDestino;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtIDVuelo;
    private javax.swing.JComboBox<String> txtOrigen;
    private ImageIcon imagenGuardar;
  

    public CreadorVuelos() {
    	
    	imagenGuardar = new ImageIcon("img/guardar.png"); 

        panelCreacionVuelo = new javax.swing.JPanel();
        lblTituloVentana = new javax.swing.JLabel();
        lbIIDVuelo = new javax.swing.JLabel();
        lblOrigen = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        txtIDVuelo = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lblHoraSalida = new javax.swing.JLabel();
        lblHoraLlegada = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnGuardar.setIcon(imagenGuardar);
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCancelar = new javax.swing.JButton();
        txtOrigen = new javax.swing.JComboBox<>();
        txtDestino = new javax.swing.JComboBox<>();

        panelCreacionVuelo.setBackground(new java.awt.Color(51, 51, 255));

        lblTituloVentana.setFont(new Font("Tahoma", Font.BOLD, 13)); 
        lblTituloVentana.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVentana.setText("Vuelo");

        lbIIDVuelo.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbIIDVuelo.setForeground(new java.awt.Color(255, 255, 255));
        lbIIDVuelo.setText("ID Vuelo");

        lblOrigen.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblOrigen.setForeground(new java.awt.Color(255, 255, 255));
        lblOrigen.setText("Origen");

        lblDestino.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblDestino.setForeground(new java.awt.Color(255, 255, 255));
        lblDestino.setText("Destino");

        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha");

        lblHoraSalida.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraSalida.setText("Hora Salida");

        lblHoraLlegada.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraLlegada.setText("Hora Llegada");

        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio");

        btnGuardar.setText("Guardar");
      

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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

        txtOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));

        txtDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));
        
        JComboBox<String> opcionesHoraSalida = new JComboBox<String>();
        opcionesHoraSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6:00\t", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00" }));
       
        JComboBox<String> opcionesHoraLlegada = new JComboBox<String>();
        opcionesHoraLlegada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "15:00\t", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00" }));
        
        javax.swing.GroupLayout glPanelCreacionVuelo = new javax.swing.GroupLayout(panelCreacionVuelo);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(panelCreacionVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelCreacionVuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }
}
