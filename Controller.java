package com.example.appointmentmanager;

import Domain.Appointment;
import Domain.Doctor;
import Domain.Patient;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Service.Service;
import org.w3c.dom.Text;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Controller {

    private Service service;

    @FXML
    private Label doctor_id;
    @FXML
    private Label doctor_name;
    @FXML
    private Label doctor_speciality;
    @FXML
    private Label doctor_grade;
    @FXML
    private Label patient_id;
    @FXML
    private Label patient_name;
    @FXML
    private Label patient_email;
    @FXML
    private Label patient_disease;

    @FXML
    private TextField doctorIdAdd;
    @FXML
    private TextField doctorNameAdd;
    @FXML
    private TextField doctorSpecialityAdd;
    @FXML
    private TextField doctorGradeAdd;
    @FXML
    private TextField patientIdAdd;
    @FXML
    private TextField patientNameAdd;
    @FXML
    private TextField patientEmailAdd;
    @FXML
    private TextField patientDiseaseAdd;
    @FXML
    private TextField appointmentIdAdd;

    @FXML
    private TextField appointmentPatIdAdd;

    @FXML
    private TextField appointmentDocIdAdd;

    @FXML
    private TextField appointmentDateAdd;



    @FXML
    private TextField doctorIdRemove;
    @FXML
    private TextField patientIdRemove;

    @FXML
    private TextField appointmentIdRemove;


    @FXML
    private TextField doctorIdUpdate;
    @FXML
    private TextField doctorNameUpdate;
    @FXML
    private TextField doctorSpecialityUpdate;
    @FXML
    private TextField doctorGradeUpdate;
    @FXML
    private TextField patientIdUpdate;
    @FXML
    private TextField patientNameUpdate;
    @FXML
    private TextField patientEmailUpdate;
    @FXML
    private TextField patientDiseaseUpdate;
    @FXML
    private TextField appointmentIdUpdate;
    @FXML
    private TextField appointmentPatIdUpdate;
    @FXML
    private TextField appointmentDocIdUpdate;
    @FXML
    private TextField appointmentDateUpdate;


    @FXML
    private TextField doctorsFiltered;
    @FXML
    private TextField patientsFiltered;
    @FXML
    private TextField appsInDate;
    @FXML
    private TextField appsForPatient;





    @FXML
    private ListView<Doctor> doctorListView;
    @FXML
    private ListView<Patient> patientListView;
    @FXML
    private ListView<Appointment> appointmentListView;

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Show and wait for the user to close the dialog
        alert.showAndWait();
    }
    @FXML
    protected void listDoctorsClick(ActionEvent event){
        populateListDoctors();
    }

    @FXML
    protected void listPatientsClick()
    {
        populateListPatients();
    }
    @FXML
    protected void listAppointmentsClick()
    {
        populateListAppointments();
    }

    @FXML
    protected void addPatient(ActionEvent evetnt) {
        try {
            int id = Integer.parseInt(patientIdAdd.getText());
            String name = patientNameAdd.getText();
            String email = patientEmailAdd.getText();
            String disease = patientDiseaseAdd.getText();
            Patient p = new Patient(id, name,email,disease);
            service.add_patient(p);
            populateListPatients();
        } catch (Exception ex) {
            showErrorDialog("Alert", "An error has occurred", ex.getMessage());
            //ex.printStackTrace();
        }
    }


    @FXML
    protected void addDoctor(ActionEvent evetnt){
        try{
            int id = Integer.parseInt(doctorIdAdd.getText());
            String name = doctorNameAdd.getText();
            String speciality = doctorSpecialityAdd.getText();
            double grade = Double.parseDouble(doctorGradeAdd.getText());
            Doctor d = new Doctor(id,name,speciality,grade);

            service.addDoctor(d);
            populateListDoctors();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }
    }

    @FXML
    protected void addAppointment(ActionEvent evetnt){
        try{
            int id = Integer.parseInt(appointmentIdAdd.getText());
            int pat_id = Integer.parseInt(appointmentPatIdAdd.getText());
            int doc_id =  Integer.parseInt(appointmentDocIdAdd.getText());
            String date = appointmentDateAdd.getText();
            Appointment app = new Appointment(id,date,pat_id,doc_id);

            service.addAppointment(app);
            populateListAppointments();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }
    }


    @FXML
    protected void deleteDoctor()
    {
        try {
            int id = Integer.parseInt(doctorIdRemove.getText());
            this.service.deleteDoctor(id);
            populateListDoctors();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }

    }

    @FXML
    protected void deletePatient()
    {
        try {
            int id = Integer.parseInt(patientIdRemove.getText());
            this.service.delete_patient(id);
            populateListPatients();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }

    }
    @FXML
    protected void deleteAppointment()
    {
        try {
            int id = Integer.parseInt(appointmentIdRemove.getText());
            this.service.deleteAppointment(id);
            populateListAppointments();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }

    }

    @FXML
    protected void updateDoctor()
    {
        try{

            int id = Integer.parseInt(doctorIdUpdate.getText());
            String name = doctorNameUpdate.getText();
            String speciality = doctorSpecialityUpdate.getText();
            double grade = Double.parseDouble(doctorGradeUpdate.getText());
            Doctor d = new Doctor(id,name,speciality,grade);
            service.update_doctor(d);
            populateListDoctors();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }
    }
    @FXML
    protected void updateAppointment()
    {
        try{
            int id = Integer.parseInt(appointmentIdUpdate.getText());
            int pat_id = Integer.parseInt(appointmentPatIdUpdate.getText());
            int doc_id = Integer.parseInt(appointmentDocIdUpdate.getText());
            String date = appointmentDateUpdate.getText();
            Appointment app = new Appointment(id,date,pat_id,doc_id);

            service.updateAppointment(app);
            populateListAppointments();
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }
    }
    @FXML
    protected void updatePatient()
    {
        try{
            int id = Integer.parseInt(patientIdUpdate.getText());
            String name = patientNameUpdate.getText();
            String email = patientEmailUpdate.getText();
            String disease = patientDiseaseUpdate.getText();

            Patient p = new Patient(id, name,email,disease);
            service.update_patient(p);
            populateListPatients();

        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
            //ex.printStackTrace();
        }
    }

    @FXML
    protected void sortDoctors()
    {
        ObservableList<Doctor> list = FXCollections.observableArrayList(this.service.doctors_sorted());
        doctorListView.setItems(list);
    }

    @FXML
    protected void doctorsWithSpeciality()
    {
        try {
            String speciality = doctorsFiltered.getText();
            ObservableList<Doctor> list = FXCollections.observableArrayList(this.service.doctors_filtered(speciality));
            doctorListView.setItems(list);
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
        }
    }

    @FXML
    protected void patientsWithDisease()
    {
        try {
            String disease = patientsFiltered.getText();
            ObservableList<Patient> list = FXCollections.observableArrayList(this.service.patients_filtered(disease));
            patientListView.setItems(list);
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
        }
    }

    @FXML
    protected void appointmentsInDate()
    {
        try {
            String date = appsInDate.getText();
            ObservableList<Appointment> list = FXCollections.observableArrayList(this.service.appointments_in_date(date));
            appointmentListView.setItems(list);
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
        }

    }

    @FXML
    protected void appsForPatient()
    {
        try{
            int id = Integer.parseInt(appsForPatient.getText());
            ObservableList<Appointment> list = FXCollections.observableArrayList(this.service.all_for_patient(id));
            appointmentListView.setItems(list);
        }
        catch(Exception ex)
        {
            showErrorDialog("Alert","An error has occurred",ex.getMessage());
        }

    }

    public void initialize(String filename)
    {
        try (FileReader fr = new FileReader(filename))
        {
            Properties props = new Properties();
            props.load(fr);

            String repoType = props.getProperty("repository_type");
            //String sourceName = props.getProperty("book_repository");
            switch (repoType)
            {
                case "inmemory":
                    //repo = new BookMemoryRepository();
                    break;
                case "textfile":
                    DoctorsRepositoryTextFile doctors_txt = new DoctorsRepositoryTextFile("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Doctors.txt");
                    PatientsRepositoryTextFile patients_txt = new PatientsRepositoryTextFile("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Patients.txt");
                    AppointmentFileRepo appointments_txt = new AppointmentFileRepo("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Appointments.txt");
                    this.service = new Service(doctors_txt,patients_txt,appointments_txt);
                    break;
                case "binaryfile":

                    try {
                        DoctorsBinaryRepository doctors_bin = new DoctorsBinaryRepository("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Doctors.bin");
                        PatientsRepositoryBinaryFile patients_bin = new PatientsRepositoryBinaryFile("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Patients.bin");
                        AppointmentFileRepo appointments_bin = new AppointmentFileRepo("C:\\JAVA_PRJ\\ASSIGNEMENT_4\\Assign_4_5\\Appointments.txt");
                        this.service = new Service(doctors_bin, patients_bin, appointments_bin);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    break;
                case "database":
                    DoctorsDatabaseRepository doctors_db = new DoctorsDatabaseRepository("doctors");
                    PatientsDatabaseRepository patients_db = new PatientsDatabaseRepository("Patients");
                    AppointmentDatabaseRepository appointments_db = new AppointmentDatabaseRepository("Appointments");
                    this.service = new Service(doctors_db,patients_db,appointments_db);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void populateListDoctors()
    {
        Collection<Doctor> it = this.service.get_doctors().values();
        ObservableList<Doctor> list = FXCollections.observableArrayList(it);
        doctorListView.setItems(list);
    }
    private void populateListPatients()
    {
        Collection<Patient> it = this.service.get_patients().values();
        ObservableList<Patient> list = FXCollections.observableArrayList(it);
        patientListView.setItems(list);
    }
    private void populateListAppointments()
    {
        Collection<Appointment> it = this.service.getAppointments().values();
        ObservableList<Appointment> list = FXCollections.observableArrayList(it);
        appointmentListView.setItems(list);
    }
}