package interfaces;

import pojos.Purchase;

public interface PurchaseManager {

	/**
	 * Saves a medicine purchase in the database.
	 *
	 * @param purchase the purchase object to save
	 * @return true if the purchase was successfully saved, false otherwise
	 */
	boolean savePurchase(Purchase purchase);

	// The sale of a medicine is registered, then the stock is reduced from the
	// inventory and lastly the purchase is registered.
	/**
	 * Registers the sale of a medicine. The stock is reduced from the inventory and
	 * the purchase is registered.
	 *
	 * @param purchase the purchase object representing the sale
	 * @return true if the sale was successfully registered, false otherwise
	 */
	boolean sellMedication(Purchase purchase);

}
