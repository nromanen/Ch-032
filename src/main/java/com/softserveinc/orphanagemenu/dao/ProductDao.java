package com.softserveinc.orphanagemenu.dao;

import java.util.List;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;

public interface ProductDao {

	void saveProduct(Product product);

	void updateProduct(Product product);

	List<Product> getAllProduct(String ... sort);
	
	List<Dimension> getAllDimension();

	Product getProductById(Long id);

	Product getProduct(String productName);

	List<AgeCategory> getAllAgeCategory();

	Dimension getDimensionByName(String dimension);
}
