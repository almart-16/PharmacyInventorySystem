package jpa;


import java.util.List; 
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    /**
     * {@inheritDoc}
     */
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
    /**
     * {@inheritDoc}
     */
    @Override
	public Role findRoleByName(String roleName) {
		TypedQuery<Role> q = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class);
		q.setParameter("roleName", roleName);
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * {@inheritDoc}
     */
    @Override
	public Role findRoleById(Integer id) {
		return em.find(Role.class, id);
	}
    /**
     * {@inheritDoc}
     */
    @Override 
	public List<Role> getAllRoles() {
		TypedQuery<Role> q = em.createQuery("SELECT r FROM Role r", Role.class);
		return q.getResultList();
	}
    /**
     * {@inheritDoc}
     */
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
    /**
     * {@inheritDoc}
     */
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
	
	public EntityManager getEntityManager () {
		return this.em;
		
	}
	
	

}

