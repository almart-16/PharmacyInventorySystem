package pojos;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "nationalId")
    private String nationalId;

    @XmlElement(name = "age")
    private int age;

    @XmlElement(name = "municipalityId")
    private int municipalityId;

    /**
     * Default constructor for Client.
     */
    public Client() {
    }

    /**
     * Parameterized constructor for Client.
     */
    public Client(String id, String name, String nationalId, int age, int municipalityId) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.age = age;
        this.municipalityId = municipalityId;
    }

    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the value of name.
     * @return The value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name.
     * @param name The new value of name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the value of nationalId.
     * @return The value of nationalId.
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     * Sets the value of nationalId.
     * @param nationalId The new value of nationalId.
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }


    /**
     * Gets the value of age.
     * @return The value of age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of age.
     * @param age The new value of age.
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Gets the value of municipalityId.
     * @return The value of municipalityId.
     */
    public int getMunicipalityId() {
        return municipalityId;
    }

    /**
     * Sets the value of municipalityId.
     * @param municipalityId The new value of municipalityId.
     */
    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
    /**
     * String representation of the Client object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", age=" + age +
                ", municipalityId=" + municipalityId +
                '}';
    }
}


