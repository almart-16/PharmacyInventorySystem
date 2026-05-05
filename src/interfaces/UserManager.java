package interfaces;

import java.util.List;

import pojos.User;

public interface UserManager {

	void createUser(User user);
	
	User findUserByUserName(String userName);
	
	User login(String userName, byte[] password);
	
	List<User> getAllUsers();
	
	void updateUser(User user);
	
	void delateUser(Integer id);
	
}
