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

///////////calcular distancia entre dos continentes de la tierra
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

	public static void main(String[] args) {
		System.out.println(calcularDistanciaPuntosSuperficieTierra(51.4077145, 30.044050, 35.219777, -101.826976));
	}

}
