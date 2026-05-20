package model;

public class Curso {

	private String codigo;
	private String nombre;
	 
	public Curso(String codigo, String nombre) {
		
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Curso(String nombre) {
		
		this.codigo = "Sin codigo";
		this.nombre = nombre;
		
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void mostrarCurso() {
		System.out.println("Curso: " + nombre);
	}
}
