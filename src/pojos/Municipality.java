package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "municipality")
@XmlAccessorType(XmlAccessType.FIELD)
public class Municipality {
	@XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    public Municipality() {
    }

    public Municipality(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
    
}
	