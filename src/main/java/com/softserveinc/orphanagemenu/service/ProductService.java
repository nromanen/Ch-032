package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.Product;

public interface ProductService {


	List<Product> getAllProductDtoSorted(String ... sort);

	ProductForm getProductFormByProductId(Long id);

	Product getNewProductFromProductForm(ProductForm productForm);

	void updateProduct(Product product);

	Product updateProductByProductForm(ProductForm productForm);

	Product getProductById(Long productId);

	Long getCount();

	List<Product> getPage(Integer offset, Integer pagecount);

	List<Product> getPage(String keyWord, Integer offset, Integer pagecount);

	Long getCount(String keyWord);

}
