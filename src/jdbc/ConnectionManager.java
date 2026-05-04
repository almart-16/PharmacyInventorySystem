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
			this.c=DriverManager.getConnection(URL);
			try (Statement s =c.createStatement()) {
                s.execute("PRAGMA foreign_keys = ON;"); }
			
			createTables();
			System.out.println("Connection established: " + URL);
			
		}catch(Exception e ) {
			System.out.println("Database accer error");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		
		//los id no son 1,2,3 los hemos puesto como M-3, por eso TEXTose
		String [] tablesQueries = {
			//municipality
			"CREATE TABLE IF NOT EXISTS Municipality (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL UNIQUE)",
            
            //medication 
            "CREATE TABLE IF NOT EXISTS Medication ( " +
            "id TEXT PRIMARY KEY, " + 
            "name TEXT NOT NULL UNIQUE, " + 
            "target_illnes TEXT, " + 
            "SS TEXT, " + 
            "recipe TEXT) ", 
            
            //supplier 
            "CREATE TABLE IF NOT EXISTS Supplier (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL)",
            
            //client 
            "CREATE TABLE IF NOT EXISTS Client (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL, " +
            "national_id TEXT UNIQUE, " +
            "municipality_id TEXT, " +
            "FOREIGN KEY(municipality_id) REFERENCES Municipality(id))",
            
            //pharmacy
            "CREATE TABLE IF NOT EXISTS Pharmacy (" + 
            "id TEXT PRIMARY KEY, " +
            "address TEXT, " +
            "phone TEXT, " +
            "registration_number TEXT UNIQUE, " +
            "municipality_id TEXT, " +
            "FOREIGN KEY(municipality_id) REFERENCES Municipality(id))",
            
 
            //inventory 
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
            
            //purchase 
            "CREATE TABLE IF NOT EXISTS Purchase (" +
            "id TEXT PRIMARY KEY, " +
            "patient_id TEXT, " +
            "pharmacy_id TEXT, " +
            "medication_id TEXT, " +
            "date TEXT, " +
            "quantity INTEGER, " +
            "price REAL, " +
            "FOREIGN KEY(client_id) REFERENCES Client(id), " +
            "FOREIGN KEY(pharmacy_id) REFERENCES Pharmacy(id), " +
            "FOREIGN KEY(medication_id) REFERENCES Medication(id))",
            
            //orders 
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
        	// SQL Exception
            System.err.println("Error creating tables");
            e.printStackTrace();
        }
	}
	public Connection getc() {
		return c;
	}
		
}
