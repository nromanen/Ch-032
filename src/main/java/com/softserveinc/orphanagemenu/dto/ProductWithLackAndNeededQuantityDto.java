package com.softserveinc.orphanagemenu.dto;

import com.softserveinc.orphanagemenu.model.Product;

public class ProductWithLackAndNeededQuantityDto {
	private Product product;
	private Double neededQuantity;
	private Double quantityAvailable;
	private Double lack;

	public ProductWithLackAndNeededQuantityDto() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getNeededQuantity() {
		return neededQuantity;
	}

	public void setNeededQuantity(Double neededQuantity) {
		this.neededQuantity = neededQuantity;
	}

	public Double getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Double quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Double getLack() {
		return lack;
	}

	public void setLack(Double lack) {
		this.lack = lack;
	}
}
