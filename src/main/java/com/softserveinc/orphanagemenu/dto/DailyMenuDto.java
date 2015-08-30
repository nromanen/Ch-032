package com.softserveinc.orphanagemenu.dto;

import java.util.List;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public class DailyMenuDto {

	private String dailyMenuId;
	private String date;
	private String day;
	private Boolean accepted;
	private Boolean exist;
	private List<DishesForConsumption> dishesForConsumptions;
	
	public DailyMenuDto() {
	}
	
	public String getDailyMenuId() {
		return dailyMenuId;
	}

	public void setDailyMenuId(String dailyMenuId) {
		this.dailyMenuId = dailyMenuId;
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

	public Boolean getExist() {
		return exist;
	}

	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public List<DishesForConsumption> getDishesForConsumptions() {
		return dishesForConsumptions;
	}

	public void setDishesForConsumptions(
			List<DishesForConsumption> dishesForConsumptions) {
		this.dishesForConsumptions = dishesForConsumptions;
	}

}
