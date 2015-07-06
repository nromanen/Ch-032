package engine;

import java.util.ArrayList;

/**
 *
 * @author Администратор
 */
public class Meal {

    private int id;
    private String name;
    private Category categoryOfMeal;
    private double price;
    private double cost;
    private boolean availability;
    private ArrayList<Ingredient> productList = new ArrayList<Ingredient>();

    public Meal() {
        name = "";
        categoryOfMeal = null;
        price = 0.0;
        cost = 0.0;
        availability = false;
        productList.clear();
    }

    public Meal(String name, Category categoryName, ArrayList<Ingredient> productList) {
        double coefficientForPrice = 0.01;
        int tempForCost = 0;
        this.availability = true;
        this.name = name;
        this.categoryOfMeal = categoryName;
        this.productList = productList;
        for (Ingredient productPortion : productList) {
            if (!productPortion.isAvailable()) {
                this.setAvailability(false);
            }
            tempForCost += productPortion.getPriceForPortion();
            cost = tempForCost;
            price = cost + cost * coefficientForPrice;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return this.categoryOfMeal;
    }

    /**
     * @param categoryOfMeal the category to set
     */
    public void setCategory(Category categoryOfMeal) {
        this.categoryOfMeal=categoryOfMeal;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the productList
     */
    public ArrayList<Ingredient> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(ArrayList<Ingredient> productList) {
        this.productList = productList;
    }

    /**
     * @return the availability
     */
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability (boolean availability)
    {
        this.availability=availability;
    }

    @Override
    public String toString() {
        return "engine.Category: " + this.getCategory().toString() + " " + ", Name: " + this.name + " " + ", Price = " + this.price + "UAH";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
