package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Dimension;

public interface DimensionDao {
	
	Long save(String dimensionName);

	Dimension getDimension(Long dimensionId);

	Dimension getDimension(String dimensionName);

	List<Dimension> getAllDimension();

}
