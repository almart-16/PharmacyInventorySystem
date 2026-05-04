package Pojos;

import java.io.Serializable;

public class Role implements Serializable {

    private Integer id;
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
