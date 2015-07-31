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
		Map<String, String> productWeights = new TreeMap<>();
		Map<String, String> idWeights = new TreeMap<>();
		for (ProductWeight productWeight : product.getProductWeight()) {
			productWeights.put(productWeight.getAgeCategory().getId()
					.toString(), productWeight.getStandartProductQuantity()
					.toString());
		}
		for (ProductWeight productWeight : product.getProductWeight()) {
			idWeights.put(productWeight.getAgeCategory().getId().toString(),
					productWeight.getId().toString());
		}
		productForm.setIdWeight(idWeights);
		productForm.setWeight(productWeights);
		return productForm;
	}

	public Product getProductByProductForm(ProductForm productForm) {

		Product product = new Product();

		if (!("".equals(productForm.getId()))) {
			Long id = Long.parseLong(productForm.getId());
			product.setId(id);
		}
		product.setName(productForm.getName());
		Dimension dimension = getDimensionById(Long.parseLong(productForm
				.getDimension()));
		product.setDimension(dimension);

		ArrayList<AgeCategory> ageCategoryList = getAllAgeCategory();
		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		for (Map.Entry<String, String> entry : productForm.getWeight()
				.entrySet()) {
			ProductWeight weight = new ProductWeight();

			weight.setStandartProductQuantity(Double.parseDouble(entry
					.getValue()));

			for (AgeCategory ageCategory : ageCategoryList) {
				if (ageCategory.getId().toString().equals(entry.getKey())) {
					weight.setAgeCategory(ageCategory);
					weight.setProduct(product);
					productWeightList.add(weight);
				}
			}
		}
		product.setProductWeight(productWeightList);
		return product;
	}

}
