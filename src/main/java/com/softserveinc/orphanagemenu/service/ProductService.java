package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;


@Service
public class ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDAO;
	
	@Transactional
	public void saveProduct(Product p) {
		this.productDAO.saveProduct(p);
	}
	
	@Transactional
	public void updateProduct(Product product){
		this.productDAO.updateProduct(product);
	}
	
	public void updateProductWeight(ProductWeight productWeight) {
		this.productDAO.updateProductWeight(productWeight);
	}
	
	@Transactional
	public ArrayList<Product> getAllProduct(){
    	return this.productDAO.getAllProduct();
	}
	
	@Transactional
	public ArrayList<ProductWeight> getAllProductWeight() {
		return this.productDAO.getAllProductWeight();
	}
	
	@Transactional
	public ArrayList<Dimension> getAllDimension(){
    	return this.productDAO.getAllDimension();
	}
	
	public ArrayList<AgeCategory> getAllCategory() {
		return this.productDAO.getAllCategory();
	}
	
	@Transactional
	public Product getProductById(Long id){
		return this.productDAO.getProductById(id);
	}
	
	@Transactional
	public Dimension getDimensionById(Long id){
		return this.productDAO.getDimensionById(id);
	}

	public ArrayList<AgeCategory> getAllAgeCategory() {
		return this.productDAO.getAllAgeCategory();
	}

	public Product getProduct(String name) {
		return this.productDAO.getProduct(name);
	}

	public void saveProductWeight(ProductWeight productWeight) {
		this.productDAO.saveproductWeight(productWeight);
		
	}


}
