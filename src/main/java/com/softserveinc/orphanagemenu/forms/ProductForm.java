package com.softserveinc.orphanagemenu.forms;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sviatoslav Fedechko
 */
public class ProductForm {
	private String id;
	private String name;
	private String dimensionId;
	// key - ageCategoryId, value - standartProductWeight
	private Map<Long, String> weightList = new TreeMap<>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDimension() {
		return dimensionId;
	}
	public void setDimension(String dimension) {
		this.dimensionId = dimension;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Long, String> getWeightList() {
		return weightList;
	}
	public void setWeightList(Map<Long, String> weightList) {
		this.weightList = weightList;
	}
}