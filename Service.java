package Service;

import Domain.Appointment;
import Domain.Doctor;
import Domain.Patient;
import Repository.*;
import Utils.SortByGrade;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;
public class Service {

    private Repository<Doctor,Integer> doctors;
    private Repository<Patient,Integer> patients;
    private Repository<Appointment,Integer> appointments;

    public Service(Repository<Doctor, Integer> doctors, Repository<Patient, Integer> patients, Repository<Appointment, Integer> appointments) {
        this.doctors = doctors;
        this.patients = patients;
        this.appointments = appointments;
    }

    //PATIENTS
    public void add_patient(Patient p)
    {
        if(patients.findById(p.getId())==null)
        {
            patients.add(p);
        }
        else
        {
            throw new RuntimeException("Id already existent");
        }
    }

    public void delete_patient(Integer id)
    {
        if(patients.getAll().containsKey(id)) {
            patients.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Id does not exist");
        }
    }

    public void update_patient(Patient p)
    {
        if(patients.getAll().containsKey(p.getId())) {
            patients.update(p.getId(), p);
        }
        else
        {
            throw new RuntimeException("Id does not exist");
        }
    }

    public Map<Integer,Patient> get_patients()
    {
        return this.patients.getAll();
    }



    //DOCTORS
    public Map<Integer,Doctor> get_doctors()
    {
        return this.doctors.getAll();
    }

    public void addDoctor(Doctor d)
    {
        if(doctors.findById(d.getId())==null)
        {
            doctors.add(d);
        }
        else
        {
            throw new RuntimeException("Id already existent");
        }
    }

    public void deleteDoctor(Integer id)
    {
        if(doctors.getAll().containsKey(id)) {
            doctors.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Id does not exist");
        }

    }
    public void update_doctor(Doctor d)
    {
        if(doctors.getAll().containsKey(d.getId())) {
            doctors.update(d.getId(), d);
        }
        else
        {
            throw new RuntimeException("Id does not exist");
        }
    }

    public Map<Integer,Appointment> getAppointments()
    {
        return this.appointments.getAll();
    }

    public void addAppointment(Appointment a) {
        if (appointments.findById(a.getId()) != null) {
            throw new RuntimeException("Id already exists");
        }
        else
        {
            this.appointments.add(a);
        }
    }


    public void deleteAppointment(Integer id)
    {
        if (appointments.findById(id)!=null) {
            appointments.deleteById(id);
        } else {
            throw new RuntimeException("Id non existent");
        }
    }
    public void updateAppointment(Appointment p)
    {
        if(appointments.findById(p.getId())!=null)
        {
            appointments.update(p.getId(),p);
        }
        else
        {
            throw new RuntimeException("Id non existent");
        }
    }


    //REPORTS//

    public List<Appointment> all_for_patient(Integer id)
    {
        //List of all appointments of a certain patient
        Predicate<Appointment> predicate1 = elem->elem.getPatient_id()==id;
        Collection<Appointment> appointments_filtered = this.getAppointments().values();
        List<Appointment> lst = appointments_filtered.stream().filter(predicate1).collect(Collectors.toList());
        return lst;
    }

    public List<Doctor> doctors_sorted()
    {
        //List of doctors sorted by grade

        Collection<Doctor> col = this.get_doctors().values();
        List<Doctor> lst = col.stream().collect(Collectors.toList());
        Collections.sort(lst,new SortByGrade());

        return lst;
    }

    public List<Doctor> doctors_filtered(String speciality)
    {
        //Doctors with a certain speciality

        Predicate<Doctor> predicate = doctor -> doctor.getSpeciality().equalsIgnoreCase(speciality);

        return get_doctors().values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Appointment> appointments_in_date(String date)
    {
        //All appointments in a certain day
        Predicate<Appointment> predicate = elem->elem.getDate().equals(date);
        Collection<Appointment> col = this.getAppointments().values();
        List<Appointment> lst = col.stream().filter(predicate).collect(Collectors.toList());
        return lst;
    }

    public List<Patient> patients_filtered(String disease)
    {
        //All patients with a certain disease
        Collection<Patient> col  = this.get_patients().values();
        Predicate<Patient> predicate = elem->elem.getDisease().equals(disease);
        List<Patient> lst = col.stream().filter(predicate).collect(Collectors.toList());
        return lst;
    }


}