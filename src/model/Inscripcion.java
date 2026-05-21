package model;

public class Inscripcion {

    private Estudiante estudiante;
    private Curso curso;
    private String fechaInscripcion;

    public Inscripcion(
            Estudiante estudiante,
            Curso curso,
            String fechaInscripcion
    ) {

        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
}
