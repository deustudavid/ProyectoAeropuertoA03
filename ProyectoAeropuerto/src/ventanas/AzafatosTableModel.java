package ventanas;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import clases.Azafato;

public class AzafatosTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<String> headers = Arrays.asList( "Nombre usuario del azafato", "Contrasenia", "Anios experiencia", "Funcion");
	private List<Azafato> azafatos;

	public AzafatosTableModel(List<Azafato> azafatos) {
		this.azafatos = azafatos;
	}
	
	public boolean isCellEditable(int row, int column) {
		
			return false;
		
	}
	
	

	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}

	@Override
	public int getRowCount() {
		return azafatos.size();
	}

	@Override
	public int getColumnCount() {
		return headers.size(); 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Azafato launch = azafatos.get(rowIndex);
		switch (columnIndex) {
			case 0: return launch.getUsuario();
			case 1: return launch.getContrasenya();
			case 2: return launch.getAnyosExperiencia();
			case 3: return launch.getFuncion();
			default: return null;
		}
	}
	
	
	

}
