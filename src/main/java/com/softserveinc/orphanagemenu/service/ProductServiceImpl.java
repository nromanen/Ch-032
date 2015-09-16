package com.softserveinc.orphanagemenu.service;

/**
 * 
 * @author Sviatoslav Fedechko
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.DimensionDao;
import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.AgeCategoryDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private DimensionDao dimensionDao;

	@Autowired
	private AgeCategoryDao ageCategoryDao;
	@Autowired
	private WarehouseItemDao warehouseItemDao;

	@Transactional
	public void saveProduct(Product p) {
		this.productDao.saveProduct(p);
	}

	@Transactional
	public void updateProduct(Product product) {

		this.productDao.updateProduct(product);
		product = productDao.getProduct(product.getName());
		WarehouseItem warehouseitem = warehouseItemDao
				.getItemByProduct(product);
		if (warehouseitem == null) {
			warehouseitem = new WarehouseItem();
			warehouseitem.setProduct(product);
			warehouseitem.setQuantity(0D);
			warehouseItemDao.saveItem(warehouseitem);
		}

	}

	/**
	 * This method return list of Product sorted asc or desc
	 * 
	 * @param sort
	 *            the sort order - asc or desc
	 * @return the list of sorted Product objects
	 */
	@Transactional
	public List<Product> getAllProductDtoSorted(String... sortOrder) {
		List<Product> products = productDao.getAllProduct();
		List<Product> productsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (Product product : products) {
			productsDto.add(mapper.map(product, Product.class));
		}
		return productsDto;
	}

	@Transactional
	public Product getProductById(Long id) {
		return this.productDao.getProductById(id);
	}

	@Transactional
	public Product getProductByName(String name) {
		return this.productDao.getProduct(name);
	}

	@Transactional
	public ProductForm getProductFormByProductId(Long id) {
		ProductForm productForm = new ProductForm();
		Product product = getProductById(id);
		productForm.setId(product.getId().toString());
		productForm.setName(product.getName());
		productForm.setDimension(product.getDimension().getName());
		Map<Long, String> weightList = new HashMap<>();
		for (ProductWeight productWeight : product.getProductWeight()) {
			weightList.put(productWeight.getAgeCategory().getId(),
					productWeight.getStandartProductQuantity().toString()
							.replace(".", ","));
		}
		productForm.setWeightList(weightList);
		return productForm;
	}

	@Transactional
	public Product getNewProductFromProductForm(ProductForm productForm) {
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDimension(dimensionDao.getDimension(productForm
				.getDimension()));
		List<AgeCategory> ageCategoryList = ageCategoryDao.getAllAgeCategory();
		Set<ProductWeight> productWeightList = new HashSet<ProductWeight>();
		int i = 0;
		for (Map.Entry<Long, String> formWeight : productForm.getWeightList()
				.entrySet()) {
			ProductWeight weight = new ProductWeight();
			weight.setStandartProductQuantity(Double.parseDouble(formWeight
					.getValue()));
			weight.setAgeCategory(ageCategoryList.get(i));
			weight.setProduct(product);
			productWeightList.add(weight);
			i++;
		}
		product.setProductWeight(productWeightList);
		return product;
	}

	@Transactional
	public Product updateProductByProductForm(ProductForm productForm) {
		Product product = getProductById(Long.parseLong(productForm.getId()));
		product.setName(productForm.getName());
		product.setDimension(dimensionDao.getDimension(productForm
				.getDimension()));
		for (Map.Entry<Long, String> formWeight : productForm.getWeightList()
				.entrySet()) {
			updateProductWeightToProduct(product, formWeight);
		}
		return product;
	}

	private void updateProductWeightToProduct(Product product,
			Entry<Long, String> formWeight) {
		for (ProductWeight productWeight : product.getProductWeight()) {
			if (formWeight.getKey().equals(
					productWeight.getAgeCategory().getId())) {
				productWeight.setStandartProductQuantity(Double
						.parseDouble(formWeight.getValue()));
			}
		}
	}

	@Transactional
	public Long getCount() {
		return productDao.getCount();
	}

	@Transactional
	public List<Product> getPage(Integer offset, Integer pagecount) {
		List<Product> products = productDao.getPage(offset, pagecount);
		List<Product> productsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (Product product : products) {
			productsDto.add(mapper.map(product, Product.class));
		}
		return productsDto;
	}

	@Override
	public List<Product> getPage(String keyWord, Integer offset,
			Integer pagecount) {
		List<Product> products = productDao.getPage(keyWord, offset, pagecount);
		List<Product> productsDto = new ArrayList<>();
		Mapper mapper = new DozerBeanMapper();
		for (Product product : products) {
			productsDto.add(mapper.map(product, Product.class));
		}
		return productsDto;
	}

	@Override
	public Long getCount(String keyWord) {
		return productDao.getCount(keyWord);
	}
}
