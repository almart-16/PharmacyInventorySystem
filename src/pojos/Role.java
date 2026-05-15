package pojos;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity 
@Table(name = "roles")
@XmlRootElement(name = "role") 
@XmlAccessorType(XmlAccessType.FIELD)

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
    
    public Role() {}

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    
    @Override
    public String toString() {
        return roleName;
    }
}
