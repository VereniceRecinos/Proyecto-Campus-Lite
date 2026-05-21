package model;

public class ExamenEscrito extends Evaluacion {

    private double nota;

    public ExamenEscrito(
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

        return nota * (ponderacion / 100);
    }
}