package pojos;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.FIELD)

public class History {
	@XmlAttribute(name = "id")
    private String id;
	
    @XmlElement(name = "pharmacyId")
    private String pharmacyId;
    
    @XmlElement(name = "medicationId")
    private String medicationId;
    
    @XmlElement(name = "movementType")
    private String movementType; // Sale o Restock

    @XmlElement(name = "quantity")
    private int quantity;

    @XmlElement(name = "price")
    private Double price;// puede ser null
    
	@XmlElement(name = "date")
    private String date;

    /**
     * Default constructor for History.
     */
    public History() {}

    /**
     * Parameterized constructor for History.
     */
    public History(String id, String pharmacyId, String medicationId, String movementType,   int quantity, Double price, String date) {
    	this.id = id;
    	this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.movementType = movementType;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the value of date.
     * @return The value of date.
     */
    public String getDate() { return date; }
    /**
     * Gets the value of movementType.
     * @return The value of movementType.
     */
    public String getMovementType() { return movementType; }
    /**
     * Gets the value of medicationId.
     * @return The value of medicationId.
     */
    public String getMedicationId() { return medicationId; }
    /**
     * Gets the value of pharmacyId.
     * @return The value of pharmacyId.
     */
    public String getPharmacyId() { return pharmacyId; }
    /**
     * Gets the value of quantity.
     * @return The value of quantity.
     */
    
    public int getQuantity() { return quantity; }
    /**
     * Gets the value of price.
     * @return The value of price.
     */
    public Double getPrice() { return price; }

    @Override
    /**
     * String representation of the History object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "History{" +
    
        		"pharmacy=" + pharmacyId + '\'' +
        	    ", medication='" + medicationId + '\'' +
        	    ", type='" + movementType + '\'' +
                ", quantity=" + quantity +
                ", price='" + price + '\'' +
                ", date=" + date +
                '}';
    }
}


