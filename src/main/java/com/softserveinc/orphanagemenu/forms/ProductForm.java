package com.softserveinc.orphanagemenu.forms;

import java.util.List;


public class ProductForm {
	private String id;
	private String name;
	private String dimensionId;
	private List<Double> weightList;
	
	
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

	public List<Double> getWeightList() {
		return weightList;
	}
	public void setWeightList(List<Double> weightList) {
		this.weightList = weightList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}