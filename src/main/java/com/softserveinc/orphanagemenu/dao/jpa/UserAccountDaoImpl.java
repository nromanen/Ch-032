package com.softserveinc.orphanagemenu.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.UserAccount;

@Repository("userAccountDao")
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {

	private static final String ALL_USER_ACCOUNTS = "SELECT ua FROM UserAccount ua";
    
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
	public UserAccount getByID(Long id) {
		return em.find(UserAccount.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserAccount> getAll() {
		return em.createQuery(ALL_USER_ACCOUNTS).getResultList();
	}
	
}
