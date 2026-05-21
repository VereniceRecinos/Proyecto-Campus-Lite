package model;

public class Curso {

    private String codigo;
    private String nombre;
    private int cupo;

    public Curso(String codigo, String nombre, int cupo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.cupo = cupo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCupo() {
        return cupo;
    }
}
