package ventanas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Equipaje;

public class EquipajesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] cabecera = { "Descripcion", "peso", "largo", "altura", "anchura" }; 	
	private List<Equipaje> equipajes;
	
	public EquipajesTableModel() {
		
	}
	
	public EquipajesTableModel(List<Equipaje> equipajes) {
		this.equipajes = equipajes;
	}
	
	@Override
	public int getColumnCount() {
		return cabecera.length;
	}

	@Override
	public String getColumnName(int index) {
		return cabecera[index];
	}

	@Override
	public int getRowCount() {
		return equipajes == null ? 0 : equipajes.size();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Equipaje Equipaje = equipajes.get(row);
		
		switch (column) {
			case 0:	return Equipaje.getDescripcion();
			case 1:	return String.valueOf(Equipaje.getPeso());
			case 2:	return String.valueOf(Equipaje.getLargo());
			case 3:	return String.valueOf(Equipaje.getAltura());
			case 4:	return String.valueOf(Equipaje.getAnchura());
			
			
			default: return null;
		}
	}


	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
}
