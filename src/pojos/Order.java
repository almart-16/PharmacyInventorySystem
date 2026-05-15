package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "pharmacyId")
    private String pharmacyId;

    @XmlElement(name = "supplierId")
    private String supplierId;

    @XmlElement(name = "medicationId")
    private String medicationId;

    @XmlElement(name = "orderDate")
    private String date;

    @XmlElement(name = "quantity")
    private int quantity;

    @XmlElement(name = "status")
    private String status;
    
    /**
     * Default constructor for Order.
     */
    public Order() {
    }

    /**
     * Parameterized constructor for Order.
     */
    public Order(String id, String pharmacyId, String supplierId, String medicationId,
                  String date, int quantity, String status) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.supplierId = supplierId;
        this.medicationId = medicationId;
        this.date = date;
        this.quantity = quantity;
        this.status = status;
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
     * Gets the value of date.
     * @return The value of date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of date.
     * @param date The new value of date.
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Gets the value of quantity.
     * @return The value of quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of quantity.
     * @param quantity The new value of quantity.
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }


    /**
     * Gets the value of status.
     * @return The value of status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of status.
     * @param status The new value of status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    /**
     * String representation of the Order object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
	
}


