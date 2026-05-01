package interfaces;
import java.util.List;

import pojos.Medication;

public interface MedicationManager {
	
	Medication findByName(String name);
	
	List<Medication> getAllMedications();
	
	boolean addMedication(Medication medication);
	
	boolean deleteMedication(String medicationId);

}
