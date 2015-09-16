package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.exception.LastAdministratorException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;

/**
 * @author Olexii Riabokon
 * @author Vladimir Perepeliuk
 */
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
	@PreAuthorize("hasRole('ROLE_Administrator')")
	public UserAccount getByLogin(String login) throws NoResultException {
		return userAccountDao.getByLogin(login);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_Administrator')")
	public UserAccount getById(Long id){
		return userAccountDao.getById(id);
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_Administrator')")
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
	@PreAuthorize("hasRole('ROLE_Administrator')")
	public UserAccount save(UserAccount userAccount) throws LastAdministratorException{
		UserAccount userAccountWithId = userAccountDao.save(userAccount);
		Mapper mapper = new DozerBeanMapper();
		return mapper.map(userAccountWithId, UserAccount.class);		
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_Administrator')")
	public void deleteByID(Long id) throws LastAdministratorException{
		if (!isLastAdministrator(id)){
				userAccountDao.delete(userAccountDao.getById(id));
		} else {
			throw new LastAdministratorException("deleteUser.LastAdministrator");
		}
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
			HashMap<String, String> rolesForForm = new HashMap<>();
			rolesForForm.put("Operator","on");
			userAccountForm.setRoles(rolesForForm);
			return userAccountForm;
		} else {
			UserAccount userAccount = getById(id);
			userAccountForm.setId(userAccount.getId().toString());
			userAccountForm.setLogin(userAccount.getLogin());
			userAccountForm.setFirstName(userAccount.getFirstName());
			userAccountForm.setLastName(userAccount.getLastName());
			userAccountForm.setEmail(userAccount.getEmail());
			userAccountForm.setPassword(userAccount.getPassword());
			HashMap<String, String> rolesForForm = new HashMap<>();
			for(Role role : userAccount.getRoles()) {
				rolesForForm.put(role.getName(), "on");
			}
			userAccountForm.setRoles(rolesForForm);
			return userAccountForm;
		}
	}
	
	@Override
	public UserAccount getUserAccountByUserAccountForm(UserAccountForm userAccountForm){
		UserAccount userAccount =  new UserAccount();
		
		if (!"".equals(userAccountForm.getId())){
			Long id = Long.parseLong(userAccountForm.getId());
			userAccount.setId(id);
		}
		userAccount.setLogin(userAccountForm.getLogin());
		userAccount.setFirstName(userAccountForm.getFirstName());
		userAccount.setLastName(userAccountForm.getLastName());
		userAccount.setEmail(userAccountForm.getEmail());
		userAccount.setPassword(userAccountForm.getPassword());
		
		Set<Role> roles = new HashSet<>();
		
		Set<String> roleNamesFromForm = userAccountForm.getRoles().keySet();
			for(String roleName : roleNamesFromForm){
				roles.add(roleDao.getRoleByName(roleName));
			}

		userAccount.setRoles(roles);
		return userAccount;
	}

	@Override
	public List<Role> getAllPossibleRoles() {
		return roleDao.getAll(); 
	}
	
}
