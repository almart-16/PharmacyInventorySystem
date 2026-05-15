package jdbc;

import java.sql.Connection;
import java.sql.Statement;

public class TestDataBaseCreation {
	
	/**
	 * Main method to create the database and tables.
	 * @param args the command line arguments
	 */
	public static void main(String[]args) {
		
        ConnectionManager connectionManager = new ConnectionManager();
        Connection c = connectionManager.getc();
		
		try {

			Statement s = c.createStatement();
			
			
			// it ensures that the relation between tables is maintained
			// by having the same value in both tables through the foreign keys
			s.execute("PRAGMA foreign_keys = OFF");
			
			
			//Eliminate tables if they already exist
			dropTables(s);
			
            s.execute("PRAGMA foreign_keys = ON;");

			createTables(s);
			
            System.out.println("Database created correctly.");

            s.close();
            c.close();
			
		} catch(Exception e) {
			System.out.println("Error creating the database");
		}
		
		
		
		
	}
	
	/**
	 * Drops all tables in the database if they exist.
	 * @param s the statement object to execute the queries
	 * @throws Exception if an error occurs during execution
	 */
	private static void dropTables(Statement s) throws Exception {

        s.executeUpdate("DROP TABLE IF EXISTS Orders");
        s.executeUpdate("DROP TABLE IF EXISTS Purchase");
        s.executeUpdate("DROP TABLE IF EXISTS Inventory");
        s.executeUpdate("DROP TABLE IF EXISTS Pharmacy");
        s.executeUpdate("DROP TABLE IF EXISTS Client");
        s.executeUpdate("DROP TABLE IF EXISTS Supplier");
        s.executeUpdate("DROP TABLE IF EXISTS Medication");
        s.executeUpdate("DROP TABLE IF EXISTS Municipality");

        // For JPA
        s.executeUpdate("DROP TABLE IF EXISTS User");
        s.executeUpdate("DROP TABLE IF EXISTS Role");
    }


	/**
	 * Creates all tables required for the database.
	 * @param s the statement object to execute the queries
	 * @throws Exception if an error occurs during execution
	 */
	private static void createTables(Statement s) throws Exception {

        createMunicipalityTable(s);
        createMedicationTable(s);
        createSupplierTable(s);
        createClientTable(s);
        createPharmacyTable(s);
        createInventoryTable(s);
        createPurchaseTable(s);
        createOrdersTable(s);

        // For JPA: login, users and roles
        createRoleTable(s);
        createUserTable(s);
    }

    
    
	/**
	 * Creates the Municipality table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createMunicipalityTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Municipality ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL UNIQUE"
                + ")";

        s.executeUpdate(sql);
    }
    
    
	/**
	 * Creates the Medication table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createMedicationTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Medication ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL UNIQUE, "
                + "targetIllness TEXT, "
                + "ss BOOLEAN NOT NULL, "
                + "prescription BOOLEAN NOT NULL"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Supplier table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createSupplierTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Supplier ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "phone TEXT NOT NULL UNIQUE"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Client table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createClientTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Client ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL, "
                + "nationalId TEXT UNIQUE, "
                + "age INTEGER, "
                + "municipalityId TEXT, "
                + "FOREIGN KEY(municipalityId) REFERENCES Municipality(id)"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Pharmacy table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createPharmacyTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Pharmacy ("
                + "id TEXT PRIMARY KEY, "
                + "address TEXT, "
                + "phone TEXT, "
                + "registrationNumber TEXT UNIQUE, "
                + "municipalityId TEXT, "
                + "photo BLOB, "
                + "FOREIGN KEY(municipalityId) REFERENCES Municipality(id)"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Inventory table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createInventoryTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Inventory ("
                + "id TEXT PRIMARY KEY, "
                + "pharmacyId TEXT, "
                + "medicationId TEXT, "
                + "supplierId TEXT, "
                + "stockQuantity INTEGER, "
                + "price REAL, "
                + "expirationDate TEXT, "
                + "minimumStock INTEGER, "
                + "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), "
                + "FOREIGN KEY(medicationId) REFERENCES Medication(id), "
                + "FOREIGN KEY(supplierId) REFERENCES Supplier(id)"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Purchase table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createPurchaseTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Purchase ("
                + "id TEXT PRIMARY KEY, "
                + "clientId TEXT, "
                + "pharmacyId TEXT, "
                + "date TEXT, "
                + "medicationId TEXT, "
                + "quantity INTEGER, "
                + "price REAL, "
                + "FOREIGN KEY(clientId) REFERENCES Client(id), "
                + "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), "
                + "FOREIGN KEY(medicationId) REFERENCES Medication(id)"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Orders table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createOrdersTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Orders ("
                + "id TEXT PRIMARY KEY, "
                + "pharmacyId TEXT, "
                + "supplierId TEXT, "
                + "medicationId TEXT, "
                + "date TEXT, "
                + "quantity INTEGER, "
                + "status TEXT, "
                + "FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), "
                + "FOREIGN KEY(supplierId) REFERENCES Supplier(id), "
                + "FOREIGN KEY(medicationId) REFERENCES Medication(id)"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the Role table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createRoleTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS Role ("
                + "id TEXT PRIMARY KEY, "
                + "name TEXT NOT NULL UNIQUE"
                + ")";

        s.executeUpdate(sql);
    }

    /**
	 * Creates the User table.
	 * @param s the statement object
	 * @throws Exception if an error occurs
	 */
	private static void createUserTable(Statement s) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS User ("
                + "id TEXT PRIMARY KEY, "
                + "username TEXT NOT NULL UNIQUE, "
                + "password BLOB NOT NULL, "
                + "roleId TEXT NOT NULL, "
                + "FOREIGN KEY(roleId) REFERENCES Role(id)"
                + ")";

        s.executeUpdate(sql);
        
        
    }
    
    
    
    
	
	
}

