package com.javagroup.restaurantmenu.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javagroup.restaurantmenu.dao.ProductDAO;
import com.javagroup.restaurantmenu.model.Product;

public class ProductDAOJDBC implements ProductDAO {

	private Connection connection; 
	
	public ProductDAOJDBC(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public void makePersistent(Product product) {
		if (product.getId() == 0){
			final String CREATE_PRODUCT = "INSERT INTO product (name, price, available) VALUES (?, ?, ?);";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(CREATE_PRODUCT);
				statement.setString(1, product.getName());
				statement.setDouble(2, product.getPrice());
				statement.setBoolean(3, product.getAvailable());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			final String UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, available=? WHERE id=?;";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(UPDATE_PRODUCT);
				statement.setString(1, product.getName());
				statement.setDouble(2, product.getPrice());
				statement.setBoolean(3, product.getAvailable());
				statement.setLong(4, product.getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Product findById(long id) {
		final String GET_PRODUCT = "SELECT * FROM product where id=?;";
		PreparedStatement statement = null;
		ResultSet rs = null;
		String name = null;
		double price = 0;
		boolean available = false;
		try {
			statement = connection.prepareStatement(GET_PRODUCT);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
				price = rs.getDouble("price");
				available = rs.getBoolean("available");
			} else {
				throw new DBException("product is not found", null);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Product(id, name, price, available);
	}

	@Override
	public void makeTransient(Product product) {
		final String DELETE_PRODUCT = "DELETE FROM product where id=?;";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(DELETE_PRODUCT);
			statement.setLong(1, product.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> findAll() {
		final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		String name = null;
		double price = 0;
		boolean available = false;
		List<Product> products = new ArrayList<Product>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(GET_ALL_PRODUCTS);
			while(rs.next()){
				id = rs.getLong("id");
				name = rs.getString("name");
				price = rs.getDouble("price");
				available = rs.getBoolean("available");
				products.add(new Product(id, name, price, available));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}	
}
