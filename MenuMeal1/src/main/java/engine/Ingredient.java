package engine;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Администратор
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {

    private double quantity;
    private double priceForPortion;
    private Product neededProduct;
    private boolean availability;

    public Ingredient() {
        quantity = 0;
        neededProduct = null;
        setAvailability(false);
    }
    
    /**
     *@author Luchyn Pavlo
     *@param quantity contains a numeric representation of product quantity that you want to take
     *@param neededProduct contains an pointer to product for initialization super() constructor and building a price
     */ 
    public Ingredient(float quantity, Product neededProduct) {
        this.quantity = quantity;
        this.neededProduct = neededProduct;
        this.setAvailability(neededProduct.isAvailable());
        this.setPriceForPortion((neededProduct.getpriceForUnit()*this.quantity)/neededProduct.getUnit());
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the availability
     */
    public boolean isAvailable() {
        return isAvailability();
    }

    public double getPriceForPortion() {
        return priceForPortion;
    }

    public void setPriceForPortion(double priceForPortion) {
        this.priceForPortion = priceForPortion;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
