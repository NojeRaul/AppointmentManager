package Repository;

import Domain.Appointment;
import Domain.Doctor;
import Domain.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppointmentDatabaseRepository extends DBRepository<Appointment,Integer> {
    public AppointmentDatabaseRepository(String tablename)
    {
        super(tablename);
        getData();
    }

    @Override
    public void getData() {
        try
        {
            openConnection();
            String selectString = "SELECT * FROM " + tableName + ";";
            try (PreparedStatement ps = conn.prepareStatement(selectString))
            {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String date = resultSet.getString("date");
                    int patient_id = resultSet.getInt("patient_id");
                    int doctor_id = resultSet.getInt("doctor_id");
                    Appointment appointment = new Appointment(id,date,patient_id,doctor_id);
                    super.add(appointment);
                }
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
    public void add(Appointment app)
    {
        try
        {
            openConnection();
            Statement statement = conn.createStatement();
            statement.execute("PRAGMA foreign_keys = ON;");
            String insertString = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString))
            {
                ps.setInt(1,app.getId());
                ps.setInt(2, app.getPatient_id());
                ps.setInt(3,app.getDoctor_id());
                ps.setString(4, app.getDate());

                ps.executeUpdate();
                super.add(app);

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
    public void update(Integer id,Appointment app)
    {
        deleteById(id);
        add(app);
    }


}