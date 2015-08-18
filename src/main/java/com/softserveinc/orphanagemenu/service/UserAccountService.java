package com.softserveinc.orphanagemenu.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.softserveinc.orphanagemenu.exception.NotSuccessDBException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

public interface UserAccountService {

	UserAccount save(UserAccount userAccount) throws NotSuccessDBException;

	void deleteByID(Long id) throws NotSuccessDBException;

	UserAccount getByLogin(String login) throws NoResultException;

	UserAccount getById(Long id);

	List<UserAccount> getAllDto();

	boolean isLastAdministrator(Long id);

	boolean hasRole(UserAccount userAccount, String roleName);

	UserAccountForm getUserAccountFormByUserAccountId(Long id);

	UserAccount getUserAccountByUserAccountForm(UserAccountForm userAccountForm);
	
	List<Role> getAllPossibleRoles();

}
