package model;

public class Proyecto extends Evaluacion {

    private double nota;

    public Proyecto(
            String nombre,
            double ponderacion,
            double nota
    ) {

        super(nombre, ponderacion);

        this.nota = nota;
    }

    @Override
    public double puntajeObtenido() {

        return nota * (ponderacion / 100);
    }
}
