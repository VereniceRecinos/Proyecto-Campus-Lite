package model;

public class Inscripcion {

	private Estudiante estudiante;
	private Curso curso;
	
	public Inscripcion(Estudiante estudiante, Curso curso) {
		
		this.estudiante = estudiante;
		this.curso = curso;
	}
	
	public void mostrarInscripcion() {
		
		System.out.println(estudiante.getNombre() + " inscrito en " + curso.getNombre());
	}
}
