package jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	@Override
    public void createUser(User user) {
		try {
			em.getTransaction().begin(); 
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
	
	@Override
	public User login(String userName, byte[] password) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ?", User.class);
			q.setParameter(1, userName);
			User user = (User) q.getSingleResult();
			
			if(BCrypt.checkpw(password, user.getPassword())) {
				return user;
			}else {
				return null;
			}
		}catch (NoResultException e) {
			return null;
		}
	}
	
	/*
	 * @Override
public User login(String userName, byte[] password) {
    try {
        // Buscamos usuario que coincida con nombre Y contraseña a la vez
        Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
        q.setParameter(1, userName);
        q.setParameter(2, password);
        
        return (User) q.getSingleResult();
    } catch (NoResultException e) {
        return null; // Login incorrecto
    }
}
	 */
	
		
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
	    Query q = em.createNativeQuery("SELECT * FROM users", User.class);
	    return q.getResultList();
	}
	
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
	
	@Override 
	public void updatePassword(User user, byte [] newPassword ) {
		String hashedPassword = BCrypt.hashpw(new String(newPassword), BCrypt.gensalt());
	    
	    try {
	        em.getTransaction().begin();
	        User managedUser = em.find(User.class, user.getUserId());
	        if (managedUser != null) {
	            managedUser.setPassword(hashedPassword.getBytes()); // Guardar siempre encriptado
	            user.setPassword(hashedPassword.getBytes());
	        }
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) em.getTransaction().rollback();
	        e.printStackTrace();
	    }
		
	}
	
	/*
	 * @Override 
public void updatePassword(User user, byte[] newPassword) {
    try {
        // 1. Iniciamos la transacción
        em.getTransaction().begin();
        
        // 2. Buscamos al usuario en la base de datos por su ID para que JPA lo "gestione"
        // Usamos user.getUserId() que es el ID del objeto que recibimos
        User managedUser = em.find(User.class, user.getUserId());
        
        if (managedUser != null) {
            // 3. Cambiamos la contraseña directamente en el objeto gestionado por JPA
            managedUser.setPassword(newPassword);
            
            // También actualizamos el objeto original que recibimos por parámetro 
            // para que el resto del programa vea el cambio
            user.setPassword(newPassword);
            
            System.out.println("Password updated successfully for user: " + user.getUserName());
        } else {
            System.out.println("User not found in database.");
        }
        
        // 4. Al hacer commit, JPA detecta que el password ha cambiado y lanza el UPDATE a la DB
        em.getTransaction().commit();
        
    } catch (Exception e) {
        // Si hay error, deshacemos cualquier cambio
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
    }
}
	 */
	
	public void disconnect() {
	    em.close();
	}
	
	
		
	
}
