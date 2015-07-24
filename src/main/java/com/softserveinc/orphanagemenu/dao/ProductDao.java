package com.softserveinc.orphanagemenu.dao;

import java.util.ArrayList;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

public interface ProductDao {


	void saveProduct(Product product);

	void updateProduct(Product product);

	ArrayList<Product> getAllProduct();
	
	ArrayList<ProductWeight> getAllProductWeight();

	ArrayList<Dimension> getAllDimension();

	Product getProductById(Long id);

	Dimension getDimensionById(Long id);

	Product getProduct(String productName);

	ArrayList<AgeCategory> getAllCategory();
}
