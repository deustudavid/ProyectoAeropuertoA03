package clases;

public class Usuario {

	private String usuario;
	private String contrasenya;

	public Usuario(String usuario, String contrasenya) {
		super();
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}

	public Usuario() {
		super();
		this.usuario = "";
		this.contrasenya = "";
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String correo) {
		this.usuario = correo;
	}

	@Override
	public String toString() {
		return "Usuario [correo=" + usuario + ", contrasenya=" + contrasenya + "]";
	}

}
