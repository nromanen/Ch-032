package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.UserAccount;

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
	
	public List<UserAccount> getAll(){
		List<UserAccount> userAccounts = userAccountDao.getAll();
		List<UserAccount> userAccountsDTO = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (UserAccount userAccount : userAccounts){
			userAccountsDTO.add(mapper.map(userAccount, UserAccount.class));
		}
		return userAccountsDTO;
	}
	
	
	public boolean hasRole (UserAccount userAccount, String roleName){
		return userAccount.getRoles().contains(roleDao.getRoleByName(roleName));
	}
}
