package interfaces;

import java.util.List;

import pojos.Pharmacy;

public interface PharmacyManager {

	/**
	 * Finds a pharmacy by its registration number.
	 *
	 * @param number the registration number of the pharmacy
	 * @return the pharmacy object if found, null otherwise
	 */
	Pharmacy findbyRegistrationNumber(String number);

	/**
	 * Finds a pharmacy by its ID.
	 *
	 * @param id the ID of the pharmacy
	 * @return the pharmacy object if found, null otherwise
	 */
	Pharmacy findById(String id);

	/**
	 * Retrieves all pharmacies in the system.
	 *
	 * @return a list of all pharmacies
	 */
	List<Pharmacy> getAllPharmacies();

}
