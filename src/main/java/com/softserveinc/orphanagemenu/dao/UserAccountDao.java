package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

/**
 * @author Olexii Riabokon
 */
public interface UserAccountDao {
	UserAccount save(UserAccount userAccount);
	void delete(UserAccount userAccount);
	UserAccount getById(Long id);
	UserAccount getByLogin(String login);
	List<UserAccount> getAll();
	List<UserAccount> getByRole(Role role);
}
