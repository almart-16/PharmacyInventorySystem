package Pojos;

public class Pharmacy {

	private String id;
    private String address;
    private String phone;
    private String registrationNumber;
    private int municipalityId;

    
    public Pharmacy() {
    }

    public Pharmacy(String id, String address, String phone, String registrationNumber, int municipalityId) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.registrationNumber = registrationNumber;
        this.municipalityId = municipalityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", municipalityId=" + municipalityId +
                '}';
    }
	
	
	
}
