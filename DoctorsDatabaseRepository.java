package Repository;

import Domain.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorsDatabaseRepository extends DBRepository<Doctor,Integer>{


    public DoctorsDatabaseRepository(String tablename)
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
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("name");
                    String speciality = resultSet.getString("speciality");
                    double grade = resultSet.getDouble("grade");
                    Doctor doctor = new Doctor(id,name,speciality,grade);
                    super.add(doctor);
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

    @Override
    public void add(Doctor doctor)
    {
        try
        {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString))
            {
                ps.setInt(1,doctor.getId());
                ps.setString(2, doctor.getName());
                ps.setString(3, doctor.getSpeciality());
                ps.setDouble(4,doctor.getGrade());
                super.add(doctor);
                ps.executeUpdate();

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
    public void update(Integer id,Doctor doctor)
    {
        deleteById(id);
        add(doctor);
    }
}