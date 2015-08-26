package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

/**
 * @author Olexii Riabokon
 */
@Repository("userAccountDao")
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {

	private static final String ALL_USER_ACCOUNTS = "SELECT ua FROM UserAccount ua";
	private static final String USER_ACCOUNT_BY_LOGIN = 
				"SELECT ua FROM UserAccount ua WHERE ua.login = :login";
	private static final String USER_ACCOUNTS_BY_ROLE = 
				  "SELECT ua "
				+ "FROM UserAccount ua join ua.roles r "
				+ "WHERE r.name = :role";
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public UserAccount save(UserAccount userAccount) {
		return em.merge(userAccount);
	}

	@Override
	public void delete(UserAccount userAccount) {
		em.remove(em.merge(userAccount));
	}

	@Override
	public UserAccount getByLogin(String login) {
		UserAccount userAccount = null;
		try {
			userAccount = (UserAccount) em.createQuery(USER_ACCOUNT_BY_LOGIN)
					.setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e){
			// it's OK - return null
		}

		return userAccount;
	}

	@Override
	public UserAccount getById(Long id) {
		return em.find(UserAccount.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserAccount> getAll() {
		return em.createQuery(ALL_USER_ACCOUNTS).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserAccount> getByRole(Role role) {
		return em.createQuery(USER_ACCOUNTS_BY_ROLE)
				.setParameter("role", role.getName())
				.getResultList();
	}

}
