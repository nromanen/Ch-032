package com.softserveinc.orphanagemenu.dao;


import java.util.List;

import com.softserveinc.orphanagemenu.model.ComponentWeight;

public interface ComponentWeightDao {

	void addComponents(ComponentWeight comp);
	
	List<ComponentWeight> addAllComponentWeight();
	
}
