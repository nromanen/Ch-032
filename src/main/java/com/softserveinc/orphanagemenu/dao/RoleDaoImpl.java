package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Role;

/**
 * @author Olexii Riabokon
 */
@Repository("roleDao")
@Transactional
public class RoleDaoImpl implements RoleDao {
	private static final String ALL_ROLES = "SELECT r FROM Role r";
	private static final String ROLE_BY_NAME = "SELECT r FROM Role r WHERE r.name = :name";
			
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Role save(Role role) {
		return em.merge(role);
	}

	@Override
	public void delete(Role role) {
		em.remove(em.merge(role));
	}

	@Override
	public Role getByID(Long id) {
		return em.find(Role.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return em.createQuery(ALL_ROLES).getResultList();
	}

	@Override
	public Role getRoleByName(String name) {
		return (Role)em.createQuery(ROLE_BY_NAME)
				.setParameter("name", name)
				.getSingleResult();
	}
}
