package engine;

/**
 *
 * @author skaraltc
 */
public class ComplexMeal {

    private Meal firstMeal;
    private Meal secondMeal;
    private Meal thirdMeal; 
    private double totalCost;
    private double totalPrice;
    
    public ComplexMeal() {
        firstMeal = null;
        secondMeal = null;
        setThirdMeal(null);
        totalCost = 0;
        totalPrice = 0;
    }

    public ComplexMeal(Meal firstMeal, Meal secondMeal, Meal thirdMeal) {
        this.firstMeal = firstMeal;
        this.secondMeal = secondMeal;
        this.setThirdMeal(thirdMeal);
        this.totalCost = firstMeal.getCost()+secondMeal.getCost()+thirdMeal.getCost();
        this.totalPrice = firstMeal.getPrice()+secondMeal.getPrice()+thirdMeal.getPrice();
    }
    
    public String toString ()
    {
        return "First meal is: "+firstMeal.toString()+"\n"+"Second meal is: "+secondMeal.toString()+"\n"+"Third meal is: "+ getThirdMeal().toString()+ "\n"+"For total price: "+this.getTotalPrice()+" UAH";
    }
    
    /**
     * @return the firstMeal
     */
    public Meal getFirstMeal() {
        return firstMeal;
    }

    /**
     * @param firstMeal the firstMeal to set
     */
    public void setFirstMeal(Meal firstMeal) {
        this.firstMeal = firstMeal;
    }

    /**
     * @return the secondMeal
     */
    public Meal getSecondMeal() {
        return secondMeal;
    }

    /**
     * @param secondMeal the secondMeal to set
     */
    public void setSecondMeal(Meal secondMeal) {
        this.secondMeal = secondMeal;
    }

    /**
     * @return the thirdMeal
     */
    public Meal getThirdMeal() {
        return thirdMeal;
    }

    /**
     * @param thirdMeal the thirdMeal to set
     */
    public void setThirdMeal(Meal thirdMeal) {
        this.thirdMeal = thirdMeal;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}
