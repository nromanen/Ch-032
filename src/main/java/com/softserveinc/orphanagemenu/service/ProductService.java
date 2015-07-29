package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.validator.user.UserAccountForm;

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
	public void updateProduct(Product product) {
		this.productDAO.updateProduct(product);
	}

	public void updateProductWeight(ProductWeight productWeight) {
		this.productDAO.updateProductWeight(productWeight);
	}

	@Transactional
	public ArrayList<Product> getAllProduct(String sort) {
		return this.productDAO.getAllProduct(sort);
	}

	@Transactional
	public ArrayList<ProductWeight> getAllProductWeight() {
		return this.productDAO.getAllProductWeight();
	}

	@Transactional
	public ArrayList<Dimension> getAllDimension() {
		return this.productDAO.getAllDimension();
	}

	public ArrayList<AgeCategory> getAllCategory() {
		return this.productDAO.getAllCategory();
	}

	@Transactional
	public Product getProductById(Long id) {
		return this.productDAO.getProductById(id);
	}

	@Transactional
	public Dimension getDimensionById(Long id) {
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

	public ProductForm getProductFormByProductId(Long id) {
		ProductForm productForm = new ProductForm();
		Product product = getProductById(id);
		productForm.setId(product.getId().toString());
		productForm.setName(product.getName());
		productForm.setDimension(product.getDimension().getName());
		Map<String, String> productWeights = new HashMap<>();
		for (ProductWeight productWeight : product.getProductWeight()) {
			productWeights.put("dimension_"
					+ productWeight.getAgeCategory().getId(), productWeight
					.getStandartProductQuantity().toString());
		}
		productForm.setWeight(productWeights);
		return productForm;
	}
}
