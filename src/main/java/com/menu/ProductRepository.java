package com.menu;
/**
 * ProductRepository created to store 
 * all essence of Product
 * 
 * @author Dima Khodan
 * @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */
import java.util.ArrayList;
import java.util.Iterator;


public class ProductRepository {
    /**
     * productList basic list where store 
     * all essence of Products
     */
    public ArrayList<Product> productList = new ArrayList();

    /**
     * Constructor comprising all repository essence
     */
    public ProductRepository() {
        this.productList.add(new Product("Carrot", 15.0, 8.0));
        this.productList.add(new Product("Potato", 20.0, 5.0));
        this.productList.add(new Product("Onion", 5.0, 9.0));
        this.productList.add(new Product("Tomato", 10.0, 10.0));
        this.productList.add(new Product("Meat", 10.0, 50.0));
        this.productList.add(new Product("Water", 40.0, 1.0));
        this.productList.add(new Product("Becon", 5.0, 70.0));
        this.productList.add(new Product("Souce", 0.5, 20.0));
        this.productList.add(new Product("Fruits", 5.0, 35.0));
        this.productList.add(new Product("Spice", 1.0, 20.0));
        this.productList.add(new Product("Cheese", 5.0, 80.0));
        this.productList.add(new Product("Sour", 10.0, 15.0));
        this.productList.add(new Product("Sugar", 1.0, 15.0));
        this.productList.add(new Product("Salt", 1.0, 5.0));
        this.productList.add(new Product("CoffeBeans", 1.0, 100.0));
        this.productList.add(new Product("TeaLeaves", 1.0, 80.0));
        this.productList.add(new Product("Beet", 10.0, 8.0));
    }

    /**
     * Gets Products list
     * @return productList
     */
    public ArrayList<Product> getProducts() {
        return this.productList;
    }

    /**
     * Method adds new product to repository
     * @param name
     * @param killo
     * @param cost
     * @return reference to new product
     */
    public Product addProduct(String name, double killo, double cost) {
        Product prod = new Product(name, killo, cost);
        this.productList.add(prod);
        return prod;
    }
    /**
     * Method adds new product by reference to repository
     * @param product 
     */
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    /**
     * Method delete product by name from repository
     * @param name 
     */
    public void deleteProductByName(String name) {
        Iterator<Product> iter = this.productList.iterator();
        while (iter.hasNext()) {
            if (!iter.next().getName().equals(name)) continue;
            iter.remove();
        }
    }

    /**
     * Method delete product by reference from repository
     * @param product 
     */
    public void deleteProduct(Product product) {
        this.productList.remove(product);
    }

    /**
     * Method get product from repository by product name
     * @param name
     * @return reference to product
     */
    public Product getProductByName(String name) {
        for (Product product : this.productList) {
            if (!product.getName().equals(name)) continue;
            return product;
        }
        return null;
    }

    /**
     * Method adds new product to repository
     * @param name
     * @return reference to product
     */
    public Product addProduct(String name) {
        for (Product product : this.productList) {
            if (!product.getName().equals(name)) continue;
            return product;
        }
        return null;
    }

    /**
     * Method Gets Product from repository by product id
     * @param id 
     */
    public void getProductById(int id) {
        for (Product product : this.productList) {
            System.out.println(product.getId() == id);
        }
    }
}