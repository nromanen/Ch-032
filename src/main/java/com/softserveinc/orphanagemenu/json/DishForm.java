package com.softserveinc.orphanagemenu.json;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Product;

import java.util.Map;
import java.util.TreeMap;

public class DishForm {
	
	private Long id;
	
	private String dishName;
	private String comp_id;
	
	private Component component_id;
	
	private AgeCategory age_category_id;
	
	private String standart_component_quantity;
	private Map<Long, String> category = new TreeMap<>();
	private Product product; 
	
	// age_category_id || double standart_quantity
	Map<Long, Double> weight;

	public Long getId() {
		return id;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Map<Long, String> getCategory() {
		return category;
	}
	public void setCategory(Map<Long, String> category) {
		this.category = category;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Component getComponent_id() {
		return component_id;
	}

	public void setComponent_id(Component component_id) {
		this.component_id = component_id;
	}

	public AgeCategory getAge_category_id() {
		return age_category_id;
	}

	public void setAge_category_id(AgeCategory age_category_id) {
		this.age_category_id = age_category_id;
	}

	public String getStandart_component_quantity() {
		return standart_component_quantity;
	}

	public void setStandart_component_quantity(String standart_component_quantity) {
		this.standart_component_quantity = standart_component_quantity;
	}


	public Map<Long, Double> getWeight() {
		return weight;
	}

	public void setWeight(Map<Long, Double> weight) {
		this.weight = weight;
	}


	
	

}
