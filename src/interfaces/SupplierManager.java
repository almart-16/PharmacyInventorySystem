package interfaces;
import java.util.List;

import pojos.Supplier;

public interface SupplierManager {

	/**
	 * Adds a new supplier to the system.
	 *
	 * @param supplier the supplier object to add
	 * @return the added supplier object
	 */
	Supplier addSupplier(Supplier supplier);
	
	/**
	 * Finds a supplier by its ID.
	 *
	 * @param id the ID of the supplier
	 * @return the supplier object if found, null otherwise
	 */
	Supplier findById(String id);
	
	/**
	 * Retrieves all suppliers in the system.
	 *
	 * @return a list of all suppliers
	 */
	List<Supplier> getAllSupliers();
	
}
