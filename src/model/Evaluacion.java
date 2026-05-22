package model;

public abstract class Evaluacion {

	protected String nombre;
	protected double ponderacion;

	public Evaluacion(
			String nombre,
			double ponderacion
			) {

		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException(
					"El nombre no puede estar vacío"
					);
		}

		if (ponderacion < 0 || ponderacion > 100) {
			throw new IllegalArgumentException(
					"La ponderacion debe estar entre 0 y 100"
					);
		}

		this.nombre = nombre;
		this.ponderacion = ponderacion;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPonderacion() {
		return ponderacion;
	}

	public abstract double puntajeObtenido();
}
