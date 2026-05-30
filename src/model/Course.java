package model;
import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String name;
    private int capacity;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
}