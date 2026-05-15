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
	@XmlTransient //evita que la contraseÃ±a se guarde en XML
	private String password;
	
	@ManyToOne 
	@JoinColumn (name = "role_id", nullable = false)
	@XmlElement(name = "role")
	private Role role;
	
	/**
	 * Default constructor for User.
	 */
	public User () {}
	
	/**
	 * Parameterized constructor for User.
	 */
	public User(Integer id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
	
	/**
	 * Gets the value of userId.
	 * @return The value of userId.
	 */
	public Integer getUserId() { return id; }
    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Gets the value of username.
     * @return The value of username.
     */
    public String getUsername() { return username; }
    /**
     * Sets the value of username.
     * @param username The new value of username.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the value of password.
     * @return The value of password.
     */
    public String getPassword() { return password; }
    /**
     * Sets the value of password.
     * @param password The new value of password.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the value of role.
     * @return The value of role.
     */
    public Role getRole() { return role; }
    /**
     * Sets the value of role.
     * @param role The new value of role.
     */
    public void setRole(Role role) { this.role = role; }
    
    @Override
    /**
     * String representation of the User object.
     * @return A string representation of the object.
     */
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role.getRoleName() + "]";
    }
	
	
	
	
	

}



