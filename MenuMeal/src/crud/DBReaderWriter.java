package crud;

import engine.*;

import java.sql.*;

public class DBReaderWriter {
    public static final String password = "root";
    public static final String login = "root";
    public static final String path = "jdbc:mysql://localhost:3306/";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String DBName = "mydatabase";
    public static final String fullConnection = path.concat(DBName);

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

    private boolean CreateDatabaseAndSetConnection(String DBName) {
        boolean flag = false;
        Connection dbConnection = null;
        try {
            dbConnection = this.setConnectionToServer(DBReaderWriter.path);
            String DBCreationQuery = "CREATE DATABASE IF NOT EXISTS " + DBReaderWriter.DBName;
            PreparedStatement preparedStatement = dbConnection.prepareStatement(DBCreationQuery);
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
            dbConnection = this.setConnectionToServer(DBReaderWriter.fullConnection);
            String DBCreationQuery = "CREATE TABLE IF NOT EXISTS products" +
                    "  (productID INT AUTO_INCREMENT NOT NULL, " +
                    "  productName VARCHAR(45) NOT NULL, " +
                    "  priceForUnit DOUBLE NOT NULL, " +
                    "  unit INT NOT NULL, " +
                    "  available BIT(1) NOT NULL, " +
                    "  PRIMARY KEY (productID)  );";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(DBCreationQuery);
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
            dbConnection = this.setConnectionToServer(DBReaderWriter.fullConnection);
            String DBCreationQuery = "CREATE TABLE IF NOT EXISTS meals" +
                    "  (mealID INT AUTO_INCREMENT NOT NULL, " +
                    "  mealName VARCHAR(45) NOT NULL, " +
                    "  category  VARCHAR(45) NOT NULL, " +
                    "  price DOUBLE NOT NULL, " +
                    "  cost DOUBLE NOT NULL, " +
                    "  availability BIT(1) NOT NULL, " +
                    "  PRIMARY KEY (mealID));";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(DBCreationQuery);
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
            dbConnection = this.setConnectionToServer(DBReaderWriter.fullConnection);
            String DBCreationQuery = "CREATE TABLE IF NOT EXISTS mealitems" +
                    "  (mealItemID INT AUTO_INCREMENT NOT NULL, " +
                    "  mealID INT NOT NULL, " +
                    "  productID INT NOT NULL, " +
                    "  quantity INT NOT NULL, " +
                    "  PRIMARY KEY (mealItemID)," +
                    "  FOREIGN KEY (mealID) REFERENCES meals(mealID) " +
                    "  ON UPDATE CASCADE " +
                    "  ON DELETE CASCADE, " +
                    "  FOREIGN KEY (productID) REFERENCES products(productID)" +
                    "  ON UPDATE CASCADE " +
                    "  ON DELETE CASCADE);";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(DBCreationQuery);
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
            this.setConnectionToServer(DBReaderWriter.path);
            this.CreateDatabaseAndSetConnection(DBReaderWriter.DBName);
            this.CreateProductsTable();
            this.CreateMealsTable();
            this.CreateMealItemsTable();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    public static boolean addProduct(Product newProduct) {
        boolean flag = false;
        Connection dbConnection = null;
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            String addProduct = "INSERT INTO products"
                    + "(productName, priceForUnit, unit, available) VALUES"
                    + "(?, ?, ?, ?)";
            PreparedStatement preparedStatementStatement = dbConnection.prepareStatement(addProduct);
            preparedStatementStatement.setString(1, newProduct.getName());
            preparedStatementStatement.setDouble(2, newProduct.getpriceForUnit());
            preparedStatementStatement.setInt(3, newProduct.getUnit());
            preparedStatementStatement.setBoolean(4, newProduct.isAvailable());
            int result = preparedStatementStatement.executeUpdate();
            dbConnection.close();
            flag = true;
            preparedStatementStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }

        return flag;
    }

    public boolean initProducts(Products allProducts) {
        boolean flag = false;
        if (isProductTableEmpty()) {
            allProducts.initProducts();
            for (Product currentProduct : allProducts.getAllProducts()) {
                DBReaderWriter.addProduct(currentProduct);
            }
            flag = true;
        }
        return flag;
    }

    private boolean isProductTableEmpty() {
        boolean flag = true;
        Connection dbConnection = null;
        String query = "SELECT COUNT(*) FROM products;";
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                if (result.getLong(1) > 0) {
                    flag = false;
                }
            }
            result.close();
            stmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return flag;
    }

    public static int readAllProductsFromDB(Products targetCollection) {
        Connection dbConnection = null;
        ResultSet result = null;
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT * FROM products;";
            result = stmt.executeQuery(query);
            while (result.next()) {
                targetCollection.addProduct(new Product(result.getString(2),
                        result.getDouble(3), result.getInt(4), result.getBoolean(5)));
            }
            result.close();
            stmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return targetCollection.getAllProducts().size();
    }

    public static int deleteProduct(String productName) {
        int result = 0;
        Connection dbConnection = null;
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            String deleteProduct = "DELETE FROM products "
                    + "WHERE productName = ?" + ";";
            PreparedStatement preparedStatementStatement = dbConnection.prepareStatement(deleteProduct);
            preparedStatementStatement.setString(1, productName);
            result = preparedStatementStatement.executeUpdate();
            preparedStatementStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return result;
    }

    public static boolean addMeal(Meal newMeal) {
        boolean flag = false;
        Connection dbConnection = null;
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            String addMeal = "INSERT INTO meals"
                    + "(mealName, category, price, cost, availability) VALUES"
                    + "(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementStatement = dbConnection.prepareStatement(addMeal);
            preparedStatementStatement.setString(1, newMeal.getName());
            preparedStatementStatement.setString(2, newMeal.getCategory().toString());
            preparedStatementStatement.setDouble(3, newMeal.getPrice());
            preparedStatementStatement.setDouble(4, newMeal.getCost());
            preparedStatementStatement.setBoolean(5, newMeal.isAvailability());
            int result = preparedStatementStatement.executeUpdate();
            dbConnection.close();
            flag = true;
            preparedStatementStatement.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return flag;
    }

    public static boolean addProductToMeal(Meal newMeal, Product product, int quantity) {
        boolean flag = false;
        Connection dbConnection = null;
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            String addMealIngredients = "INSERT INTO mealitems"
                    + "(mealID, productID, quantity) VALUES"
                    + "(?, ?, ?)";
            PreparedStatement ptsmt = dbConnection.prepareStatement(addMealIngredients);
            ptsmt.setInt(1, newMeal.getId());
            ptsmt.setInt(2, product.getId());
            ptsmt.setInt(3, quantity);
            int res = ptsmt.executeUpdate();
            dbConnection.close();
            flag = true;
            ptsmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return flag;
    }

    public static boolean initMeals(Meals allMeals, Products allProducts, Categories allCategories) {
        boolean flag = false;
        if (isMealsTableEmpty()) {
            allMeals.initAllMeals(allProducts, allCategories);
            for (Meal currentMeal : allMeals.getMealList()) {
                DBReaderWriter.addMeal(currentMeal);
            }
            flag = true;
        }
        return flag;
    }

    private static boolean isMealsTableEmpty() {
        boolean flag = true;
        Connection dbConnection = null;
        String query = "SELECT COUNT(*) FROM meals;";
        try {
            dbConnection = setConnectionToServer(DBReaderWriter.fullConnection);
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                if (result.getLong(1) > 0) {
                    flag = false;
                }
            }
            result.close();
            stmt.close();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return flag;
    }
}

