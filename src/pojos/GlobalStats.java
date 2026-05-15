package pojos;

import javax.xml.bind.annotation.*;

/**
 * POJO class representing the global statistical data for a given period.
 * Used for macro-level analytics and reporting functionalities.
 */
@XmlRootElement(name = "globalStats")
@XmlAccessorType(XmlAccessType.FIELD)
public class GlobalStats {

    @XmlElement(name = "period")
    private String period; // e.g. "2026-05" or "All-Time"
    
    @XmlElement(name = "totalMedicationsSold")
    private int totalMedicationsSold;
    
    @XmlElement(name = "globalRevenue")
    private double globalRevenue;
    
    @XmlElement(name = "globalCost")
    private double globalCost;
    
    @XmlElement(name = "globalProfit")
    private double globalProfit;
    
    @XmlElement(name = "globalProfitMargin")
    private double globalProfitMargin;

    /**
     * Default constructor for GlobalStats.
     */
    public GlobalStats() {
    }

    /**
     * Parameterized constructor for GlobalStats.
     * 
     * @param period               The time period for these statistics (e.g., "2026-05" or "All-Time").
     * @param totalMedicationsSold The total number of medications sold in this period.
     * @param globalRevenue        The total revenue generated across all sales.
     * @param globalCost           The total cost incurred for the sold medications.
     * @param globalProfit         The total profit generated in this period.
     * @param globalProfitMargin   The global profit margin represented as a percentage.
     */
    public GlobalStats(String period, int totalMedicationsSold, double globalRevenue, 
                       double globalCost, double globalProfit, double globalProfitMargin) {
        this.period = period;
        this.totalMedicationsSold = totalMedicationsSold;
        this.globalRevenue = globalRevenue;
        this.globalCost = globalCost;
        this.globalProfit = globalProfit;
        this.globalProfitMargin = globalProfitMargin;
    }

    /**
     * Retrieves the time period.
     * 
     * @return The time period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the time period.
     * 
     * @param period The new time period.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Retrieves the total number of medications sold.
     * 
     * @return The total medications sold.
     */
    public int getTotalMedicationsSold() {
        return totalMedicationsSold;
    }

    /**
     * Sets the total number of medications sold.
     * 
     * @param totalMedicationsSold The new total medications sold.
     */
    public void setTotalMedicationsSold(int totalMedicationsSold) {
        this.totalMedicationsSold = totalMedicationsSold;
    }

    /**
     * Retrieves the global revenue.
     * 
     * @return The global revenue.
     */
    public double getGlobalRevenue() {
        return globalRevenue;
    }

    /**
     * Sets the global revenue.
     * 
     * @param globalRevenue The new global revenue.
     */
    public void setGlobalRevenue(double globalRevenue) {
        this.globalRevenue = globalRevenue;
    }

    /**
     * Retrieves the global cost.
     * 
     * @return The global cost.
     */
    public double getGlobalCost() {
        return globalCost;
    }

    /**
     * Sets the global cost.
     * 
     * @param globalCost The new global cost.
     */
    public void setGlobalCost(double globalCost) {
        this.globalCost = globalCost;
    }

    /**
     * Retrieves the global profit.
     * 
     * @return The global profit.
     */
    public double getGlobalProfit() {
        return globalProfit;
    }

    /**
     * Sets the global profit.
     * 
     * @param globalProfit The new global profit.
     */
    public void setGlobalProfit(double globalProfit) {
        this.globalProfit = globalProfit;
    }

    /**
     * Retrieves the global profit margin.
     * 
     * @return The global profit margin.
     */
    public double getGlobalProfitMargin() {
        return globalProfitMargin;
    }

    /**
     * Sets the global profit margin.
     * 
     * @param globalProfitMargin The new global profit margin.
     */
    public void setGlobalProfitMargin(double globalProfitMargin) {
        this.globalProfitMargin = globalProfitMargin;
    }

    /**
     * Returns a string representation of the GlobalStats object.
     * 
     * @return A string with the object's details.
     */
    @Override
    public String toString() {
        return "GlobalStats{" +
                "period='" + period + '\'' +
                ", totalMedicationsSold=" + totalMedicationsSold +
                ", globalRevenue=" + globalRevenue +
                ", globalCost=" + globalCost +
                ", globalProfit=" + globalProfit +
                ", globalProfitMargin=" + globalProfitMargin + "%" +
                '}';
    }
}
