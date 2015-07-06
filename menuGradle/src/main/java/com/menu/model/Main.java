package com.menu.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	public static Logger log = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {
		
		HibernateCRUD hbr = new HibernateCRUD();
		
		hbr.initEntityManager();
		
//		System.out.println(new Product ("sausage", 0.07, "gr", true));
		
//		 hbr.createProduct(new Product ("sausage", 0.07, "GR", true));

		// hbr.deleteProduct(new Product ("sausage", 0.07, "GR", true));

//		 hbr.updateProduct(new Product("sausage", 0.08, "GR", true), "sausages");
		
//		System.out.println(hbr.readProduct("sausage"));
//		 hbr.getAllProducts();

	}

}
