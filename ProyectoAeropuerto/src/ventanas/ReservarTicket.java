package ventanas;



import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ReservarTicket extends javax.swing.JInternalFrame {
	

    
    private javax.swing.JLabel lbl_IdVuelo;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnBuscadorDestinos;
    private javax.swing.JButton btnBuscarPasajero;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JLabel lbl_ID_Vuelo;
    private javax.swing.JLabel lblHoraSalida;
    private javax.swing.JLabel lblClase;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblAsientos;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblTicketNum;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelBusquedaVuelo;
    private javax.swing.JPanel panelVuelo;
    private javax.swing.JPanel panelBusquedaPasajero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaVuelos;
    private javax.swing.JComboBox<String> txtClaseVuelo;
    private javax.swing.JTextField txtDni;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JComboBox<String> txtDestino;
    private javax.swing.JLabel txtHoraSalida;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtTelefono;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JSpinner txtAsientos;
    private javax.swing.JComboBox<String> txtOrigen;
    private javax.swing.JLabel txtTicketNum;
    private javax.swing.JLabel txtPrecioTotal;
   

    public ReservarTicket() {

        panelBusquedaVuelo = new javax.swing.JPanel();
        txtOrigen = new javax.swing.JComboBox<>();
        txtDestino = new javax.swing.JComboBox<>();
        lblOrigen = new javax.swing.JLabel();
        lblDestino = new javax.swing.JLabel();
        btnBuscadorDestinos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVuelos = new javax.swing.JTable();
        lblTicketNum = new javax.swing.JLabel();
        txtTicketNum = new javax.swing.JLabel();
        panelBusquedaPasajero = new javax.swing.JPanel();
        lblDni = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JLabel();
        btnBuscarPasajero = new javax.swing.JButton();
        panelVuelo = new javax.swing.JPanel();
        lbl_ID_Vuelo = new javax.swing.JLabel();
        lblHoraSalida = new javax.swing.JLabel();
        lblClase = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblAsientos = new javax.swing.JLabel();
        lbl_IdVuelo = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JLabel();
        txtClaseVuelo = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        txtAsientos = new javax.swing.JSpinner();
        txtdate = new com.toedter.calendar.JDateChooser();
        btnReservar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        txtPrecioTotal = new javax.swing.JLabel();

        panelBusquedaVuelo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciona pais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Italia\t", "Srilanka", "UK", "USA", "Canada", "China" }));

        txtDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Italia\t", "Srilanka", "UK", "USA", "Canada", "China" }));

        lblOrigen.setText("Origen");

        lblDestino.setText("Destino");

        btnBuscadorDestinos.setText("Buscar para estos lugares");
   

        javax.swing.GroupLayout gl_panelBusquedaVuelo = new javax.swing.GroupLayout(panelBusquedaVuelo);
        panelBusquedaVuelo.setLayout(gl_panelBusquedaVuelo);
        gl_panelBusquedaVuelo.setHorizontalGroup(
            gl_panelBusquedaVuelo.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelBusquedaVuelo.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(gl_panelBusquedaVuelo.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDestino)
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gl_panelBusquedaVuelo.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscadorDestinos)
                .addGap(49, 49, 49))
        );
        gl_panelBusquedaVuelo.setVerticalGroup(
            gl_panelBusquedaVuelo.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelBusquedaVuelo.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(gl_panelBusquedaVuelo.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrigen)
                    .addComponent(lblDestino))
                .addGap(18, 18, 18)
                .addGroup(gl_panelBusquedaVuelo.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnBuscadorDestinos)
                .addContainerGap())
        );

        tablaVuelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
            		 "ID Vuelo", "Origen", "Destino", "Fecha", "HoraSalida", "HoraLlegada", "Precio"
            }
        ));
      
        jScrollPane1.setViewportView(tablaVuelos);

        lblTicketNum.setText("Ticket num");

        txtTicketNum.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        txtTicketNum.setForeground(new java.awt.Color(255, 0, 0));
        txtTicketNum.setText("Ticket Num");

        lblDni.setText("DNI pasajero");

        lblNombre.setText("Nombre");

        lblApellido.setText("Apellido");

        lblTelefono.setText("Telefono");

        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        txtNombre.setForeground(new java.awt.Color(255, 0, 0));
        txtNombre.setText("lblNombre");

        txtApellido.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        txtApellido.setForeground(new java.awt.Color(255, 0, 0));
        txtApellido.setText("lblApellido");

        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 14));
        txtTelefono.setForeground(new java.awt.Color(255, 0, 0));
        txtTelefono.setText("lblTelefono");

        btnBuscarPasajero.setText("Buscar pasajero");
      

        javax.swing.GroupLayout gl_panelBusquedaPasajero = new javax.swing.GroupLayout(panelBusquedaPasajero);
        panelBusquedaPasajero.setLayout(gl_panelBusquedaPasajero);
        gl_panelBusquedaPasajero.setHorizontalGroup(
            gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelBusquedaPasajero.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gl_panelBusquedaPasajero.createSequentialGroup()
                        .addComponent(lblDni)
                        .addGap(34, 34, 34)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(btnBuscarPasajero))
                    .addGroup(gl_panelBusquedaPasajero.createSequentialGroup()
                        .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblApellido)
                            .addComponent(lblTelefono))
                        .addGap(56, 56, 56)
                        .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre))))
                .addContainerGap())
        );
        gl_panelBusquedaPasajero.setVerticalGroup(
            gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelBusquedaPasajero.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPasajero))
                .addGap(26, 26, 26)
                .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre))
                .addGap(31, 31, 31)
                .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido))
                .addGap(36, 36, 36)
                .addGroup(gl_panelBusquedaPasajero.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        lbl_ID_Vuelo.setText("ID Vuelo");

        lblHoraSalida.setText("Hora salida");

        lblClase.setText("Clase");

        lblPrecio.setText("Precio");

        lblAsientos.setText("Asientos");

        lbl_IdVuelo.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        lbl_IdVuelo.setForeground(new java.awt.Color(255, 0, 0));
        lbl_IdVuelo.setText("lbl_ID_Vuelo");

        txtHoraSalida.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtHoraSalida.setForeground(new java.awt.Color(255, 0, 0));
        txtHoraSalida.setText("lblHoraSalida");

        txtClaseVuelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Economico", "Business" }));

        

        javax.swing.GroupLayout gl_panelVuelo = new javax.swing.GroupLayout(panelVuelo);
        gl_panelVuelo.setHorizontalGroup(
        	gl_panelVuelo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelVuelo.createSequentialGroup()
        			.addGap(19)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panelVuelo.createParallelGroup(Alignment.TRAILING)
        					.addComponent(lblHoraSalida)
        					.addComponent(lblClase, Alignment.LEADING)
        					.addComponent(lblPrecio, Alignment.LEADING)
        					.addGroup(gl_panelVuelo.createSequentialGroup()
        						.addComponent(lbl_ID_Vuelo)
        						.addGap(16)))
        				.addGroup(gl_panelVuelo.createSequentialGroup()
        					.addComponent(lblAsientos)
        					.addGap(35)))
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelVuelo.createSequentialGroup()
        					.addGap(60)
        					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtHoraSalida)
        						.addGroup(gl_panelVuelo.createSequentialGroup()
        							.addComponent(lbl_IdVuelo)
        							.addGap(34)
        							.addComponent(txtdate, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(gl_panelVuelo.createSequentialGroup()
        					.addGap(50)
        					.addGroup(gl_panelVuelo.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(txtClaseVuelo, 0, 116, Short.MAX_VALUE)
        						.addComponent(txtPrecio)
        						.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(26, Short.MAX_VALUE))
        );
        gl_panelVuelo.setVerticalGroup(
        	gl_panelVuelo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelVuelo.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lbl_ID_Vuelo)
        					.addComponent(lbl_IdVuelo))
        				.addComponent(txtdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(74)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblHoraSalida)
        				.addComponent(txtHoraSalida))
        			.addGap(23)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblClase)
        				.addComponent(txtClaseVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPrecio)
        				.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panelVuelo.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblAsientos)
        				.addComponent(txtAsientos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVuelo.setLayout(gl_panelVuelo);

        btnReservar.setText("Reservar");
       

        btnCancelar.setText("Cancelar");
      

        txtPrecioTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        txtPrecioTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtPrecioTotal.setText("PrecioTotal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(32)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(44)
        							.addComponent(lblTicketNum))
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(txtTicketNum)))
        					.addGap(41)
        					.addComponent(panelBusquedaPasajero, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        					.addGap(31))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
        							.addGap(101)))
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(51)
        							.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
        							.addGap(0, 142, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(33)
        							.addComponent(panelVuelo, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        							.addContainerGap())))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(42)
        					.addComponent(panelBusquedaVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(53)
        					.addComponent(lblTicketNum)
        					.addGap(18)
        					.addComponent(txtTicketNum))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(31)
        					.addComponent(panelBusquedaPasajero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
        					.addGap(34)
        					.addComponent(txtPrecioTotal, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(panelVuelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnReservar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(19, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    
        setVisible(true);
  

    
  
    }
    
    
    
    
 

}
