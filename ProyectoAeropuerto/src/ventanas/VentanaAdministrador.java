package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import main.VentanaInicio;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;


public class VentanaAdministrador extends JFrame {

	private JDesktopPane panelEscritorio;
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
	private JMenuItem menuItemEliminarReserva;
	private JMenuItem menuItemVerVuelos;
	private JMenuItem menuItemGestionarAzafatos;
	private JMenuItem menuItemEliminarVuelo;
	private JMenuItem menuItemGestionarEquipajes;
	private JMenuItem menuItemDarPermisos;

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
	private ImageIcon imagenBorrar;
	private ImageIcon imagenUsuario;
	
	public static JFrame ventanaActual;
	static JTable tablaVuelos;

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
		imagenBorrar = new ImageIcon("img/papelera.png");
		imagenUsuario = new ImageIcon("img/usuario.png");
		imagenLlave = new ImageIcon("img/llave.png");

		panelEscritorio = new JDesktopPane();
		panelEscritorio.setBackground(new Color(0, 255, 255));
		menuPrincipal = new JMenuBar();
		menuPrincipal.setBackground(Color.LIGHT_GRAY);
		menuPasajero = new JMenu();
		menuPasajero.setIcon(imagenPasajero);
		menuPasajero.setMnemonic('P');

		menuItemAniadirPasajero = new JMenuItem();
		menuItemAniadirPasajero.setIcon(imagenAniadir);
		menuItemAniadirPasajero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuItemAniadirPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaVuelos.setVisible(false);
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
				tablaVuelos.setVisible(false);
				ReservarTicket r = new ReservarTicket();
				panelEscritorio.add(r);
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
		
		tablaVuelos = new JTable();
		DefaultTableModel modeloTablaVuelos = (DefaultTableModel)tablaVuelos.getModel();

		GroupLayout gl_panelEscritorio = new GroupLayout(panelEscritorio);
		gl_panelEscritorio.setHorizontalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(59)
					.addComponent(tablaVuelos, GroupLayout.PREFERRED_SIZE, 709, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(582, Short.MAX_VALUE))
		);
		gl_panelEscritorio.setVerticalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(78)
					.addComponent(tablaVuelos, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(246, Short.MAX_VALUE))
		);
		
		
		
		panelEscritorio.setLayout(gl_panelEscritorio);

		menuPasajero.setText("Pasajero");
		
		menuItemAniadirPasajero.setText("Anadir pasajero");

		menuPasajero.add(menuItemAniadirPasajero);

		menuItemActualizarPasajero.setText("Actualizar pasajero");
		menuItemActualizarPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tablaVuelos.setVisible(false);
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
		menuPasajero.add(menuItemGestionarEquipajes);

		menuTickets.setText("Tickets");

		menuItemReservarTicket.setText("Reservar Ticket");
		menuItemReservarTicket.setIcon(imagenReservar);

		menuTickets.add(menuItemReservarTicket);

		menuItemVerTickets.setText("Ver tickets");
		menuItemVerTickets.setIcon(imagenListar);

		menuItemVerTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tablaVuelos.setVisible(false);
				VerTickets v = new VerTickets();
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

		menuItemAnadirVuelo = new JMenuItem();
		menuItemAnadirVuelo.setIcon(imagenAniadir);
		menuItemAnadirVuelo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuItemAnadirVuelo.setText("Anadir vuelo");
		menuItemAnadirVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tablaVuelos.setVisible(false);
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
				//tablaVuelos.setVisible(tru);
				//hacer consulta a la base de datos
				//tablaVuelos.set
				Object [] fila = new Object[2];
				//fila[0] = "dato columna 1"; --> cada fila un vuelo
				//fila[1] = "dato columna 3"; --> cada fila un vuelo
				//modeloTablaVerVuelos.addRow ( fila );

			}
		});

		menuItemEliminarVuelo = new JMenuItem();
		menuItemEliminarVuelo.setIcon(imagenBorrar);
		menuItemEliminarVuelo.setText("Eliminar vuelo");
		menuVuelo.add(menuItemEliminarVuelo);

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
				tablaVuelos.setVisible(false);
				VentanaPermisos v = new VentanaPermisos();
				panelEscritorio.add(v);
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
}