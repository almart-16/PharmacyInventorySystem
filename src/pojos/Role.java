package pojos;

import java.io.Serializable;

import javax.persistence.*;

@Entity 
@Table(name = "roles")

public class Role implements Serializable {
	

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
	private Integer id;
    
    @Column(name="roleName", nullable = false, unique = true)
    private String roleName; // Ej: "admin", "pharmacist", "manager"ç
    
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
