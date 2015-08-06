package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.model.AgeCategory;

@Service
public class AgeCategoryService {
	
	@Autowired
	@Qualifier("ageCategoryImpl")
	private AgeCategoryDao ageCategoryDao;
	
	
	@Transactional
	public ArrayList<AgeCategory> getAllAgeCategory() {
		return this.ageCategoryDao.getAllAgeCategory();
	}
	
}
