package ventanas;



import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import javax.swing.JTextField;



public class CreadorPasajeros extends javax.swing.JInternalFrame {

     
    private javax.swing.JButton btnBuscarFoto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblTituloVentana;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioHombre;
    private javax.swing.JRadioButton radioMujer;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtFoto;
    private JTextField txtEdad;
    

   private ImageIcon imagenGuardar;
    
    
    
   
    public CreadorPasajeros() {
    	imagenGuardar = new ImageIcon("img/guardar.png"); 

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
        lblTituloVentana = new javax.swing.JLabel();
        panelCentral = new javax.swing.JPanel();
        lblEdad = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        radioHombre = new javax.swing.JRadioButton();
        radioMujer = new javax.swing.JRadioButton();
        txtFoto = new javax.swing.JLabel();
        btnBuscarFoto = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
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


     

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

        javax.swing.GroupLayout glPanelIzquierda = new javax.swing.GroupLayout(panelIzquierda);
        panelIzquierda.setLayout(glPanelIzquierda);
        glPanelIzquierda.setHorizontalGroup(
            glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glPanelIzquierda.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(glPanelIzquierda.createSequentialGroup()
                        .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(glPanelIzquierda.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(47, 47, 47)
                                .addComponent(txtNombre))
                            .addGroup(glPanelIzquierda.createSequentialGroup()
                                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblTelefono)
                                    .addComponent(lblDni))
                                .addGap(38, 38, 38)
                                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtDni))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(glPanelIzquierda.createSequentialGroup()
                        .addComponent(lblApellido)
                        .addGap(48, 48, 48)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        glPanelIzquierda.setVerticalGroup(
            glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glPanelIzquierda.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(glPanelIzquierda.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDireccion)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        lblTituloVentana.setFont(new java.awt.Font("Tahoma", 1, 18));   
        lblTituloVentana.setText("Nuevo pasajero");

        panelCentral.setBackground(new java.awt.Color(51, 0, 255));

        lblEdad.setFont(new java.awt.Font("Tahoma", 1, 11));   
        lblEdad.setForeground(new java.awt.Color(255, 255, 255));
        lblEdad.setText("Edad");

        lblGenero.setFont(new java.awt.Font("Tahoma", 1, 11));   
        lblGenero.setForeground(new java.awt.Color(255, 255, 255));
        lblGenero.setText("Genero");

        radioHombre.setText("Hombre");

        radioMujer.setText("Mujer");
        
        txtEdad = new JTextField();

        javax.swing.GroupLayout glPanelCentral = new javax.swing.GroupLayout(panelCentral);
        glPanelCentral.setHorizontalGroup(
        	glPanelCentral.createParallelGroup(Alignment.LEADING)
        		.addGroup(glPanelCentral.createSequentialGroup()
        			.addGap(22)
        			.addGroup(glPanelCentral.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(glPanelCentral.createSequentialGroup()
        					.addComponent(lblEdad)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        				.addGroup(glPanelCentral.createSequentialGroup()
        					.addComponent(lblGenero)
        					.addGap(46)
        					.addComponent(radioHombre)
        					.addGap(18)
        					.addComponent(radioMujer)))
        			.addContainerGap(41, Short.MAX_VALUE))
        );
        glPanelCentral.setVerticalGroup(
        	glPanelCentral.createParallelGroup(Alignment.LEADING)
        		.addGroup(glPanelCentral.createSequentialGroup()
        			.addGap(35)
        			.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEdad)
        				.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(glPanelCentral.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblGenero)
        				.addComponent(radioHombre)
        				.addComponent(radioMujer))
        			.addContainerGap(199, Short.MAX_VALUE))
        );
        panelCentral.setLayout(glPanelCentral);

        txtFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBuscarFoto.setText("Seleccionar foto");
        btnBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
                try {
                    JFileChooser picchooser = new JFileChooser();
                   picchooser.showOpenDialog(null);
                   
                   if (picchooser.getSelectedFile() != null) {
					
				
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
                          
                } 
                   else {
                	   JOptionPane.showMessageDialog(picchooser, "Ninguna foto fue seleccionada");
                   }
                          
                    } catch (IOException ex) {
                        Logger.getLogger(CreadorPasajeros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
               
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setIcon(imagenGuardar);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
    			String erdni = "[0-9]{8}[A-Z]";
				String d = txtDni.getText();
				boolean correctoDni = Pattern.matches(erdni, d);
				if(correctoDni) {
					//falta meter el dato de cada textfield en una variable y crear un nuevo Pasajero 
					// con todos los datos
					String n = txtNombre.getText();
					/* .....*/
		
					JOptionPane.showMessageDialog(null, "Pasajero registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null, "El dni no es correcto", "��ERROR!!", JOptionPane.ERROR_MESSAGE);
				}
          
            }
        });

        btnCancelar.setText("Cancelar");
       

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(25)
        					.addComponent(lblTituloVentana))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(337)
        							.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))))
        			.addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(27, Short.MAX_VALUE)
        			.addComponent(lblTituloVentana)
        			.addGap(41)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(25)
        					.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        					.addGroup(layout.createSequentialGroup()
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addComponent(panelIzquierda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addGap(4)
        			.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addGap(49))
        );
        getContentPane().setLayout(layout);

        pack();
    } 
}
