package Pojos;

public class Order {
	private String id;
    private String pharmacyId;
    private String supplierId;
    private String medicationId;
    private String date;
    private int quantity;
    private String status;

    public Order() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }


    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }


    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
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
