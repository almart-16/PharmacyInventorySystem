package interfaces;

import java.util.List;

import Pojos.Pharmacy;

public interface PharmacyManager {
	
	Pharmacy findbyRegistrationNumber(String number);
	
	Pharmacy findById(String id);
	
	List <Pharmacy> getAllPharmacies();

}
