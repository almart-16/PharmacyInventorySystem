package interfaces;

import java.util.List;

import pojos.Role;

public interface RoleManager {
	
	
	void createRole(Role role);
	
	Role findRoleByName(String roleName);
	
	Role findRoleById(Integer id);
	
	List<Role> getAllRoles();
	
	void updateRole(Role role);
	
	void delateRole(Integer id);
	
	

}
