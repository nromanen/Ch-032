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

	public SubmenuForm() {

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
}
