package com.softserveinc.orphanagemenu.dto;

import com.softserveinc.orphanagemenu.model.AgeCategory;

public class AgeCategoryNormsAndFactDto implements Comparable<AgeCategoryNormsAndFactDto> {
	private AgeCategory ageCategory;
	private Double norma;
	private Double factQuantity;
	
	public AgeCategoryNormsAndFactDto() {
		super();
		
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public Double getNorma() {
		return norma;
	}
	public void setNorma(Double norma) {
		this.norma = norma;
	}
	public Double getFactQuantity() {
		return factQuantity;
	}
	public void setFactQuantity(Double factQuantity) {
		this.factQuantity = factQuantity;
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
		AgeCategoryNormsAndFactDto other = (AgeCategoryNormsAndFactDto) obj;
		if (ageCategory == null) {
			if (other.ageCategory != null)
				return false;
		} else if (!ageCategory.equals(other.ageCategory))
			return false;
		return true;
	}
	@Override
	public int compareTo(AgeCategoryNormsAndFactDto o) {
		if(this.getAgeCategory().getId()<o.getAgeCategory().getId()){
			return -1;
		}
		else {
			return 1;
		}
		
	}
	
	
	

}
