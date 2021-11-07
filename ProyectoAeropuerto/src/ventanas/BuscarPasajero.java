package ventanas;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



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
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JPanel panelIzquierda;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioHombre;
    private javax.swing.JRadioButton radioMujer;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtFoto;
    private JTextField txtEdad;

    private ImageIcon imagenGuardar;
    private ImageIcon imagenBuscar;

    public BuscarPasajero() {
    	
    	imagenGuardar = new ImageIcon("img/guardar.png"); 
    	imagenBuscar = new ImageIcon("img/lupa.png"); 

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
        lblEdad = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        radioHombre = new javax.swing.JRadioButton();
        radioMujer = new javax.swing.JRadioButton();
        txtFoto = new javax.swing.JLabel();
        btnBuscarFoto = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        
        btnActualizar.setIcon(imagenGuardar);
        btnActualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCancelar = new javax.swing.JButton();
        txtDNI = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnBuscar.setIcon(imagenBuscar);
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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

        lblDNIPasajero.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        lblDNIPasajero.setText("DNI del pasajero: ");

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

        javax.swing.GroupLayout gl_panelCentral = new javax.swing.GroupLayout(panelCentral);
        gl_panelCentral.setHorizontalGroup(
        	gl_panelCentral.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCentral.createSequentialGroup()
        			.addGap(22)
        			.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_panelCentral.createSequentialGroup()
        					.addComponent(lblEdad)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
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
        			.addGap(35)
        			.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEdad)
        				.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
               
            }
        });

        btnActualizar.setText("Actualizar");
    

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	dispose();
            	VentanaAdministrador.desbloquearBotones();
               
            }
        });

        btnBuscar.setText("Buscar");
   

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblDNIPasajero)
        					.addGap(29)
        					.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
        				.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(30)
        					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(337)
        							.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(panelCentral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))))
        			.addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(23, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblDNIPasajero)
        				.addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnBuscar))
        			.addGap(38)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelIzquierda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(25)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(txtFoto, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        						.addComponent(panelCentral, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(18)
        					.addComponent(btnBuscarFoto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
        			.addGap(49))
        );
        getContentPane().setLayout(layout);

        pack();
        
    }
    
  
 
}
