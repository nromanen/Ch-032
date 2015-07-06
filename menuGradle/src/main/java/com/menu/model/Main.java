package com.menu.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		
		HibernateCRUD hbr = new HibernateCRUD();
		hbr.CreateDatabaseAndSetConnection(HibernateCRUD.DBName);
		hbr.CreateAllTables();
		
//		System.out.println(new Product ("sausage", 0.07, "gr", true));
		
		 hbr.addProduct(new Product ("sausage", 0.07, "GR", "true"));
		 hbr.addProduct(new Product ("carrot", 0.0052, "GR", "true"));
		 hbr.addProduct(new Product ("potato", 0.0084, "GR", "true"));
		 hbr.addProduct(new Product ("oil", 0.015, "ML", "true"));
		 hbr.addProduct(new Product ("water", 0.0005, "ML", "true"));
		 hbr.addProduct(new Product ("onion", 0.004, "GR", "true"));
		 hbr.addProduct(new Product ("pasta", 0.0205, "GR", "true"));
		 hbr.addProduct(new Product ("blackTea", 0.40, "GR", "true"));
		 hbr.addProduct(new Product ("greenTea", 0.60, "GR", "true"));
		 hbr.addProduct(new Product ("meat", 0.099, "GR", "true"));
		

	}

}
