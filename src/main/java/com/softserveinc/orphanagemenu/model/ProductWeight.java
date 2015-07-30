package com.softserveinc.orphanagemenu.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "product_weight")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.product",
        joinColumns = @JoinColumn(name = "product_id")),
    @AssociationOverride(name = "primaryKey.ageCategory",
        joinColumns = @JoinColumn(name = "age_category_id")) })
public class ProductWeight implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private ProductWeightId primaryKey = new ProductWeightId();
	private Double standartProductQuantity;
	private Product product;
	private AgeCategory ageCategory;

	public ProductWeight() {
    }

	@EmbeddedId
	public ProductWeightId getPrimaryKey() {
		return primaryKey;
	}


	public void setPrimaryKey(ProductWeightId primaryKey) {
		this.primaryKey = primaryKey;
	}


	@Column(name = "standart_product_quantity")
	public Double getStandartProductQuantity() {
		return standartProductQuantity;
	}

	public void setStandartProductQuantity(Double standartProductQuantity) {
		this.standartProductQuantity = standartProductQuantity;
	}

	@Transient
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Transient
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ageCategory == null) ? 0 : ageCategory.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime
				* result
				+ ((standartProductQuantity == null) ? 0
						: standartProductQuantity.hashCode());
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
		ProductWeight other = (ProductWeight) obj;
		if (ageCategory == null) {
			if (other.ageCategory != null)
				return false;
		} else if (!ageCategory.equals(other.ageCategory))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (standartProductQuantity == null) {
			if (other.standartProductQuantity != null)
				return false;
		} else if (!standartProductQuantity
				.equals(other.standartProductQuantity))
			return false;
		return true;
	}




}
