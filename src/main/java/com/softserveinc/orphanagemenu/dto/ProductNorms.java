package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductNorms {
	private String productName;
	private ArrayList<AgeCategoryNorms> categoryWithNormsAndFact = new ArrayList<AgeCategoryNorms>();

	public ProductNorms() {
		super();	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public List<AgeCategoryNorms> getCategoryWithNormsAndFact() {
		return categoryWithNormsAndFact;
	}

	public void addCategoryWithNormsAndFact(
			AgeCategoryNorms categoryWithNormsAndFact) {
		if (this.categoryWithNormsAndFact.contains(categoryWithNormsAndFact)) {
			AgeCategoryNorms ageCat = this.categoryWithNormsAndFact
					.get(this.categoryWithNormsAndFact
							.indexOf(categoryWithNormsAndFact));
			ageCat.setFactProductQuantity(ageCat.getFactProductQuantity()
					+ categoryWithNormsAndFact.getFactProductQuantity());

		} else {
			this.categoryWithNormsAndFact.add(categoryWithNormsAndFact);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
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
		ProductNorms other = (ProductNorms) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}
