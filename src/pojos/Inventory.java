package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "inventory")
@XmlAccessorType(XmlAccessType.FIELD)

public class Inventory {
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "pharmacyId")
    private String pharmacyId;

    @XmlElement(name = "medicationId")
    private String medicationId;

    @XmlElement(name = "supplierId")
    private String supplierId;

    @XmlElement(name = "stockQuantity")
    private int stockQuantity;

    @XmlElement(name = "price")
    private double price;

    @XmlElement(name = "expirationDate")
    private String expirationDate;

    @XmlElement(name = "minimumStock")
    private int minimumStock;

    /**
     * Default constructor for Inventory.
     */
    public Inventory() {
    }

    /**
     * Parameterized constructor for Inventory.
     */
    public Inventory(String id, String pharmacyId, String medicationId, String supplierId,
                     int stockQuantity, double price, String expirationDate, int minimumStock) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.supplierId = supplierId;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.expirationDate = expirationDate;
        this.minimumStock = minimumStock;
    }

    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the value of pharmacyId.
     * @return The value of pharmacyId.
     */
    public String getPharmacyId() {
        return pharmacyId;
    }

    /**
     * Sets the value of pharmacyId.
     * @param pharmacyId The new value of pharmacyId.
     */
    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }


    /**
     * Gets the value of medicationId.
     * @return The value of medicationId.
     */
    public String getMedicationId() {
        return medicationId;
    }

    /**
     * Sets the value of medicationId.
     * @param medicationId The new value of medicationId.
     */
    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }


    /**
     * Gets the value of supplierId.
     * @return The value of supplierId.
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of supplierId.
     * @param supplierId The new value of supplierId.
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }


    /**
     * Gets the value of stockQuantity.
     * @return The value of stockQuantity.
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the value of stockQuantity.
     * @param stockQuantity The new value of stockQuantity.
     */
    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = stockQuantity;
    }


    /**
     * Gets the value of price.
     * @return The value of price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of price.
     * @param price The new value of price.
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }


    /**
     * Gets the value of expirationDate.
     * @return The value of expirationDate.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of expirationDate.
     * @param expirationDate The new value of expirationDate.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    /**
     * Gets the value of minimumStock.
     * @return The value of minimumStock.
     */
    public int getMinimumStock() {
        return minimumStock;
    }

    /**
     * Sets the value of minimumStock.
     * @param minimumStock The new value of minimumStock.
     */
    public void setMinimumStock(int minimumStock) {
        if (minimumStock < 0) {
            throw new IllegalArgumentException("Minimum stock cannot be negative");
        }
        this.minimumStock = minimumStock;
    }

    @Override
    /**
     * String representation of the Inventory object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", expirationDate='" + expirationDate + '\'' +
                ", minimumStock=" + minimumStock +
                '}';
    }
}
	
	

