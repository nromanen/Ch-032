package com.javagroup.restaurantmenu;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.javagroup.restaurantmenu.model.Ingredient;
import com.javagroup.restaurantmenu.model.Product;

public class IngredientTest {

	@Test
	public void equalsCheckForDifferentObjectsWithSameFields() {
		Ingredient ingredient1 = new Ingredient(new Product("A", 1.5, true), 1);
		Ingredient ingredient2 = new Ingredient(new Product("A", 1.0, true), 1);
		AssertJUnit.assertNotSame(ingredient1, ingredient2);
		AssertJUnit.assertTrue(ingredient1.equals(ingredient2));
	}

	@Test( dataProvider = "quantityNotValidArgumentsProvider",
			expectedExceptions = IllegalArgumentException.class)
	public void setQuantityShouldThrowIfWrongArgument(int quantity) {
		new Ingredient().setQuantity(quantity);
	}

	@DataProvider(name = "quantityNotValidArgumentsProvider")
	public Object[][] quntityBadArguments() {
		return new Object[][] {{-1}, {0}};
	}  

}
