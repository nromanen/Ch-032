package com.menu.model;

public class DB {
	
	// String queryCreate = "CREATE TABLE ingredients "
	// + "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
	// + "title VARCHAR(20), price DOUBLE, "
	// + "ingredientDimension VARCHAR(4), available BIT(1))";
	// String selectAllFromMenu = "SELECT * FROM ingredients"
	// + "WHERE id= ? and title= ?";
	// java.sql.Statement stmt = con.createStatement();
	
	//add foreighn key primery key
	
	// String queryCreate = "CREATE TABLE Meals "
	// + "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
	// + "title VARCHAR(20), price DOUBLE, "
	// + "mealcategory VARCHAR(20), available BIT(1))";
	// java.sql.Statement stmt1 = con.createStatement();
	// int rs1 = stmt1.executeUpdate(queryCreate);
	
	// String insertTableSQL2 = "INSERT INTO ingredients"
	// + "(title, price, ingredientDimension, available) VALUES"
	// + "(?, ?, ?, ?)";
	// PreparedStatement pstmt = con.prepareStatement(insertTableSQL2);
	// for (Ingredient ingred : PortionsOfMeals.ingredients)
	// {
	// pstmt.setString(1, ingred.getTitle());
	// pstmt.setDouble(2, ingred.getPrice());
	// pstmt.setString(3, ingred.getIngredientDimension().toString());
	// pstmt.setBoolean(4, ingred.isAvailable());
	// pstmt.executeUpdate();
	// }
	//new PortionsOfMeals().initMenu();
	
	//String insertTableSQL2 = "INSERT INTO meals"
//			+ "(title, price, mealcategory, available) VALUES"
//			+ "(?, ?, ?, ?)";
	//PreparedStatement pstmt = con.prepareStatement(insertTableSQL2);
	//for (Meal meal : PortionsOfMeals.mealList) {
//		pstmt.setString(1, meal.getTitle());
//		pstmt.setDouble(2, meal.getPrice());
//		pstmt.setString(3, meal.getMealcategory().toString());
//		pstmt.setBoolean(4, meal.isAvailable());
//		pstmt.executeUpdate();
	//}


	
	

//	public void initializeMealList() {
//	Ingredient carrot = new Ingredient("carrot", 0.0052, "GR", true);
//	Ingredient potato = new Ingredient("potato", 0.0084, "GR", true);
//	Ingredient oil = new Ingredient("oil", 0.015, "ML", true);
//	Ingredient water = new Ingredient("water", 0.0005, "ML", true);
//	Ingredient onion = new Ingredient("onion", 0.004, "GR", true);
//	Ingredient pasta = new Ingredient("pasta", 0.0205, "GR", true);
//	Ingredient blackTea = new Ingredient("blackTea", 0.40, "GR", true);
//	Ingredient greenTea = new Ingredient("greenTea", 0.60, "GR", true);
//	Ingredient meat = new Ingredient("meat", 0.099, "GR", true);
//	ingredients.add(carrot);
//	ingredients.add(potato);
//	ingredients.add(oil);
//	ingredients.add(water);
//	ingredients.add(onion);
//	ingredients.add(pasta);
//	ingredients.add(blackTea);
//	ingredients.add(greenTea);
//	ingredients.add(meat);
//}

//public void initMenu() {
//
//	addMeal(new Meal("fryPotato", MealCategory.SECOND.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(300, getByTitle("potato")),
//					new PortionOfIngredient(50, getByTitle("onion")),
//					new PortionOfIngredient(50, getByTitle("oil"))))));
//
//	addMeal(new Meal("potatoPastaSoup", MealCategory.FIRST.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(200, getByTitle("potato")),
//					new PortionOfIngredient(200, getByTitle("pasta")),
//					new PortionOfIngredient(50, getByTitle("onion")),
//					new PortionOfIngredient(300, getByTitle("water"))))));
//
//	addMeal(new Meal("fryPotatoWithMeat", MealCategory.SECOND.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(300, getByTitle("potato")),
//					new PortionOfIngredient(300, getByTitle("meat")),
//					new PortionOfIngredient(50, getByTitle("onion")),
//					new PortionOfIngredient(50, getByTitle("oil"))))));
//
//	addMeal(new Meal("bigBlackTea", MealCategory.DRINK.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(20, getByTitle("blackTea")),
//					new PortionOfIngredient(300, getByTitle("water"))))));
//
//	addMeal(new Meal("bigGreenTea", MealCategory.DRINK.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(20, getByTitle("greenTea")),
//					new PortionOfIngredient(300, getByTitle("water"))))));
//
//	addMeal(new Meal("carrotPastaWaterSoup", MealCategory.FIRST.toString(),
//			new ArrayList<PortionOfIngredient>(Arrays.asList(
//					new PortionOfIngredient(200, getByTitle("carrot")),
//					new PortionOfIngredient(200, getByTitle("pasta")),
//					new PortionOfIngredient(300, getByTitle("water"))))));
//}
}
