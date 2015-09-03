package com.softserveinc.orphanagemenu.dao;


import java.util.List;

import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 * @author Sviatoslav Fedechko
 */
public interface FactProductQuantityDao {
	
	FactProductQuantity getBySubmenuAndComponentWeight(Submenu submenu, ComponentWeight componentWeight);

	FactProductQuantity getFactProductQuantity(Submenu submenu, ComponentWeight componentWeight);

	FactProductQuantity getById(Long id);
	
	List<FactProductQuantity> getFactProductQuantityListByIdies(List<Long> factProductQuantityIds);
	void save(FactProductQuantity factProductQuantity);
}
