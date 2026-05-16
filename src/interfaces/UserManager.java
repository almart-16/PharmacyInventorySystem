package interfaces;

import java.util.List;

import pojos.Role;
import pojos.User;

public interface UserManager {

	/**
	 * Creates a new user in the system.
	 *
	 * @param user the user object to create
	 */
	void createUser(String userName, String password, Role role);
	
	/**
	 * Finds a user by their username.
	 *
	 * @param userName the username to search for
	 * @return the user object if found, null otherwise
	 */
	User findUserByUserName(String userName);
	
	/**
	 * Authenticates a user with their username and password.
	 *s
	 * @param userName the username
	 * @param password the password
	 * @return the authenticated user object if successful, null otherwise
	 */
	User login(String userName, String password);
	
	/**
	 * Retrieves all users in the system.
	 *
	 * @return a list of all users
	 */
	List<User> getAllUsers();
	
	/**
	 * Updates an existing user's information.
	 *
	 * @param user the user object with updated information
	 */
	void updateUser(User user);
	
	/**
	 * Deletes a user from the system by their ID.
	 *
	 * @param id the ID of the user to delete
	 */
	void delateUser(Integer id);
	
	/**
	 * Updates the password for a specific user.
	 *
	 * @param user the user object whose password is to be updated
	 * @param newPassword the new password to set
	 */
	void updatePassword(User user, String newPassword);
	
}
