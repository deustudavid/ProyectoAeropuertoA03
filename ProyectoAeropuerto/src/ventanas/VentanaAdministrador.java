package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import main.VentanaInicio;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.SystemColor;
import java.awt.Font;


public class VentanaAdministrador extends JFrame {

	private static JDesktopPane panelEscritorio;
	public static JLabel lblDeustoFly;
	private static JMenu menuPasajero;
	private static JMenu menuTickets;
	private static JMenu menuVuelo;
	private static JMenu menuUsuario;
	private JMenuBar menuPrincipal;
	private JMenuItem menuItemAniadirPasajero;
	private JMenuItem menuItemActualizarPasajero;
	private JMenuItem menuItemReservarTicket;
	private JMenuItem menuItemVerTickets;
	private JMenuItem menuItemAnadirVuelo;
	private JMenuItem menuItemCerrarSesion;
	private JMenuItem menuItemVerVuelos;
	private JMenuItem menuItemGestionarAzafatos;
	private JMenuItem menuItemGestionarEquipajes;
	private JMenuItem menuItemDarPermisos;
	private JMenuItem menuItemVentanaAzafato;

	private ImageIcon imagenAzafato;
	private ImageIcon imagenCerrarSesion;
	private ImageIcon imagenAniadir;
	private ImageIcon imagenLlave;
	private ImageIcon imagenMaleta;
	private ImageIcon imagenPasajero;
	private ImageIcon imagenTicket;
	private ImageIcon imagenAvion;
	private ImageIcon imagenActualizar;
	private ImageIcon imagenReservar;
	private ImageIcon imagenListar;
	private ImageIcon imagenUsuario;
	private ImageIcon imagenIrAzafato;
	public static JFrame ventanaActual;

	public VentanaAdministrador() {

		ventanaActual = this;

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
		imagenUsuario = new ImageIcon("img/usuario.png");
		imagenLlave = new ImageIcon("img/llave.png");
		imagenIrAzafato = new ImageIcon("img/flecha.png");

		panelEscritorio = new JDesktopPane();
		panelEscritorio.setBackground(new Color(0, 0, 128));
		menuPrincipal = new JMenuBar();
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPasajero = new JMenu();
		menuPasajero.setIcon(imagenPasajero);
		menuPasajero.setMnemonic('P');
		
		 lblDeustoFly = new JLabel("DEUSTOFLY");
		lblDeustoFly.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 36));
		lblDeustoFly.setForeground(new Color(255, 255, 0));

		menuItemAniadirPasajero = new JMenuItem();
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
		menuItemActualizarPasajero = new JMenuItem();
		menuItemActualizarPasajero.setIcon(imagenActualizar);

		menuItemActualizarPasajero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		menuTickets = new JMenu();
		menuTickets.setIcon(imagenTicket);
		menuTickets.setMnemonic('T');
		menuItemReservarTicket = new JMenuItem();
		menuItemReservarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDeustoFly.setVisible(false);
				ReservarTicket r = new ReservarTicket();
				panelEscritorio.add(r);
				r.setVisible(true);
				bloquearBotones();
			}
		});
		menuItemVerTickets = new JMenuItem();
		menuVuelo = new JMenu();
		menuVuelo.setMnemonic('V');
		menuUsuario = new JMenu();
		menuUsuario.setMnemonic('U');// alt+u para abrir menu usuario

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1366, 768));
		setVisible(true);
		
		

		GroupLayout gl_panelEscritorio = new GroupLayout(panelEscritorio);
		gl_panelEscritorio.setHorizontalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelEscritorio.createSequentialGroup()
					.addContainerGap(570, Short.MAX_VALUE)
					.addComponent(lblDeustoFly, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addGap(450))
		);
		gl_panelEscritorio.setVerticalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(221)
					.addComponent(lblDeustoFly, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(314, Short.MAX_VALUE))
		);
		
		
		
		panelEscritorio.setLayout(gl_panelEscritorio);

		menuPasajero.setText("Pasajero");
		
		menuItemAniadirPasajero.setText("Anadir pasajero");

		menuPasajero.add(menuItemAniadirPasajero);

		menuItemActualizarPasajero.setText("Actualizar pasajero");
		menuItemActualizarPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				BuscarPasajero b = new BuscarPasajero();
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
		menuItemGestionarEquipajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CreadorEquipajes b = new CreadorEquipajes();
				panelEscritorio.add(b);
				b.setVisible(true);
				bloquearBotones();

			}
		});
		menuPasajero.add(menuItemGestionarEquipajes);

		menuTickets.setText("Tickets");

		menuItemReservarTicket.setText("Reservar Ticket");
		menuItemReservarTicket.setIcon(imagenReservar);

		menuTickets.add(menuItemReservarTicket);

		menuItemVerTickets.setText("Ver tickets");
		menuItemVerTickets.setIcon(imagenListar);

		menuItemVerTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				VerTickets v = new VerTickets();
				panelEscritorio.add(v);
				v.setVisible(true);

				bloquearBotones();

			}

		});

		menuTickets.add(menuItemVerTickets);

		menuPrincipal.add(menuTickets);

		menuVuelo.setText("Vuelo");
		menuVuelo.setIcon(imagenAvion);

		menuPrincipal.add(menuVuelo);

		menuItemAnadirVuelo = new JMenuItem();
		menuItemAnadirVuelo.setIcon(imagenAniadir);
		menuItemAnadirVuelo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuItemAnadirVuelo.setText("Anadir vuelo");
		menuItemAnadirVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CreadorVuelos f = new CreadorVuelos();
				panelEscritorio.add(f);
				f.setVisible(true);
				bloquearBotones();

			}
		});

		menuVuelo.add(menuItemAnadirVuelo);

		menuItemVerVuelos = new JMenuItem();
		menuItemVerVuelos.setIcon(imagenListar);
		menuItemVerVuelos.setText("Ver vuelos");
		menuVuelo.add(menuItemVerVuelos);
		menuItemVerVuelos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				VerVuelos v = new VerVuelos();
				panelEscritorio.add(v);
				v.setVisible(true);
				bloquearBotones();
			}
		});

		menuUsuario.setText("Usuario");
		menuUsuario.setIcon(imagenUsuario);

		menuPrincipal.add(menuUsuario);

		menuItemGestionarAzafatos = new JMenuItem();
		menuItemGestionarAzafatos.setIcon(imagenAzafato);
		menuItemGestionarAzafatos.setText("Gestionar azafatos");
		menuUsuario.add(menuItemGestionarAzafatos);

		menuItemCerrarSesion = new JMenuItem();
		menuItemCerrarSesion.setIcon(imagenCerrarSesion);
		menuItemCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual = null;
				dispose();
				new VentanaInicio();
			}
		});
		menuItemCerrarSesion.setText("Cerrar sesion");
		menuUsuario.add(menuItemCerrarSesion);

		menuItemDarPermisos = new JMenuItem();
		menuItemDarPermisos.setText("Dar permisos");
		menuItemDarPermisos.setIcon(imagenLlave);
		menuUsuario.add(menuItemDarPermisos);
		menuItemDarPermisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				VentanaPermisos v = new VentanaPermisos();
				panelEscritorio.add(v);
				v.setVisible(true);
				bloquearBotones();

			}

		});
		
		menuItemVentanaAzafato = new JMenuItem();
		menuItemVentanaAzafato.setText("Ventana Azafato");
		menuItemVentanaAzafato.setIcon(imagenIrAzafato);
		menuUsuario.add(menuItemVentanaAzafato);
		menuItemVentanaAzafato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				VentanaAzafato v = new VentanaAzafato();
				v.setVisible(true);
				bloquearBotones();
			
		}
		});

		setJMenuBar(menuPrincipal);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelEscritorio));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelEscritorio));

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
	 * Metodo que sirve para saber si la ventana que est� abierta es la de
	 * administrador o la de azafato
	 * 
	 * @return true si la ventana es la VentanaAdministrador false si es la ventana
	 *         VentanaAzafato
	 */
	public static boolean VentanaAdminEstaActiva() {
		if (ventanaActual == null) {
			return false;
		}

		else if (ventanaActual.isShowing() && ventanaActual != null) {
			return true;
		}
		return false;

	}
	public static JDesktopPane getPanelEscritorio() {
		return panelEscritorio;
	}
}