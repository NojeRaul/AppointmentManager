package Domain;
import java.util.Objects;

public class Appointment implements Entity<Integer>{

    private String date;
    Integer id,doctor_id,patient_id;

    public Appointment(Integer id, String date, Integer patient_id, Integer doctor_id) {
        this.id = id;
        this.date = date;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date='" + date + '\'' +
                ", id=" + id +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(date, that.date) && Objects.equals(doctor_id, that.doctor_id) && Objects.equals(patient_id, that.patient_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, doctor_id, patient_id);
    }
}