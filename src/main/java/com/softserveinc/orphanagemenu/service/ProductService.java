package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.DimensionDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service
@Transactional
public class ProductService {

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDAO;

	@Autowired
	@Qualifier("DimensionDao")
	private DimensionDao dimensionDAO;

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
	public List<Product> getAllProductDtoSorted(String sort) {
		List<Product> products = productDAO.getAllProduct(sort);
		List<Product> productsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (Product product : products) {
			productsDto.add(mapper.map(product, Product.class));
		}
		return productsDto;
	}

	@Transactional
	public ArrayList<ProductWeight> getAllProductWeight() {
		return this.productDAO.getAllProductWeight();
	}

	@Transactional
	public ArrayList<Dimension> getAllDimension() {
		return this.productDAO.getAllDimension();
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
		List<Double> weightList = new ArrayList<>();
		for (ProductWeight productWeight : product.getProductWeight()) {
			weightList.add(productWeight.getStandartProductQuantity());
		}
		productForm.setWeightList(weightList);
		return productForm;
	}

	public Product getNewProductByProductForm(ProductForm productForm) {
		Product product = new Product();
		if (!("".equals(productForm.getId()))) {
			return product = getProductById(Long.parseLong(productForm.getId()));
		}
		product.setName(productForm.getName());
		product.setDimension(getDimensionById(Long.parseLong(productForm
				.getDimension())));
		ArrayList<AgeCategory> ageCategoryList = getAllAgeCategory();
		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		for (int i = 0; i < ageCategoryList.size(); i++) {
			ProductWeight weight = new ProductWeight();
			weight.setStandartProductQuantity(productForm.getWeightList()
					.get(i));
			weight.setAgeCategory(ageCategoryList.get(i));
			weight.setProduct(product);
			productWeightList.add(weight);
		}
		product.setProductWeight(productWeightList);
		return product;
	}
	
	public Product updateProductByProductForm(ProductForm productForm) {
		
		Product product = getProductById(Long.parseLong(productForm.getId()));
		
		product.setName(productForm.getName());
		product.setDimension(getDimensionById(Long.parseLong(productForm
				.getDimension())));
//		ArrayList<AgeCategory> ageCategoryList = getAllAgeCategory();
		
		for (int i=0; i<product.getProductWeight().size(); i++) {
//			for (int j = 0; j < ageCategoryList.size(); j++) {
			product.getProductWeight().iterator().next().setStandartProductQuantity(productForm.getWeightList()
					.get(i));
//			}
		}
		return product;
	}
}
