package model;
public class Project extends Evaluation {
    private double grade;
    public Project(String name, double weight, double grade) {
        super(name, weight);
        if (grade < 0 || grade > 100) throw new IllegalArgumentException("Nota entre 0 y 100");
        this.grade = grade;
    }
    @Override public double getObtainedScore() { return (grade * 1.1) * (weight / 100); }
    @Override public double getGrade() { return this.grade; }
}