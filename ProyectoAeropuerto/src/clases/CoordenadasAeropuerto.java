package clases;

public class CoordenadasAeropuerto {
	double latitud;
	double longitud;

	public CoordenadasAeropuerto(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public CoordenadasAeropuerto() {
		super();
		this.latitud = 0.0;
		this.longitud = 0.0;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	/**
	 * Calcula distancia entre dos puntos de la tierra
	 * @param double latitudPunto1 (latitud del punto A)
	 * @param double longitudPunto1 (longitud del punto A)
	 * @param double latitudPunto2 (latitud del punto B)
	 * @param double longitudPunto2 (longitud del punto B)
	 * 
	 * Nota: al reservar un vuelo en su correspondiente ventana, si tanto el punto A como el punto B pertenecen al mismo pais,
	 * no se cobran tarifas extra
	 * 
	 * En caso contrario, se llama a esta funcion para que calcule la distancia entre el pais de origen y destino,
	 * y se calcula una comision.
	 * 
	 * @return double distancia
	 */
	public static double calcularDistanciaPuntosSuperficieTierra(double latitudPunto1, double longitudPunto1,
			double latitudPunto2, double longitudPunto2) {
		latitudPunto1 = Math.toRadians(latitudPunto1);
		longitudPunto1 = Math.toRadians(longitudPunto1);
		latitudPunto2 = Math.toRadians(latitudPunto2);
		longitudPunto2 = Math.toRadians(longitudPunto2);

		final double radioTerrestre = 6371.01;// km
		double distancia = radioTerrestre * Math.acos(Math.sin(latitudPunto1) * Math.sin(latitudPunto2))
				+ Math.cos(latitudPunto1) * Math.cos(latitudPunto2) * Math.cos(longitudPunto1 - longitudPunto2);
		return distancia;
		
	}

	

}
