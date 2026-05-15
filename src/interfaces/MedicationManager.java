package interfaces;
import java.util.List;

import pojos.Medication;

public interface MedicationManager {
	
	/**
	 * Finds a medication by its name.
	 *
	 * @param name the name of the medication
	 * @return the medication object if found, null otherwise
	 */
	Medication findByName(String name);
	
	/**
	 * Retrieves all available medications.
	 *
	 * @return a list of all medications
	 */
	List<Medication> getAllMedications();
	
	/**
	 * Adds a new medication to the system.
	 *
	 * @param medication the medication object to add
	 * @return true if the medication was successfully added, false otherwise
	 */
	boolean addMedication(Medication medication);
	
	/**
	 * Deletes a medication from the system by its ID.
	 *
	 * @param medicationId the ID of the medication to delete
	 * @return true if the medication was successfully deleted, false otherwise
	 */
	boolean deleteMedication(String medicationId);

}
