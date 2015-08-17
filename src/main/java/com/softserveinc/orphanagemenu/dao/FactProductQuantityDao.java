package com.softserveinc.orphanagemenu.dao;


import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface FactProductQuantityDao {
	
	FactProductQuantity getBySubmenuAndComponentWeight(Submenu submenu, ComponentWeight componentWeight);
}
