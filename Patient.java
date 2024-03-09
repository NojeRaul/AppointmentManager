package Domain;

import java.io.Serializable;

public class Patient implements Entity<Integer>,Serializable {
    private Integer id;
    private String name,email,disease;

    public Patient(Integer id, String name, String email, String disease) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }


}