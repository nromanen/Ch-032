/**
 * 
 */

package com.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This public class creates an Product object with followed parameters:
 * (int) id, (String) title, (double) price, (Dimension (enum)) dimension, (boolean)
 * availability.
 * 
 * @author Sviatoslav
 * 
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name="product")
public class Product{
	
	public static Logger product = LogManager.getLogger(Product.class.getName());

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
	private int id;

	/** This private String value consist the title of dish ingredient. */
	@Column(name="title")
	private String title;

	/** This private double value consist the price of dish ingredient. */
	@Column(name="price")
	private double price;

	/** This private enum value consist the type of ingredient dimension. */
	@Column(name="productDimension")
	@Enumerated(EnumType.STRING)
	private ProductDimension productDimension;

	/**
	 * This private boolean value consist the availability of meal product.
	 */
	@Column(name="available")
	private boolean available;
	
	public static List  <Product> productList = new ArrayList  <Product> ();
	
	public Product() {
		super();
	}
	
	
	/**
	 * This public constructor set up all parameters. title, price, dimension,
	 * available.
	 * 
	 * @param title
	 *            String type
	 * @param price
	 *            double type
	 * @param dimension
	 *            String type
	 * @param available
	 *            boolean type
	 */
	public Product(String title, double price, String productDimension,
			String available) {
		super();
		this.title = title;
		if (price <= 0) {
			System.out.println("Your price must be above 0");
		} else {
			this.price = price;
		}
		this.productDimension = ProductDimension.valueOf(productDimension.toUpperCase());
		this.available = Boolean.valueOf(available);
		
//		product.info("Product obgect have been created.");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingredient " + title + " [id=" + id + ", title=" + title + ", price=" + price
				+ ", productDimension=" + productDimension
				+ ", available=" + available + "]"+"\n";
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the dimension
	 */
	public String getProductDimension() {
		return productDimension.toString();
	}

	/**
	 * @param productDimension
	 *            the dimension to set
	 */
	public void setProductDimension(String productDimension) {
		this.productDimension = ProductDimension
				.valueOf(productDimension);
	}
	
	/**
	 * @return the availability
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param availability
	 *            the availability to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
