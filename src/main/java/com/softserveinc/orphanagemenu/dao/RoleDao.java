package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Role;

public interface RoleDao {
	Role save(Role role);
	void delete(Role role);
	Role getByID(Long id);
	List<Role> getAll();
	Role getRoleByName(String name);
}
