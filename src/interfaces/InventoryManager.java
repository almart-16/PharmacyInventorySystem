package interfaces;

import java.util.List;

import pojos.Inventory;

public interface InventoryManager {
	
	Inventory findInventory(String pharmacyid, String medicationId);
	
	boolean increaseStock(String pharmacyId, String medicationId, int quantity);
	
	boolean reduceStock(String pharmacyId, String medicationId, int quantity);
	
	// In use case 5: after adding a medicine, it is also added to the inventory
	boolean addInventory(Inventory inventory);
	
	boolean updateInventoryInformation(String pharmacyId, String medicationId, double price,
			                           String expirationDate, String supplierId);
	
	// Inventory has the information about how many stock (medicines) are available
	List<Inventory> getLowStockMedications();
	
	// Advise there is not enough stock
	boolean isLowStock(String pharmacyId, String medicationId);
	
	
	// Advise there is no stock at all
	boolean isOutOfStock(String pharmacyId, String medicationId);


}
