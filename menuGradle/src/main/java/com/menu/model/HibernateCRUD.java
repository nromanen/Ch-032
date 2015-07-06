package com.menu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateCRUD {

	public static final String password = "root";
	public static final String login = "root";
	public static final String path = "jdbc:mysql://localhost:3306/";
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String DBName = "mydatabase";
	public static final String fullConnection = path.concat(DBName);

	private static EntityManager em;

	public void initEntityManager() {
		em = Persistence.createEntityManagerFactory("persistence")
				.createEntityManager();
	}

	public String addProduct(Product product) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(product);
		transaction.commit();
		return "Product has been saved";
	}

	public void updateProduct(Product product, String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class)
				.setParameter("title", name).getSingleResult();
		product.setId(pr.getId());
		em.merge(product);
		transaction.commit();
	}

	public String getAllProducts() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		// This Product must be class name not table name
		String query = "SELECT p FROM Product p";
		Product.productList = em.createQuery(query, Product.class)
				.getResultList();
		return Product.productList.toString();
	}

	public Product readProduct(String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		// This Product must be class name not table name
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class)
				.setParameter("title", name).getSingleResult();
		return pr;
	}

	public void deleteProductByName(String name) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		String query = "SELECT p FROM Product p WHERE p.title=:title";
		Product pr = em.createQuery(query, Product.class)
				.setParameter("title", name).getSingleResult();
		em.remove(pr);
		transaction.commit();
	}

	private static Connection setConnectionToServer(String path) {
		Connection dbConnection = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(path, login, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public boolean CreateDatabaseAndSetConnection(String DBName) {
		boolean flag = false;
		Connection dbConnection = null;
		try {
			dbConnection = this.setConnectionToServer(HibernateCRUD.path);
			String DBCreationQuery = "CREATE DATABASE IF NOT EXISTS "
					+ HibernateCRUD.DBName;
			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(DBCreationQuery);
			preparedStatement.executeUpdate();
			flag = true;
			preparedStatement.close();
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	private boolean CreateProductsTable() {
		boolean flag = false;
		Connection dbConnection = null;
		try {
			dbConnection = this
					.setConnectionToServer(HibernateCRUD.fullConnection);
			String DBCreationQuery = "CREATE TABLE IF NOT EXISTS products"
					+ "  (productID INT AUTO_INCREMENT NOT NULL, "
					+ "  productName VARCHAR(45) NOT NULL, "
					+ "  priceForUnit DOUBLE NOT NULL, "
					+ "  unit INT NOT NULL, " + "  available BIT(1) NOT NULL, "
					+ "  PRIMARY KEY (productID)  );";

			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(DBCreationQuery);
			preparedStatement.executeUpdate();
			flag = true;
			preparedStatement.close();
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	private boolean CreateMealsTable() {
		boolean flag = false;
		Connection dbConnection = null;
		try {
			dbConnection = this
					.setConnectionToServer(HibernateCRUD.fullConnection);
			String DBCreationQuery = "CREATE TABLE IF NOT EXISTS meals"
					+ "  (mealID INT AUTO_INCREMENT NOT NULL, "
					+ "  mealName VARCHAR(45) NOT NULL, "
					+ "  category  VARCHAR(45) NOT NULL, "
					+ "  price DOUBLE NOT NULL, " + "  cost DOUBLE NOT NULL, "
					+ "  availability BIT(1) NOT NULL, "
					+ "  PRIMARY KEY (mealID));";
			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(DBCreationQuery);
			preparedStatement.executeUpdate();
			flag = true;
			preparedStatement.close();
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	private boolean CreateMealItemsTable() {
		boolean flag = false;
		Connection dbConnection = null;
		try {
			dbConnection = this
					.setConnectionToServer(HibernateCRUD.fullConnection);
			String DBCreationQuery = "CREATE TABLE IF NOT EXISTS mealitems"
					+ "  (mealItemID INT AUTO_INCREMENT NOT NULL, "
					+ "  mealID INT NOT NULL, "
					+ "  productID INT NOT NULL, "
					+ "  quantity INT NOT NULL, "
					+ "  PRIMARY KEY (mealItemID),"
					+ "  FOREIGN KEY (mealID) REFERENCES meals(mealID) "
					+ "  ON UPDATE CASCADE "
					+ "  ON DELETE CASCADE, "
					+ "  FOREIGN KEY (productID) REFERENCES products(productID)"
					+ "  ON UPDATE CASCADE " + "  ON DELETE CASCADE);";
			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(DBCreationQuery);
			preparedStatement.executeUpdate();
			flag = true;
			preparedStatement.close();
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	public boolean CreateAllTables() {
		boolean flag = false;
		try {
			this.setConnectionToServer(HibernateCRUD.path);
			this.CreateDatabaseAndSetConnection(HibernateCRUD.DBName);
			this.CreateProductsTable();
			this.CreateMealsTable();
			this.CreateMealItemsTable();
			flag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}
 
}
