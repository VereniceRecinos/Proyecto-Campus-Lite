package model;

public class Estudiante {

    private String carnet;
    private String nombre;
    private String apellido;
    private String correo;

    public Estudiante(
            String carnet,
            String nombre,
            String apellido,
            String correo
    ) {

        if (carnet == null || carnet.isEmpty()) {
            throw new IllegalArgumentException(
                "El carnet no puede estar vacío"
            );
        }

        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}