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
import com.softserveinc.orphanagemenu.validator.user.UserForm;

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
	
	public UserForm getUserFormByUserAccountId(Long id){
		UserForm userForm =  new UserForm();
		if (id == null){
			return userForm;
		} else {
			UserAccount userAccount = getByID(id);
			userForm.setId(userAccount.getId().toString());
			userForm.setLogin(userAccount.getLogin());
			userForm.setFirstName(userAccount.getFirstName());
			userForm.setLastName(userAccount.getLastName());
			userForm.setEmail(userAccount.getEmail());
			userForm.setPassword(userAccount.getPassword());
			userForm.setAdministrator(hasRole(userAccount, "Administrator"));
			userForm.setOperator(hasRole(userAccount, "Operator"));
			return userForm;
		}
	}
	
	public UserAccount getUserAccountByUserForm(UserForm userForm){
		UserAccount userAccount =  new UserAccount();
		
		if (userForm.getId() != ""){
			Long id = Long.parseLong(userForm.getId());
			userAccount.setId(id);
		}
		userAccount.setLogin(userForm.getLogin());
		userAccount.setFirstName(userForm.getFirstName());
		userAccount.setLastName(userForm.getLastName());
		userAccount.setEmail(userForm.getEmail());
		userAccount.setPassword(userForm.getPassword());
		
		Set<Role> roles = new HashSet<>();
		if (userForm.isAdministrator()){
			Role roleAdministrator = roleDao.getRoleByName("Administrator");
			roles.add(roleAdministrator);
		}
		if (userForm.isOperator()){
			Role roleAdministrator = roleDao.getRoleByName("Operator");
			roles.add(roleAdministrator);
		}
		userAccount.setRoles(roles);
		return userAccount;
	}
}
