package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.model.ComponentWeight;

public interface ComponentWeightService {

	public void saveComponents(ComponentWeight comp);
	
	public List<ComponentWeight> getAllComponentWeight();
	
}
