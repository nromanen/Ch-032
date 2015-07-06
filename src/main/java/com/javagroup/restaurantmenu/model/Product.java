package com.javagroup.restaurantmenu.model;

import java.util.Objects;

/**
 * Class for storing products that are the part of ingredients 
 * that are used to compose dishes.
 * 
 * @author Oleksii Riabokon
 * @author Vova Perepelyuk
 */
public class Product {
	/**
	 * id of the product.
	 */
	private long id;

	/**
	 * Name of the product.
	 */
	private String name;
    
	/**
	 * Price of the product in dollars.
	 */
	private double price;
    
	/**
	 * Flag to mark the product available (true) or unavailable (false).
	 */
	private boolean available;

    /**
     * Constructs a Product with empty fields.
     */	
    public Product() {
    }

    /**
     * Constructs a Product with full fields definition.
     * @param name the name of the product
     * @param price the price of the product in dollars
     * @param available Flag to mark the product available (true) 
     * or unavailable (false).
     */    
    public Product(String name, double price, boolean available) {
    	this.name = name;
        this.price = price;
        this.available = available;
    }
    
    /**
     * Constructs a Product with full fields definition.
     * @param id id of the product
     * @param name the name of the product
     * @param price the price of the product in dollars
     * @param available Flag to mark the product available (true) 
     * or unavailable (false).
     */
    public Product(long id, String name, double price, boolean available) {
    	this.id = id;
    	this.name = name;
        this.price = price;
        this.available = available;
    }

    /**
     * @return the id of the product
     */
    public long getId() {
		return id;
	}

    /**
     * @param id id of the product to set
     */
    public void setId(long id) {
		this.id = id;
	}
        
    /**
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of the product to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price of the product (in dollars)
     */
    public double getPrice() {
        return price;
    }

    /**
     * 
     * @param price price of the product to set (in dollars)
     */    
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the flag if the product available (true) or unavailable (false)
     */
    public boolean getAvailable() {
        return available;
    }

    /**
     * @param available flag to mark the product available (true) 
     * or unavailable (false).
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns a string representation of this product. The string
     * representation includes values of such fields: name, price, available<br>
     * Example: Product{name=tea, price=12.3, available=true}
     * @return a string representation of this product
     */
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" 
        			+ price + ", available=" + available + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    
}
