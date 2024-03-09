package Domain;

import java.io.Serializable;

public class Doctor implements Entity<Integer>, Serializable {
    private Integer id;
    private String name,speciality;

    private Double grade;

    public Doctor(Integer id, String name,String speciality, Double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.speciality = speciality;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", grade=" + grade +
                '}';
    }
}