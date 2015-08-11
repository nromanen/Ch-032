package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

public interface ProductDao {


	void saveProduct(Product product);

	void updateProduct(Product product);

	List<Product> getAllProduct(String sort);
	
	List<ProductWeight> getAllProductWeight();

	List<Dimension> getAllDimension();

	Product getProductById(Long id);

	Dimension getDimensionById(Long id);

	Product getProduct(String productName);

	List<AgeCategory> getAllCategory();

	List<AgeCategory> getAllAgeCategory();

	void saveproductWeight(ProductWeight productWeight);

	void updateProductWeight(ProductWeight productWeight);

	Product getProductByName(String name);
	
	List<Product> getAllProduct();

	Dimension getDimensionByName(String dimension);
}
