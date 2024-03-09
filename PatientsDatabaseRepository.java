package Repository;

import Domain.Doctor;
import Domain.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientsDatabaseRepository extends DBRepository<Patient,Integer> {

    public PatientsDatabaseRepository(String tablename) {
        super(tablename);
        getData();
    }

    @Override
    public void getData() {
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String disease = resultSet.getString("disease");
                    Patient patient = new Patient(id, name, email, disease);
                    super.add(patient);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void add(Patient patient) {
        try {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setInt(1, patient.getId());
                ps.setString(2, patient.getName());
                ps.setString(3, patient.getEmail());
                ps.setString(4, patient.getDisease());
                super.add(patient);
                ps.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void deleteById(Integer id)
    {

        try
        {
            openConnection();
            String deleteString = "DELETE FROM " + tableName + " WHERE Id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteString))
            {
                ps.setInt(1,id);
                ps.executeUpdate();
                super.deleteById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {

            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void update(Integer id,Patient patient)
    {
        deleteById(id);
        add(patient);
    }
}