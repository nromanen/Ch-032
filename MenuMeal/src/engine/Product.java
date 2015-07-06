package engine;

import validators.NameValidator;
import validators.PriceValidator;

/**
 * @author Luchyn Pavlo
 */
public class Product {

    private int id;
    private String name;
    private double priceForUnit;
    private int unit;
    private boolean available;

    private static PriceValidator priceValidator = new PriceValidator();
    private static NameValidator nameValidator = new NameValidator();


    public Product() {
        name = "";
        priceForUnit = 0;
        unit = 0;
        available = false;
    }

    /**
     * @param name         represents an alphanumeric name of your product
     * @param priceForUnit is for
     * @param unit         contains a unit which is used to measure a quantity of your product
     * @author Luchyn Pavlo
     */
    public Product(String name, double priceForUnit, int unit, boolean available) {
        if (nameValidator.isNameValid(name)) {
            this.name = name;
        }
        if (priceValidator.isPriceValid(priceForUnit)) {
            this.priceForUnit = priceForUnit;
        }
        this.unit = unit;
        this.available = available;

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the priceForUnit
     */
    public double getpriceForUnit() {
        return priceForUnit;
    }

    /**
     * @param priceForUnit the priceForUnit to set
     */
    public void setpriceForUnit(float priceForUnit) {
        this.priceForUnit = priceForUnit;
    }

    /**
     * @return the unit
     */
    public int getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(int unit) {
        this.unit = unit;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "[" + this.getName() + " " + this.getpriceForUnit() + " " + this.getUnit() + " " + this.isAvailable() + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
