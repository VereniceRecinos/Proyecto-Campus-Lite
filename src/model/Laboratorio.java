package model;

public class Laboratorio extends Evaluacion {

	private double nota;

	public Laboratorio(
			String nombre,
			double ponderacion,
			double nota
			) {

		super(nombre, ponderacion);

		if (nota < 0 || nota > 100) {
throw new IllegalArgumentException(
"La nota debe estar entre 0 y 100"
);
		}

		this.nota = nota;
	}

	@Override
	public double puntajeObtenido() {

		double bono = 2;

		return (nota + bono) * (ponderacion / 100);
	}
	
	@Override
	public double getNota() {
	    return this.nota;
	}
}