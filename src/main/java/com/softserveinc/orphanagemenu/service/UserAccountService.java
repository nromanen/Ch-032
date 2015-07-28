package com.softserveinc.orphanagemenu.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;

public interface UserAccountService {

	public abstract UserAccount save(UserAccount userAccount);

	public abstract boolean deleteByID(Long id);

	public abstract UserAccount getByLogin(String login)
			throws NoResultException;

	public abstract UserAccount getById(Long id);

	public abstract List<UserAccount> getAllDto();

	public abstract boolean isLastAdministrator(Long id);

	public abstract boolean hasRole(UserAccount userAccount, String roleName);

	public abstract UserAccountForm getUserAccountFormByUserAccountId(Long id);

	public abstract UserAccount getUserAccountByUserAccountForm(
			UserAccountForm userAccountForm);

}