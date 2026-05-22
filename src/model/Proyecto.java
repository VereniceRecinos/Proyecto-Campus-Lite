package model;

public class Proyecto extends Evaluacion {

    private double nota;

    public Proyecto(
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

        	double notaFinal = nota * 1.1;

        	return notaFinal * (ponderacion / 100);
    }
}
