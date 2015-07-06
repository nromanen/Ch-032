package com.javagroup.restaurantmenu.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.model.Group;
import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class DishDAOJDBC implements DishDAO {
	private static final Logger logger = LogManager.getLogger(DishDAOJDBC.class);
	private Connection connection; 
	
	public DishDAOJDBC(Connection connection){
		this.connection = connection;
	}

	@Override
	public void makePersistent(Dish dish) {
		long dishId = 0;
		
		if (dish.getId() == 0) {
			final String CREATE_DISH = "INSERT INTO dish (name, dish_group) VALUES (?, ?);";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(CREATE_DISH,
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, dish.getName());
				statement.setString(2, dish.getGroup().toString());
				statement.executeUpdate();
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				dishId = keys.getLong(1);
				dish.setId(dishId);
			} catch (SQLException e) {
				logger.error("can't persist (create) dish (name=" 
						+ dish.getName() 
						+ ") due to DB problems", e);
			}
		} else {
			final String UPDATE_DISH = "UPDATE dish SET name=?, dish_group=? WHERE id=?";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(UPDATE_DISH);
				statement.setString(1, dish.getName());
				statement.setString(2, dish.getGroup().toString());
				statement.setLong(3, dish.getId());
				statement.executeUpdate();
				dishId = dish.getId();
			} catch (SQLException e) {
				logger.error("can't persist (update) dish (id=" 
						+ dish.getId() 
						+ ") due to DB problems", e);
			}
			deleteDishContent(dishId);
		}
		
		persistDishContent(dish, dishId);
		logger.info("dish (id=" + dishId + ") persisted");

	}

	private void persistDishContent(Dish dish, long dishId) {
		PreparedStatement statement;
		final String CREATE_DISH_CONTENT = "INSERT INTO dish_content (dish_id, product_id, quantity) VALUES (?, ?, ?);";
		for (Ingredient ingredient : dish.getIngredients()){
			try {
				statement = connection.prepareStatement(CREATE_DISH_CONTENT);
				statement.setLong(1, dishId);
				statement.setLong(2, ingredient.getProduct().getId());
				statement.setInt(3, ingredient.getQuantity());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteDishContent(long dishId) {
		PreparedStatement statement;
		final String DELETE_DISH_CONTENT = "DELETE from dish_content WHERE dish_id=?";
			try {
				statement = connection.prepareStatement(DELETE_DISH_CONTENT);
				statement.setLong(1, dishId);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void makeTransient(Dish dish) {
		if (dish.getId() == 0) {
			return;
		} else {
			deleteDishContent(dish.getId());
			final String DELETE_DISH = "DELETE FROM dish WHERE id=?";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(DELETE_DISH);
				statement.setLong(1, dish.getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
 	public List<Dish> findAll() {
		final String GET_DISH_IDS = "SELECT id FROM dish;";
		List<Dish> dishes = new ArrayList<Dish>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		long dishId = 0L;
		try {
			statement = connection.prepareStatement(GET_DISH_IDS);
			rs = statement.executeQuery();
			while(rs.next()){
				dishId = rs.getLong("id");
				dishes.add(findById(dishId));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dishes;
	}

	@Override
	public Dish findById(long id) {
		final String GET_DISH = "SELECT * FROM dish where id=?;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		String name = null;
		Group group = null;
		List<Ingredient> ingredients = null; 
		try {
			statement = connection.prepareStatement(GET_DISH);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
				group = Group.valueOf(rs.getString("dish_group"));
			} else {
				throw new DBException("dish is not found", null);	
			}
			ingredients = getDishContent(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Dish(id, name, group, ingredients);
	}

	private List<Ingredient> getDishContent(long id) {
		final String GET_DISH_CONTENT = "SELECT dc.quantity, p.id, p.name, "
				+ "p.price, p.available FROM dish_content AS dc, product AS p "
				+ "WHERE dc.dish_id=? and dc.product_id=p.id;";
		List<Ingredient> ingredients = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Long productId = 0L;
		String productName = null;
		double productPrice = 0;
		boolean productAvailable = false;
		int quantity = 0;
		try {
			statement = connection.prepareStatement(GET_DISH_CONTENT);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while(rs.next()) {
				productId = rs.getLong("id");
				productName = rs.getString("name");
				productPrice = rs.getDouble("price");
				productAvailable = rs.getBoolean("available");
				quantity = rs.getInt("quantity");
				ingredients.add(new Ingredient(new Product(productId,
						productName, productPrice, productAvailable), 
						quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
	// 

}
