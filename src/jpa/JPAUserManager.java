package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import interfaces.UserManager;
import pojos.User;


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
        em.getTransaction().begin();
        em.persist(user); // Guarda el usuario (y si es un Client, guarda sus datos extra)
        em.getTransaction().commit();
    }
	
	

}
