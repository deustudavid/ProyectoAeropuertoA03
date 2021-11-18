package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import main.VentanaInicio;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.InputEvent;

public class VentanaAdministrador extends javax.swing.JFrame {

    private javax.swing.JDesktopPane jDesktopPane1;
    private static javax.swing.JMenu menuPasajero;
    private static javax.swing.JMenu menuTickets;
    private static javax.swing.JMenu menuVuelo;
    private static javax.swing.JMenu menuUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemAniadirPasajero;
    private javax.swing.JMenuItem menuItemActualizarPasajero;
    private javax.swing.JMenuItem menuItemReservarTicket;
    private javax.swing.JMenuItem menuItemVerTickets;
    private JMenuItem menuItemAnadirVuelo;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemEliminarReserva;
    private JMenuItem menuItemVerVuelos;
    private JMenuItem menuItemGestionarAzafatos;
    private JMenuItem menuItemEliminarVuelo;
    private JMenuItem menuItemGestionarEquipajes;
    
   
    private ImageIcon imagenAzafato;
    private ImageIcon imagenCerrarSesion;
    private ImageIcon imagenAniadir;
    private ImageIcon imagenAvion2;
    private ImageIcon imagenMaleta;
    private ImageIcon imagenPasajero;
    private ImageIcon imagenTicket;
    private ImageIcon imagenAvion;
    private ImageIcon imagenActualizar;
    private ImageIcon imagenReservar;
    private ImageIcon imagenListar;
    private ImageIcon imagenBorrar;
    private ImageIcon imagenUsuario;
    private JButton btnEscogerAvion;
    private JPanel panelsur;
    private JPanel contentPane;
    
    
    
    public static JFrame ventanaActual;

 
    public VentanaAdministrador() {
    	
    	ventanaActual=this;
	
	
    imagenAzafato = new ImageIcon("img/azafato.png");
    imagenCerrarSesion = new ImageIcon("img/logout.png");
    imagenAniadir = new ImageIcon("img/plus.png");
    imagenMaleta = new ImageIcon("img/maleta.png");
    imagenPasajero = new ImageIcon("img/pasajero.png");
    imagenTicket = new ImageIcon("img/ticket.png");
    imagenAvion = new ImageIcon("img/avion.png"); 
    imagenActualizar = new ImageIcon("img/actualizar.png"); 
    imagenReservar = new ImageIcon("img/escribir.png"); 
	imagenListar = new ImageIcon("img/blocnotas.png"); 
	imagenBorrar = new ImageIcon("img/papelera.png");
	imagenUsuario = new ImageIcon("img/usuario.png");
	imagenAvion2 = new ImageIcon("img/avion2.png");
		   
   
   
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jDesktopPane1.setBackground(new Color(0, 255, 255));
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBar1.setBackground(Color.LIGHT_GRAY);
        menuPasajero = new javax.swing.JMenu();
        menuPasajero.setIcon(imagenPasajero);
        menuPasajero.setMnemonic('P');
        
        menuItemAniadirPasajero = new javax.swing.JMenuItem();
        menuItemAniadirPasajero.setIcon(imagenAniadir);
        menuItemAniadirPasajero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menuItemAniadirPasajero.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
               CreadorPasajeros cus = new CreadorPasajeros();
        		jDesktopPane1.add(cus);
        		cus.setVisible(true);   
        		bloquearBotones();
        	}
        });
        menuItemActualizarPasajero = new javax.swing.JMenuItem();
        menuItemActualizarPasajero.setIcon(imagenActualizar);
   
        menuItemActualizarPasajero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menuTickets = new javax.swing.JMenu();
        menuTickets.setIcon(imagenTicket);
        menuTickets.setMnemonic('T');
        menuItemReservarTicket = new javax.swing.JMenuItem();
        menuItemReservarTicket.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReservarTicket r=new ReservarTicket();
            	jDesktopPane1.add(r);
            	bloquearBotones();
        	}
        });
        menuItemVerTickets = new javax.swing.JMenuItem();
        menuVuelo = new javax.swing.JMenu();
        menuVuelo.setMnemonic('V');
        menuUsuario = new javax.swing.JMenu();
        menuUsuario.setMnemonic('U');//alt+u para abrir menu usuario

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setVisible(true);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        menuPasajero.setText("Pasajero");

        menuItemAniadirPasajero.setText("Anadir pasajero");
      
        menuPasajero.add(menuItemAniadirPasajero);
        
        

        menuItemActualizarPasajero.setText("Actualizar pasajero");
        menuItemActualizarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BuscarPasajero b= new BuscarPasajero();
            	jDesktopPane1.add(b);
                b.setVisible(true);
                bloquearBotones();
              
            }
        });
        menuPasajero.add(menuItemActualizarPasajero);

        jMenuBar1.add(menuPasajero);
        
        menuItemGestionarEquipajes = new JMenuItem();
        menuItemGestionarEquipajes.setIcon(imagenMaleta);
        menuItemGestionarEquipajes.setText("Gestionar equipajes");
        menuPasajero.add(menuItemGestionarEquipajes);

        menuTickets.setText("Tickets");

        menuItemReservarTicket.setText("Reservar Ticket");
        menuItemReservarTicket.setIcon(imagenReservar);
       
        menuTickets.add(menuItemReservarTicket);

        menuItemVerTickets.setText("Ver tickets");
        menuItemVerTickets.setIcon(imagenListar);
        
        menuItemVerTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	VerTickets v=new VerTickets();
            	jDesktopPane1.add(v);
                v.setVisible(true);
            	
            	bloquearBotones();
            
            }
            
        });
        /*
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);
        JPanel panelsur = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelsur.getLayout();
         contentPane.add(panelsur, BorderLayout.SOUTH);
         panelsur.add(btnEscogerAviones);
        */

        JButton btnEscogerAviones = new JButton();
        
       
        btnEscogerAviones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("aviones");
	    		FileNameExtensionFilter fnef = new FileNameExtensionFilter("JPG & JFIF", "jpg","jfif");
	    		fc.setFileFilter(fnef);
	    		int sel = fc.showOpenDialog(null);
	    		if(sel == JFileChooser.APPROVE_OPTION) {
	    			File ficheroSeleccionado = fc.getSelectedFile();
	    			System.out.println("Nombre del fichero seleccionado"+ ficheroSeleccionado.getName());
	    			System.out.println("Ruta del fichero seleccionado: "+ ficheroSeleccionado.getAbsolutePath());

				
	    		}
	    
	    		
			}});
       
	
        menuTickets.add(menuItemVerTickets);

        jMenuBar1.add(menuTickets);
        
        menuItemEliminarReserva = new JMenuItem();
        menuItemEliminarReserva.setIcon(imagenBorrar);
        menuItemEliminarReserva.setText("Eliminar reserva");
        menuTickets.add(menuItemEliminarReserva);

        menuVuelo.setText("Vuelo");
        menuVuelo.setIcon(imagenAvion);
        
        JMenuItem menuItemElegirAvion = new JMenuItem();
        menuItemElegirAvion = new JMenuItem();
        menuItemElegirAvion.setIcon(imagenAvion2);
        menuItemElegirAvion.setText("Elegir avion");
        
        
        // imagenAvion2 = img.getScaledInstance(label.getWidth(),label.getHeight(),Image;
        //ImageIcon imagenAvion2 = new ImageIcon(new ImageIcon("imagenAvion2.png").getImage().getScaledInstance(20, 20, imagenAvion2.SCALE_DEFAULT)); 
    
        menuVuelo.add(menuItemElegirAvion);
        
       

        jMenuBar1.add(menuVuelo);
        
        menuItemAnadirVuelo = new JMenuItem();
        menuItemAnadirVuelo.setIcon(imagenAniadir);
        menuItemAnadirVuelo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menuItemAnadirVuelo.setText("Anadir vuelo");
        menuItemAnadirVuelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 CreadorVuelos f = new CreadorVuelos();
                 jDesktopPane1.add(f);
                 f.setVisible(true);
                 bloquearBotones();
            
            }
        });
        
        menuVuelo.add(menuItemAnadirVuelo);
        
        menuItemVerVuelos = new JMenuItem();
        menuItemVerVuelos.setIcon(imagenListar);
        menuItemVerVuelos.setText("Ver vuelos");
        menuVuelo.add(menuItemVerVuelos);
        
        menuItemEliminarVuelo = new JMenuItem();
        menuItemEliminarVuelo.setIcon(imagenBorrar);
        menuItemEliminarVuelo.setText("Eliminar vuelo");
        menuVuelo.add(menuItemEliminarVuelo);

        menuUsuario.setText("Usuario");
        menuUsuario.setIcon(imagenUsuario);

        jMenuBar1.add(menuUsuario);
        
        menuItemGestionarAzafatos = new JMenuItem();
        menuItemGestionarAzafatos.setIcon(imagenAzafato);
        menuItemGestionarAzafatos.setText("Gestionar azafatos");
        menuUsuario.add(menuItemGestionarAzafatos);
        
        
        menuItemCerrarSesion = new JMenuItem();
        menuItemCerrarSesion.setIcon(imagenCerrarSesion);
        menuItemCerrarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ventanaActual=null;
        		dispose();
        		new VentanaInicio();
        	}
        });
        menuItemCerrarSesion.setText("Cerrar sesion");
        menuUsuario.add(menuItemCerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }

    
    
    public static void bloquearBotones() {
    	    
			menuPasajero.setEnabled(false);	
    	    menuTickets.setEnabled(false);
    	    menuVuelo.setEnabled(false);
    	    menuUsuario.setEnabled(false);
    	
    }
    
    public static void desbloquearBotones() {
	    
			menuPasajero.setEnabled(true);
			menuTickets.setEnabled(true);
    	    menuVuelo.setEnabled(true);
    	    menuUsuario.setEnabled(true);
    	
    }
    
    /**
     * Metodo que sirve para saber si la ventana que est� abierta es la de administrador o la de azafato
     * @return true si la ventana es la VentanaAdministrador
     * 			false si es la ventana VentanaAzafato
     */
    public static boolean VentanaAdminEstaActiva() { 
    	if (ventanaActual == null) {
			return false;
		}
    	
    	else if ( ventanaActual.isShowing() && ventanaActual != null) {
		 return true;
	 }
		return false;
	
    }
    
    	
}
