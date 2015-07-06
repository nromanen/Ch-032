package com.javagroup.restaurantmenu.model;

import java.util.Objects;

/**
 * Entity that contains product and quantity of product.
 *
 * <p>It also calculates the total price of ingredient.
 *
 * @author Volodymyr Perepeliuk <vovan4uk86@gmail.com>
 * @version 1.0
 */
public class Ingredient {

	private long id;
	
    /**
     * Represents product
     */
    private Product product;

    /**
     * Represents quantity of the product
     */
    private int quantity;

    /**
    * Constructs ingredient with empty fields.
    */
    public Ingredient() {
    }

    /**
     * Constructs an Ingredient with full fields definition.
     * @param product product
     * @param quantity the quantity of the product
     */  
    public Ingredient(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product sets the product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity if it's valid (it must be > 0).
     * @param quantity sets the quantity of the product
     */
    public void setQuantity(int quantity) {
        if (!isQuantityValid(quantity)) {
        	throw new IllegalArgumentException();
        }
        this.quantity = quantity;
    }

    /**
     * Calculates the total price of ingredient. Total price is the 
     * multiplication of the product price and quantity of the product.
     * @return total ingredient price
     */    
    public double getPrice() {
        return product.getPrice() * quantity;
    }

    
    
    @Override
	public String toString() {
		return "Ingredient [product=" + product + ", quantity=" + quantity
				+ "]";
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        return Objects.equals(this.product, other.product);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.product);
        return hash;
    }

    /**
     * Validate quantity  
     * @return true if quantity is valid otherwise return false
     */ 
    public boolean isQuantityValid(int quantityPrompted) {
    	if (quantityPrompted <= 0) {
    		return false;
    	} 
    	return true;
    }

}
