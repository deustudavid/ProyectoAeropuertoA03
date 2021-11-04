package ventanas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.VentanaInicio;

public class VentanaAzafato extends JFrame {

	private javax.swing.JDesktopPane jDesktopPane1;
    private static javax.swing.JMenu menuPasajero;
    private static javax.swing.JMenu menuTickets;
    private static javax.swing.JMenu menuVuelo;
    private static javax.swing.JMenu menuUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemAniadirPasajero;
    private javax.swing.JMenuItem menuItemGestionarPasajero;
    private javax.swing.JMenuItem menuItemReservarTicket;
    private javax.swing.JMenuItem menuItemVerTickets;
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemEliminarReserva;
    private JMenuItem menuItemVerVuelos;
    private JMenuItem menuItemGestionarEquipajes;

 
    public VentanaAzafato() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBar1.setBackground(Color.orange);
        menuPasajero = new javax.swing.JMenu();
        menuItemAniadirPasajero = new javax.swing.JMenuItem();
        menuItemAniadirPasajero.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
               CreadorPasajeros cus = new CreadorPasajeros();
        		jDesktopPane1.add(cus);
        		cus.setVisible(true);   
        		bloquearBotones();
        	}
        });
        menuItemGestionarPasajero = new javax.swing.JMenuItem();
        menuTickets = new javax.swing.JMenu();
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
        menuUsuario = new javax.swing.JMenu();

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

        menuItemGestionarPasajero.setText("Gestionar pasajero");
        menuItemGestionarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BuscarPasajero b= new BuscarPasajero();
            	jDesktopPane1.add(b);
                b.setVisible(true);
                bloquearBotones();
              
            }
        });
        menuPasajero.add(menuItemGestionarPasajero);

        jMenuBar1.add(menuPasajero);
        
        menuItemGestionarEquipajes = new JMenuItem();
        menuItemGestionarEquipajes.setText("Gestionar equipajes");
        menuPasajero.add(menuItemGestionarEquipajes);

        menuTickets.setText("Tickets");

        menuItemReservarTicket.setText("Reservar Ticket");
       
        menuTickets.add(menuItemReservarTicket);

        menuItemVerTickets.setText("Ver tickets");
        
        menuItemVerTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	VerTickets v=new VerTickets();
            	jDesktopPane1.add(v);
                v.setVisible(true);
            	
            	bloquearBotones();
            
            }
        });
        menuTickets.add(menuItemVerTickets);

        jMenuBar1.add(menuTickets);
        
        menuItemEliminarReserva = new JMenuItem();
        menuItemEliminarReserva.setText("Eliminar reserva");
        menuTickets.add(menuItemEliminarReserva);

        menuVuelo.setText("Vuelo");

        jMenuBar1.add(menuVuelo);
        
        menuItemVerVuelos = new JMenuItem();
        menuItemVerVuelos.setText("Ver vuelos");
        menuVuelo.add(menuItemVerVuelos);

        menuUsuario.setText("Usuario");

        jMenuBar1.add(menuUsuario);
        
        menuItemCerrarSesion = new JMenuItem();
        menuItemCerrarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
    	    menuTickets.setEnabled(false);;
    	    menuVuelo.setEnabled(false);;
    	    menuUsuario.setEnabled(false);;
    	
    }
    
    public static void desbloquearBotones() {
	    
				menuPasajero.setEnabled(true);
				menuTickets.setEnabled(true);;
    	    menuVuelo.setEnabled(true);;
    	    menuUsuario.setEnabled(true);;
    	
    }
}
