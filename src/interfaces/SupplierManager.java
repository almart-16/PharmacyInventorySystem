package interfaces;
import java.util.List;

import pojos.Supplier;

public interface SupplierManager {

	Supplier addSupplier(Supplier supplier);
	
	Supplier findById(String id);
	
	List<Supplier> getAllSupliers();
	
}
