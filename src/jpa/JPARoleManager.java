package jpa;


import java.util.List; 
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import interfaces.RoleManager;
import pojos.Role;

public class JPARoleManager implements RoleManager {
	
	private EntityManager em;
	
	public JPARoleManager () {
		
		this.em = Persistence.createEntityManagerFactory("pharmacy-unit").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void createRole(Role role) {
		try {
			em.getTransaction().begin();
			em.persist(role);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) 
				em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	@Override
	public Role findRoleByName(String roleName) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE roleName = ?", Role.class);
		q.setParameter(1, roleName);
		try {
			return (Role) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Role findRoleById(Integer id) {
		return em.find(Role.class, id);
	}
	
	@Override 
	public List<Role> getAllRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		return q.getResultList();
	}
	
	@Override
	public void updateRole(Role role) {
		try {
			em.getTransaction().begin();
			em.merge(role); // Actualiza los datos del rol buscando por su ID
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) 
				em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	@Override
	public void delateRole(Integer id) {
		try {
			em.getTransaction().begin();
			Role role = em.find(Role.class, id);
			if (role != null) {
				em.remove(role);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) 
				em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
	    em.close();
	}
	
	

}
