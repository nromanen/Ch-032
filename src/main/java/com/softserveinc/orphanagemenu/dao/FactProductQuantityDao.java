package com.softserveinc.orphanagemenu.dao;


import java.util.List;
import java.util.Set;

import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public interface FactProductQuantityDao {
	
	FactProductQuantity getBySubmenuAndComponentWeight(Submenu submenu, ComponentWeight componentWeight);

	FactProductQuantity getFactProductQuantity(Submenu submenu, ComponentWeight componentWeight);

	FactProductQuantity getById(Long id);
	
	List<FactProductQuantity> getFactProductQuantityListByIdies(List<Long> factProductQuantityIds);
}
