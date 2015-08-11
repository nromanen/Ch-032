package com.softserveinc.orphanagemenu.json;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Product;

import java.util.Map;

public class DishForm {
	
	private Long id;
	
	private String dishName;
	
	private Component component_id;
	
	private AgeCategory age_category_id;
	
	private String standart_component_quantity;
	
	private Product product; 
	
	// age_category_id || double standart_quantity
	Map<Long, Double> weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((age_category_id == null) ? 0 : age_category_id.hashCode());
		result = prime * result
				+ ((component_id == null) ? 0 : component_id.hashCode());
		result = prime * result
				+ ((dishName == null) ? 0 : dishName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime
				* result
				+ ((standart_component_quantity == null) ? 0
						: standart_component_quantity.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishForm other = (DishForm) obj;
		if (age_category_id == null) {
			if (other.age_category_id != null)
				return false;
		} else if (!age_category_id.equals(other.age_category_id))
			return false;
		if (component_id == null) {
			if (other.component_id != null)
				return false;
		} else if (!component_id.equals(other.component_id))
			return false;
		if (dishName == null) {
			if (other.dishName != null)
				return false;
		} else if (!dishName.equals(other.dishName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (standart_component_quantity == null) {
			if (other.standart_component_quantity != null)
				return false;
		} else if (!standart_component_quantity
				.equals(other.standart_component_quantity))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	

}
