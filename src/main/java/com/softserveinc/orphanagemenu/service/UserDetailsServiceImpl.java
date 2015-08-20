package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.RoleDao;
import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.model.Role;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	@Qualifier("userAccountDao")
	private UserAccountDao userAccountDao;
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserAccount userAccount = userAccountDao.getByLogin(login);
		List<GrantedAuthority> authorities = buildUserAuthority(userAccount.getRoles());
		return buildUserForAuthentication(userAccount, authorities);
	}
	
	private User buildUserForAuthentication(UserAccount userAccount, List<GrantedAuthority> authorities) {
		return new User(userAccount.getLogin(), userAccount.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}
	

}
