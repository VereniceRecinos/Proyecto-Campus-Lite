package model;

public abstract class Evaluacion {
	
	protected String nombre;
	protected double nota;
	
	public Evaluacion(String nombre, double nota) {
		
		if (nota < 0 || nota > 100) {
			throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
		}
		
		this.nombre = nombre;
		this.nota = nota;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getNota() {
		return nota;
	}
	
	public abstract void mostrarResultado();

}
