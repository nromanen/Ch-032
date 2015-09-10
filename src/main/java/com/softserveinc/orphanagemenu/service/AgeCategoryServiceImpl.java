package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.model.AgeCategory;

@Service
@Transactional
public class AgeCategoryServiceImpl implements AgeCategoryService {
	
	@Autowired
	private AgeCategoryDao ageCategoryDao;
	
	@Override
	@Transactional
	public List<AgeCategory> getAllAgeCategory() {
		return this.ageCategoryDao.getAllAgeCategory();
	}
	
}
	
