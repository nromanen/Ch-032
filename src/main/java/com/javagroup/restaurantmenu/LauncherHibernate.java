package com.javagroup.restaurantmenu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Transaction;

import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.dao.hibernate.DishDAOHibernate;
import com.javagroup.restaurantmenu.dao.hibernate.HibernateUtil;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;
import com.javagroup.restaurantmenu.util.DataBaseCreator;
import com.javagroup.restaurantmenu.util.Dishes;

public class LauncherHibernate {
		
	private static final Logger logger = LogManager.getLogger(LauncherHibernate.class);

	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("org.jboss.logging.provider", "slf4j");

		logger.info("application is started...");
		H2DBServer h2dbServer = new H2DBServer();
		Connection connection = h2dbServer.getConnection();
		new DataBaseCreator().CreateDatabaseTables(connection);
		DishDAO dishDAO = new DishDAOHibernate();
		
		// create and persist dish
		Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Product product1 = new Product("honey", 5, true);
		Product product2 = new Product("vodka", 15, true);
		List<Ingredient> ingredients1 = new ArrayList<>();
		ingredients1.add(new Ingredient(product1, 100));
		ingredients1.add(new Ingredient(product2, 10));
		Dish dish1 = new Dish("medovuha", Group.DRINK, ingredients1);
		dishDAO.makePersistent(dish1);
		long id1 = dish1.getId();
		System.out.println("\nCreated and persisted dish:");
		System.out.println(dish1);
		tx.commit();
		
		// find dish by id
		Transaction tx3 = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Dish dish2 = dishDAO.findById(id1);
		// transform dish with lazy field initialization into DTO object loaded fields
		Mapper mapper = new DozerBeanMapper();
		Dish dishDTO = mapper.map(dish2, Dish.class);
		System.out.println("\nDish Found by id (printed under hibernate session):");
		System.out.println(dish2);
		tx3.commit();

		System.out.println("\nDish (DTO) printed without hibernate session):");
		System.out.println(dishDTO);
		System.out.println(dishDTO.getIngredients());

		// Serializing DTO dish into JSON
		List<Dish> dishes = new ArrayList<>();
		dishes.add(dishDTO);
		String dishesAsJSON = Dishes.asJson(dishes);
		
		// De-Serializing dish from JSON
		Dish dish3 = Dishes.asList(dishesAsJSON).get(0);
		System.out.println("\nSerialized and desirealized dish:");
		System.out.println(dish3);
		System.out.println(dish3.getIngredients());

		// create and persist another dish into DB 
		Transaction tx4 = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Product product6 = new Product("green tee", 6, true);
		Product product7 = new Product("mint", 7, true);
		List<Ingredient> ingredients3 = new ArrayList<>();
		ingredients3.add(new Ingredient(product6, 100));
		ingredients3.add(new Ingredient(product7, 10));
		Dish dish4 = new Dish("green mint tea", Group.DRINK, ingredients3);
		dishDAO.makePersistent(dish4);
		long id4 = dish4.getId();
		System.out.println("\nCreate and persist another dish into DB:");
		System.out.println(dish4);
		System.out.println(dish4.getIngredients());
		tx4.commit();

		System.out.println("\nFound all the dishes:");
		Transaction tx5 = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		System.out.println(dishDAO.findAll());
		tx5.commit();

		System.out.println("\nMake transient one and find all the dishes again:");
		Transaction tx6 = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		dishDAO.makeTransient(dishDAO.findById(id4));
		System.out.println(dishDAO.findAll());
		tx6.commit();

		HibernateUtil.shutdown();

		System.out.println("\nPress [Enter] to stop.");
		System.in.read();
		h2dbServer.closeServer();
		logger.info("application is correctly stopped...");
	}
}