package model;
import java.io.Serializable;

public abstract class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected double weight;

    public Evaluation(String name, double weight) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (weight < 0 || weight > 100) {
            throw new IllegalArgumentException("La ponderacion debe estar entre 0 y 100");
        }
        this.name = name;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public abstract double getObtainedScore();
    public abstract double getGrade();
}