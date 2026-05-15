package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "pharmacy")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pharmacy {

	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "phone")
    private String phone;

    @XmlElement(name = "registrationNumber")
    private String registrationNumber;

    @XmlElement(name = "municipalityId")
    private int municipalityId;

    @XmlElement(name = "photo")
    private byte[] photo;
    
    public Pharmacy() {
    }

    public Pharmacy(String id, String address, String phone, String registrationNumber, int municipalityId,byte[] photo ) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.registrationNumber = registrationNumber;
        this.municipalityId = municipalityId;
        this.photo = photo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public int getMunicipalityId() { return municipalityId; }
    public void setMunicipalityId(int municipalityId) { this.municipalityId = municipalityId; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }
    
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
