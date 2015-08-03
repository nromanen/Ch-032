package com.softserveinc.orphanagemenu.forms;

import java.util.Map;
import java.util.TreeMap;


public class ProductForm {
	private String id;
	private String name;
	private String dimensionId;
	private Map<Long, Double> weightList = new TreeMap<>();
	
	
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
	public Map<Long, Double> getWeightList() {
		return weightList;
	}
	public void setWeightList(Map<Long, Double> weightList) {
		this.weightList = weightList;
	}
}