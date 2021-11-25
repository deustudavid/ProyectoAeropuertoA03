package ventanas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import main.VentanaInicio;
import java.awt.SystemColor;

public class VentanaAzafato extends JFrame {

	private javax.swing.JDesktopPane panelEscritorio;
    private static javax.swing.JMenu menuPasajero;
    private static javax.swing.JMenu menuTickets;
    private static javax.swing.JMenu menuVuelo;
    private static javax.swing.JMenu menuUsuario;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenuItem menuItemAniadirPasajero;
    private javax.swing.JMenuItem menuItemActualizarPasajero;
    private javax.swing.JMenuItem menuItemReservarTicket;
    private javax.swing.JMenuItem menuItemVerTickets;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemEliminarReserva;
    private JMenuItem menuItemVerVuelos;
    private JMenuItem menuItemGestionarEquipajes;

   
    private ImageIcon imagenCerrarSesion;
    private ImageIcon imagenAniadir;
    private ImageIcon imagenMaleta;
    private ImageIcon imagenPasajero;
    private ImageIcon imagenTicket;
    private ImageIcon imagenAvion;
    private ImageIcon imagenActualizar;
    private ImageIcon imagenReservar;
    private ImageIcon imagenListar;
    private ImageIcon imagenBorrar;
    private ImageIcon imagenUsuario;
    
    public static JFrame ventanaActual;
    
    public VentanaAzafato() {
    	
    	ventanaActual = this;
    	
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

        panelEscritorio = new javax.swing.JDesktopPane();
        panelEscritorio.setBackground(new Color(240, 248, 255));
        menuPrincipal = new javax.swing.JMenuBar();
        menuPrincipal.setBackground(SystemColor.info);
        menuPasajero = new javax.swing.JMenu();
        menuPasajero.setIcon(imagenPasajero);
        menuPasajero.setMnemonic('P');
        
        menuItemAniadirPasajero = new javax.swing.JMenuItem();
        menuItemAniadirPasajero.setIcon(imagenAniadir);
        menuItemAniadirPasajero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menuItemAniadirPasajero.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
               CreadorPasajeros cus = new CreadorPasajeros();
        		panelEscritorio.add(cus);
        		cus.setVisible(true);   
        		bloquearBotones();
        	}
        });

        menuTickets = new javax.swing.JMenu();
        menuTickets.setIcon(imagenTicket);
        menuTickets.setMnemonic('T');
        menuItemReservarTicket = new javax.swing.JMenuItem();
        menuItemReservarTicket.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReservarTicket r=new ReservarTicket();
            	panelEscritorio.add(r);
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

        javax.swing.GroupLayout gl_panelEscritorio = new javax.swing.GroupLayout(panelEscritorio);
        panelEscritorio.setLayout(gl_panelEscritorio);
        gl_panelEscritorio.setHorizontalGroup(
            gl_panelEscritorio.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        gl_panelEscritorio.setVerticalGroup(
            gl_panelEscritorio.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        menuPasajero.setText("Pasajero");

        menuItemAniadirPasajero.setText("Anadir pasajero");
      
        menuPasajero.add(menuItemAniadirPasajero);
      
        menuItemActualizarPasajero = new javax.swing.JMenuItem();
        menuItemActualizarPasajero.setIcon(imagenActualizar);
        menuItemActualizarPasajero.setText("Actualizar pasajero");
        menuItemActualizarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BuscarPasajero b= new BuscarPasajero();
            	panelEscritorio.add(b);
                b.setVisible(true);
                bloquearBotones();
              
            }
        });
        menuPasajero.add(menuItemActualizarPasajero);

        menuPrincipal.add(menuPasajero);
        
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
            	panelEscritorio.add(v);
                v.setVisible(true);
            	
            	bloquearBotones();
            
            }
        });
        menuTickets.add(menuItemVerTickets);

        menuPrincipal.add(menuTickets);
        
        menuItemEliminarReserva = new JMenuItem();
        menuItemEliminarReserva.setIcon(imagenBorrar);
        menuItemEliminarReserva.setText("Eliminar reserva");
        menuTickets.add(menuItemEliminarReserva);

        menuVuelo.setText("Vuelo");
        menuVuelo.setIcon(imagenAvion);

        menuPrincipal.add(menuVuelo);
        
      
        
 
      
        menuItemVerVuelos = new JMenuItem();
        menuItemVerVuelos.setIcon(imagenListar);
        menuItemVerVuelos.setText("Ver vuelos");
        menuVuelo.add(menuItemVerVuelos);
        


        menuUsuario.setText("Usuario");
        menuUsuario.setIcon(imagenUsuario);

        menuPrincipal.add(menuUsuario);
        

        
        
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

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEscritorio)
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
    public static boolean VentanaAzafatoEstaActiva() { 
    	if (ventanaActual == null) {
			return false;
		}
    	
    	else if ( ventanaActual.isShowing() && ventanaActual != null) {
		 return true;
	 }
		return false;
	
    }
 
    

}