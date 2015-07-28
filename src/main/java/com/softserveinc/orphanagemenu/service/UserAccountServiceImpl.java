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
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
		
	@Override
	public UserAccount save(UserAccount userAccount){
		UserAccount userAccountWithId = userAccountDao.save(userAccount);
		Mapper mapper = new DozerBeanMapper();
		UserAccount userAccountDto =  mapper.map(userAccountWithId, UserAccount.class);
		return userAccountDto;		
	}
	
	@Override
	public boolean deleteByID(Long id){
		if (!isLastAdministrator(id)){
			userAccountDao.delete(userAccountDao.getById(id));
			return true;
		}
		return false;
	}

	@Override
	public UserAccount getByLogin(String login) throws NoResultException {
		UserAccount userAccount = userAccountDao.getByLogin(login);
		return userAccount;
	}

	@Override
	public UserAccount getById(Long id){
		UserAccount userAccount = userAccountDao.getById(id);
		return userAccount;
	}
	
	@Override
	public List<UserAccount> getAllDto(){
		List<UserAccount> userAccounts = userAccountDao.getAll();
		List<UserAccount> userAccountsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (UserAccount userAccount : userAccounts){
			userAccountsDto.add(mapper.map(userAccount, UserAccount.class));
		}
		return userAccountsDto;
	}
	
	@Override
	public boolean isLastAdministrator(Long id){
		UserAccount userAccount = userAccountDao.getById(id);
		if (!hasRole(userAccount, "Administrator")){
			return false;
		}
		List<UserAccount> userAccounts = userAccountDao.getByRole(roleDao.getRoleByName("Administrator"));
		if (userAccounts.size() > 1 ){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean hasRole (UserAccount userAccount, String roleName){
		Set<Role> roles = userAccount.getRoles(); 
		for (Role role : roles){
			if (role.getName().equals(roleName)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public UserAccountForm getUserAccountFormByUserAccountId(Long id){
		UserAccountForm userAccountForm =  new UserAccountForm();
		if (id == null){
			return userAccountForm;
		} else {
			UserAccount userAccount = getById(id);
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
	
	@Override
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
