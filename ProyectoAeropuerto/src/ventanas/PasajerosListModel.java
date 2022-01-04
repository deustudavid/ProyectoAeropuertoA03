package ventanas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.DefaultListModel;

import clases.Pasajero;

/**
 * Esta clase define un modelo propio para la lista de constelaciones
 * que permite obtener facilmente la lista de constelaciones.
 *
 */
public class PasajerosListModel extends DefaultListModel<Pasajero> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Obtiene la lista de constelaciones asociada al modelo de datos
	 * @return la lista de constelaciones asociada al modelo de datos
	 */
	public List<Pasajero> getpasajeros() {
		List<Pasajero> pasajeros = new ArrayList<>();
		
		Enumeration<Pasajero> it = this.elements();
		while (it.hasMoreElements()) {
			pasajeros.add(it.nextElement());
		}
		
		return pasajeros;
	}

}
