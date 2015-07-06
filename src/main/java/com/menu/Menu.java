package com.menu;
import org.apache.logging.log4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * Menu class has main main and other methods
 * which make actions with menu class
 @author Dima Khodan
 @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */

public class Menu{
/**
     * Menu name
     */
    private String name;
    /**
     * List with dishes in Menu
     */
    ArrayList<Dish> menuList = new ArrayList();

    /**
     * @param name Setting menu name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return menu name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Empty constructor
     */
    public Menu() {
    }

    /**
     * constructor
     * @param name returns menu name
     */
    public Menu(String name) {
        this.name = name;
    }


    /**
     * Main project class
     * @param args command line parameter
     */
    private static final Logger logger = LogManager.getLogger(Menu.class.getName());

    public static void main(String[] args) {
        System.out.println();
        ProductRepository pr = new ProductRepository();
        DishRepository dr = new DishRepository(pr);
        logger.error("error");
        logger.debug("debug");
        //JsonWritingAndReadingFile json = new JsonWritingAndReadingFile();
       // json.parsingJson(dr.dishList);
       //json.jsonReadingFromFile("fail.json");
        Dish dish=new Dish();
        Database db=new Database();
        db.addDish(dr.getDishByName("Tea"));

        db.closeConnection();



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager();

        em.close();

        emf.close(); //close at application end

    }

    /**
     * Adds new menu
     * @param name menu name
     * @param dishName name of dishes
     * @param dishRep repository with dishes
     * @return ArrayList<Dish> list with dishes
     */
    public static ArrayList<Dish> addMenu(String name, String[] dishName, 
            DishRepository dishRep) {
        ArrayList<Dish> newDishMenu = new ArrayList<Dish>();
        for (String dName : dishName) {
            if (!dishRep.isAvailabilityByName(dName)) continue;
            newDishMenu.add(dishRep.getDishByName(dName));
        }

        System.out.println("New Menu: " + name);
        return newDishMenu;
    }
    /**
     * Collects list of 3 dishes
     * which matches for given price
     * @param price Maximum  complex dinner price
     * @param dishRep A copy of the class DishRepository
     *                with all avilable dishes
     *
     * @return ArrayList<ArrayList<Dish[]>> list
     * if no dish found returns null
     */
    public static ArrayList<Dish[]> complexDinner(double price, DishRepository dishRep) {
        int i;
        boolean flagFound = false;
        Dish[] dishTemp = new Dish[3];
        ArrayList<Dish> cat0 = new ArrayList<Dish>();
        ArrayList<Dish> cat1 = new ArrayList<Dish>();
        ArrayList<Dish> cat2 = new ArrayList<Dish>();
        ArrayList<Dish> dishList = dishRep.getDishes();
        ArrayList<Dish[]> complexDinners = new ArrayList<Dish[]>();
        double tempPrice = 0.0;
        block5 : for (i = 0; i < dishList.size(); ++i) {
            switch (dishList.get(i).getCategory()) {
                case 0: {
                    cat0.add(dishList.get(i));
                    continue block5;
                }
                case 1: {
                    cat1.add(dishList.get(i));
                    continue block5;
                }
                case 2: {
                    cat2.add(dishList.get(i));
                }
            }
        }

        for (i = 0; i < cat0.size(); ++i) {
            tempPrice = (cat0.get(i)).totalCost();
            dishTemp[0]=cat0.get(i);
            for (int j = 0; j < cat1.size(); ++j) {
                tempPrice+=(cat1.get(j)).totalCost();
                dishTemp[1]=cat1.get(j);
                for (int k = 0; k < cat2.size(); ++k) {
                    dishTemp[2]=cat2.get(k);
                    if ((tempPrice+=(cat2.get(k)).totalCost()) <= price) {
                        for (int a = 0; a < dishTemp.length; ++a) {

                            System.out.println(dishTemp[a].getDishName());
                        }

                        System.out.format("Costs %.2f UAH\n", tempPrice);
                        System.out.format("Change %.2f UAH\n\n\n", price - tempPrice);
                        flagFound = true;

                        complexDinners.add(dishTemp.clone());


                    }

                }
            }
        }
        if (!flagFound) {
            System.out.println("Can't satisfy your request");
            return null;
        }
        return complexDinners;
    }
}