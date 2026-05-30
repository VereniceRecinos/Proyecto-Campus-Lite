package model;

import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;
import view.DashboardView;
import utils.PersistenceManager;

    /*
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import view.EduManagerFrame;
import view.PantallaEstudiantes;
*/

import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. Inicializar listas vacías
        List<Student> studentList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        List<Enrollment> enrollmentList = new ArrayList<>();
        List<Evaluation> evaluationList = new ArrayList<>();

        // 2. Cargar datos del disco duro a las listas
        PersistenceManager.loadData(studentList, courseList, enrollmentList, evaluationList);

        // 3. Arrancar la aplicación pasándole las listas llenas
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardView(studentList, courseList, enrollmentList, evaluationList).setVisible(true);
        });
    }
            
        
            // se agrega Login
            
		
	    /*	// Ejecutamos la pantalla en el hilo de Swing
        SwingUtilities.invokeLater(() -> {
            new EduManagerFrame().setVisible(true);
        });
    
        // Ejecutamos la pantalla Estudiantes
        SwingUtilities.invokeLater(() -> {
            PantallaEstudiantes ventana = new PantallaEstudiantes();
            ventana.setVisible(true);
        });*/


	}
        
        /*public static void estudent_test () {
            		Estudiante estudiante1 = new Estudiante(
				"2026001",
				"Emilyn",
				"Recinos",
				"emilyn@gmail.com"
				);

		Curso curso1 = new Curso(
				"IPC1",
				"Programacion 1", 30);

		Inscripcion inscripcion1 = new Inscripcion(
				estudiante1,
				curso1,
				"21/05/2026");

		Evaluacion examen1 = new ExamenEscrito(
				"Parcial 1", 30, 95);

		Evaluacion laboratorio1 = new Laboratorio(
				"Laboratorio 1", 20, 90);

		Evaluacion proyecto1 = new Proyecto(
				"Proyecto Final", 50, 88);

		System.out.println(
				estudiante1.getNombre()
				);

		System.out.println(
				curso1.getNombre()
				);

		System.out.println(
				inscripcion1.getFechaInscripcion()
				);

		List<Evaluacion> evaluaciones =
				new ArrayList<>();

		evaluaciones.add(examen1);
		evaluaciones.add(laboratorio1);
		evaluaciones.add(proyecto1);

		System.out.println(
				"\n---- POLIMORFISMO ----"
				);

		for (Evaluacion e : evaluaciones) {

			System.out.println(
					e.getNombre()
					);

			System.out.println(
					"Puntaje obtenido: " +
					String.format("%.2f",e.puntajeObtenido())
			);
		}
        }*/
                


