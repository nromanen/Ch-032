package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.AgeCategory;

public interface AgeCategoryDao {

	List<AgeCategory> getAllAgeCategory();
	
	Long getCount();
}
