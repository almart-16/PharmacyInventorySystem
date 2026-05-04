package interfaces;

import java.util.List;

import Pojos.Inventory;

public interface InventoryManager {
	
	Inventory findInventory(String pharmacyid, String medicationId);
	
	boolean increaseStock(String pharmacyId, String medicationId, int quantity);
	
	boolean reduceStock(String pharmacyId, String medicationId, int quantity);
	
	// En el use case 5: después de añadir un medication, este se añade al inventario
	boolean addInventory(Inventory inventory);
	
	boolean updateInventoryInformation(String pharmacyId, String medicationId, double price,
			                           String expirationDate, String supplierId);
	
	// Inventory es quien tiene la informacion de cuanto stock hay de los medicamentos
	List<Inventory> getLowStockMedications();
	
	// Aviso de que hay poco stock
	boolean isLowStock(String pharmacyId, String medicationId);
	
	
	// Aviso de que no hay stock
	boolean isOutOfStock(String pharmacyId, String medicationId);


}
