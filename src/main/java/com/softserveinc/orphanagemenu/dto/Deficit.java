package com.softserveinc.orphanagemenu.dto;

import com.softserveinc.orphanagemenu.model.Product;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public class Deficit {
	private Product product;
	private Double quantity;

	public Deficit() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Deficit [product=" + product + ", quantity=" + quantity + "]";
	} 
	
	
}
