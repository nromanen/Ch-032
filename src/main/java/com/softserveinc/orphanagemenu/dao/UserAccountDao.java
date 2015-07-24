package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.UserAccount;

public interface UserAccountDao {
	UserAccount save(UserAccount userAccount);
	void delete(UserAccount userAccount);
	UserAccount getByID(Long id);
	List<UserAccount> getAll();
}
