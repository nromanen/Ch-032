package com.softserveinc.orphanagemenu.dto;

import com.softserveinc.orphanagemenu.model.AgeCategory;

public class NormstForAgeCategoryDto implements
		Comparable<NormstForAgeCategoryDto> {
	private AgeCategory ageCategory;
	private Double standartProductQuantity;
	private Double factProductQuantity;

	public NormstForAgeCategoryDto() {

	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public Double getStandartProductQuantity() {
		return standartProductQuantity;
	}

	public void setStandartProductQuantity(Double norma) {

		this.standartProductQuantity = norma;

	}

	public Double getFactProductQuantity() {
		return factProductQuantity;
	}

	public void setFactProductQuantity(Double factQuantity) {
		this.factProductQuantity = factQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ageCategory == null) ? 0 : ageCategory.hashCode());
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
		NormstForAgeCategoryDto other = (NormstForAgeCategoryDto) obj;
		if (ageCategory == null) {
			if (other.ageCategory != null)
				return false;
		} else if (!ageCategory.equals(other.ageCategory))
			return false;
		return true;
	}

	@Override
	public int compareTo(NormstForAgeCategoryDto o) {
		if (this.getAgeCategory().getId() < o.getAgeCategory().getId()) {
			return -1;
		} else {
			return 1;
		}

	}

}
