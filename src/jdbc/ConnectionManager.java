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
            "SS BOOLEAN NOT NULL, " + 
            "prescription BOOLEAN NOT NULL) ", 
            
            //supplier 
            "CREATE TABLE IF NOT EXISTS Supplier (" +
            "id TEXT PRIMARY KEY, " +
<<<<<<< HEAD
            "name TEXT NOT NULL)" +
            "phone TEXT NOT NULL UNIQUE",
=======
            "name TEXT NOT NULL, "+ 
            "phone TEXT)",
>>>>>>> 4378796aff638ca928e9bfcec81bc7ad33828fc3
            
     
        
            //client 
            "CREATE TABLE IF NOT EXISTS Client (" +
            "id TEXT PRIMARY KEY, " +
            "name TEXT NOT NULL, " +
            "nationalId TEXT UNIQUE, " +
            "age INTEGER " +
            "municipalityId TEXT, " +
            "FOREIGN KEY(municipalityId) REFERENCES Municipality(id))",
            
            //pharmacy
            "CREATE TABLE IF NOT EXISTS Pharmacy (" + 
            "id TEXT PRIMARY KEY, " +
            "address TEXT, " +
            "phone TEXT, " +
            "registrationNumber TEXT UNIQUE, " +
            "municipalityId TEXT, " +
            "FOREIGN KEY(municipalityId) REFERENCES Municipality(id))",
            "registration_number TEXT UNIQUE, " +
            "municipality_id TEXT, " +
            "photo BLOB,"+ //punto extra 
            "FOREIGN KEY(municipalityId) REFERENCES Municipality(id))",

            
 
            //inventory 
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
            
            //purchase 
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
            
            //orders 
            "CREATE TABLE IF NOT EXISTS Order (" +
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
	public Connection getc() {
		return c;
	}
}
