package interfaces;

public interface HistoryManager {
	// For use case 9:

	/**
	 * Shows the history of operations for a specific pharmacy.
	 *
	 * @param pharmacyId the ID of the pharmacy
	 */
	void showHistoryByPharmacy(String pharmacyId);

	/**
	 * Shows the history of operations related to a specific medication.
	 *
	 * @param pharmacyId the ID of the pharmacy to check medication history
	 */
	void showHistoryByMedication(String pharmacyId);

	/**
	 * Shows the history of all operations.
	 */
	void showAllHistory();

}
