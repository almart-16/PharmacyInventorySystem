package pojos;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "users")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "user_id")
    @XmlAttribute(name = "id")
	private Integer id;
	
	@Column (name="userName", nullable = false, unique = true)
	@XmlElement(name = "username")
	private String username;
	
	@Column(name = "password", nullable = false)
	@XmlTransient //evita que la contraseña se guarde en XML
	private String password;
	
	@ManyToOne 
	@JoinColumn (name = "role_id", nullable = false)
	@XmlElement(name = "role")
	private Role role;
	
	public User () {}
	
	public User(Integer id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
	
	public Integer getUserId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role.getRoleName() + "]";
    }
	
	
	
	
	

}

