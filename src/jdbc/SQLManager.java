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
    private final ReportManager report;
    
    /**
     * Default constructor that initializes all managers with a database connection.
     */
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
        this.report = new JDBCReportManager(c);
        
    }
    /**
     * Gets the inventory manager.
     * @return the inventory manager
     */
    public InventoryManager getInventoryManager() {
        return inventory;
    }

    /**
     * Gets the medication manager.
     * @return the medication manager
     */
    public MedicationManager getMedicationManager() {
        return medication;
    }

    /**
     * Gets the orders manager.
     * @return the orders manager
     */
    public OrdersManager getOrdersManager() {
        return orders;
    }

    /**
     * Gets the pharmacy manager.
     * @return the pharmacy manager
     */
    public PharmacyManager getPharmacyManager() {
        return pharmacy;
    }

    /**
     * Gets the purchase manager.
     * @return the purchase manager
     */
    public PurchaseManager getPurchaseManager() {
        return purchase;
    }

    /**
     * Gets the supplier manager.
     * @return the supplier manager
     */
    public SupplierManager getSupplierManager() {
        return supplier;
    }

    /**
     * Gets the history manager.
     * @return the history manager
     */
    public HistoryManager getHistoryManager() {
        return history;
    }

    /**
     * Gets the report manager.
     * @return the report manager
     */
    public ReportManager getReportManager() {
        return report;
    }
}
