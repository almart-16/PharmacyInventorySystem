package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private Connection c;
    private final String URL = "jdbc:sqlite:./src/db/pharmacy.db";
    
    public ConnectionManager () {
        initializeDB();
    }
        
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
            "target_illness TEXT, " + 
            "SS BOOLEAN NOT NULL, " + 
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
            "registration_number TEXT UNIQUE, " +
            "municipality_id TEXT, " +
            "photo BLOB, " + 
            "FOREIGN KEY(municipality_id) REFERENCES Municipality(id))",
            
            // Inventory
            "CREATE TABLE IF NOT EXISTS Inventory (" +
            "id TEXT PRIMARY KEY, " +
            "pharmacy_id TEXT, " +
            "medication_id TEXT, " +
            "supplier_id TEXT, " +
            "stock_quantity INTEGER, " +
            "price REAL, " +
            "expiration_date TEXT, " +
            "minimum_stock INTEGER, " +
            "FOREIGN KEY(pharmacy_id) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(medication_id) REFERENCES Medication(id), " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(id))",
            
            // Purchase
            "CREATE TABLE IF NOT EXISTS Purchase (" +
            "id TEXT PRIMARY KEY, " +
            "client_id TEXT, " +
            "pharmacy_id TEXT, " +
            "date TEXT, " +
            "medication_id TEXT, " +
            "quantity INTEGER, " +
            "price REAL, " +
            "FOREIGN KEY(client_id) REFERENCES Client(id), " +
            "FOREIGN KEY(pharmacy_id) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(medication_id) REFERENCES Medication(id))",
            
            // Orders (Corregido: Nombre cambiado de Order a Orders)
            "CREATE TABLE IF NOT EXISTS Orders (" +
            "id TEXT PRIMARY KEY, " +
            "pharmacy_id TEXT, " +
            "supplier_id TEXT, " +
            "medication_id TEXT, " +
            "date TEXT, " +
            "quantity INTEGER, " +
            "status TEXT, " +
            "FOREIGN KEY(pharmacy_id) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(supplier_id) REFERENCES Supplier(id), " +
            "FOREIGN KEY(medication_id) REFERENCES Medication(id))"
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
    
    public Connection getc() {
        return c;
    }
}