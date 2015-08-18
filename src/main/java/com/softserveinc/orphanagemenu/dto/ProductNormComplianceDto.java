package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.List;


public class ProductNormComplianceDto {
	private String name;
	private ArrayList<AgeCategoryNormsAndFactDto> categoryWithNormsAndFact = new ArrayList<AgeCategoryNormsAndFactDto>();
	
	public ProductNormComplianceDto() {
		super();
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<AgeCategoryNormsAndFactDto> getCategoryWithNormsAndFact() {
		return categoryWithNormsAndFact;
	}
	public void setCategoryWithNormsAndFact(
			AgeCategoryNormsAndFactDto categoryWithNormsAndFact) {
		if(this.categoryWithNormsAndFact.contains(categoryWithNormsAndFact)){
			int indexID = this.categoryWithNormsAndFact.indexOf(categoryWithNormsAndFact);
			AgeCategoryNormsAndFactDto ageCat = this.categoryWithNormsAndFact.get(indexID);
			ageCat.setFactQuantity(ageCat.getFactQuantity()+categoryWithNormsAndFact.getFactQuantity());
			
		} else{
			this.categoryWithNormsAndFact.add(categoryWithNormsAndFact);
		}
		
		
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProductNormComplianceDto other = (ProductNormComplianceDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
