package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * Represents a medication supplier.
 */
public class Supplier {
	
	@XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "phone")
    private String phone;
    /**
     * Default constructor for Supplier.
     */
    public Supplier() {}

    /**
     * Parameterized constructor for Supplier.
     *
     * @param id The unique identifier of the supplier.
     * @param name The name of the supplier.
     * @param phone The contact phone number of the supplier.
     */
    public Supplier(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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
     * Gets the value of name.
     * @return The value of name.
     */
    public String getName() { return name; }
    /**
     * Sets the value of name.
     * @param name The new value of name.
     */
    public void setName(String name) { this.name = name; }

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
    
    @Override
    /**
     * String representation of the Supplier object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }
}

