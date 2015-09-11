package com.softserveinc.orphanagemenu.dto;

import java.util.Map;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public class ProductQuantitiesReportColumn {

	private ConsumptionType consumptionType;
	private Dish dish;
	private Map <Product, Map <AgeCategory, Double>> productQuantities;
	
	public ProductQuantitiesReportColumn() {
	}

	public ConsumptionType getConsumptionType() {
		return consumptionType;
	}

	public void setConsumptionType(ConsumptionType consumptionType) {
		this.consumptionType = consumptionType;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Map<Product, Map<AgeCategory, Double>> getProductQuantities() {
		return productQuantities;
	}

	public void setProductQuantities(
			Map<Product, Map<AgeCategory, Double>> productQuantities) {
		this.productQuantities = productQuantities;
	}

	@Override
	public String toString() {
		return "ProductQuantitiesReportColumn [consumptionType="
				+ consumptionType + ", dish=" + dish + ", productQuantities="
				+ productQuantities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumptionType == null) ? 0 : consumptionType.hashCode());
		result = prime * result + ((dish == null) ? 0 : dish.hashCode());
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
		ProductQuantitiesReportColumn other = (ProductQuantitiesReportColumn) obj;
		if (consumptionType == null) {
			if (other.consumptionType != null)
				return false;
		} else if (!consumptionType.equals(other.consumptionType))
			return false;
		if (dish == null) {
			if (other.dish != null)
				return false;
		} else if (!dish.equals(other.dish))
			return false;
		return true;
	}

}
