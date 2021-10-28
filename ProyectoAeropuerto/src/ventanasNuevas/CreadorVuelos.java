package ventanasNuevas;


import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;


public class CreadorVuelos extends javax.swing.JInternalFrame {
	
	   
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblTituloVentana;
    private javax.swing.JLabel lbI_IDVuelo;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHoraSalida;
    private javax.swing.JLabel lblHoraLlegada;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JPanel panelCreacionVuelo;
    private javax.swing.JTextField txtHoraLlegada;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JComboBox<String> txtDestino;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtIDVuelo;
    private javax.swing.JComboBox<String> txtOrigen;
  

    public CreadorVuelos() {

        panelCreacionVuelo = new javax.swing.JPanel();
        lblTituloVentana = new javax.swing.JLabel();
        lbI_IDVuelo = new javax.swing.JLabel();
        lblOrigen = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        txtIDVuelo = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        lblHoraSalida = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JTextField();
        txtHoraLlegada = new javax.swing.JTextField();
        lblHoraLlegada = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtOrigen = new javax.swing.JComboBox<>();
        txtDestino = new javax.swing.JComboBox<>();

        panelCreacionVuelo.setBackground(new java.awt.Color(51, 51, 255));

        lblTituloVentana.setFont(new Font("Tahoma", Font.BOLD, 13)); 
        lblTituloVentana.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloVentana.setText("Vuelo");

        lbI_IDVuelo.setFont(new java.awt.Font("Tahoma", 1, 11));
        lbI_IDVuelo.setForeground(new java.awt.Color(255, 255, 255));
        lbI_IDVuelo.setText("ID Vuelo");

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
        lblHoraLlegada.setText("HoraLlegada");

        lblPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecio.setText("Precio");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              dispose();
              VentanaPadre.desbloquearBotones();
            }
        });

        txtOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));

        txtDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "Usa", "Canada", "China" }));

        javax.swing.GroupLayout gl_panelCreacionVuelo = new javax.swing.GroupLayout(panelCreacionVuelo);
        gl_panelCreacionVuelo.setHorizontalGroup(
        	gl_panelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        			.addGap(34)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTituloVentana)
        						.addComponent(lbI_IDVuelo))
        					.addGap(28)
        					.addComponent(txtIDVuelo, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblOrigen)
        						.addComponent(lblDestino))
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(txtOrigen, 0, 136, Short.MAX_VALUE)
        						.addComponent(txtDestino, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        			.addGap(103)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblFecha)
        						.addComponent(lblHoraSalida)
        						.addComponent(lblHoraLlegada))
        					.addGap(62)
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(txtFecha, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        						.addComponent(txtHoraSalida)
        						.addComponent(txtHoraLlegada)))
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addComponent(lblPrecio)
        					.addGap(52)
        					.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(95, Short.MAX_VALUE))
        		.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        			.addContainerGap(455, Short.MAX_VALUE)
        			.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
        			.addGap(39))
        );
        gl_panelCreacionVuelo.setVerticalGroup(
        	gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        			.addGap(36)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblTituloVentana)
        					.addComponent(lblFecha))
        				.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lbI_IDVuelo)
        				.addComponent(txtIDVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblHoraSalida)
        				.addComponent(txtHoraSalida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(28)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblOrigen)
        				.addComponent(txtHoraLlegada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblHoraLlegada)
        				.addComponent(txtOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addGap(24)
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblDestino)
        						.addComponent(txtDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(gl_panelCreacionVuelo.createSequentialGroup()
        					.addGap(34)
        					.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblPrecio)
        						.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        			.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
        			.addGroup(gl_panelCreacionVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
        			.addGap(43))
        );
        panelCreacionVuelo.setLayout(gl_panelCreacionVuelo);

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
