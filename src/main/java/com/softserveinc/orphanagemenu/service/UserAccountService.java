package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;

@Service("userAccountService")
@Transactional
public class UserAccountService {

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
		
	public void deleteByID(Long id){
		userAccountDao.delete(userAccountDao.getByID(id));
	}
	
	public UserAccount getByID(Long id){
		UserAccount userAccount = userAccountDao.getByID(id);
		Mapper mapper = new DozerBeanMapper();
		UserAccount userAccountDTO =  mapper.map(userAccount, UserAccount.class);
		return userAccountDTO;
	}
	
	public UserAccount getByLogin(String login) throws NoResultException {
		UserAccount userAccount = userAccountDao.getByLogin(login);
		return userAccount;
	}
	
	public List<UserAccount> getAll(){
		List<UserAccount> userAccounts = userAccountDao.getAll();
		List<UserAccount> userAccountsDTO = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (UserAccount userAccount : userAccounts){
			userAccountsDTO.add(mapper.map(userAccount, UserAccount.class));
		}
		return userAccountsDTO;
	}
	
	public UserAccount save(UserAccount userAccount){
		UserAccount userAccountWithId = userAccountDao.save(userAccount);
		Mapper mapper = new DozerBeanMapper();
		UserAccount userAccountDTO =  mapper.map(userAccountWithId, UserAccount.class);
		return userAccountDTO;		
	}
	
	// TODO don't go to DB for roles twice
	public boolean hasRole (UserAccount userAccount, String roleName){
		return userAccount.getRoles().contains(roleDao.getRoleByName(roleName));
	}
	
	public UserAccountForm getUserAccountFormByUserAccountId(Long id){
		UserAccountForm userAccountForm =  new UserAccountForm();
		if (id == null){
			return userAccountForm;
		} else {
			UserAccount userAccount = getByID(id);
			userAccountForm.setId(userAccount.getId().toString());
			userAccountForm.setLogin(userAccount.getLogin());
			userAccountForm.setFirstName(userAccount.getFirstName());
			userAccountForm.setLastName(userAccount.getLastName());
			userAccountForm.setEmail(userAccount.getEmail());
			userAccountForm.setPassword(userAccount.getPassword());
			userAccountForm.setAdministrator(hasRole(userAccount, "Administrator"));
			userAccountForm.setOperator(hasRole(userAccount, "Operator"));
			return userAccountForm;
		}
	}
	
	public UserAccount getUserAccountByUserAccountForm(UserAccountForm userAccountForm){
		UserAccount userAccount =  new UserAccount();
		
		if (userAccountForm.getId() != ""){
			Long id = Long.parseLong(userAccountForm.getId());
			userAccount.setId(id);
		}
		userAccount.setLogin(userAccountForm.getLogin());
		userAccount.setFirstName(userAccountForm.getFirstName());
		userAccount.setLastName(userAccountForm.getLastName());
		userAccount.setEmail(userAccountForm.getEmail());
		userAccount.setPassword(userAccountForm.getPassword());
		
		Set<Role> roles = new HashSet<>();
		if (userAccountForm.isAdministrator()){
			Role roleAdministrator = roleDao.getRoleByName("Administrator");
			roles.add(roleAdministrator);
		}
		if (userAccountForm.isOperator()){
			Role roleAdministrator = roleDao.getRoleByName("Operator");
			roles.add(roleAdministrator);
		}
		userAccount.setRoles(roles);
		return userAccount;
	}
}
