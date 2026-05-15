package interfaces;

import java.util.List;

import pojos.Role;

/**
 * Interface defining operations for managing user roles.
 */
public interface RoleManager {
	
	/**
	 * Creates a new role in the system.
	 *
	 * @param role the role object to create
	 */
	void createRole(Role role);
	
	/**
	 * Finds a role by its name.
	 *
	 * @param roleName the name of the role
	 * @return the role object if found, null otherwise
	 */
	Role findRoleByName(String roleName);
	
	/**
	 * Finds a role by its ID.
	 *
	 * @param id the ID of the role
	 * @return the role object if found, null otherwise
	 */
	Role findRoleById(Integer id);
	
	/**
	 * Retrieves all roles in the system.
	 *
	 * @return a list of all roles
	 */
	List<Role> getAllRoles();
	
	/**
	 * Updates an existing role in the system.
	 *
	 * @param role the role object with updated information
	 */
	void updateRole(Role role);
	
	/**
	 * Deletes a role from the system by its ID.
	 *
	 * @param id the ID of the role to delete
	 */
	void delateRole(Integer id);
	
}
