package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
    
	@PersistenceContext
    private EntityManager em;

    public void saveUser(User user) {
		em.persist(user);
    }

	@Override
	public List<User> listUser() {
		  TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			  List<User> results = query.getResultList();
		return results;
	}
}