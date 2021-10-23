

public class Usuario {

	private String correo;
	private String contrasenya;

	public Usuario(String correo, String contrasenya) {
		super();
		this.correo = correo;
		this.contrasenya = contrasenya;
	}
	
	public Usuario() {
		super();
		this.correo = "";
		this.contrasenya = "";
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", contrasenya=" + contrasenya + "]";
	}



}
