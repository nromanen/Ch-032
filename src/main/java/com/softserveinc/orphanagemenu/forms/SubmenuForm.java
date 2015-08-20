package com.softserveinc.orphanagemenu.forms;

import java.util.Set;

import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;


public class SubmenuForm {

	String dishName;
	String dailyMenuId;
	String consumptionTypeId;
	String ageCategoryId;
	Set<Dish> dishes;
	Set<Product> products;
	
	public SubmenuForm (){ 
		
	}
	
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDailyMenuId() {
		return dailyMenuId;
	}

	public void setDailyMenuId(String dailyMenuId) {
		this.dailyMenuId = dailyMenuId;
	}

	public String getConsumptionTypeId() {
		return consumptionTypeId;
	}

	public void setConsumptionTypeId(String consumptionTypeId) {
		this.consumptionTypeId = consumptionTypeId;
	}

	public String getAgeCategoryId() {
		return ageCategoryId;
	}

	public void setAgeCategoryId(String ageCategoryId) {
		this.ageCategoryId = ageCategoryId;
	}

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ageCategoryId == null) ? 0 : ageCategoryId.hashCode());
		result = prime
				* result
				+ ((consumptionTypeId == null) ? 0 : consumptionTypeId
						.hashCode());
		result = prime * result
				+ ((dailyMenuId == null) ? 0 : dailyMenuId.hashCode());
		result = prime * result
				+ ((dishName == null) ? 0 : dishName.hashCode());
		result = prime * result + ((dishes == null) ? 0 : dishes.hashCode());
		result = prime * result
				+ ((products == null) ? 0 : products.hashCode());
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
		SubmenuForm other = (SubmenuForm) obj;
		if (ageCategoryId == null) {
			if (other.ageCategoryId != null)
				return false;
		} else if (!ageCategoryId.equals(other.ageCategoryId))
			return false;
		if (consumptionTypeId == null) {
			if (other.consumptionTypeId != null)
				return false;
		} else if (!consumptionTypeId.equals(other.consumptionTypeId))
			return false;
		if (dailyMenuId == null) {
			if (other.dailyMenuId != null)
				return false;
		} else if (!dailyMenuId.equals(other.dailyMenuId))
			return false;
		if (dishName == null) {
			if (other.dishName != null)
				return false;
		} else if (!dishName.equals(other.dishName))
			return false;
		if (dishes == null) {
			if (other.dishes != null)
				return false;
		} else if (!dishes.equals(other.dishes))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	
}
