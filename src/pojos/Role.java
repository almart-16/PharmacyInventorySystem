package pojos;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity 
@Table(name = "roles")
@XmlRootElement(name = "role") 
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * Represents a user role in the system.
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    @XmlAttribute(name = "id")
	private Integer id;
    
    @Column(name="roleName", nullable = false, unique = true)
    @XmlElement(name = "rolename")
    private String roleName; // Ej: "admin", "pharmacist", "manager"
    
    /**
     * Default constructor for Role.
     */
    public Role() {}

    /**
     * Parameterized constructor for Role.
     *
     * @param id The unique identifier of the role.
     * @param roleName The name of the role (e.g. admin, pharmacist).
     */
    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public Integer getId() { return id; }
    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Gets the value of roleName.
     * @return The value of roleName.
     */
    public String getRoleName() { return roleName; }
    /**
     * Sets the value of roleName.
     * @param roleName The new value of roleName.
     */
    public void setRoleName(String roleName) { this.roleName = roleName; }
    
    @Override
    /**
     * String representation of the Role object.
     * @return A string representation of the object.
     */
    public String toString() {
        return roleName;
    }
}


