package Pojos;

public class Purchase {
	private String id;
    private String patientId;
    private String pharmacyId;
    private String date;
    private String medicationId;
    private int quantity;
    private double price;

    public Purchase() {
    }

    public Purchase(String id, String patientId, String pharmacyId, String date,
                    String medicationId, int quantity, double price) {
        this.id = id;
        this.patientId = patientId;
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


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
                ", patientId='" + patientId + '\'' +
                ", pharmacyId='" + pharmacyId + '\'' +
                ", date='" + date + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}
