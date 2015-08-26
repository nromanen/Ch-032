package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.ConsumptionType;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public interface ConsumptionTypeDao {
	
	List<ConsumptionType> getAll();
	ConsumptionType getById(Long id);
}
