package com.softserveinc.orphanagemenu.dto;

import java.util.List;

public class DailyMenuDto {

	private String date;
	private String day;
	private Boolean accepted;
	private List<DishesForConsumption> dishesForConsumptions;
	
	public DailyMenuDto() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public List<DishesForConsumption> getDishesForConsumptions() {
		return dishesForConsumptions;
	}

	public void setDishesForConsumptions(
			List<DishesForConsumption> dishesForConsumptions) {
		this.dishesForConsumptions = dishesForConsumptions;
	}

}
