package jdbc;

import java.sql.Connection;

import interfaces.*;


public class SQLManager {
	private final InventoryManager inventory;
    private final MedicationManager medication;
    private final OrdersManager orders;
    private final PharmacyManager pharmacy;
    private final PurchaseManager purchase;
    private final SupplierManager supplier;
    private final HistoryManager history;
    
    public SQLManager() {
    	ConnectionManager cManager = new ConnectionManager();
    	Connection c = cManager.getc();
    	
    	this.inventory = new JDBCInventoryManager(c);
        this.medication = new JDBCMedicationManager(c);
        this.orders = new JDBCOrdersManager(c);
        this.pharmacy = new JDBCPharmacyManager(c);
        this.purchase = new JDBCPurchaseManager(c);
        this.supplier = new JDBCSupplierManager(c);
        this.history = new JDBCHistoryManager(c);
        
    }
    public InventoryManager getInventoryManager() {
        return inventory;
    }

    public MedicationManager getMedicationManager() {
        return medication;
    }

    public OrdersManager getOrdersManager() {
        return orders;
    }

    public PharmacyManager getPharmacyManager() {
        return pharmacy;
    }

    public PurchaseManager getPurchaseManager() {
        return purchase;
    }

    public SupplierManager getSupplierManager() {
        return supplier;
    }

    public HistoryManager getHistoryManager() {
        return history;
    }
}

