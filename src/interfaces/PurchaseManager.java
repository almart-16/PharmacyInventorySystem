package interfaces;

import pojos.Purchase;

public interface PurchaseManager {

	// Save a medicine purchase in the data base
	boolean savePurchase(Purchase purchase);
	
	// The sale of a medicine is registered, then the stock is reduced from the inventory and lastly the purchase is registered.
	boolean sellMedication(Purchase purchase);
	
	
}
