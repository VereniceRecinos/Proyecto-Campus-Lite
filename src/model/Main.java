package model;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Estudiante estudiante1 = new Estudiante("2026001", "Emilyn");
		
		Curso curso1 = new Curso("C001", "Programacion 1");
		
		Inscripcion inscripcion1 = new Inscripcion(estudiante1, curso1);
		
		estudiante1.mostrarInfo();
		
		curso1.mostrarCurso();
		
		inscripcion1.mostrarInscripcion();
		
		Evaluacion examen1 = new Examen("Parcial 1", 95);
		
		examen1.mostrarResultado();
		
		List<Evaluacion> evaluaciones = new ArrayList<>();
		
		evaluaciones.add(examen1);
		
		System.out.println("Cantidad de evaluaciones: " + evaluaciones.size());

	}

}
