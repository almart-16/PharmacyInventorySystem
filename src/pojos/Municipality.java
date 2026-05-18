package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "municipality")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * Represents a municipality in the system.
 */
public class Municipality {
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "name")
    private String name;

    /**
     * Default constructor for Municipality.
     */
    public Municipality() {
    }

    /**
     * Parameterized constructor for Municipality.
     *
     * @param id The unique identifier of the municipality.
     * @param name The name of the municipality.
     */
    public Municipality(String id, String name) {
        this.id = id;
        this.name = name;
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



    @Override
    /**
     * String representation of the Municipality object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Municipality{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    
}
