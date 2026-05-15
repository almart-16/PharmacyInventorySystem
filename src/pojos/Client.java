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

    public Client() {
    }

    public Client(String id, String name, String nationalId, int age, int municipalityId) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.age = age;
        this.municipalityId = municipalityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    @Override
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
