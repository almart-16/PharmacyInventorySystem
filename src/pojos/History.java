package pojos;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.FIELD)

public class History {

	@XmlElement(name = "date")
    private String date;

    @XmlElement(name = "movementType")
    private String movementType; // Sale o Restock

    @XmlElement(name = "medicationId")
    private String medicationId;

    @XmlElement(name = "quantity")
    private int quantity;

    @XmlElement(name = "price")
    private Double price;// puede ser null

    /**
     * Default constructor for History.
     */
    public History() {}

    /**
     * Parameterized constructor for History.
     */
    public History(String date, String movementType, String medicationId, int quantity, Double price) {
        this.date = date;
        this.movementType = movementType;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.price = price;
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
                "date='" + date + '\'' +
                ", type='" + movementType + '\'' +
                ", medication='" + medicationId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}


