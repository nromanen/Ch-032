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

@Entity
@Table(name="meal")
public class Meal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="mealcategory")
	@Enumerated(EnumType.STRING)
	private MealCategory mealcategory;
	
	@Column(name="price")
	private double price;
	
	@Column(name="available")
	private boolean available;
	
//	private List<Ingredient> ingredientList = new ArrayList<Ingredient>();
	
	/** This is private array list of Meals objects */
	public static List<Meal> mealList = new ArrayList<Meal>();

	public Meal() {
		super();
	}

	/**
	 * @param title
	 * @param mealcategory
	 * @param ingredientList
	 */
	public Meal(int id, String title, String mealcategory,
			List<Ingredient> ingredientList) {
		super();
		this.id = id;
		this.available = true;
		this.title = title;
		this.mealcategory = MealCategory.valueOf(mealcategory);
		Ingredient.ingredientsList = ingredientList;
		for (Ingredient ingredient : ingredientList) {
			if (!ingredient.getProduct().isAvailable()) {
				this.available = false;
				break;}
			this.price += ingredient.getPrice();
		}
	}
	
	/**
	 * @return the mealList
	 */
	public static List<Meal> getMealList() {
		return mealList;
	}

	/**
	 * @param mealList the mealList to set
	 */
	public static void setMealList(List<Meal> mealList) {
		Meal.mealList = mealList;
	}

	public void addMeal(Meal meal) {
		if (meal.isAvailable()) {
			mealList.add(meal);
		}
	}

	/**
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
	 * @return the mealcategory
	 */
	public MealCategory getMealcategory() {
		return mealcategory;
	}

	/**
	 * @param mealcategory
	 *            the mealcategory to set
	 */
	public void setMealcategory(String mealcategory) {
		this.mealcategory = MealCategory.valueOf(mealcategory);
	}

	
	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available
	 *            the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setMealcategory(MealCategory mealcategory) {
		this.mealcategory = mealcategory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Meal " + title + " [id=" + id + ", title=" + title + ", mealcategory="
				+ mealcategory + ", price=" + price + ", available="
				+ available + "\n" + "]";
	}



}
