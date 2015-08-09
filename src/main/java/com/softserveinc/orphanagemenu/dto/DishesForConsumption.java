package com.softserveinc.orphanagemenu.dto;

import java.util.Set;

import com.softserveinc.orphanagemenu.model.ConsumptionType;

public class DishesForConsumption {

	private ConsumptionType consumptionType;
	Set<IncludingDeficitDish> includingDeficitDishes;
	
	public DishesForConsumption() {
	}

	public ConsumptionType getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionType consumptionType) {
		this.consumptionType = consumptionType;
	}

	public Set<IncludingDeficitDish> getIncludingDeficitDishes() {
		return includingDeficitDishes;
	}

	public void setIncludingDeficitDishes(
			Set<IncludingDeficitDish> includingDeficitDishes) {
		this.includingDeficitDishes = includingDeficitDishes;
	}

}
