
import jdbc.*;
import pojos.*;
import interfaces.*;
import java.sql.Connection;

public class main {
	
	public static void main(String[] args) {
        System.out.println("Starting Pharmacy Management System");

        // Initialize the database and get the connection
        ConnectionManager connectionManager = new ConnectionManager();
        Connection conn = connectionManager.getc();

        if (conn == null) {
            System.err.println("Could not establish a connection to the database. Terminating execution.");
            return;
        }

        // Initialize Managers
        MedicationManager medicationManager = new JDBCMedicationManager(conn);
        PharmacyManager pharmacyManager = new JDBCPharmacyManager(conn);
        InventoryManager inventoryManager = new JDBCInventoryManager(conn);
        PurchaseManager purchaseManager = new JDBCPurchaseManager(conn);

        // Create test data (Flow simulation)
        try {
            System.out.println("\n[1] Registering a new medication...");
            Medication ibuprofen = new Medication("M-1", "Ibuprofen 600mg", "Pain and inflammation", true, false);
            medicationManager.addMedication(ibuprofen);
            System.out.println("Medication registered: " + ibuprofen.getName());

            System.out.println("\n[2] Registering a new pharmacy...");
           
            // We assume municipality "MUN-1" already exists in the DB, or we allow it if SQLite does not have PRAGMA strict configured by default.
            
            Pharmacy pharmacy = new Pharmacy("PH-1", "Fake Street 123", "555-1234", "REG-001", 1, null);
           
            // NOTE: To use this method, JDBCPharmacyManager must implement the interface correctly
            // Temporary cast if the create method is not in the PharmacyManager interface
            
            ((JDBCPharmacyManager) pharmacyManager).create(pharmacy);
            System.out.println("Pharmacy registered: " + pharmacy.getAddress());

            System.out.println("\n[3] Adding inventory to the pharmacy...");
            Inventory inv = new Inventory("INV-1", "PH-1", "M-1", "SUP-1", 50, 4.50, "2028-12-31", 10);
            inventoryManager.addInventory(inv);
            System.out.println("Inventory added. Current stock: " + inv.getStockQuantity());

            System.out.println("\n[4] Processing a sale...");
            
            // Create a purchase for 5 units of Ibuprofen
            
            Purchase purchase = new Purchase("PUR-1", "CLI-1", "PH-1", "2026-05-11", "M-1", 5, 22.50);
            boolean success = purchaseManager.sellMedication(purchase);
            
            if (success) {
                Inventory updatedInv = inventoryManager.findInventory("PH-1", "M-1");
                System.out.println("Stock after sale: " + updatedInv.getStockQuantity());
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the test execution:");
            e.printStackTrace();
        }

        System.out.println("\nExecution finished ");
    }
}

