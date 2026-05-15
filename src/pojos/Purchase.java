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

    public Purchase() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    @Override
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
