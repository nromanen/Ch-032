package com.softserveinc.orphanagemenu.dto;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dish;

public class SubmenuEditPageDto {
	private String consumptionTypeName;
	private String date;
	private Map<AgeCategory, Integer> ageCatsAndQty;
	private List<Dish> dishes;
	private List<SubmenuEditPageTableDto> submenuEditPageTableDtos;
	
	public String getConsumptionTypeName() {
		return consumptionTypeName;
	}
	public void setConsumptionTypeName(String consumptionTypeName) {
		this.consumptionTypeName = consumptionTypeName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<AgeCategory, Integer> getAgeCatsAndQty() {
		return ageCatsAndQty;
	}
	public void setAgeCatsAndQty(Map<AgeCategory, Integer> ageCats) {
		this.ageCatsAndQty = ageCats;
	}
	public List<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	public List<SubmenuEditPageTableDto> getSubmenuEditTableDtos() {
		return submenuEditPageTableDtos;
	}
	public void setSubmenuEditTableDtos(List<SubmenuEditPageTableDto> submenuEditTableDtos) {
		this.submenuEditPageTableDtos = submenuEditTableDtos;
	}
	
	
	
	

}
