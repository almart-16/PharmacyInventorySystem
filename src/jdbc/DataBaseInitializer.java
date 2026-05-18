package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInitializer {
	
	private Connection c;
	
	
	public DataBaseInitializer(Connection c) {
		this.c = c;	}
	
	
	/**
     * Creates all necessary tables for the pharmacy inventory system.
     */
    public void createTables() {
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
            "purchasePrice REAL, " +
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
            "FOREIGN KEY(medicationId) REFERENCES Medication(id))",
          
            
            // History
            "CREATE TABLE IF NOT EXISTS History (" +
            		"id TEXT PRIMARY KEY, " +
            		"pharmacyId TEXT, " +
            		"medicationId TEXT, " +
            		"movementType TEXT, " +
            		"quantity INTEGER, " +
            		"price REAL, " +
            		"date TEXT, " +
            		"FOREIGN KEY(pharmacyId) REFERENCES Pharmacy(id), " +
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

        // Try to add purchasePrice column to existing Inventory table
        try (Statement s = c.createStatement()) {
            s.executeUpdate("ALTER TABLE Inventory ADD COLUMN purchasePrice REAL");
        } catch (SQLException e) {
            // Ignore error if column already exists
        }
        
        // Update old initial data if purchasePrice is missing or null
        try (Statement s = c.createStatement()) {
            s.executeUpdate("UPDATE Inventory SET purchasePrice = price / 2 WHERE purchasePrice IS NULL");
        } catch (SQLException e) {
            // Ignore error
        }
    }
    
    
    public void insertInitialData() {
    	try (Statement s = c.createStatement()){
    		
    		// Municipalities
            s.executeUpdate(
                "INSERT OR IGNORE INTO Municipality (id, name) VALUES " +
                "('MUN-0', 'Boadilla del Monte'), " +
                "('MUN-1', 'Pozuelo de Alarcon'), " +
                "('MUN-2', 'Alcorcon')"
            );

         // Medications
            s.executeUpdate(
                "INSERT OR IGNORE INTO Medication (id, name, targetIllness, ss, prescription) VALUES " +
                "('M-0', 'Ibuprofeno', 'Anti-inflammatory', 0, 0), " +
                "('M-1', 'Paracetamol', 'Analgesic and antipyretic', 0, 0), " +
                "('M-2', 'Aspirina', 'Antiplatelet', 0, 0), " +
                "('M-3', 'Amoxicilina', 'Antibiotic', 1, 1), " +
                "('M-4', 'Omeprazol', 'Antacid', 1, 1), " +
                "('M-5', 'Loratadina', 'Antihistamine', 0, 0), " +
                "('M-6', 'Metformina', 'Antidiabetic', 1, 1), " +
                "('M-7', 'Diazepam', 'Anxiolytic', 1, 1)"
            );
            
    		
         // Suppliers
            s.executeUpdate(
                "INSERT OR IGNORE INTO Supplier (id, name, phone) VALUES " +
                "('S-0', 'Bayer', '623401393'), " +
                "('S-1', 'Cardinal Health', '629461032'), " +
                "('S-2', 'Bidafarma', '64928351')"
            );

         // Clients
            s.executeUpdate(
                "INSERT OR IGNORE INTO Client (id, name, nationalId, age, municipalityId) VALUES " +
                "('C-0', 'Paola', '27673831L', 70, 'MUN-1'), " +
                "('C-1', 'Alex', '36219443S', 32, 'MUN-2'), " +
                "('C-2', 'Alba', '58347523P', 15, 'MUN-0')"
            );
            
            
            // Pharmacies
            s.executeUpdate(
                "INSERT OR IGNORE INTO Pharmacy (id, address, phone, registrationNumber, municipalityId, photo) VALUES " +
                "('P-0', 'Calle Monteamor, 3', '639826101', '28-82016', 'MUN-0', NULL), " +
                "('P-1', 'Calle Tomillo, 9', '619362434', '28-46194', 'MUN-2', NULL), " +
                "('P-2', 'Calle Sotavento, 22', '610372947', '28-98451', 'MUN-1', NULL)"
            );
            
         // Inventory
            s.executeUpdate(
                "INSERT OR IGNORE INTO Inventory " +
                "(id, pharmacyId, medicationId, supplierId, stockQuantity, price, purchasePrice, expirationDate, minimumStock) VALUES " +
                "('I-0', 'P-2', 'M-2', 'S-0', 30, 6.0, 3.0, '2029-04-01', 7), " +
                "('I-1', 'P-0', 'M-1', 'S-2', 20, 4.0, 2.0, '2027-03-01', 7), " +
                "('I-2', 'P-1', 'M-0', 'S-1', 25, 5.0, 2.5, '2032-09-01', 7), " +
                "('I-3', 'P-0', 'M-3', 'S-0', 50, 12.0, 6.0, '2028-05-01', 10), " +
                "('I-4', 'P-1', 'M-4', 'S-1', 100, 8.0, 3.5, '2027-10-15', 20), " +
                "('I-5', 'P-2', 'M-5', 'S-2', 40, 5.5, 2.0, '2029-01-20', 10), " +
                "('I-6', 'P-0', 'M-6', 'S-1', 60, 4.0, 1.5, '2028-11-30', 15), " +
                "('I-7', 'P-1', 'M-7', 'S-0', 20, 15.0, 8.0, '2027-08-10', 5)"
            );
            
            
         // Purchases
            s.executeUpdate(
                "INSERT OR IGNORE INTO Purchase " +
                "(id, clientId, pharmacyId, date, medicationId, quantity, price) VALUES " +
                "('PUR-0', 'C-2', 'P-0', '2026-04-12', 'M-1', 1, 4.0), " +
                "('PUR-1', 'C-1', 'P-1', '2026-01-04', 'M-2', 2, 6.0), " +
                "('PUR-2', 'C-0', 'P-2', '2026-03-26', 'M-0', 1, 5.0)"
            );
    		
         // Orders
            s.executeUpdate(
                "INSERT OR IGNORE INTO Orders " +
                "(id, pharmacyId, supplierId, medicationId, date, quantity, status) VALUES " +
                "('ORD-0', 'P-1', 'S-1', 'M-2', '2026-03-12', 10, 'Pending'), " +
                "('ORD-1', 'P-2', 'S-2', 'M-0', '2026-01-10', 15, 'Pending'), " +
                "('ORD-2', 'P-0', 'S-0', 'M-1', '2026-02-11', 20, 'Pending')"
            );
            
         // History
            s.executeUpdate(
            	    "INSERT OR IGNORE INTO History " +
            	    "(id, pharmacyId, medicationId, movementType, quantity, price, date) VALUES " +
            	    "('H-0', 'P-0', 'M-1', 'SALE', 1, 4.0, '2026-04-12'), " +
            	    "('H-1', 'P-1', 'M-0', 'RESTOCK', 25, 125.0, '2026-03-01'), " +
            	    "('H-2', 'P-2', 'M-2', 'RESTOCK', 10, 30.0, '2026-03-12')"
            	);

            System.out.println("Initial data inserted correctly");

    		
    	} catch (SQLException e) {
    		System.err.println("Error inserting the initial data");
			e.printStackTrace();
		}
    	
    	
    	
    	
    }
    
    
    
    
    
    
    
    
	

}
