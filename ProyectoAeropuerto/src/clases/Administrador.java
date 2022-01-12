package clases;

public class Administrador extends Usuario {
	String cargo;

	

	public Administrador(String usuario, String contrasenya, String cargo) {
		super(usuario, contrasenya);
		this.cargo = cargo;
	}

	public Administrador() {
		super();
		this.cargo = "";
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	

	

}
