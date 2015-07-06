package engine;

import crud.DBReaderWriter;
import crud.JSONReaderWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

    private String name;
    private ArrayList<Meal> mealList = new ArrayList<Meal>();
    private static DBReaderWriter dbReaderWriter = new DBReaderWriter();
    // private static JSONReaderWriter jsonReaderWriter = new JSONReaderWriter();


    public void addMeal(Meal meal) {
        mealList.add(meal);
    }

    /**
     * @param neededCategory represents a needed category
     * @return returns a boolean value
     * @author Luchyn Pavlo
     */
    public boolean isAvailableByCategory(Category neededCategory) {
        int counter = 0;
        for (int i = 0; i < mealList.size(); i++) {
            if (mealList.get(i).getCategory().equals(neededCategory)) {
                if (mealList.get(i).isAvailability()) {
                    counter++;
                }
            }
        }
        if (counter > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param neededCategory represents a needed category
     * @author Luchyn Pavlo
     */
    public String getTheMostExpensiveMealByCategory(Category neededCategory) {
        boolean flagForChanging = false;
        System.out.println("Getting the most expensive " + neededCategory.toString() + " meal");
        double tempForPrice = 0;
        int tempForIndex = 0;
        if (isAvailableByCategory(neededCategory)) {
            for (int i = 0; i < mealList.size(); i++) {
                if (mealList.get(i).getCategory().equals(neededCategory)) {
                    if (mealList.get(i).getPrice() > tempForPrice) {
                        flagForChanging = true;
                        tempForPrice = mealList.get(i).getPrice();
                        tempForIndex = i;
                    }
                }
            }
        }
        if (flagForChanging) {
            return mealList.get(tempForIndex).toString();
        } else {
            return "No dish available now";
        }

    }

    /**
     * @param price represents a price limit for choosing complex meal
     * @author Luchyn Pavlo
     */
    public ArrayList getComplexMealsForPrice(double price, Category first, Category second, Category third, ComplexMeals complexMeals) {
        for (Meal currentMeal : mealList) {
            if (currentMeal.isAvailability()) {
                if (currentMeal.getCategory().equals(first)) { //getting first meal
                    for (Meal currentMeal2 : mealList) {
                        if (currentMeal2.getCategory().equals(second)) { //getting second meal
                            for (Meal currentMeal3 : mealList) {
                                if (currentMeal3.getCategory().equals(third)) { //getting third meal
                                    if (currentMeal.getPrice() + currentMeal2.getPrice() + currentMeal3.getPrice() <= price) {
                                        complexMeals.addComplexMeal(new ComplexMeal(currentMeal, currentMeal2, currentMeal3));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return complexMeals.getComplexMealList();
    }

   public static void main(String[] args) {
        //working with JSON
//        Menu MealMenu = new Menu("Main menu");
//        Menus allMenus = new Menus();
//        allMenus.addMenu(MealMenu);
//        Categories allCategories = new Categories();
//        allCategories.initCategories();
//        Products allProducts = new Products();
//        allProducts.initProducts();
//        Meals allMeals = new Meals();
//        MealMenu.initAllMeals(allProducts, allCategories, allMeals);
//        ComplexMeals complexMeals = new ComplexMeals();
//        MealMenu.getComplexMealsForPrice(53.53, allCategories.getCategoryByName("first"),
//                allCategories.getCategoryByName("second"),
//                allCategories.getCategoryByName("third"),
//                complexMeals);
//        jsonReaderWriter.WriteAllToFile(allProducts, allCategories, allMeals, complexMeals, allMenus);
//        jsonReaderWriter.ReadAllFromFile(allProducts, allCategories, allMeals, complexMeals, allMenus);

        //working with database
        Categories allCategories = new Categories();
        allCategories.initCategories();
        Menu MealMenu = new Menu("Main menu");
        Menus allMenus = new Menus();
        allMenus.addMenu(MealMenu);
        Meals allMeals = new Meals();

        dbReaderWriter.CreateAllTables();
        Products allProducts = new Products();
        dbReaderWriter.initProducts(allProducts);
        allProducts.getAllProducts().clear();
        DBReaderWriter.readAllProductsFromDB(allProducts);
//        DBReaderWriter.deleteProduct("carrot");
       DBReaderWriter.initMeals(allMeals,allProducts,allCategories);

    }

    public Menu() {
        this.name = "";
        this.mealList.clear();
    }

    public Menu(ArrayList<Meal> mealList) {
        this.mealList = mealList;
    }

    public Menu(String menuName) {
        this.name = menuName;
    }

    /**
     * @return the productList
     */
    public ArrayList<Meal> getmealList() {
        return mealList;
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

} 
