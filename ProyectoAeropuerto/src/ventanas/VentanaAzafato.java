package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import main.VentanaInicio;
import javax.swing.GroupLayout.Alignment;

public class VentanaAzafato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7025051843001903322L;
	static JDesktopPane panelEscritorio;
	private static JMenu menuPasajero;
	private static JMenu menuTickets;
	private static JMenu menuVuelo;
	private static JMenu menuUsuario;
	private JMenuBar menuPrincipal;
	private JMenuItem menuItemAniadirPasajero;
	private JMenuItem menuItemActualizarPasajero;
	private JMenuItem menuItemReservarTicket;
	private JMenuItem menuItemVerTickets;
	private JMenuItem menuItemCerrarSesion;
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
	private ImageIcon imagenUsuario;

	public static JFrame ventanaActual;
	public static JLabel lblDeustoFly;

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
		new ImageIcon("img/papelera.png");
		imagenUsuario = new ImageIcon("img/usuario.png");

		panelEscritorio = new JDesktopPane();
		panelEscritorio.setBackground(new Color(0, 250, 154));
		menuPrincipal = new JMenuBar();
		menuPrincipal.setBackground(SystemColor.info);
		menuPasajero = new JMenu();
		menuPasajero.setIcon(imagenPasajero);
		menuPasajero.setMnemonic('P');
		

		lblDeustoFly = new JLabel("DEUSTOFLY");
		lblDeustoFly.setForeground(new Color(72, 61, 139));
		lblDeustoFly.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 36));

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
					.addContainerGap(574, Short.MAX_VALUE)
					.addComponent(lblDeustoFly, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addGap(446))
		);
		gl_panelEscritorio.setVerticalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(262)
					.addComponent(lblDeustoFly, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(273, Short.MAX_VALUE))
		);
		panelEscritorio.setLayout(gl_panelEscritorio);

		menuPasajero.setText("Pasajero");

		menuItemAniadirPasajero.setText("Anadir pasajero");

		menuPasajero.add(menuItemAniadirPasajero);

		menuItemActualizarPasajero = new JMenuItem();
		menuItemActualizarPasajero.setIcon(imagenActualizar);
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

		setJMenuBar(menuPrincipal);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelEscritorio));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelEscritorio));

		pack();
	}

	/**
	 * deshabilita el menu
	 */
	public static void bloquearBotones() {

		menuPasajero.setEnabled(false);
		menuTickets.setEnabled(false);
		menuVuelo.setEnabled(false);
		menuUsuario.setEnabled(false);

	}
	
	/**
	 * habilita el menu
	 */
	public static void desbloquearBotones() {

		menuPasajero.setEnabled(true);
		menuTickets.setEnabled(true);
		menuVuelo.setEnabled(true);
		menuUsuario.setEnabled(true);

	}

	/**
	 * 	
	 * Metodo que sirve para saber si la ventana en uso es la de
	 * administrador o la de azafato
	 * 
	 * @return boolean true si la ventana es la VentanaAzafato,false si es la ventana
	 *         VentanaAdministrador
	 *
	 * 
	 */
	public static boolean VentanaAzafatoEstaActiva() {
		if (ventanaActual == null) {
			return false;
		}

		else if (ventanaActual.isShowing() && ventanaActual != null) {
			return true;
		}
		return false;

	}

}