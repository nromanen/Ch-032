package com.javagroup.restaurantmenu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {
	
	public void CreateDatabaseTables(Connection connection){
		final String CREATE_PRODUCT = 
				"CREATE TABLE product " 
				+ "(id BIGINT NOT NULL AUTO_INCREMENT (1) PRIMARY KEY," 
				+ "name CHAR(30)," 
				+ "price DOUBLE,"
				+ "available BOOLEAN);";
		final String CREATE_DISH =
				"CREATE TABLE dish"
				+ "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "name CHAR(30),"
				+ "dish_group CHAR(30));";
		final String CREATE_DISH_CONTENT =
				"CREATE TABLE dish_content"
				+ "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "dish_id BIGINT,"
				+ "ingredient_index BIGINT,"
				+ "product_id BIGINT,"
				+ "quantity INT,"
				+ "FOREIGN KEY (dish_id) REFERENCES dish(id),"
				+ "FOREIGN KEY (product_id) REFERENCES product(id));";
		try {
			Statement statement = connection.createStatement();
			statement.execute(CREATE_PRODUCT);
			statement.execute(CREATE_DISH);
			statement.execute(CREATE_DISH_CONTENT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
