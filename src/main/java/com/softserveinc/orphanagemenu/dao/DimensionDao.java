package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Dimension;

public interface DimensionDao {
	public Long save(String dimensionName);

	public Dimension getDimension(Long dimensionId);

	public Dimension getDimension(String dimensionName);

	List<Dimension> getAllDimension();

}
