package com.softserveinc.orphanagemenu.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProductWeightId implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private Product product;
		private AgeCategory ageCategory;
		
		@ManyToOne(cascade = CascadeType.ALL)
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		
		@ManyToOne(cascade = CascadeType.ALL)
		public AgeCategory getAgeCategory() {
			return ageCategory;
		}
		public void setAgeCategory(AgeCategory ageCategory) {
			this.ageCategory = ageCategory;
		}
		
		
}
