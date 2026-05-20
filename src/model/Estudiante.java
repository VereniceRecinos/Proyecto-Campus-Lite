package model;

public class Estudiante {

	private String carnet;
	private String nombre;
	
	public Estudiante(String carnet, String nombre) {
		
		if (carnet == null || carnet.isEmpty()) {
			throw new IllegalArgumentException("El carnet es obligatorio");
		}
		
		this.carnet = carnet;
		this.nombre = nombre;
	}
	
	public String getCarnet() {
		return carnet;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void mostrarInfo() {
		System.out.println("Carnet: " + carnet);
		System.out.println("Nombre: " + nombre);
	}
}
