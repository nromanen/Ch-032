package com.softserveinc.orphanagemenu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "product_weight")
public class ProductWeight {

	private Long id;
	private Double standartProductQuantity;
	private Product product;
	private AgeCategory ageCategory;
	
	public ProductWeight() {
    }

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "standart_product_quantity")
	public Double getStandartProductQuantity() {
		return standartProductQuantity;
	}

	public void setStandartProductQuantity(Double standartProductQuantity) {
		this.standartProductQuantity = standartProductQuantity;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
    @JoinColumn(name = "age_category_id")	
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
		result = prime * result + ((ageCategory == null) ? 0 : ageCategory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((standartProductQuantity == null) ? 0 : standartProductQuantity.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (standartProductQuantity == null) {
			if (other.standartProductQuantity != null)
				return false;
		} else if (!standartProductQuantity.equals(other.standartProductQuantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductWeight [id=" + id + ", standartProductQuantity="
				+ standartProductQuantity + ", product=" + product
				+ ", ageCategory=" + ageCategory + "]";
	}




}
