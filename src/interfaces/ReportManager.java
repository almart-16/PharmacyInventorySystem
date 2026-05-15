package interfaces;

import java.util.List;
import pojos.GlobalStats;
import pojos.MedicationStats;

public interface ReportManager {

    /**
     * Calculates the monthly sales, revenue, and profit of each medication.
     * @param month The month as a two-digit string (e.g. "05")
     * @param year The year as a four-digit string (e.g. "2026")
     * @return List of medication statistics.
     */
    List<MedicationStats> getMonthlyMedicationSales(String month, String year);

    /**
     * Calculates total revenue and profit per medication across all time.
     * @return List of medication statistics.
     */
    List<MedicationStats> getTotalRevenueAndProfitPerMedication();

    /**
     * Calculates the global monthly revenue and profit.
     * @param month The month as a two-digit string (e.g. "05")
     * @param year The year as a four-digit string (e.g. "2026")
     * @return The global stats.
     */
    GlobalStats getGlobalMonthlyRevenueAndProfit(String month, String year);

    /**
     * Retrieves the top-selling medications per month.
     * @param month The month as a two-digit string (e.g. "05")
     * @param year The year as a four-digit string (e.g. "2026")
     * @param limit The maximum number of records to return.
     * @return List of medication statistics.
     */
    List<MedicationStats> getTopSellingMedications(String month, String year, int limit);

    /**
     * Retrieves low-selling medications.
     * @param limit The maximum number of records to return.
     * @return List of medication statistics.
     */
    List<MedicationStats> getLowSellingMedications(int limit);
}
