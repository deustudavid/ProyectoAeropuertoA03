package ventanas;


import java.awt.SystemColor;

public class VerTickets extends javax.swing.JInternalFrame {
	
	   
    private javax.swing.JButton btnCancelar;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JTable tabla;
    

   
    public VerTickets() {

        panelScroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        tabla.setBackground(SystemColor.info);
        btnCancelar = new javax.swing.JButton();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TicketNum", "ID Vuelo", "DNI Pasajero", "Clase", "Precio", "Asiento", "Fecha"
            }
        ));
        panelScroll.setViewportView(tabla);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }


}
