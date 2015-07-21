package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.User;

public interface UserDao {
	void saveUser(User user);
	List<User> listUser();
}
