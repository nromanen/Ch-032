package com.javagroup.restaurantmenu.model;

import java.util.ArrayList;
import java.util.List;

import com.javagroup.restaurantmenu.dao.memory.DishDAOMemory;

// import java.util.*;

/**
 * Generates list of complexes, price which is less than specified.
 *
 * @author Volodymyr Perepeliuk <vovan4uk86@gmail.com>
 * @version 1.0
 */
public class Menu {
    /**
     * Constructs an empty menu.
     */
    public Menu() {
    }

    /**
     * Generates list of complexes where price is less than specified.
     *
     * @param price sets the maximum allowable price of complex
     * @param dishDAO an object that allows to obtain list of dishes using 
     * different separation criteria
     * @return list of complexes where price is less than specified
     */
    public List<Complex> getComplexListByPriceLimit(double price, 
    		DishDAOMemory dishDAO) {

        List<Complex> complexList = new ArrayList<Complex>();

        // Creates three lists of dishes by different categories
        List<Dish> firstList = dishDAO
        		.getAllAvailableDishesByGroup(Group.FIRST);
        List<Dish> secondList = dishDAO
        		.getAllAvailableDishesByGroup(Group.SECOND);
        List<Dish> drinkList = dishDAO
        		.getAllAvailableDishesByGroup(Group.DRINK);

        /*  Generate all possible variants of complexes, the where price is less
            than specified in the parameters */
        int i = 1;
        for (Dish firstDish : firstList) {
            for (Dish secondDish : secondList) {
                for (Dish drink : drinkList) {
                    if ((firstDish.getPrice() 
                    		+ secondDish.getPrice() 
                    		+ drink.getPrice()) <= price) {
                        complexList.add(
                        		new Complex("complex " + Integer.toString(i), 
                        				firstDish, secondDish, drink));
                        i++;
                    }
                }
            }
        }
        return complexList;
    }

    /**
     * Generates list of all complexes.
     * @param dishDAO an object that allows to obtain list of dishes using 
     * different separation criteria
     * @return list of all complexes
     */
    public List<Complex> getAllComplexes(DishDAOMemory dishDAO) {

        List<Complex> complexList = new ArrayList<Complex>();

        // Creates three lists of dishes by different categories
        List<Dish> firstList = dishDAO.getAllDishesByGroup(Group.FIRST);
        List<Dish> secondList = dishDAO.getAllDishesByGroup(Group.SECOND);
        List<Dish> drinkList = dishDAO.getAllDishesByGroup(Group.DRINK);

        /*
         * Generate all possible variants of complexes
         */
        int i = 1;
        for (Dish firstDish : firstList) {
            for (Dish secondDish : secondList) {
                for (Dish drink : drinkList) {
                    complexList.add(new Complex("complex " 
                    		+ Integer.toString(i), 
                    			firstDish, secondDish, drink));
                    i++;
                }
            }
        }
        return complexList;
    }
}
