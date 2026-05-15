package pojos;

import javax.xml.bind.annotation.*;

/**
 * POJO class representing the statistical data for a specific medication.
 * Used for analytics and reporting functionalities.
 */
@XmlRootElement(name = "medicationStats")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicationStats {
    
    @XmlElement(name = "medicationId")
    private String medicationId;
    
    @XmlElement(name = "medicationName")
    private String medicationName;
    
    @XmlElement(name = "quantitySold")
    private int quantitySold;
    
    @XmlElement(name = "totalRevenue")
    private double totalRevenue;
    
    @XmlElement(name = "totalCost")
    private double totalCost;
    
    @XmlElement(name = "totalProfit")
    private double totalProfit;
    
    @XmlElement(name = "profitMargin")
    private double profitMargin; // Represented as a percentage (e.g. 25.5 for 25.5%)

    /**
     * Default constructor for MedicationStats.
     */
    public MedicationStats() {
    }

    /**
     * Parameterized constructor for MedicationStats.
     * 
     * @param medicationId   The unique identifier for the medication.
     * @param medicationName The name of the medication.
     * @param quantitySold   The total quantity of the medication sold.
     * @param totalRevenue   The total revenue generated from sales.
     * @param totalCost      The total cost of purchasing the medication.
     * @param totalProfit    The total profit generated.
     * @param profitMargin   The profit margin represented as a percentage.
     */
    public MedicationStats(String medicationId, String medicationName, int quantitySold, 
                           double totalRevenue, double totalCost, double totalProfit, double profitMargin) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.quantitySold = quantitySold;
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.totalProfit = totalProfit;
        this.profitMargin = profitMargin;
    }

    /**
     * Retrieves the medication ID.
     * 
     * @return The medication ID.
     */
    public String getMedicationId() {
        return medicationId;
    }

    /**
     * Sets the medication ID.
     * 
     * @param medicationId The new medication ID.
     */
    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    /**
     * Retrieves the medication name.
     * 
     * @return The medication name.
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Sets the medication name.
     * 
     * @param medicationName The new medication name.
     */
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * Retrieves the quantity of the medication sold.
     * 
     * @return The quantity sold.
     */
    public int getQuantitySold() {
        return quantitySold;
    }

    /**
     * Sets the quantity of the medication sold.
     * 
     * @param quantitySold The new quantity sold.
     */
    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    /**
     * Retrieves the total revenue generated.
     * 
     * @return The total revenue.
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Sets the total revenue generated.
     * 
     * @param totalRevenue The new total revenue.
     */
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    /**
     * Retrieves the total cost of the medication.
     * 
     * @return The total cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost of the medication.
     * 
     * @param totalCost The new total cost.
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Retrieves the total profit generated.
     * 
     * @return The total profit.
     */
    public double getTotalProfit() {
        return totalProfit;
    }

    /**
     * Sets the total profit generated.
     * 
     * @param totalProfit The new total profit.
     */
    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    /**
     * Retrieves the profit margin percentage.
     * 
     * @return The profit margin.
     */
    public double getProfitMargin() {
        return profitMargin;
    }

    /**
     * Sets the profit margin percentage.
     * 
     * @param profitMargin The new profit margin.
     */
    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    /**
     * Returns a string representation of the MedicationStats object.
     * 
     * @return A string with the object's details.
     */
    @Override
    public String toString() {
        return "MedicationStats{" +
                "medicationId='" + medicationId + '\'' +
                ", medicationName='" + medicationName + '\'' +
                ", quantitySold=" + quantitySold +
                ", totalRevenue=" + totalRevenue +
                ", totalCost=" + totalCost +
                ", totalProfit=" + totalProfit +
                ", profitMargin=" + profitMargin + "%" +
                '}';
    }
}
