package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "purchase")
@XmlAccessorType(XmlAccessType.FIELD)

public class Purchase {
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "clientId")
    private String clientId;

    @XmlElement(name = "pharmacyId")
    private String pharmacyId;

    @XmlElement(name = "purchaseDate")
    private String date;

    @XmlElement(name = "medicationId")
    private String medicationId;

    @XmlElement(name = "quantity")
    private int quantity;

    @XmlElement(name = "totalPrice")
    private double price;

    /**
     * Default constructor for Purchase.
     */
    public Purchase() {
    }

    /**
     * Parameterized constructor for Purchase.
     */
    public Purchase(String id, String clientId, String pharmacyId, String date,
                    String medicationId, int quantity, double price) {
        this.id = id;
        this.clientId = clientId;
        this.pharmacyId = pharmacyId;
        this.date = date;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.price = price;
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
     * Gets the value of clientId.
     * @return The value of clientId.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of clientId.
     * @param clientId The new value of clientId.
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    @Override
    /**
     * String representation of the Purchase object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", cliientId='" + clientId + '\'' +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", date='" + date + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}


