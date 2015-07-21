package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.model.User;

public interface UserService {

    public void saveUser(User user);

    public List<User> listUser();

}
