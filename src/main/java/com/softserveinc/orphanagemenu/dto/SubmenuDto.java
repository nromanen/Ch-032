package com.softserveinc.orphanagemenu.dto;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dish;

public class SubmenuDto {
	private String consumptionTypeName;
	private String date;
	private Map<AgeCategory, Integer> ageCatsAndQty;
	private List<Dish> dishes;
	private List<SubmenuEditTableDto> submenuEditTableDtos;
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
	public List<SubmenuEditTableDto> getSubmenuEditTableDtos() {
		return submenuEditTableDtos;
	}
	public void setSubmenuEditTableDtos(List<SubmenuEditTableDto> submenuEditTableDtos) {
		this.submenuEditTableDtos = submenuEditTableDtos;
	}
	
	
	
	

}
