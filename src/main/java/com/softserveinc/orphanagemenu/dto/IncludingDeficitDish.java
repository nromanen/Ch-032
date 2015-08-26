package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.orphanagemenu.model.Dish;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@SuppressWarnings("rawtypes")
public class IncludingDeficitDish implements Comparable {

	private Dish dish;
	private List<Deficit> deficits = new ArrayList<Deficit>();
	
	public IncludingDeficitDish() {
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public List<Deficit> getDeficits() {
		return deficits;
	}

	public void setDeficits(List<Deficit> deficits) {
		this.deficits = deficits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		IncludingDeficitDish other = (IncludingDeficitDish) obj;
		if (dish == null) {
			if (other.dish != null)
				return false;
		} else if (!dish.equals(other.dish))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IncludingDeficitDish [dish=" + dish + ", deficits=" + deficits
				+ "]";
	}


	@Override
	public int compareTo(Object otherDish) {
		return dish.getName().compareTo(((IncludingDeficitDish)otherDish).getDish().getName());
	}


}
