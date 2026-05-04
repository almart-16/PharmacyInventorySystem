package Pojos;

import java.io.Serializable;

public class User implements Serializable {
	private Integer id;
	private String username;
	private byte[] password;
	private Role role;
	
	public User () {}
	
	public User(Integer id, String username, byte[] password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
	
	public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public byte[] getPassword() { return password; }
    public void setPassword(byte[] password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", role=" + role.getRoleName() + "]";
    }
	
	
	
	
	

}

