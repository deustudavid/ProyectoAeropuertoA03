
public class Vuelo {
	int codigo;
	String origen;
	String destino;
	Riesgo riesgo;
	
	public Vuelo(int codigo, String origen, String destino, Riesgo riesgo) {
		super();
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.riesgo = riesgo;
	}
	
	public Vuelo() {
		super();
		this.codigo = 0;
		this.origen = "";
		this.destino = "";
		this.riesgo = null;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Riesgo getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(Riesgo riesgo) {
		this.riesgo = riesgo;
	}

	@Override
	public String toString() {
		return "Vuelo [codigo=" + codigo + ", origen=" + origen + ", destino=" + destino + ", riesgo=" + riesgo + "]";
	}
	
	
	
	
	

}
