package interfaces;

import java.util.List;

import pojos.Pharmacy;

public interface PharmacyManager {
	
	Pharmacy findbyRegistrationNumber(String number);
	
	Pharmacy findById(String id);
	
	List <Pharmacy> getAllPharmacies();

}
