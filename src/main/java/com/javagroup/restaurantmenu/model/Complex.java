package com.javagroup.restaurantmenu.model;


/**
 * Entity that contains name of complex (set of dishes) and 
 * three dishes - one for each group of dishes (FIRST, SECOND, DRINKS).
 *
 * <p>It also calculates the total price of complex.
 *
 * @author Volodymyr Perepeliuk <vovan4uk86@gmail.com>
 * @version 1.0
 */
public class Complex {
    /**
     * Name of the complex.
     */
    private String name;

    /**
     * Object to store dish for first group.
     */
    private Dish firstDish;

    /**
     * Object to store dish for second group.
     */
    private Dish secondDish;

    /**
     * Object to store dish for drink group.
     */
    private Dish drink;

    /**
     * Constructs a complex with empty fields.
     */
    public Complex() {
    }

    /**
     * Constructs a complex (set of dishes) with three dishes - one for 
     * each group of dishes (FIRST, SECOND, DRINKS).
     */
    public Complex(String name, Dish firstDish, Dish secondDish, Dish drink) {
        this.name = name;
        this.firstDish = firstDish;
        this.secondDish = secondDish;
        this.drink = drink;
    }

    /**
     * @return the name of the complex
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets the name of the complex
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the object that represents dish for the first group
     */
    public Dish getFirstDish() {
        return firstDish;
    }

    /**
     * @param firstDish sets the object that represents dish for the first group
     */
    public void setFirstDish(Dish firstDish) {
        this.firstDish = firstDish;
    }

    /**
     * @return the object that represents dish for the second group
     */
    public Dish getSecondDish() {
        return secondDish;
    }

    /**
     * @param secondDish sets the object that represents dish 
     * for the second group
     */
    public void setSecondDish(Dish secondDish) {
        this.secondDish = secondDish;
    }

    /**
     * @return the object that represents dish for the drink group
     */
    public Dish getDrink() {
        return drink;
    }

    /**
     * @param drink sets the object that represents dish for the drink group
     */
    public void setDrink(Dish drink) {
        this.drink = drink;
    }

    /**
     * Calculates the total price of complex. Total price is the summary of 
     * the prices of all dishes.
     * @return total complex price
     */
    public double getPrice() {
        return firstDish.getPrice() + secondDish.getPrice() + drink.getPrice();
    }

     /**
     * Returns a string representation of this complex. The string
     * representation includes values of such fields: name, 
     * dish of the first group, dish of the second group, dish of the drink 
     * group, total price<br>   
     * Example: Complex name: Complex1 <br>
     * firstDish=Borsch (20.0) usd <br>
     * secondDish=Bliny (10.0) usd <br>
     * drink=Tea (4.0) usd <br>
     * total=34.0 usd
     * @return a string representation of this complex
     */
    @Override
    public String toString() {
        return "\n\nComplex name: " + name 
                + "\n firstDish=" + firstDish    
                + "\n secondDish=" + secondDish 
                + "\n drink=" + drink 
                + "\n total=" + getPrice() + " usd";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drink == null) ? 0 : drink.hashCode());
		result = prime * result
				+ ((firstDish == null) ? 0 : firstDish.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((secondDish == null) ? 0 : secondDish.hashCode());
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
		Complex other = (Complex) obj;
		if (drink == null) {
			if (other.drink != null)
				return false;
		} else if (!drink.equals(other.drink))
			return false;
		if (firstDish == null) {
			if (other.firstDish != null)
				return false;
		} else if (!firstDish.equals(other.firstDish))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (secondDish == null) {
			if (other.secondDish != null)
				return false;
		} else if (!secondDish.equals(other.secondDish))
			return false;
		return true;
	}
    
}
