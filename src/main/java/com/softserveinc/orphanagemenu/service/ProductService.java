package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private ProductDao productDao;

	@Autowired
	@Qualifier("DimensionDao")
	private DimensionDao dimensionDAO;

	@Transactional
	public void saveProduct(Product p) {
		this.productDao.saveProduct(p);
	}

	@Transactional
	public void updateProduct(Product product) {
		this.productDao.updateProduct(product);
	}

	public void updateProductWeight(ProductWeight productWeight) {
		this.productDao.updateProductWeight(productWeight);
	}

	@Transactional
	public List<Product> getAllProductDtoSorted(String sort) {
		List<Product> products = productDao.getAllProduct(sort);
		List<Product> productsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (Product product : products) {
			productsDto.add(mapper.map(product, Product.class));
		}
		return productsDto;
	}

	@Transactional
	public ArrayList<ProductWeight> getAllProductWeight() {
		return this.productDao.getAllProductWeight();
	}

	@Transactional
	public ArrayList<Dimension> getAllDimension() {
		return this.productDao.getAllDimension();
	}

	@Transactional
	public Product getProductById(Long id) {
		return this.productDao.getProductById(id);
	}

	@Transactional
	public Dimension getDimensionById(Long id) {
		return this.productDao.getDimensionById(id);
	}

	public ArrayList<AgeCategory> getAllAgeCategory() {
		return this.productDao.getAllAgeCategory();
	}

	public Product getProduct(String name) {
		return this.productDao.getProduct(name);
	}

	public void saveProductWeight(ProductWeight productWeight) {
		this.productDao.saveproductWeight(productWeight);

	}

	public ProductForm getProductFormByProductId(Long id) {
		ProductForm productForm = new ProductForm();
		Product product = getProductById(id);
		productForm.setId(product.getId().toString());
		productForm.setName(product.getName());
		productForm.setDimension(product.getDimension().getName());
		Map<Long, String> weightList = new HashMap<>();
		for (ProductWeight productWeight : product.getProductWeight()) {
			weightList.put(productWeight.getAgeCategory().getId(), productWeight.getStandartProductQuantity().toString());
		}
		productForm.setWeightList(weightList);
		return productForm;
	}

	public Product getNewProductFromProductForm(ProductForm productForm) {
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDimension(getDimensionById(Long.parseLong(productForm
				.getDimension())));
		ArrayList<AgeCategory> ageCategoryList = getAllAgeCategory();
		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		int i=0;
		for (Map.Entry<Long, String> formWeight : productForm.getWeightList().entrySet()) {
			ProductWeight weight = new ProductWeight();
			weight.setStandartProductQuantity(Double.parseDouble(formWeight.getValue()));
			weight.setAgeCategory(ageCategoryList.get(i));
			weight.setProduct(product);
			productWeightList.add(weight);
			i++;
		}
		product.setProductWeight(productWeightList);
		return product;
	}

	public Product updateProductByProductForm(ProductForm productForm) {
		Product product = getProductById(Long.parseLong(productForm.getId()));
		product.setName(productForm.getName());
		product.setDimension(getDimensionById(Long.parseLong(productForm
				.getDimension())));
		for (Map.Entry<Long, String> formWeight : productForm.getWeightList().entrySet()) {
			for (ProductWeight productWeight : product.getProductWeight()) {
				if (formWeight.getKey().equals(
						productWeight.getAgeCategory().getId())) {
					productWeight.setStandartProductQuantity(Double.parseDouble(formWeight.getValue()));
				}
			}
		}
		return product;
	}
}
