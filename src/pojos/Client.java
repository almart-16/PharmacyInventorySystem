package Pojos;

public class Client {
	private String id;
    private String name;
    private String nationalId;
    private int age;
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
