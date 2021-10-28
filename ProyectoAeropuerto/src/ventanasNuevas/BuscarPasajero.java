package ventanasNuevas;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;



public class BuscarPasajero extends javax.swing.JInternalFrame {
	

    private javax.swing.JButton btnBuscarFoto;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDNIPasajero;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioHombre;
    private javax.swing.JRadioButton radioMujer;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtDNI;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtFoto;


    public BuscarPasajero() {
    	
    	

        panelIzquierda = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        lblDNIPasajero = new javax.swing.JLabel();
        panelCentral = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        radioHombre = new javax.swing.JRadioButton();
        radioMujer = new javax.swing.JRadioButton();
        txtFoto = new javax.swing.JLabel();
        btnBuscarFoto = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtDNI = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        panelIzquierda.setBackground(new java.awt.Color(51, 0, 255));

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre");

        lblApellido.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblApellido.setForeground(new java.awt.Color(255, 255, 255));
        lblApellido.setText("Apellido");

        lblDni.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblDni.setForeground(new java.awt.Color(255, 255, 255));
        lblDni.setText("DNI");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Tfno");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblDireccion.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccion.setText("Direccion");

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlastnameActionPerformed(evt);
            }
        });

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassportActionPerformed(evt);
            }
        });

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

        javax.swing.GroupLayout gl_panelIzquierda = new javax.swing.GroupLayout(panelIzquierda);
        panelIzquierda.setLayout(gl_panelIzquierda);
        gl_panelIzquierda.setHorizontalGroup(
            gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelIzquierda.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gl_panelIzquierda.createSequentialGroup()
                        .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(gl_panelIzquierda.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(47, 47, 47)
                                .addComponent(txtNombre))
                            .addGroup(gl_panelIzquierda.createSequentialGroup()
                                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblTelefono)
                                    .addComponent(lblDni))
                                .addGap(38, 38, 38)
                                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtDni))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(gl_panelIzquierda.createSequentialGroup()
                        .addComponent(lblApellido)
                        .addGap(48, 48, 48)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        gl_panelIzquierda.setVerticalGroup(
            gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_panelIzquierda.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(gl_panelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        lblDNIPasajero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDNIPasajero.setText("DNI del pasajero: ");

        panelCentral.setBackground(new java.awt.Color(51, 0, 255));

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha ncto.");

        lblGenero.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(255, 255, 255));
        lblGenero.setText("Genero");

        radioHombre.setText("Hombre");

        radioMujer.setText("Mujer");

        javax.swing.GroupLayout gl_panelCentral = new javax.swing.GroupLayout(panelCentral);
        gl_panelCentral.setHorizontalGroup(
        	gl_panelCentral.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCentral.createSequentialGroup()
        			.addGap(22)
        			.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelCentral.createSequentialGroup()
        					.addComponent(lblFecha)
        					.addGap(18)
        					.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panelCentral.createSequentialGroup()
        					.addComponent(lblGenero)
        					.addGap(46)
        					.addComponent(radioHombre)
        					.addGap(18)
        					.addComponent(radioMujer)))
        			.addContainerGap(41, Short.MAX_VALUE))
        );
        gl_panelCentral.setVerticalGroup(
        	gl_panelCentral.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCentral.createSequentialGroup()
        			.addGap(29)
        			.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lblFecha)
        				.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblGenero)
        				.addComponent(radioHombre)
        				.addComponent(radioMujer))
        			.addContainerGap(160, Short.MAX_VALUE))
        );
        panelCentral.setLayout(gl_panelCentral);

        txtFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBuscarFoto.setText("Seleccionar foto");
        btnBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
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

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblDNIPasajero)
                        .addGap(29, 29, 29)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(337, 337, 337)
                                .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIPasajero)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(panelCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
 
    
   
    
    
    
    
    
    
    
    
    
    private void txtlastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlastnameActionPerformed

    private void txtpassportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassportActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    
      
        try {
        JFileChooser picchooser = new JFileChooser();
       picchooser.showOpenDialog(null);
       File pic = picchooser.getSelectedFile();       
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","png","jpg");
       picchooser.addChoosableFileFilter(filter);     
      String path= pic.getAbsolutePath();
       BufferedImage img;                    
       img = ImageIO.read(picchooser.getSelectedFile());
       ImageIcon imageIcon = new ImageIcon(new
       ImageIcon(img).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
              txtFoto.setIcon(imageIcon); 
              
              
             File image = new File(path);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            for(int readNum; (readNum=fis.read(buff)) !=-1 ; )
            {
                baos.write(buff,0,readNum);
            }
            byte[] userimage=baos.toByteArray();
              
              
              
        } catch (IOException ex) {
            Logger.getLogger(CreadorPasajeros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
       
       
       
       
       
       
        
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         String id = txtDNI.getText();
         String firstname = txtNombre.getText();
         String lastname = txtApellido.getText();
         String nic = txtDni.getText(); 
        String passport = txtTelefono.getText();
         String address = txtDireccion.getText();
        
        DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
        String date = da.format(txtFecha.getDate());
        String Gender;
        
        if(radioHombre.isSelected())
        {
            Gender = "Male";
        }
        else
        {
            Gender = "FeMale";
        }
     
    }//GEN-LAST:event_jButton2ActionPerformed

    

   



}
