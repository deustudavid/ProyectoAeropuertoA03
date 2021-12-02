package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.VentanaInicio;

import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.InputEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

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
	private JMenuItem menuEscogerAvion;
	private JLabel lblFoto;
	private JPanel pnlDerecha;
	private JPanel contentPane;
	private String rutaFoto;

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
	private ImageIcon imagenAvion2;
	private ImageIcon im;
	private ImageIcon imagenConDimensiones;

	static JFrame ventanaActual;
	static JTable tablaVuelos;
	private JMenuItem EscogerAvion;
	private JTextField tipoDeAvion;
	private JTextField InfoAvion;

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
		imagenAvion2 = new ImageIcon("img/Avion3.png");

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

		JLabel labelAvion = new JLabel();

		InfoAvion = new JTextField();
		InfoAvion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		InfoAvion.setBackground(Color.CYAN);
		InfoAvion.setColumns(10);

		GroupLayout gl_panelEscritorio = new GroupLayout(panelEscritorio);
		gl_panelEscritorio.setHorizontalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(59)
					.addComponent(tablaVuelos, GroupLayout.PREFERRED_SIZE, 709, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelEscritorio.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEscritorio.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
							.addComponent(labelAvion, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
							.addGap(38))
						.addGroup(gl_panelEscritorio.createSequentialGroup()
							.addGap(147)
							.addComponent(InfoAvion, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panelEscritorio.setVerticalGroup(
			gl_panelEscritorio.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelEscritorio.createSequentialGroup()
					.addGap(78)
					.addGroup(gl_panelEscritorio.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelEscritorio.createSequentialGroup()
							.addComponent(InfoAvion, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelAvion, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addComponent(tablaVuelos, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)))
		);

		// pone el tamaño por defecto

		// menuEscogerAvion.setText("Avion");
		// menuEscogerAvion.setIcon(imagenAvion2);

		// menuEscogerAvion.add(menuVuelo);

		pnlDerecha = new JPanel();
		contentPane = new JPanel();
		contentPane.add(pnlDerecha, BorderLayout.SOUTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		// rutaFoto = new String();
		JLabel lblFoto = new JLabel("");
		lblFoto.setSize(150, 205);
		lblFoto.setVisible(false);
		pnlDerecha.add(lblFoto);

		ImageIcon im = new ImageIcon(rutaFoto);

		lblFoto.setIcon(imagenConDimensiones);

		menuEscogerAvion = new JMenuItem("Tipo de Avion");
		menuEscogerAvion.setIcon(imagenAvion2);
		menuVuelo.add(menuEscogerAvion);
		// hashmap
		HashMap<String, String> hashAviones = new HashMap<String, String>();
		hashAviones.put("Tipo1.jpg", "AIRBUS");
		hashAviones.put("Tipo2.jpg", "AIRJET");
		hashAviones.put("Tipo3.jfif", "EUROFLY");
		hashAviones.put("Tipo4.jpg", "TURBLUNT");
		InfoAvion.setEditable(false);

		menuEscogerAvion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("aviones");
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("JPG & JFIF", "jpg", "jfif");
				fc.setFileFilter(fnef);
				int sel = fc.showOpenDialog(null);
				if (sel == JFileChooser.APPROVE_OPTION) {

					File ficheroSeleccionado = fc.getSelectedFile();

					System.out.println("Nombre del fichero seleccionado" + ficheroSeleccionado.getName());
					System.out.println("Ruta del fichero seleccionado: " + ficheroSeleccionado.getAbsolutePath());

					// hashmap
					System.out.println("Tipo de avion" + hashAviones.get(ficheroSeleccionado.getName()));
					InfoAvion.setText("- " + hashAviones.get(ficheroSeleccionado.getName()));

					// prueba
					// //PORQUE DA ERROR Image.SCALE_DEFAULT
					// ImageIcon imageIcon = new ImageIcon(
					// new
					// ImageIcon(ficheroSeleccionado.getAbsolutePath()).getImage().getScaledInstance(20,
					// 20,Image.SCALE_DEFAULT));
					// labelAvion.setIcon(imageIcon);
					// bueno
					ImageIcon im = new ImageIcon(ficheroSeleccionado.getAbsolutePath());
					im.setDescription(ficheroSeleccionado.getAbsolutePath());

					labelAvion.setIcon(im);

				}

			}
		});

		/*
		 * JButton btnEscogerAviones = new JButton();
		 * 
		 * btnEscogerAviones.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { JFileChooser fc = new
		 * JFileChooser("aviones"); FileNameExtensionFilter fnef = new
		 * FileNameExtensionFilter("JPG & JFIF", "jpg","jfif"); fc.setFileFilter(fnef);
		 * int sel = fc.showOpenDialog(null); if(sel == JFileChooser.APPROVE_OPTION) {
		 * File ficheroSeleccionado = fc.getSelectedFile();
		 * System.out.println("Nombre del fichero seleccionado"+
		 * ficheroSeleccionado.getName());
		 * System.out.println("Ruta del fichero seleccionado: "+
		 * ficheroSeleccionado.getAbsolutePath());
		 * 
		 * 
		 * }
		 * 
		 * } });
		 */

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
	 * Metodo que sirve para saber si la ventana que está abierta es la de
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
