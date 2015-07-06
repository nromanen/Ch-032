package engine;

import java.util.ArrayList;

/**
 * Created by skaraltc on 6/19/2015.
 */
public class ComplexMeals {
    private static ArrayList<ComplexMeal> complexMealList = new ArrayList<ComplexMeal>();

    public void addComplexMeal (ComplexMeal complexMeal)
    {
        this.complexMealList.add(complexMeal);
    }

    public ArrayList<ComplexMeal> getComplexMealList() {
        return complexMealList;
    }

    public void setComplexMealList(ArrayList<ComplexMeal> complexMealList) {
        this.complexMealList = complexMealList;
    }

    public String toString ()
    {
        String output = "";
        for (ComplexMeal currentComplexMeal: complexMealList)
        {
            output+="["+currentComplexMeal.toString() + "]"+"\n";
        }
        return output;
    }
}
