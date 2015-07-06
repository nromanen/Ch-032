package engine;

import java.util.ArrayList;
import java.util.Arrays;

public class Meals {
    private static ArrayList<Meal> mealList = new ArrayList<Meal>();

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(ArrayList<Meal> mealList) {
        this.mealList = mealList;
    }

    public String toString() {
        String output = "";
        for (Meal currentMeal : mealList) {
            output += "[" + currentMeal.toString() + "]" + "\n";
        }
        return output;
    }

    public void addMeal (Meal meal)
    {
        this.mealList.add(meal);
    }

    public void initAllMeals(Products allProducts, Categories categories) {
        Meal roastPotatoes = new Meal("Roast potatoe", categories.getCategoryByName("Second"),
                new ArrayList<Ingredient>(Arrays.asList(
                        new Ingredient(500, allProducts.getProductByName("potato")),
                        new Ingredient(100, allProducts.getProductByName("oil")),
                        new Ingredient(50, allProducts.getProductByName("sauce"))
                )));

        Meal soup = new Meal("Soup", categories.getCategoryByName("First"),
                new ArrayList<Ingredient>(Arrays.asList(
                        new Ingredient(1, allProducts.getProductByName("water")),
                        new Ingredient(300, allProducts.getProductByName("carrot")),
                        new Ingredient(100, allProducts.getProductByName("bread"))
                )));
        Meal soup_2 = new Meal("Soup With Tea", categories.getCategoryByName("First"),
                new ArrayList<Ingredient>(Arrays.asList(
                        new Ingredient(1, allProducts.getProductByName("water")),
                        new Ingredient(300, allProducts.getProductByName("carrot")),
                        new Ingredient(100, allProducts.getProductByName("bread")),
                        new Ingredient(30, allProducts.getProductByName("greenTea"))
                )));
        Meal green_tea_meal = new Meal("Green Tea", categories.getCategoryByName("Third"),
                new ArrayList<Ingredient>(Arrays.asList(
                        new Ingredient(500, allProducts.getProductByName("water")),
                        new Ingredient(30, allProducts.getProductByName("greenTea"))
                )));
        Meal black_tea_meal = new Meal("Black Tea", categories.getCategoryByName("Third"),
                new ArrayList<Ingredient>(Arrays.asList(
                        new Ingredient(500, allProducts.getProductByName("water")),
                        new Ingredient(30, allProducts.getProductByName("greenTea"))
                )));
        this.addMeal(roastPotatoes);
        this.addMeal(soup);
        this.addMeal(soup_2);
        this.addMeal(green_tea_meal);
        this.addMeal(black_tea_meal);
    }
}
