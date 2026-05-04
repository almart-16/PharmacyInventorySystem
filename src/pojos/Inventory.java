package Pojos;

public class Inventory {
	private String id;
    private String pharmacyId;
    private String medicationId;
    private String supplierId;
    private int stockQuantity;
    private double price;
    private String expirationDate;
    private int minimumStock;

    public Inventory() {
    }

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


    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }


    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }


    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.stockQuantity = stockQuantity;
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


    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(int minimumStock) {
        if (minimumStock < 0) {
            throw new IllegalArgumentException("Minimum stock cannot be negative");
        }
        this.minimumStock = minimumStock;
    }

    @Override
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
	
	