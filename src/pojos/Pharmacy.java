package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "pharmacy")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * Represents a pharmacy in the inventory system.
 */
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
    
    /**
     * Default constructor for Pharmacy.
     */
    public Pharmacy() {
    }

    /**
     * Parameterized constructor for Pharmacy.
     *
     * @param id The unique identifier of the pharmacy.
     * @param address The address of the pharmacy.
     * @param phone The contact phone number of the pharmacy.
     * @param registrationNumber The official registration number of the pharmacy.
     * @param municipalityId The identifier of the municipality where the pharmacy is located.
     * @param photo A byte array representing the photo of the pharmacy.
     */
    public Pharmacy(String id, String address, String phone, String registrationNumber, int municipalityId,byte[] photo ) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.registrationNumber = registrationNumber;
        this.municipalityId = municipalityId;
        this.photo = photo;
    }

    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public String getId() { return id; }
    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets the value of address.
     * @return The value of address.
     */
    public String getAddress() { return address; }
    /**
     * Sets the value of address.
     * @param address The new value of address.
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Gets the value of phone.
     * @return The value of phone.
     */
    public String getPhone() { return phone; }
    /**
     * Sets the value of phone.
     * @param phone The new value of phone.
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Gets the value of registrationNumber.
     * @return The value of registrationNumber.
     */
    public String getRegistrationNumber() { return registrationNumber; }
    /**
     * Sets the value of registrationNumber.
     * @param registrationNumber The new value of registrationNumber.
     */
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    /**
     * Gets the value of municipalityId.
     * @return The value of municipalityId.
     */
    public int getMunicipalityId() { return municipalityId; }
    /**
     * Sets the value of municipalityId.
     * @param municipalityId The new value of municipalityId.
     */
    public void setMunicipalityId(int municipalityId) { this.municipalityId = municipalityId; }

    /**
     * Gets the value of photo.
     * @return The value of photo.
     */
    public byte[] getPhoto() { return photo; }
    /**
     * Sets the value of photo.
     * @param photo The new value of photo.
     */
    public void setPhoto(byte[] photo) { this.photo = photo; }
    
    @Override
    /**
     * String representation of the Pharmacy object.
     * @return A string representation of the object.
     */
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


