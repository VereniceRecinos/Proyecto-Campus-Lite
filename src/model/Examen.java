package model;

public class Examen extends Evaluacion {

	public Examen(String nombre, double nota) {
		super(nombre, nota);
	}
	
	@Override
	
	public void mostrarResultado() {
		
		System.out.println("Evaluacion: " + nombre);
		System.out.println("Nota: " + nota);
	}

}
