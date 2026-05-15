package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private Connection c;
    private final String URL = "jdbc:sqlite:./src/db/pharmacy.db";
    
    /**
     * Default constructor that initializes the database connection.
     */
    public ConnectionManager () {
        initializeDB();
    }
        
    /**
     * Initializes the database connection and creates tables if they do not exist.
     */
    private void initializeDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection(URL);
            try (Statement s = c.createStatement()) {
                s.execute("PRAGMA foreign_keys = ON;"); 
            }
            createTables();
            System.out.println("Connection established: " + URL);
        } catch(Exception e) {
            System.out.println("Database access error");
            e.printStackTrace();
        }
    }
    
    /**
     * Creates all necessary tables for the pharmacy inventory system.
     */
    private void createTables() {
        String[] tablesQueries = {
            // Municipality
            "CREATE TABLE IF NOT EXISTS Municipality (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL UNIQUE)",
            
            // Medication (Corregido: target_illness y prescription)
            "CREATE TABLE IF NOT EXISTS Medication ( " +
            "id TEXT PRIMARY KEY, " + 
            "name TEXT NOT NULL UNIQUE, " + 
            "targetIllness TEXT, " + 
            "ss BOOLEAN NOT NULL, " + 
            "prescription BOOLEAN NOT NULL)", 
            
            // Supplier (Corregido: errores de comillas y campos duplicados)
            "CREATE TABLE IF NOT EXISTS Supplier (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL, " +
            "phone TEXT NOT NULL UNIQUE)",
            
            // Client
            "CREATE TABLE IF NOT EXISTS Client (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL, " +
            "nationalId TEXT UNIQUE, " +
            "age INTEGER, " +
            "municipalityId TEXT, " +
            "FOREIGN KEY(municipalityId) REFERENCES Municipality(id))",
            
            // Pharmacy (Corregido: duplicidades eliminadas)
            "CREATE TABLE IF NOT EXISTS Pharmacy (" + 
            "id TEXT PRIMARY KEY, " +
            "address TEXT, " +
            "phone TEXT, " +
            "registrationNumber TEXT UNIQUE, " +
            "municipalityId TEXT, " +
            "photo BLOB, " + 
            "FOREIGN KEY(municipalityId) REFERENCES Municipality(id))",
            
            // Inventory
            "CREATE TABLE IF NOT EXISTS Inventory (" +
            "id TEXT PRIMARY KEY, " +
            "pharmacyId TEXT, " +
            "medicationId TEXT, " +
            "supplierId TEXT, " +
            "stockQuantity INTEGER, " +
            "price REAL, " +
            "expirationDate TEXT, " +
            "minimumStock INTEGER, " +
            "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(medicationId) REFERENCES Medication(id), " +
            "FOREIGN KEY(supplierId) REFERENCES Supplier(id))",
            
            // Purchase
            "CREATE TABLE IF NOT EXISTS Purchase (" +
            "id TEXT PRIMARY KEY, " +
            "clientId TEXT, " +
            "pharmacyId TEXT, " +
            "date TEXT, " +
            "medicationId TEXT, " +
            "quantity INTEGER, " +
            "price REAL, " +
            "FOREIGN KEY(clientId) REFERENCES Client(id), " +
            "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(medicationId) REFERENCES Medication(id))",
            
            // Orders (Corregido: Nombre cambiado de Order a Orders)
            "CREATE TABLE IF NOT EXISTS Orders (" +
            "id TEXT PRIMARY KEY, " +
            "pharmacyId TEXT, " +
            "supplierId TEXT, " +
            "medicationId TEXT, " +
            "date TEXT, " +
            "quantity INTEGER, " +
            "status TEXT, " +
            "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(supplierId) REFERENCES Supplier(id), " +
            "FOREIGN KEY(medicationId) REFERENCES Medication(id))"
        };
    
        try (Statement s = c.createStatement()) {
            for (String query : tablesQueries) {
                s.executeUpdate(query); 
            }
        } catch (SQLException e) {
            System.err.println("Error creating tables");
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