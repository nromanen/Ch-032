package com.softserveinc.orphanagemenu.dto;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ConsumptionType;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public class DishesForConsumption {

	
	private ConsumptionType consumptionType;
	private List<IncludingDeficitDish> includingDeficitDishes;
	private Map<AgeCategory, Integer> childQuantity;
	
	public DishesForConsumption() {
	}

	public Map<AgeCategory, Integer> getChildQuantity() {
		return childQuantity;
	}

	public void setChildQuantity(Map<AgeCategory, Integer> childQuantity) {
		this.childQuantity = childQuantity;
	}

	public ConsumptionType getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionType consumptionType) {
		this.consumptionType = consumptionType;
	}

	public List<IncludingDeficitDish> getIncludingDeficitDishes() {
		return includingDeficitDishes;
	}

	public void setIncludingDeficitDishes(
			List<IncludingDeficitDish> includingDeficitDishes) {
		this.includingDeficitDishes = includingDeficitDishes;
	}

}
