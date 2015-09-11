package com.softserveinc.orphanagemenu.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.softserveinc.orphanagemenu.exception.LastAdministratorException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

/**
 * @author Olexii Riabokon
 * @author Vladimir Perepeliuk
 */
public interface UserAccountService {

	UserAccount save(UserAccount userAccount) throws LastAdministratorException;

	void deleteByID(Long id) throws LastAdministratorException;

	UserAccount getByLogin(String login) throws NoResultException;

	UserAccount getById(Long id);

	List<UserAccount> getAllDto();

	boolean isLastAdministrator(Long id);

	boolean hasRole(UserAccount userAccount, String roleName);

	UserAccountForm getUserAccountFormByUserAccountId(Long id);

	UserAccount getUserAccountByUserAccountForm(UserAccountForm userAccountForm);
	
	List<Role> getAllPossibleRoles();

}
