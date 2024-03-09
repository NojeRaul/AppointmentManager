package Repository;

import Domain.Entity;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DBRepository<T extends Entity<Id>,Id> extends MemoryRepository<T,Id>{
    protected final String URL = "jdbc:sqlite:identifier.sqlite";


    protected String tableName;
    protected Connection conn = null;

    public DBRepository(String tableName) {
        this.tableName = tableName;
    }

    public abstract void getData();


    public void openConnection() throws SQLException {
        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(URL);
            if (conn == null || conn.isClosed()) {
                System.out.println(URL);
                conn = dataSource.getConnection();
                System.out.println(conn.isClosed());
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }
    }
}