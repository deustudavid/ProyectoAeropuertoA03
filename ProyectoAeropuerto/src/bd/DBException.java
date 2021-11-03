package bd;

/**
 * Representa un error en la base de datos
 *
 */
public class DBException extends Exception {
	private String mensaje;
	/**
	 * Construye la excepción con le mensaje indicado
	 * @param message mensaje contenido en la excepción
	 */

	
	public DBException(String message) throws DBException{
		super(message);
		mensaje = message;
	}

	/**
	 * Construye la excepción con el mensaje y la excepción
	 * interna anidada.
	 * @param message mensaje de la excepción
	 * @param t excepción interna anidada
	 */
	public DBException(String message, Throwable t) throws DBException{
		super(message, t);
		mensaje = message;
	}
	
	public String toString(){
		return mensaje;
	}
}

//