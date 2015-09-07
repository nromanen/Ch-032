package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.orphanagemenu.model.Component;

public class SubmenuEditPageTableDto {
	private IncludingDeficitDish dishAndDeficit;
	private List<ProductNormsAndFact> norms;

	public IncludingDeficitDish getDishAndDeficit() {
		return dishAndDeficit;
	}

	public void setDishAndDeficit(IncludingDeficitDish dishAndDeficit) {
		this.dishAndDeficit = dishAndDeficit;
		List<Component> components = new ArrayList<Component>(dishAndDeficit.getDish().getComponents());
		this.norms = new ProductNormAndFactHelper().parseComponents(components);
			}

	public List<ProductNormsAndFact> getNorms() {
		return norms;
	}

	public void setNorms(List<ProductNormsAndFact> norms) {
		this.norms = norms;
	}
}
