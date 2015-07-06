package com.menu;
/**
 * Class Dish created to serve
 * all actions which connected with dish
 * entities
 * Dish class has Product class copy inside
 *
 * @author Dima Khodan
 * @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
@Entity
@Table(name= "Dish")
public class Dish {
    public HashMap<Product, Double> getMapDishProductsList() {
        return mapDishProductsList;
    }

    /**
     * Map with all dish components(Product class) and it's weights
     */
    public HashMap<Product, Double> mapDishProductsList;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Dish name
     */
    private String dishName;
    /**
     * Dish category
     * May be
     * 0: Drinks
     * 1: 1-st dishes
     * 2: 2-d dishes
     */
    private int category;

    /**
     * Empty constructor
     */
    public Dish() {
    }

    public void setMapDishProductsList(HashMap<Product, Double> mapDishProductsList) {
        this.mapDishProductsList = mapDishProductsList;
    }

    /**
     * Constructor
     * @param dishName sets Dish name
     * @param cat sets Dish category
     * @param map Map with Dish components
     */
    public Dish(String dishName, int cat, HashMap<Product, Double> map) {
        this.dishName = dishName;
        this.category = cat;
        this.mapDishProductsList = map;

    }

    /**
     * Sets Dish name
     * @param name dish name
     */
    public void setDishName(String name) {
        this.dishName = name;
    }

    /**
     * Gets dish name
     * @return dish name
     */
    public String getDishName() {
        return this.dishName;
    }

    /**
     * Sets dish category
     * @param cat dish category
     */
    public void setCategory(int cat) {
        this.category = cat;
    }

    /**
     * Gets dish category
     * @return dish category
     */
    public int getCategory() {
        return this.category;
    }
    /**
    * Prints all dishes from menu
    * with them weights
     */
    public void getAll() {
        for (Map.Entry<Product, Double> entry : 
                this.mapDishProductsList.entrySet()) {
            System.out.println("Product: " + entry.getKey().getName() 
                    + ". Weight: " + entry.getValue());
        }
        System.out.println();
    }

    /**
     * Returns Dish prime cost
     * @return prime cost
     */
    public double totalCost() {
        double result = 0.0;
        for (Map.Entry<Product, Double> entry : 
                this.mapDishProductsList.entrySet()) {
            result+=entry.getKey().getCost() + entry.getValue();
        }
        return result;
    }

    /**
     * Override equal method used to compare Dish instances
     * @param anotherDish Dish class instance
     * @return boolean false if not equal otherwise true
     */
    public boolean equals(Object anotherDish) {
        if (!(anotherDish != null && anotherDish instanceof Dish)) {
            return false;
        }
        Dish another = (Dish)anotherDish;
        return ((Dish)anotherDish).getDishName().equals(this.getDishName());
    }
    /**
     * Override toString method
     * @return Dish string name
     */
    public String toString() {
        return this.dishName;
    }
}