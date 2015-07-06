package com.javagroup.restaurantmenu.model;

import java.util.List;
import java.util.Iterator;

//import java.util.*;

/**
 * Class to storing the dish. It includes it's name, group 
 * and list of ingredients.
 *
 * @author Volodymyr Perepeliuk
 * @version 1.0
 */
public class Dish {
	
	private long id; 
    /**
     * Represents the name of the dish
     */
    private String name;

    /**
     * Represents the group of the dish
     */
    private Group group;

    /**
     * Represents the list of ingredients of the dish
     */
    private List<Ingredient> ingredients;

    /**
     * Constructs a dish with empty fields.
     */
    public Dish() {
    }

    /**
     * Constructs a Dish with full fields definition.
     * @param name name of the dish
     * @param group the group of the dish
     * @param ingredients the list of ingredients that forms the dish
     */  
    public Dish(String name, Group group, List<Ingredient> ingredients) {
        this.name = name;
        this.group = group;
        this.ingredients = ingredients;
    }
    
    /**
     * Constructs a Dish with full fields definition.
     * @param id id of the dish
     * @param name name of the dish
     * @param group the group of the dish
     * @param ingredients the list of ingredients that forms the dish
     */  
    public Dish(long id, String name, Group group, List<Ingredient> ingredients) {
    	this.id = id;
        this.name = name;
        this.group = group;
        this.ingredients = ingredients;
    }
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
     * @return the name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets the name of the dish
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the group of the dish
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group sets the group of the dish
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the list of the ingredients of the dish
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients sets the list of the dish ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Add new ingredient to ingredients list of the dish.
     *
     * @param ingredient ingredient to add to the ingredients lists of the dish
     */
    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            Ingredient existedIngredient = 
                    ingredients.get(ingredients.indexOf(ingredient));
            existedIngredient.setQuantity(existedIngredient.getQuantity()
            		+ ingredient.getQuantity());
        } else {
            ingredients.add(ingredient);
        }
        
    }

    /**
     * Remove ingredient set as parameter from ingredients list of the dish.
     *
     * @param ingredient ingredient to remove from ingredients list of the dish
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }
    
     /**
     * Remove ingredient from dish by ingredient name.
     * @param ingredientName name of ingredient to remove 
     * from ingredients list of the dish
     */
    public void removeIngredient(String ingredientName) {
    	Iterator<Ingredient> iterator = ingredients.iterator();
    	Ingredient tempIngredient;
    	while(iterator.hasNext()){
    		tempIngredient = iterator.next();
    		if (tempIngredient.getProduct().getName().equals(ingredientName)) {
    			iterator.remove();
    			break;
    		}
    	}
    }
 
    /**
     * Calculates the total price of dish. Total price of the dish is a summary
     * of the prices of all ingredients.
     * @see com.javagroup.restaurantmenu.model.restaurant.Ingredient#getPrice()
     * @return total dish price in dollars
     */
    public double getPrice() {
        double result = 0;
        for (Ingredient ingredient : ingredients) {
            result += ingredient.getPrice();
        }
        return result;
    }
 
    /**
     * Returns possibility to prepare dish meaning there are all the needed 
     * ingredients for the dish. Returns <tt>true</tt> if all ingredients 
     * are available.
     * @return <tt>true</tt> if all ingredients are available
     */
    public boolean getAvailable() {
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.getProduct().getAvailable()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation of this Dish. The string
     * representation includes values of such fields: name, price<br>   
     * Example: Soup (24.0) usd
     * @return a string representation of this dish
     */    
    @Override
    public String toString() {
        return name + " (" + getPrice() + ") usd";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result
				+ ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Dish other = (Dish) obj;
		if (group != other.group)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    
    
}
