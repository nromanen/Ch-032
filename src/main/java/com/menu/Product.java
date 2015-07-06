package com.menu;
/**
 * Class Product created to serve
 * all actions which connected with products
 * entities
 *
 * @author Dima Khodan
 * @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */
import java.util.ArrayList;

public class Product {
    /**
     * Product name
     */
    private String name;
    /**
     * Product id
     */
    private int id;
    /**
     * Product weight (in kilogram)
     */
    private double killogram;
    /**
     * Product cost )for kilogram)
     */
    private double cost;
    /**
     * Product repository which contains list of all products
     */
    public static ArrayList<Product> productRepository;

    /**
     * Empty constructor
     */
    public Product() {
    }

    /**
     * Construtor
     * @param name set product name
     * @param killo set product weight which we
     *              hawe in store(in kiligram)
     * @param cost  sets product price(for kilogram)
     */
    public Product(String name, double killo, double cost) {
        this.name = name;
        this.killogram = killo;
        this.cost = cost;
    }

    /**
     * Setter for product weight
     * @param killogram product weight(in kilogram)
     */
    public void setKillogram(double killogram) {
        this.killogram = killogram;
    }

    /**
     * Getter for  product weight
     * @return product ewight(in kilogram)
     */
    public double getKillogram() {
        return this.killogram;
    }

    /**
     * Setter for product cost
     * @param cost product price for 1 kilogram
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Getter for product cost
     * @return roduct cost for 1 kilogram
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Getter for product name
     * @return  product name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for froduct name
     * @param name sets product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets priduct id
     * @param id product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets product id
     * @return product id
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * Gets product class by it's name
     * @param name product name
     * @param prList product list
     * @return Prodct class copy with given name
     * if name doesn't exist in repository returns null
     */
    public static Product getByName(String name, ArrayList<Product> prList) {
        for (Product product : prList) {
            if (!product.getName().equals(name)) continue;
            return product;
        }
        return null;
    }

    /**
     * Overided equal method used to compare Product instances
     * @param anotherProduct Product class instance
     * @return boolean false if not equal otherwise true
     */
    @Override
    public boolean equals(Object anotherProduct) {
        if (!(anotherProduct != null && anotherProduct instanceof Product)) {
            return false;
        }
        Product another = (Product)anotherProduct;
        return ((Product)anotherProduct).getName().equals(this.getName());
    }

    /**
     * Overided toString method
     * @return Product string name
     */
    @Override
    public String toString() {
        return this.name;
    }
}