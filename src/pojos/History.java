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

    public History() {}

    public History(String date, String movementType, String medicationId, int quantity, Double price) {
        this.date = date;
        this.movementType = movementType;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getDate() { return date; }
    public String getMovementType() { return movementType; }
    public String getMedicationId() { return medicationId; }
    public int getQuantity() { return quantity; }
    public Double getPrice() { return price; }

    @Override
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
