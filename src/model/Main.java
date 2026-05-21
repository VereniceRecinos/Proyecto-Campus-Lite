package model;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Estudiante estudiante1 = new Estudiante(
                "2026001",
                "Emilyn",
                "Recinos",
                "emilyn@gmail.com"
        );

        Curso curso1 = new Curso(
                "IPC1",
                "Programacion 1",
                30
        );

        Inscripcion inscripcion1 = new Inscripcion(
                estudiante1,
                curso1,
                "21/05/2026"
        );

        Evaluacion examen1 = new ExamenEscrito(
                "Parcial 1",
                30,
                95
        );

        System.out.println(
                estudiante1.getNombre()
        );

        System.out.println(
                curso1.getNombre()
        );

        System.out.println(
                inscripcion1.getFechaInscripcion()
        );

        System.out.println(
                examen1.puntajeObtenido()
        );

        List<Evaluacion> evaluaciones =
                new ArrayList<>();

        evaluaciones.add(examen1);

        System.out.println(
                evaluaciones.size()
        );
    }
}