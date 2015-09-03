package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import com.softserveinc.orphanagemenu.model.Product;

/**
 * @author Pavlo 
 * @author Sviatoslav Fedechko
 */
public interface ProductDao {

	void saveProduct(Product product);

	void updateProduct(Product product);

	List<Product> getAllProduct(String ... sort);
	
	Product getProductById(Long id);

	Product getProduct(String productName);

	Long getCount();

	List<Product> getPage(Integer offset, Integer pagecount);

	List<Product> getPage(String keyWord, Integer offset, Integer pagecount);

	Long getCount(String keyWord);
}
