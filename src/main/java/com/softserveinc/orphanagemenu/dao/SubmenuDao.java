package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Submenu;

public interface SubmenuDao {
	Submenu save(Submenu submenu);
	void delete(Submenu submenu);
	Submenu getByID(Long id);
	List<Submenu> getAll();
	
}
