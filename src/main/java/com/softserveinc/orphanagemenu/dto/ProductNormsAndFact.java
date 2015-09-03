package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductNormsAndFact {
	private String productName;

	private String dimension;	

	private List<NormAndFactForAgeCategory> categoryWithNormsAndFact = new ArrayList<NormAndFactForAgeCategory>();

	public ProductNormsAndFact() {
		super();	
		}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public List<NormAndFactForAgeCategory> getCategoryWithNormsAndFact() {
		return categoryWithNormsAndFact;
	}
	public String getDimension() {
		return dimension;
	}	
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public void addNormsAndFact(
			
			NormAndFactForAgeCategory categoryWithNormsAndFact) {
		if (this.categoryWithNormsAndFact.contains(categoryWithNormsAndFact)) {
			NormAndFactForAgeCategory ageCat = this.categoryWithNormsAndFact
					.get(this.categoryWithNormsAndFact
							.indexOf(categoryWithNormsAndFact));
			ageCat.addFactProductQuantity(ageCat.getFactProductQuantity()
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
		ProductNormsAndFact other = (ProductNormsAndFact) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

}
