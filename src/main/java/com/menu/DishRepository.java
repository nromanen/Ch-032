package com.menu;
/**
 * DishRepository created to store
 * all essence of Dish
 * 
 * @author Dima Khodan
 * @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DishRepository {
    /**
     * dishList basic list where store
     * all essence of dishes
     */
    public ArrayList<Dish> dishList = new ArrayList();


    /**
     * Constructor comprising all repository essence
     *
     */
    public DishRepository(){}
    /**
     * Constructor comprising all repository essence
     * @param  prod
     */
    public DishRepository(ProductRepository prod) {
        HashMap productsListMap = new HashMap<Product, Double>();
        productsListMap.put((Product)prod.addProduct("Carrot"), 1.1);
        productsListMap.put((Product)prod.addProduct("Tomato"), 2.2);
        productsListMap.put((Product)prod.addProduct("Potato"), 2.2);
        productsListMap.put((Product)prod.addProduct("Water"), 2.2);
        productsListMap.put((Product)prod.addProduct("Onion"), 2.2);
        productsListMap.put((Product)prod.addProduct("Beet"), 2.2);
        productsListMap.put((Product)prod.addProduct("Salt"), 2.2);
        this.dishList.add(new Dish("Borsh", 1, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Carrot"), 2.2);
        productsListMap.put(prod.addProduct("Potato"), 2.2);
        productsListMap.put(prod.addProduct("Water"), 2.2);
        productsListMap.put(prod.addProduct("Onion"), 2.2);
        productsListMap.put(prod.addProduct("Meat"), 2.2);
        productsListMap.put(prod.addProduct("Salt"), 2.2);
        this.dishList.add(new Dish("Soup", 1, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Meat"), 2.2);
        productsListMap.put(prod.addProduct("Becon"), 2.2);
        productsListMap.put(prod.addProduct("Tomato"), 2.2);
        productsListMap.put(prod.addProduct("Sour"), 2.2);
        productsListMap.put(prod.addProduct("Cheese"), 2.2);
        productsListMap.put(prod.addProduct("Spice"), 2.2);
        this.dishList.add(new Dish("Pizza", 2, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Meat"), 2.2);
        productsListMap.put(prod.addProduct("Potato"), 2.2);
        productsListMap.put(prod.addProduct("Sour"), 2.2);
        productsListMap.put(prod.addProduct("Salt"), 2.2);
        this.dishList.add(new Dish("PotatoWithMeat", 2, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Meat"), 2.2);
        productsListMap.put(prod.addProduct("Beet"), 2.2);
        productsListMap.put(prod.addProduct("Tomato"), 2.2);
        productsListMap.put(prod.addProduct("Salt"), 2.2);
        this.dishList.add(new Dish("Salat", 2, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Water"), 0.1);
        productsListMap.put(prod.addProduct("TeaLeaves"), 0.1);
        productsListMap.put(prod.addProduct("Sugar"), 0.004);
        this.dishList.add(new Dish("Tea", 0, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("CoffeBeans"), 2.2);
        productsListMap.put(prod.addProduct("Water"), 2.2);
        productsListMap.put(prod.addProduct("Sugar"), 2.2);
        this.dishList.add(new Dish("Coffe", 0, productsListMap));
        productsListMap = new HashMap();
        productsListMap.put(prod.addProduct("Fruits"), 0.5);
        productsListMap.put(prod.addProduct("Sugar"), 0.004);
        this.dishList.add(new Dish("Juice", 0, productsListMap));
    }

    /**
     * Gets dishes list
     * @return dishList
     */
    public ArrayList<Dish> getDishes() {
        return this.dishList;
    }

    /**
     * Method adds new dish to repository by reference
     * @param dish 
     */
    public void addDish(Dish dish) {
        this.dishList.add(dish);
    }
    
    /**
     * Method adds new dish to repository
     * @param name
     * @param category
     * @param dishProducts 
     */
    public void addDish(String name, int category, 
            HashMap<Product, Double> dishProducts) {
        this.dishList.add(new Dish(name, category, dishProducts));
    }

    /**
     * Method delete dish by name from repository
     * @param name 
     */
    public void deleteDishByName(String name) {
        Iterator<Dish> iter = this.dishList.iterator();
        while (iter.hasNext()) {
            if (!iter.next().getDishName().equals(name)) continue;
            iter.remove();
        }
    }

    /**
     * Method delete dish by reference from repository
     * @param dish 
     */
    public void deleteDish(Dish dish) {
        this.dishList.remove(dish);
    }

    /**
     * Method checks available dish in list
     * @param name
     * @return true if available 
     */
    public boolean isAvailabilityByName(String name) {
        for (Dish dish : this.dishList) {
            if (!dish.getDishName().equals(name)) continue;
            return true;
        }
        return false;
    }

    /**
     * Methods gets dish from repository by name
     * @param name
     * @return reference to dish
     * @throws DishNotFoundException 
     */
    public Dish getDishByName(String name) throws DishNotFoundException {
        for (Dish dish : this.dishList) {
            if (!dish.getDishName().equals(name)) continue;
            return dish;
        }
        throw new DishNotFoundException(name);
    }
}