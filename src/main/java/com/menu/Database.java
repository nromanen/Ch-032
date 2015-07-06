package com.menu;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    Statement st = null;
    Connection con=null;
    private static final Logger logger = LogManager.getLogger(Database.class);

        public Database(){
        try {

            Class.forName("org.h2.Driver").newInstance();
            con  = DriverManager.getConnection("jdbc:h2:~/test","sa", "");

           st = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTableProducts() {
        try {
        st.execute("Drop table Products");
        st.execute("CREATE TABLE Products (" +
                "ID int NOT NULL AUTO_INCREMENT," +
                "pName varchar(20) NOT NULL UNIQUE," +
                "pWeight real(5)," +
                "pCost real(5)," +
                "PRIMARY KEY (ID)" +
                ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTableDishes() {
        try {
        st.execute("Drop table Dishes");
        st.execute("CREATE TABLE Dishes (" +
                "ID int NOT NULL AUTO_INCREMENT," +
                "dName varchar (20) UNIQUE," +
                "dCategory int(1)," +
                "PRIMARY KEY (ID)" +
                ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTableListIngredients() {
        try {
            st.execute("Drop table ListIngredients");
            st.execute("CREATE TABLE ListIngredients (" +
                    "ID int NOT NULL AUTO_INCREMENT," +
                    "dID int (2)," +
                    "pID int (2),"+
                    "pWeight real(5)," +
                    "PRIMARY KEY (ID)" +
                    "FOREIGN KEY (dID)" +
                    "FOREIGN KEY (pID)" +
                    ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addDish(Dish dish){
        ResultSet rs;
        logger.debug("Start to add dish");
        try {

            HashMap<Product, Double> mapDishProductsList=new HashMap<Product, Double>(dish.getMapDishProductsList());
            //mapDishProductsList=dish.getMapDishProductsList();
            st.execute("INSERT INTO DISHES (dName, dCategory)"+
                    "VALUES ('"+dish.getDishName()+"',"+dish.getCategory()+");");
            rs=st.executeQuery("SELECT ID FROM Dishes WHERE dname='"+dish.getDishName()+"'");
            rs.next();
            dish.setID(rs.getInt(1));

            for (Map.Entry<Product, Double> entry : mapDishProductsList.entrySet()) {
                st.execute("INSERT INTO ListIngredients (dID,pID, pWeight)" +
                        "VALUES (" + dish.getID() + "," + entry.getKey().getId() + "," + entry.getValue()+ ");");

            }

        } catch (Exception e) {
            logger.error("addDish",e);


            e.printStackTrace();
        }
    }
    public Dish getDish(String dName){
        Dish dish=new Dish();
        Product prod=new Product();
        HashMap<Product, Double> mapDishProductsList=new HashMap<Product, Double>();
        ResultSet rs;
        try {

            rs = st.executeQuery("SELECT * FROM Dishes " +
                    "WHERE dName='" + dName + "';");
            rs.next();
            dish.setID(rs.getInt("ID"));
            dish.setDishName(dName);
            dish.setCategory(rs.getInt("dCategory"));

//            rs=st.executeQuery("SELECT ListIngredients.dID, ListIngredients.pID, ListIngredients.pWeight, Products.ID, Products.pName, Products.Cost,  "+
//            "FROM ListIngredients,Products" +
//                    "WHERE (ListIngredients.dID="+ dish.getID()+") AND (ListIngredients.pID=Products.ID) ORDER BY ListIngredients.dID ;");

            rs=st.executeQuery("SELECT dishes.ID , listIngredients.pWeight ,dishes.dName,products.pName, Products.ID, products.pCost FROM Dishes INNER JOIN  ListIngredients ON dishes.ID=ListIngredients.dID INNER JOIN Products ON products.ID=ListIngredients.pID\n" +
                    "WHERE (ListIngredients.dID="+dish.getID()+") AND (ListIngredients.pID=Products.ID) AND (dishes.dname='"+dName+"') ORDER BY  dishes.ID");

            while (rs.next()) {
                prod.setId( rs.getInt("Products.ID"));
                prod.setName(rs.getString("Products.pName"));
                prod.setCost(rs.getDouble("products.pCost"));
                double pWeight= rs.getDouble("listIngredients.pWeight");
                mapDishProductsList.put(prod,pWeight);

            }
            dish.setMapDishProductsList(mapDishProductsList);
        return dish;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Dish> getAllDishes(){
        Dish dish=new Dish();
        Product prod=new Product();
        ArrayList<Dish> dishList = new ArrayList();

        HashMap<Product, Double> mapDishProductsList=new HashMap<Product, Double>();
        ResultSet rsDishList;
        ResultSet rsDishIngredients;
        try {

            rsDishList = st.executeQuery("SELECT * FROM Dishes");
            while(rsDishList.next()) {
                dish.setID(rsDishList.getInt("ID"));
                dish.setDishName(rsDishList.getString("dName"));
                dish.setCategory(rsDishList.getInt("dCategory"));

//            rsDishList=st.executeQuery("SELECT ListIngredients.dID, ListIngredients.pID, ListIngredients.pWeight, Products.ID, Products.pName, Products.Cost,  "+
//            "FROM ListIngredients,Products" +
//                    "WHERE (ListIngredients.dID="+ dish.getID()+") AND (ListIngredients.pID=Products.ID) ORDER BY ListIngredients.dID ;");

                rsDishIngredients = st.executeQuery("SELECT dishes.ID , listIngredients.pWeight ,dishes.dName,products.pName, Products.ID, products.pCost FROM Dishes INNER JOIN  ListIngredients ON dishes.ID=ListIngredients.dID INNER JOIN Products ON products.ID=ListIngredients.pID\n" +
                        "WHERE (ListIngredients.dID=" + dish.getID() + ") AND (ListIngredients.pID=Products.ID) AND (dishes.dname='" + dish.getDishName() + "') ORDER BY  dishes.ID");

                while (rsDishIngredients.next()) {
                    prod.setId(rsDishIngredients.getInt("Products.ID"));
                    prod.setName(rsDishIngredients.getString("Products.pName"));
                    prod.setCost(rsDishIngredients.getDouble("products.pCost"));
                    double pWeight = rsDishIngredients.getDouble("listIngredients.pWeight");
                    mapDishProductsList.put(prod, pWeight);

                }
                dish.setMapDishProductsList(mapDishProductsList);
                dishList.add(dish);
                dish=new Dish();
                mapDishProductsList=new HashMap<Product, Double>();
            }
            return dishList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> productList = new ArrayList();
        Product prod=new Product();
        ResultSet rsProdList;
        try {
            rsProdList = st.executeQuery("SELECT * FROM Products");
            while(rsProdList.next()) {
                prod.setId(rsProdList.getInt("ID"));
                prod.setName(rsProdList.getString("pName"));
                prod.setCost(rsProdList.getDouble("pCost"));
                prod.setKillogram(rsProdList.getDouble("pWeight"));
                productList.add(prod);
                productList = new ArrayList();
                prod=new Product();

            }
            return productList;
        }
        catch (Exception e) {
        e.printStackTrace();

        } return null;
    }

    public void addProduct(Product product){

        try {
            st.execute("INSERT INTO Products (pName, pWeight, pCost)"+
                    "VALUES ("+product.getName()+"',"+product.getKillogram()+","+product.getCost()+");");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteProduct(Product product){

        try {
            st.execute("DELETE FROM Products where"+
                    "pName=('"+product.getName()+"';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Product getProductByName(String productName){
        Product prod=new Product();
        ResultSet result;
        try {
           result= st.executeQuery("SELECT * FROM Products where" +
                   "pName=('" + productName + "';");
            prod.setId(result.getInt("ID"));
            prod.setName(result.getString("pName"));
            prod.setKillogram(result.getDouble("pWeight"));
            prod.setCost(result.getDouble("pCost"));
            return prod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void closeConnection(){
        try{
            this.st.close();
            this.con.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
