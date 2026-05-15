package interfaces;

import java.util.List;

import pojos.Inventory;

public interface InventoryManager {

	/**
	 * Finds an inventory record for a specific pharmacy and medication.
	 *
	 * @param pharmacyId   the ID of the pharmacy
	 * @param medicationId the ID of the medication
	 * @return the inventory object if found, null otherwise
	 */
	Inventory findInventory(String pharmacyId, String medicationId);

	/**
	 * Increases the stock of a medication in a specific pharmacy.
	 *
	 * @param pharmacyId   the ID of the pharmacy
	 * @param medicationId the ID of the medication
	 * @param quantity     the amount to increase the stock by
	 * @return true if the stock was successfully increased, false otherwise
	 */
	boolean increaseStock(String pharmacyId, String medicationId, int quantity);

	/**
	 * Reduces the stock of a medication in a specific pharmacy.
	 *
	 * @param pharmacyId   the ID of the pharmacy
	 * @param medicationId the ID of the medication
	 * @param quantity     the amount to reduce the stock by
	 * @return true if the stock was successfully reduced, false otherwise
	 */
	boolean reduceStock(String pharmacyId, String medicationId, int quantity);

	/**
	 * Adds a new inventory record. In use case 5: after adding a medicine, it is
	 * also added to the inventory.
	 *
	 * @param inventory the inventory object to add
	 * @return true if the inventory was successfully added, false otherwise
	 */
	boolean addInventory(Inventory inventory);

	/**
	 * Updates the inventory information such as price, expiration date, and
	 * supplier.
	 *
	 * @param pharmacyId     the ID of the pharmacy
	 * @param medicationId   the ID of the medication
	 * @param price          the new price
	 * @param purchasePrice  the new purchase price
	 * @param expirationDate the new expiration date
	 * @param supplierId     the new supplier ID
	 * @return true if the inventory information was successfully updated, false
	 *         otherwise
	 */
	boolean updateInventoryInformation(String pharmacyId, String medicationId, double price, double purchasePrice,
			String expirationDate, String supplierId);

	/**
	 * Retrieves a list of medications that have low stock.
	 *
	 * @return a list of inventory objects with low stock
	 */
	List<Inventory> getLowStockMedications();

	/**
	 * Checks if a specific medication has low stock in a pharmacy.
	 *
	 * @param pharmacyId   the ID of the pharmacy
	 * @param medicationId the ID of the medication
	 * @return true if the stock is low, false otherwise
	 */
	boolean isLowStock(String pharmacyId, String medicationId);

	/**
	 * Checks if a specific medication is completely out of stock in a pharmacy.
	 *
	 * @param pharmacyId   the ID of the pharmacy
	 * @param medicationId the ID of the medication
	 * @return true if the medication is out of stock, false otherwise
	 */
	boolean isOutOfStock(String pharmacyId, String medicationId);

}
