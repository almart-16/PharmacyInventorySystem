package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private Connection c;
    private final String URL = "jdbc:sqlite:./src/db/pharmacy_final_run.db?journal_mode=WAL&busy_timeout=30000";

    /**
     * Default constructor that initializes the database connection.
     */
    public ConnectionManager() {
        initializeDB();
    }

    /**
     * Initializes the database connection and creates tables if they do not exist.
     */
    private void initializeDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection(URL);

            DataBaseInitializer initializer = new DataBaseInitializer(c);
            initializer.createTables();
            initializer.insertInitialData();

            System.out.println("Connection established: " + URL);

        } catch (Exception e) {
            System.out.println("Database access error");
            e.printStackTrace();
        }
    }

    /**
     * Gets the established database connection.
     *
     * @return the connection object
     */
    public Connection getc() {
        return c;
    }
}