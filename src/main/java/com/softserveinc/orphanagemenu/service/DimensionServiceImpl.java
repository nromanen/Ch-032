package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.DimensionDao;
import com.softserveinc.orphanagemenu.model.Dimension;

@Service
@Transactional
public class DimensionServiceImpl implements DimensionService {
	
	@Autowired
	private DimensionDao dimensionDao;

	@Override
	public List<Dimension> getAllDimension() {
		 return dimensionDao.getAllDimension();
	}

}
