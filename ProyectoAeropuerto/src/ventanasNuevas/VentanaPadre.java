package ventanasNuevas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import main.VentanaInicio;

public class VentanaPadre extends javax.swing.JFrame {

    private javax.swing.JDesktopPane jDesktopPane1;
    private static javax.swing.JMenu menuPasajero;
    private static javax.swing.JMenu menuTickets;
    private static javax.swing.JMenu menuVuelo;
    private static javax.swing.JMenu menuUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemAniadirPasajero;
    private javax.swing.JMenuItem menuItemBuscarPasajero;
    private javax.swing.JMenuItem menuItemReservarTicket;
    private javax.swing.JMenuItem menuItemVerTickets;
    private JMenuItem menuItemAnadirVuelo;
    private JMenuItem menuItemCerrarSesion;
 
    public VentanaPadre() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
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
        menuItemBuscarPasajero = new javax.swing.JMenuItem();
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

        menuItemBuscarPasajero.setText("Buscar pasajero");
        menuItemBuscarPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	BuscarPasajero b= new BuscarPasajero();
            	jDesktopPane1.add(b);
                b.setVisible(true);
                bloquearBotones();
              
            }
        });
        menuPasajero.add(menuItemBuscarPasajero);

        jMenuBar1.add(menuPasajero);

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

        menuVuelo.setText("Vuelo");

        jMenuBar1.add(menuVuelo);
        
        menuItemAnadirVuelo = new JMenuItem();
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
