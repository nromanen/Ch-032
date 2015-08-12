package com.softserveinc.orphanagemenu.json;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Product;

import java.util.Map;

public class DishForm {
	
	private Long id;
	
	private String dishName;
	
	private Component component;
	
	private AgeCategory ageCategoryId;
	
	private String standartComponentQuantity;
	
	private Product product; 
	
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

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public AgeCategory getAgeCategoryId() {
		return ageCategoryId;
	}

	public void setAgeCategoryId(AgeCategory ageCategoryId) {
		this.ageCategoryId = ageCategoryId;
	}

	public String getStandartComponentQuantity() {
		return standartComponentQuantity;
	}

	public void setStandartComponentQuantity(String standartComponentQuantity) {
		this.standartComponentQuantity = standartComponentQuantity;
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
				+ ((ageCategoryId == null) ? 0 : ageCategoryId.hashCode());
		result = prime * result
				+ ((component == null) ? 0 : component.hashCode());
		result = prime * result
				+ ((dishName == null) ? 0 : dishName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime
				* result
				+ ((standartComponentQuantity == null) ? 0
						: standartComponentQuantity.hashCode());
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
		if (ageCategoryId == null) {
			if (other.ageCategoryId != null)
				return false;
		} else if (!ageCategoryId.equals(other.ageCategoryId))
			return false;
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
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
		if (standartComponentQuantity == null) {
			if (other.standartComponentQuantity != null)
				return false;
		} else if (!standartComponentQuantity
				.equals(other.standartComponentQuantity))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	

}
