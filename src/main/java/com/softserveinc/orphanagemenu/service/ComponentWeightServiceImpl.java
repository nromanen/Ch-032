package com.softserveinc.orphanagemenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.softserveinc.orphanagemenu.dao.ComponentWeightDao;
import com.softserveinc.orphanagemenu.model.ComponentWeight;

@Service
public class ComponentWeightServiceImpl implements ComponentWeightService {
	
	@Autowired
	@Qualifier("componentWeightDaoImpl")
	private ComponentWeightDao componentWeightDao;
	
	@Transactional
	public void saveComponents(ComponentWeight comp) {
		this.componentWeightDao.addComponents(comp);
    }
	
	@Transactional
	public List<ComponentWeight> getAllComponentWeight() {
		return this.componentWeightDao.addAllComponentWeight();
	}
}
