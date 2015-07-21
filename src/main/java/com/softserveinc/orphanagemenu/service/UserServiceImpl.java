package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

import com.softserveinc.orphanagemenu.dao.UserDao;
import com.softserveinc.orphanagemenu.model.User;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDao userDao;
 
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Transactional
    public List<User> listUser() {
    	return userDao.listUser();
    }
 
}