package com.menu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mealitem")
public class MealItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="mealId")
	private int mealId;
	
	@Column(name="ingredientId")
	private int ingredientId;
	
	@Column(name="quantity")
	private int quantity;

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
	 * @return the mealId
	 */
	public int getMealId() {
		return mealId;
	}

	/**
	 * @param mealId the mealId to set
	 */
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	/**
	 * @return the ingredientId
	 */
	public int getIngredientId() {
		return ingredientId;
	}

	/**
	 * @param ingredientId the ingredientId to set
	 */
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

