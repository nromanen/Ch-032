/**
 * Copyright SoftServe.inc
 */

package com.menu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sviatoslav
 */
public class Ingredient {

	private double quantity;
	private Product product;
	private double price;

	/** This is private array list of Ingredient objects. */
	public static List<Ingredient> ingredientsList = new ArrayList<Ingredient>();

	public Ingredient() {
		super();
	}

	/**
	 * @param quantity
	 * @param product
	 * @param price
	 * @param availability
	 */
	public Ingredient(double quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
		double tmpPrice = Math.round(quantity * product.getPrice() * 100.0);
		this.price = tmpPrice / 100;
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the ingredient
	 */
	public Product getIngredient() {
		return product;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param product
	 *            the ingredient to set
	 */
	public void setIngredient(Product product) {
		this.product = product;
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
	 * @return the IngredientsList
	 */
	public static List<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	/**
	 * @param ingredientsList
	 *            the portionOfIngredientsList to set
	 */
	public static void setIngredientsList(
			 List<Ingredient> ingredientsList) {
		Ingredient.ingredientsList = ingredientsList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public  String toString() {
		return "Ingredient " + product.getTitle()
				+ " [quantity=" + quantity
				+ ", price=" + price 
				+ ", product=" + product + "\n" + "]";
	}
}
