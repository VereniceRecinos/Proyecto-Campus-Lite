package utils;

import java.io.*;
import java.util.List;

import model.Course;
import model.Enrollment;
import model.Evaluation;
import model.Student;

public class PersistenceManager {
    private static final String FILE_NAME = "campus_lite_data.dat";

    // Guarda todas las listas en el archivo
    public static void saveData(List<Student> students, List<Course> courses, List<Enrollment> enrollments, List<Evaluation> evaluations) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            oos.writeObject(courses);
            oos.writeObject(enrollments);
            oos.writeObject(evaluations);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Carga los datos del archivo a las listas en memoria
    @SuppressWarnings("unchecked")
    public static void loadData(List<Student> students, List<Course> courses, List<Enrollment> enrollments, List<Evaluation> evaluations) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // Si el programa se abre por primera vez, no hace nada

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students.addAll((List<Student>) ois.readObject());
            courses.addAll((List<Course>) ois.readObject());
            enrollments.addAll((List<Enrollment>) ois.readObject());
            evaluations.addAll((List<Evaluation>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
