package jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;


import interfaces.UserManager;
import pojos.Role;
import pojos.User;
import java.util.List;


public class JPAUserManager implements UserManager {
	//como connection en JDBC
	private EntityManager em;
	
	public JPAUserManager() {
		//mismo nombre que en el persistence
		this.em = Persistence.createEntityManagerFactory("pharmacy-unit").createEntityManager();
		
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
	}
    /**
     * {@inheritDoc}
     */
    @Override
    public void createUser(String userName, String normalPassword, Role role) {
		try {
			em.getTransaction().begin(); 
			String hashedPassword = BCrypt.hashpw(normalPassword, BCrypt.gensalt());
			// It encripts the normal password introduced by the user before saving it in the database,
			// by doing this, you can later in the login compare the 2 passwords now written in the same
			// "language", which is the hashed one.
			
			User user = new User();
			user.setUsername(userName);
			user.setPassword(hashedPassword);
			
			// merge the detached role into the current persistence context to avoid detached entity exceptions
			Role managedRole = em.merge(role);
			user.setRole(managedRole);
			
			em.persist(user); // Guarda el usuario (y si es un Client, guarda sus datos extra)
			em.getTransaction().commit(); 
			
	
		
		}catch (Exception e) {
			//Si vas a crear un usuario y, por ejemplo, se corta la conexión a internet justo cuando el nombre se ha guardado 
			//pero la contraseña no, el rollback() borra ese nombre.
			if (em.getTransaction().isActive()) 
				em.getTransaction().rollback();
			e.printStackTrace();
		}
  	}
    /**
     * {@inheritDoc}
     */
    @Override
	public User findUserByUserName (String userName) {
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ?", User.class);
	    q.setParameter(1, userName); //en el primer ? metes la variable userName
	    try {
	        return (User) q.getSingleResult();
	    } catch (Exception e) {
	        return null; 
	    }
	}
    /**
     * {@inheritDoc}
     */
    @Override
	public User login(String userName, String password) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE userName = ?", User.class);
			q.setParameter(1, userName);
			User user = (User) q.getSingleResult();
			
			if(user == null) {
				return null;
			}
			
			String hashedPassword = user.getPassword();
			
			if(BCrypt.checkpw(password, hashedPassword)) {
				return user;
			}else {
				return null;
			}
		}catch (NoResultException e) {
			return null;
		} catch(IllegalArgumentException e) {
			System.out.println("Password stored in database is not a valid BCrypt hash.");
			return null;
		}
	}
	

	public boolean checkPassword(String userName, String password) {
	    User user = this.findUserByUserName(userName);
	    if (user == null) {
	        return false;
	    }
	
	    return BCrypt.checkpw(password, user.getPassword());
	}
	
		
	
	@SuppressWarnings("unchecked")
    /**
     * {@inheritDoc}
     */
    @Override
	public List<User> getAllUsers() {
	    Query q = em.createNativeQuery("SELECT * FROM users", User.class);
	    return q.getResultList();
	}
    /**
     * {@inheritDoc}
     */
    @Override
	public void updateUser(User user) {
		try {
			em.getTransaction().begin();
			em.merge(user); // 'merge' busca el ID y actualiza el resto de campos
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
    /**
     * {@inheritDoc}
     */
    @Override
	public void delateUser(Integer id) {
		try {
			em.getTransaction().begin();
			// primero hay que encontrar el objeto para borrarlo
			User user = em.find(User.class, id);
			if (user != null) {
				em.remove(user);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
    /**
     * {@inheritDoc}
     */
    @Override 
	public void updatePassword(User user, String newPassword ) {
		String hashedPassword = BCrypt.hashpw(new String(newPassword), BCrypt.gensalt());
	    
	    try {
	        em.getTransaction().begin();
	        User managedUser = em.find(User.class, user.getUserId());
	        if (managedUser != null) {
	            managedUser.setPassword(hashedPassword); // Guardar siempre encriptado
	            user.setPassword(hashedPassword);
	        }
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) em.getTransaction().rollback();
	        e.printStackTrace();
	    }
		
	}
	
	
	public void disconnect() {
	    em.close();
	}
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	
		
	
}

