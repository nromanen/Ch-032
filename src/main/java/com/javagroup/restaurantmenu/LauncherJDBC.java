package com.javagroup.restaurantmenu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.dao.ProductDAO;
import com.javagroup.restaurantmenu.dao.jdbc.DBException;
import com.javagroup.restaurantmenu.dao.jdbc.DishDAOJDBC;
import com.javagroup.restaurantmenu.dao.jdbc.ProductDAOJDBC;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;
import com.javagroup.restaurantmenu.util.DataBaseCreator;

public class LauncherJDBC {
	
	private static final Logger logger = LogManager.getLogger(LauncherJDBC.class);

	public static void main(String[] args) throws Exception {
		
		logger.info("application is started...");
		H2DBServer h2dbServer = new H2DBServer();
		Connection connection = h2dbServer.getConnection();
		new DataBaseCreator().CreateDatabaseTables(connection);
		ProductDAO productDAO = new ProductDAOJDBC(connection);

		System.out.println("\n-- creating product...");
		Product product = new Product("sugar", 5, true);
		System.out.println("created product: " + product);
		
		System.out.println("\n-- persisting product...");
		productDAO.makePersistent(product);

		System.out.println("\n-- loading product...");
		product = productDAO.findById(1);
		System.out.println("found product: " + product);

		System.out.println("\n-- changing product...");
		product.setName("water");
		System.out.println("changed product: " + product);
		
		System.out.println("\n-- persisting product again...");
		productDAO.makePersistent(product);

		System.out.println("\n-- loading product...");
		product = productDAO.findById(product.getId());
		System.out.println("found product: " + product);
		
		System.out.println("\n-- deleting product...");
		productDAO.makeTransient(product);

		System.out.println("\n-- loading product...");
		try {
			product = productDAO.findById(1);
		} catch (DBException e) {
			System.out.println(e.getErrorMessage());
		}
		
		System.out.println("\n-- creating product...");
		Product product1 = new Product("tea", 5, true);
		Product product2 = new Product("salt", 15, true);
		System.out.println("created products: " + product1 +", " + product2);

		System.out.println("\n-- persisting products...");
		productDAO.makePersistent(product1);
		productDAO.makePersistent(product2);
		
		List<Product> products = productDAO.findAll();
		System.out.println("\n-- loaded products...");
		System.out.println(products);
		
		product1.setId(2);
		product2.setId(3);
		System.out.println("-----------------------");
		System.out.println("\n-- creating and persisting first dish...");
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient(product1, 100));
		ingredients.add(new Ingredient(product2, 10));
		Dish dish = new Dish("solted tea", Group.DRINK, ingredients);
		DishDAO dishDAO = new DishDAOJDBC(connection);
		dishDAO.makePersistent(dish);

		System.out.println("\n-- creating and persisting second dish...");
		dish.setId(0);
		dish.setName("juice");
		ingredients.clear();
		ingredients.add(new Ingredient(product1, 400));
		ingredients.add(new Ingredient(product2, 40));
		dish.setIngredients(ingredients);
		dishDAO.makePersistent(dish);
		
		List<Dish> dishes = dishDAO.findAll();
		System.out.println("\nAll the dishes:");
		System.out.println(dishes);
		
		System.out.println("\nDelete one dish then load all the dishes:");
		dishDAO.makeTransient(dish);
		dishes = dishDAO.findAll();
		System.out.println(dishes);		

		System.out.println("\nPress [Enter] to stop.");
        System.in.read();
		h2dbServer.closeServer();
		logger.info("application is correctly stopped...");
    }

}
