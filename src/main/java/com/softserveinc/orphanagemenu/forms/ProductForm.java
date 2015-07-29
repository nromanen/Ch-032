package com.softserveinc.orphanagemenu.forms;

import java.util.Map;

public class ProductForm {
	private String id;
	private String name;
	private String dimension;
	private Map<String, String> weight;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public Map<String, String> getWeight() {
		return weight;
	}
	public void setWeight(Map<String, String> weight) {
		this.weight = weight;
	}
	
}