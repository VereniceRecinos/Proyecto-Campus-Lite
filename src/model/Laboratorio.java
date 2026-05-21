package model;

public class Laboratorio extends Evaluacion {

    private double nota;

    public Laboratorio(
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